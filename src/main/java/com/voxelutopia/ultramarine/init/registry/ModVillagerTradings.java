package com.voxelutopia.ultramarine.init.registry;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.npc.villager.VillagerTrades;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;

import java.util.Optional;

/** Runtime equivalent of the 26.1 VILLAGER_TRADE / TRADE_SET data. */
public final class ModVillagerTradings {
    private static final float PRICE_MULTIPLIER = 0.05f;

    private ModVillagerTradings() {
    }

    public static void register() {
        villager(VillagerProfession.MASON, 4, offer(Items.EMERALD, 5, ModItems.RAW_HEMATITE, 2, 12, 10), offer(Items.EMERALD, 5, ModItems.MAGNESITE, 2, 12, 10));
        villager(VillagerProfession.MASON, 5, offer(Items.EMERALD, 10, ModItems.JADE, 1, 6, 20));
        villager(VillagerProfession.TOOLSMITH, 4, offer(Items.EMERALD, 6, ModItems.BRONZE_INGOT, 1, 12, 10));
        villager(VillagerProfession.TOOLSMITH, 5, offer(Items.EMERALD, 40, ModItems.CARRIAGE, 1, 1, 10));
        villager(VillagerProfession.FARMER, 3, offer(Items.EMERALD, 4, ModItems.POLISHED_ROSEWOOD_PLANK, 8, 12, 5), offer(Items.EMERALD, 4, ModItems.POLISHED_EBONY_PLANK, 8, 12, 5), offer(Items.EMERALD, 30, ModItems.SILK, 4, 4, 10));
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 4, offers -> offers.add((entity, random, access) -> new MerchantOffer(new ItemCost(Items.EMERALD, 20), Optional.of(new ItemCost(ModItems.EMPTY_BAMBOO_TEA_BASKET, 1)), new ItemStack(ModItems.BAMBOO_TEA_BASKET), 4, 10, PRICE_MULTIPLIER)));
        villager(VillagerProfession.LIBRARIAN, 4, offer(Items.EMERALD, 20, ModItems.XUAN_PAPER, 4, 10, 20));
        villager(VillagerProfession.LIBRARIAN, 5, offer(Items.EMERALD, 30, ModItems.PORCELAIN_TEAPOT, 1, 2, 20));
        villager(VillagerProfession.CLERIC, 5, offer(Items.EMERALD, 40, ModItems.SUNDIAL, 1, 1, 20));

        villager(ModVillagerProfessions.COOK_KEY, 1, offer(Items.PORKCHOP, 15, Items.EMERALD, 1, 12, 2), offer(Items.BEEF, 15, Items.EMERALD, 1, 12, 2), offer(Items.CHICKEN, 20, Items.EMERALD, 1, 12, 2), offer(Items.EMERALD, 8, ModItems.COOKED_MEAT, 4, 12, 2));
        villager(ModVillagerProfessions.COOK_KEY, 2, offer(Items.MUTTON, 15, Items.EMERALD, 1, 12, 4), offer(Items.RABBIT, 15, Items.EMERALD, 1, 12, 4), offer(Items.EMERALD, 6, ModItems.GREASE, 2, 8, 5), offer(Items.EMERALD, 6, ModItems.FUR, 2, 8, 5));
        villager(ModVillagerProfessions.COOK_KEY, 3, offer(Items.DRIED_KELP, 40, Items.EMERALD, 2, 15, 8), offer(Items.SUGAR, 40, Items.EMERALD, 1, 20, 8), offer(Items.EGG, 16, Items.EMERALD, 1, 20, 8), offer(Items.EMERALD, 12, ModItems.MUNG_BEAN_CAKE, 4, 12, 10), offer(Items.EMERALD, 12, ModItems.MOONCAKE, 4, 12, 10));
        villager(ModVillagerProfessions.COOK_KEY, 4, offer(Items.EMERALD, 12, ModItems.CABBAGE_BASKET, 1, 3, 20), offer(Items.EMERALD, 12, ModItems.CELERY_BASKET, 1, 3, 20), offer(Items.EMERALD, 12, ModItems.ORANGE_BASKET, 1, 3, 20), offer(Items.EMERALD, 12, ModItems.APPLE_BASKET, 1, 3, 20), offer(Items.EMERALD, 12, ModItems.EGGPLANT_BASKET, 1, 3, 20), offer(Items.EMERALD, 12, ModItems.PEAR_BASKET, 1, 3, 20), offer(Items.EMERALD, 5, ModItems.BAOZI, 2, 6, 20));
        villager(ModVillagerProfessions.COOK_KEY, 5, offer(Items.EMERALD, 25, ModItems.XIAOLONGBAO, 1, 2, 30), offer(Items.EMERALD, 30, ModItems.WINE_POT, 1, 2, 20), offer(Items.EMERALD, 40, ModItems.BOTTLE_GOURD, 1, 1, 30));

        TradeOfferHelper.registerWanderingTraderOffers(offers -> offers.addOffersToPool(TradeOfferHelper.WanderingTraderOffersBuilder.SELL_COMMON_ITEMS_POOL,
                offer(Items.EMERALD, 6, ModItems.INCENSE, 1, 6, 30), offer(Items.EMERALD, 6, ModItems.SILK, 1, 8, 30), offer(Items.EMERALD, 10, ModItems.JADE, 1, 2, 30), offer(Items.EMERALD, 12, ModItems.PAINTING_SCROLL, 1, 1, 30), offer(Items.EMERALD, 20, ModItems.BLUE_AND_WHITE_PORCELAIN_VASE, 1, 1, 30)));
    }

    private static void villager(net.minecraft.resources.ResourceKey<VillagerProfession> profession, int level, VillagerTrades.ItemListing... factories) {
        var key = profession;
        TradeOfferHelper.registerVillagerOffers(key, level, offers -> offers.addAll(java.util.List.of(factories)));
    }

    private static void villager(VillagerProfession profession, int level, VillagerTrades.ItemListing... factories) {
        var key = BuiltInRegistries.VILLAGER_PROFESSION.getResourceKey(profession).orElseThrow();
        TradeOfferHelper.registerVillagerOffers(key, level, offers -> offers.addAll(java.util.List.of(factories)));
    }

    private static VillagerTrades.ItemListing offer(Item costItem, int costCount, Item resultItem, int resultCount, int maxUses, int xp) {
        return (entity, random, access) -> new MerchantOffer(new ItemCost(costItem, costCount), new ItemStack(resultItem, resultCount), maxUses, xp, PRICE_MULTIPLIER);
    }
}
