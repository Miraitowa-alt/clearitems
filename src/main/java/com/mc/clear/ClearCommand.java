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
                .requires(source -> true) // 所有人可用，防止 275+ 模组环境闪退

                // 1. 开启自动清理
                .then(CommandManager.literal("start").executes(context -> {
                    ConfigManager.config.enabled = true;
                    ConfigManager.saveConfig();
                    ClearMod.resetTimer();
                    context.getSource().sendFeedback(() -> Text.literal("§a[ClearItems] 自动清理已启动"), true);
                    return 1;
                }))

                // 2. 关闭自动清理
                .then(CommandManager.literal("stop").executes(context -> {
                    ConfigManager.config.enabled = false;
                    ConfigManager.saveConfig();
                    context.getSource().sendFeedback(() -> Text.literal("§c[ClearItems] 自动清理已关闭"), true);
                    return 1;
                }))

                // 3. 设置清理时间
                .then(CommandManager.literal("set")
                        .then(CommandManager.argument("seconds", IntegerArgumentType.integer(10, 3600))
                                .executes(context -> {
                                    int seconds = IntegerArgumentType.getInteger(context, "seconds");
                                    ConfigManager.config.interval = seconds;
                                    ConfigManager.saveConfig();
                                    ClearMod.resetTimer();
                                    context.getSource().sendFeedback(() -> Text.literal("§e[ClearItems] 间隔已设为 " + seconds + "s"), true);
                                    return 1;
                                })))

                // 4. 添加白名单 (直接输入 ID 版本)
                .then(CommandManager.literal("add")
                        .then(CommandManager.argument("item", ItemStackArgumentType.itemStack(registryAccess))
                                .executes(context -> {
                                    // 从参数中直接获取物品 ID
                                    var item = ItemStackArgumentType.getItemStackArgument(context, "item").getItem();
                                    String itemId = Registries.ITEM.getId(item).toString();

                                    if (!ConfigManager.config.whiteList.contains(itemId)) {
                                        ConfigManager.config.whiteList.add(itemId);
                                        ConfigManager.saveConfig();
                                        context.getSource().sendFeedback(() -> Text.literal("§a[ClearItems] 已添加保护: " + itemId), true);
                                    } else {
                                        context.getSource().sendFeedback(() -> Text.literal("§e[ClearItems] " + itemId + " 已在白名单中"), true);
                                    }
                                    return 1;
                                })))

                // 5. 删除白名单 (直接输入 ID 版本)
                .then(CommandManager.literal("remove")
                        .then(CommandManager.argument("item", ItemStackArgumentType.itemStack(registryAccess))
                                .executes(context -> {
                                    var item = ItemStackArgumentType.getItemStackArgument(context, "item").getItem();
                                    String itemId = Registries.ITEM.getId(item).toString();

                                    if (ConfigManager.config.whiteList.contains(itemId)) {
                                        ConfigManager.config.whiteList.remove(itemId);
                                        ConfigManager.saveConfig();
                                        context.getSource().sendFeedback(() -> Text.literal("§c[ClearItems] 已移除保护: " + itemId), true);
                                    } else {
                                        context.getSource().sendFeedback(() -> Text.literal("§e[ClearItems] 白名单中未找到 " + itemId), true);
                                    }
                                    return 1;
                                })))

                // 6. 列出白名单
                .then(CommandManager.literal("list").executes(context -> {
                    if (ConfigManager.config.whiteList.isEmpty()) {
                        context.getSource().sendFeedback(() -> Text.literal("§f[ClearItems] 当前白名单没有任何物品"), true);
                    } else {
                        context.getSource().sendFeedback(() -> Text.literal("§6[ClearItems] 当前白名单列表:"), true);
                        for (String id : ConfigManager.config.whiteList) {
                            context.getSource().sendFeedback(() -> Text.literal("§7 - " + id), true);
                        }
                    }
                    return 1;
                }))

                // 7. 立即清理
                .then(CommandManager.literal("clear").executes(context -> {
                    ClearMod.executeClear(context.getSource().getServer());
                    return 1;
                }))
        );
    }
}