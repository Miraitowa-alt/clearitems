package com.mc.clear;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.ItemStackArgumentType;
import net.minecraft.registry.Registries;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class ClearCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess) {

        dispatcher.register(CommandManager.literal("clearitems")

                // 设定间隔 (所有人可用)
                .then(CommandManager.literal("set")
                        .then(CommandManager.argument("seconds", IntegerArgumentType.integer(10, 3600))
                                .executes(context -> {
                                    int seconds = IntegerArgumentType.getInteger(context, "seconds");
                                    ConfigManager.config.interval = seconds;
                                    ConfigManager.saveConfig();
                                    if (ConfigManager.config.enabled) ClearMod.resetTimer();
                                    context.getSource().sendFeedback(() -> Text.literal("§6[ClearItems] 间隔已由玩家设为 " + seconds + " 秒"), true);
                                    return 1;
                                })))

                // 添加白名单 (所有人可用)
                .then(CommandManager.literal("add")
                        .then(CommandManager.argument("item", ItemStackArgumentType.itemStack(registryAccess))
                                .executes(context -> {
                                    var item = ItemStackArgumentType.getItemStackArgument(context, "item").getItem();
                                    String itemId = Registries.ITEM.getId(item).toString();
                                    if (!ConfigManager.config.whiteList.contains(itemId)) {
                                        ConfigManager.config.whiteList.add(itemId);
                                        ConfigManager.saveConfig();
                                        context.getSource().sendFeedback(() -> Text.literal("§e[ClearItems] 玩家已添加保护: " + itemId), true);
                                    }
                                    return 1;
                                })))

                // 移除白名单 (所有人可用)
                .then(CommandManager.literal("remove")
                        .then(CommandManager.argument("item", ItemStackArgumentType.itemStack(registryAccess))
                                .executes(context -> {
                                    var item = ItemStackArgumentType.getItemStackArgument(context, "item").getItem();
                                    String itemId = Registries.ITEM.getId(item).toString();
                                    if (ConfigManager.config.whiteList.remove(itemId)) {
                                        ConfigManager.saveConfig();
                                        context.getSource().sendFeedback(() -> Text.literal("§c[ClearItems] 玩家已移除保护: " + itemId), true);
                                    }
                                    return 1;
                                })))

                // 开启自动清理 (所有人可用)
                .then(CommandManager.literal("start").executes(context -> {
                    ClearMod.resetTimer();
                    context.getSource().sendFeedback(() -> Text.literal("§a[ClearItems] 自动清理已激活"), true);
                    return 1;
                }))

                // 停止自动清理 (所有人可用)
                .then(CommandManager.literal("stop").executes(context -> {
                    ClearMod.stopAutoClear();
                    context.getSource().sendFeedback(() -> Text.literal("§c[ClearItems] 自动清理已被玩家禁用"), true);
                    return 1;
                }))

                // 立即清理 (所有人可用)
                .then(CommandManager.literal("clear").executes(context -> {
                    ClearMod.executeClear(context.getSource().getServer());
                    return 1;
                }))
        );
    }
}