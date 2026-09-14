package com.voxelutopia.ultramarine.init.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;

import java.util.concurrent.CompletableFuture;

/**
 * Minecraft 1.21.11 has no VILLAGER_TRADE or TRADE_SET registries. The same
 * offers are registered at runtime by ModVillagerTradings through Fabric's
 * trade events. This provider remains part of the data generation pipeline so
 * the 26.1 trade definition has a stable, explicit migration boundary.
 */
public final class ModVillagerTradeProvider implements DataProvider {
    public ModVillagerTradeProvider(FabricDataOutput output) {
    }

    @Override
    public CompletableFuture<?> run(CachedOutput output) {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public String getName() {
        return "Ultramarine Villager Trades (runtime-registered on 1.21.11)";
    }
}
