package net.id.aether.commands;


import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.id.aether.component.AetherComponents;
import net.id.aether.component.MoaGenes;
import net.id.aether.entities.passive.moa.MoaAttributes;
import net.id.aether.entities.passive.moa.MoaEntity;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class MoaStatCommand {

    public static final AttributeSuggester ATTRIBUTE_SUGGESTER = new AttributeSuggester();

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                literal("moastat")
                        .requires((source) -> source.hasPermissionLevel(2))
                        .then(argument("target", EntityArgumentType.entities())
                                .then(literal("query")
                                        .executes(context -> printStat(context.getSource(), EntityArgumentType.getEntities(context, "target"), null))
                                        .then(argument("attribute", StringArgumentType.word()).suggests(ATTRIBUTE_SUGGESTER)
                                                .executes((context -> printStat(context.getSource(), EntityArgumentType.getEntities(context, "target"), StringArgumentType.getString(context, "attribute"))))))
                                .then(literal("assign").then(argument("attribute", StringArgumentType.word())
                                        .then(argument("value", FloatArgumentType.floatArg())
                                                .executes(context -> setStat(context.getSource(), EntityArgumentType.getEntity(context, "target"), StringArgumentType.getString(context, "attribute"), FloatArgumentType.getFloat(context, "value")))))))
        );
    }

    private static int printStat(ServerCommandSource source, Collection<? extends Entity> entities, String rawAttribute) {
        String attributeId = rawAttribute == null ? "ALL" : rawAttribute;
        entities.forEach(entity -> {
            if (entity instanceof MoaEntity moa) {
                MoaGenes genes = moa.getGenes();
                source.sendFeedback(new TranslatableText("commands.the_aether.moastat.name", moa.getDisplayName()).formatted(Formatting.LIGHT_PURPLE), false);
                source.sendFeedback(new TranslatableText("commands.the_aether.moastat.race", new TranslatableText(genes.getRace().getTranslationKey())).formatted(Formatting.LIGHT_PURPLE), false);
                if (attributeId.equals("HUNGER")) {
                    source.sendFeedback(new TranslatableText("commands.the_aether.moastat.print", new TranslatableText("commands.the_aether.moastat.hunger"), String.format("%.2f", genes.getHunger())).formatted(Formatting.GOLD, Formatting.ITALIC), false);
                } else if (attributeId.equals("ALL")) {
                    for (MoaAttributes attribute : MoaAttributes.values()) {
                        source.sendFeedback(new TranslatableText("commands.the_aether.moastat.print", new TranslatableText(attribute.getTranslationKey()), genes.getAttribute(attribute)).formatted(Formatting.GOLD, Formatting.ITALIC), false);
                    }
                } else {
                    try {
                        MoaAttributes attribute = MoaAttributes.valueOf(attributeId);
                        source.sendFeedback(new TranslatableText("commands.the_aether.moastat.print", new TranslatableText(attribute.getTranslationKey()), genes.getAttribute(attribute)).formatted(Formatting.GOLD, Formatting.ITALIC), false);
                    } catch (IllegalArgumentException e) {
                        source.sendError(new TranslatableText("commands.the_aether.moastat.failure.attribute"));
                    }
                }
            } else {
                source.sendError(new TranslatableText("commands.the_aether.moastat.failure.entity", entity.getType().getName()));
            }
        });
        return 1;
    }

    private static int setStat(ServerCommandSource source, Entity entity, String attributeId, float value) {
        if (entity instanceof MoaEntity moa) {
            MoaGenes genes = moa.getGenes();
            source.sendFeedback(new TranslatableText("commands.the_aether.moastat.name", moa.getDisplayName()).formatted(Formatting.LIGHT_PURPLE), false);
            source.sendFeedback(new TranslatableText("commands.the_aether.moastat.race", new TranslatableText(genes.getRace().getTranslationKey())).formatted(Formatting.LIGHT_PURPLE), false);
            if (attributeId.equals("HUNGER")) {
                genes.setHunger(Math.min(Math.max(value, 100), 0));
                source.sendFeedback(new TranslatableText("commands.the_aether.moastat.set", new TranslatableText("commands.the_aether.moastat.hunger"), String.format("%.2f", genes.getHunger())).formatted(Formatting.AQUA, Formatting.ITALIC), false);
            } else if (attributeId.equals("ALL")) {
                for (MoaAttributes attribute : MoaAttributes.values()) {
                    genes.setAttribute(attribute, value);
                    source.sendFeedback(new TranslatableText("commands.the_aether.moastat.set", new TranslatableText(attribute.getTranslationKey()), genes.getAttribute(attribute)).formatted(Formatting.AQUA, Formatting.ITALIC), false);
                }
            } else {
                try {
                    MoaAttributes attribute = MoaAttributes.valueOf(attributeId);
                    genes.setAttribute(attribute, value);
                    source.sendFeedback(new TranslatableText("commands.the_aether.moastat.set", new TranslatableText(attribute.getTranslationKey()), genes.getAttribute(attribute)).formatted(Formatting.AQUA, Formatting.ITALIC), false);
                } catch (IllegalArgumentException e) {
                    source.sendError(new TranslatableText("commands.the_aether.moastat.failure.attribute"));
                }
            }
            AetherComponents.MOA_GENETICS_KEY.sync(moa);
        } else {
            source.sendError(new TranslatableText("commands.the_aether.moastat.failure.entity", entity.getType().getName()));
        }
        return 1;
    }

    public static class AttributeSuggester implements SuggestionProvider<ServerCommandSource> {
        @Override
        public CompletableFuture<Suggestions> getSuggestions(CommandContext<ServerCommandSource> context, SuggestionsBuilder builder) throws CommandSyntaxException {
            Arrays.stream(MoaAttributes.values()).forEach(attribute -> builder.suggest(attribute.name()));
            builder.suggest("HUNGER");
            builder.suggest("ALL");
            return builder.buildFuture();
        }
    }
}
