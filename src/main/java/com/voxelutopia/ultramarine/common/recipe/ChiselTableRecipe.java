package com.voxelutopia.ultramarine.common.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.voxelutopia.ultramarine.common.menu.ChiselTableMenu;
import com.voxelutopia.ultramarine.init.registry.ModRecipeSerializers;
import com.voxelutopia.ultramarine.init.registry.ModRecipeTypes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record ChiselTableRecipe(String group, Ingredient material, Ingredient template, List<Ingredient> colors,
                                ItemStack result) implements Recipe<ChiselTableRecipe.ChiselTableRecipeInput> {

    @Override
    public boolean matches(ChiselTableRecipeInput pContainer, @NotNull Level pLevel) {
        ItemStack usedMaterial = pContainer.getItem(ChiselTableMenu.SLOT_MATERIAL);
        ItemStack usedTemplate = pContainer.getItem(ChiselTableMenu.SLOT_TEMPLATE);
        List<ItemStack> usedColors = Arrays.asList(ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY);
        for (int i = 0, j = 2; j < pContainer.size(); i++, j++) {
            usedColors.set(i, pContainer.getItem(j));
        }
        usedColors = usedColors.stream().filter(item -> !item.isEmpty()).collect(Collectors.toList());
        return material.test(usedMaterial) && template.test(usedTemplate) && compareColors(this.colors, usedColors);
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull ChiselTableRecipe.ChiselTableRecipeInput pContainer, @NotNull net.minecraft.core.HolderLookup.Provider registries) {
        return this.result.copy();
    }

    public @NotNull ItemStack getResultItem() {
        return result.copy();
    }

    @Override
    public boolean showNotification() {
        return true;
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.STONECUTTER;
    }

    @Override
    public @NotNull RecipeSerializer<ChiselTableRecipe> getSerializer() {
        return ModRecipeSerializers.CHISEL_TABLE_SERIALIZER;
    }

    @Override
    public @NotNull RecipeType<ChiselTableRecipe> getType() {
        return ModRecipeTypes.CHISEL_TABLE;
    }

    private static boolean compareColors(List<Ingredient> recipeColors, List<ItemStack> usedColors) {
        if (recipeColors.size() != usedColors.size()) return false;
        List<ItemStack> usedColorsReverse = new ArrayList<>(usedColors);
        Collections.reverse(usedColorsReverse);
        boolean fwd = true, rvs = true;
        for (int i = 0; i < recipeColors.size(); i++) {
            fwd = recipeColors.get(i).test(usedColors.get(i)) && fwd;
            rvs = recipeColors.get(i).test(usedColorsReverse.get(i)) && rvs;
        }
        return fwd || rvs;
    }

    public static final MapCodec<ChiselTableRecipe> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
            Codec.STRING.optionalFieldOf("group", "").forGetter(ChiselTableRecipe::group),
            Ingredient.CODEC.fieldOf("material").forGetter(ChiselTableRecipe::material),
            Ingredient.CODEC.fieldOf("template").forGetter(ChiselTableRecipe::template),
            Codec.list(Ingredient.CODEC).fieldOf("colors").forGetter(ChiselTableRecipe::colors),
            ItemStack.CODEC.fieldOf("result").forGetter(ChiselTableRecipe::result)
    ).apply(i, ChiselTableRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, ChiselTableRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.STRING_UTF8, ChiselTableRecipe::group,
                    Ingredient.CONTENTS_STREAM_CODEC, ChiselTableRecipe::material,
                    Ingredient.CONTENTS_STREAM_CODEC, ChiselTableRecipe::template,
                    ByteBufCodecs.<RegistryFriendlyByteBuf, Ingredient>list().apply(Ingredient.CONTENTS_STREAM_CODEC), ChiselTableRecipe::colors,
                    ItemStack.STREAM_CODEC, ChiselTableRecipe::result,
                    ChiselTableRecipe::new
            );

    public static final RecipeSerializer<ChiselTableRecipe> SERIALIZER = new RecipeSerializer<>() {
        @Override
        public @NotNull MapCodec<ChiselTableRecipe> codec() {
            return CODEC;
        }

        @Override
        public @NotNull StreamCodec<RegistryFriendlyByteBuf, ChiselTableRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    };

    public record ChiselTableRecipeInput(Container container) implements RecipeInput {
        @Override
        public ItemStack getItem(int slot) {
            return container.getItem(slot);
        }

        @Override
        public int size() {
            return container.getContainerSize();
        }
    }
}
