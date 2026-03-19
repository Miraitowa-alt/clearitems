package com.mc.clear;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.registry.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

public class ClearMod implements ModInitializer {
    private static int ticksLeft = -1;
    private static final ServerBossBar bossBar = new ServerBossBar(
            Text.literal("§6[ClearItems] 距离下一次清理"),
            BossBar.Color.BLUE,
            BossBar.Style.PROGRESS
    );

    @Override
    public void onInitialize() {
        ConfigManager.loadConfig();

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            ClearCommand.register(dispatcher, registryAccess);
        });

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            if (ticksLeft > 0) {
                ticksLeft--;
                updateBossBar(server);
            } else if (ticksLeft == 0) {
                executeClear(server);
                resetTimer();
            }
        });

        resetTimer();
    }

    private static void updateBossBar(MinecraftServer server) {
        float maxTicks = ConfigManager.config.interval * 20f;
        bossBar.setPercent(Math.max(0, Math.min(1, ticksLeft / maxTicks)));
        int seconds = ticksLeft / 20;
        bossBar.setName(Text.literal("§6[ClearItems] 清理倒计时: §e" + seconds + "秒"));
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            bossBar.addPlayer(player);
        }
    }

    public static void resetTimer() {
        ticksLeft = ConfigManager.config.interval * 20;
        bossBar.setVisible(true);
    }

    public static void stopAutoClear() {
        ticksLeft = -1;
        bossBar.setVisible(false);
    }


    public static int executeClear(MinecraftServer server) {
        int totalDroppedItems = 0;
        for (ServerWorld world : server.getWorlds()) {
            for (Entity entity : world.iterateEntities()) {
                if (entity instanceof ItemEntity item) {
                    String itemId = Registries.ITEM.getId(item.getStack().getItem()).toString();
                    if (!ConfigManager.config.whiteList.contains(itemId)) {

                        totalDroppedItems += item.getStack().getCount();
                        item.discard();
                    }
                }
            }
        }

        final int finalCount = totalDroppedItems;

        server.getPlayerManager().broadcast(Text.literal("§b[ClearItems] 清理完成！共清除了 " + finalCount + " 个垃圾！"), false);
        return finalCount;
    }
}