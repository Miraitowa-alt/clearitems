package com.mc.clear;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.registry.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

public class ClearMod implements ModInitializer {
    private static int timeLeft = 300;
    private static int tickCounter = 0;

    @Override
    public void onInitialize() {
        ConfigManager.loadConfig();
        if (ConfigManager.config != null) {
            timeLeft = ConfigManager.config.interval;
        }

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            ClearCommand.register(dispatcher, registryAccess);
        });

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            if (ConfigManager.config == null || !ConfigManager.config.enabled) return;

            tickCounter++;
            if (tickCounter >= 20) {
                tickCounter = 0;
                timeLeft--;

                if (timeLeft <= 0) {
                    executeClear(server);
                    timeLeft = ConfigManager.config.interval;
                }

                if (ConfigManager.config.showActionBar) {
                    sendActionBar(server, timeLeft);
                }
            }
        });
    }

    public static void resetTimer() {
        if (ConfigManager.config != null) timeLeft = ConfigManager.config.interval;
        tickCounter = 0;
    }

    private void sendActionBar(MinecraftServer server, int time) {
        String color = (time <= 10) ? "§c§l" : (time <= 30 ? "§e" : "§a");
        Text text = Text.literal(color + "⌛ 清理倒计时: " + time + "s");
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            player.sendMessage(text, true);
        }
    }

    public static void executeClear(MinecraftServer server) {
        int count = 0;
        for (ServerWorld world : server.getWorlds()) {
            for (Entity entity : world.iterateEntities()) {
                if (entity instanceof ItemEntity item) {
                    String itemId = Registries.ITEM.getId(item.getStack().getItem()).toString();
                    if (ConfigManager.config != null && !ConfigManager.config.whiteList.contains(itemId)) {
                        entity.discard();
                        count++;
                    }
                }
            }
        }
        int finalCount = count;
        server.getPlayerManager().broadcast(Text.literal("§6[ClearItems] §f清理完成，共移除 §e" + finalCount + " §f个掉落物"), false);
    }
}