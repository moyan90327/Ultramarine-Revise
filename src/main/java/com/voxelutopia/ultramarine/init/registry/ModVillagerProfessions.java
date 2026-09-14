package com.voxelutopia.ultramarine.init.registry;

import com.google.common.collect.ImmutableSet;
import com.voxelutopia.ultramarine.Ultramarine;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.villager.VillagerProfession;

/**
 * 1.21.11 has no TRADE_SET registry. Trade levels are registered through
 * Fabric's villager trade events while preserving the 26.1 offers.
 */
public final class ModVillagerProfessions {
    private ModVillagerProfessions() {
    }

    public static final ResourceKey<VillagerProfession> COOK_KEY = ResourceKey.create(
            BuiltInRegistries.VILLAGER_PROFESSION.key(),
            Identifier.fromNamespaceAndPath(Ultramarine.MOD_ID, "cook")
    );
    public static VillagerProfession COOK;

    public static void registerModVillagerProfession() {
        Holder<PoiType> cookingPoi = BuiltInRegistries.POINT_OF_INTEREST_TYPE.wrapAsHolder(ModPoiTypes.COOKING_POI);
        COOK = Registry.register(BuiltInRegistries.VILLAGER_PROFESSION, COOK_KEY,
                new VillagerProfession(
                        Component.translatable("entity." + Ultramarine.MOD_ID + ".cook"),
                        holder -> holder.value() == cookingPoi.value(),
                        holder -> holder.value() == cookingPoi.value(),
                        ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_BUTCHER
                ));
    }
}
