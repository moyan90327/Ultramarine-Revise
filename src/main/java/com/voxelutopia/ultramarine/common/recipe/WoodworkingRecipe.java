package com.voxelutopia.ultramarine.common.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.voxelutopia.ultramarine.init.registry.ModRecipeSerializers;
import com.voxelutopia.ultramarine.init.registry.ModRecipeTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.RecipeBookCategories;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

/** 1.21.11 equivalent of the 26.1 woodworking recipe. */
public class WoodworkingRecipe extends SingleItemRecipe {
    private final String group;
    private final Ingredient ingredient;
    private final ItemStack result;

    public WoodworkingRecipe(String group, Ingredient ingredient, ItemStack result) {
        super(group, ingredient, result);
        this.group = group;
        this.ingredient = ingredient;
        this.result = result;
    }

    @Override
    public boolean matches(SingleRecipeInput input, @NotNull Level level) {
        return ingredient.test(input.getItem(0));
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull SingleRecipeInput input, HolderLookup.@NotNull Provider registries) {
        return result.copy();
    }

    @Override public boolean isSpecial() { return true; }
    @Override public String group() { return group; }
    @Override public @NotNull RecipeBookCategory recipeBookCategory() { return RecipeBookCategories.STONECUTTER; }
    @Override public @NotNull RecipeType<WoodworkingRecipe> getType() { return ModRecipeTypes.WOODWORKING; }
    @Override public @NotNull RecipeSerializer<WoodworkingRecipe> getSerializer() { return ModRecipeSerializers.WOODWORKING_SERIALIZER; }
    @Override public PlacementInfo placementInfo() { return PlacementInfo.create(ingredient); }
    public Ingredient getIngredient() { return ingredient; }
    public ItemStack getResult() { return result; }
    public ItemStack getResultItem(HolderLookup.Provider registries) { return result.copy(); }
    public ItemStack getResultItem() { return result.copy(); }

    public enum Serializer implements RecipeSerializer<WoodworkingRecipe> {
        INSTANCE;
        public static final MapCodec<WoodworkingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Codec.STRING.optionalFieldOf("group", "").forGetter(WoodworkingRecipe::group),
                Ingredient.CODEC.fieldOf("ingredient").forGetter(WoodworkingRecipe::getIngredient),
                ItemStack.CODEC.fieldOf("result").forGetter(WoodworkingRecipe::getResult)
        ).apply(instance, WoodworkingRecipe::new));
        public static final StreamCodec<RegistryFriendlyByteBuf, WoodworkingRecipe> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.STRING_UTF8, WoodworkingRecipe::group,
                Ingredient.CONTENTS_STREAM_CODEC, WoodworkingRecipe::getIngredient,
                ItemStack.STREAM_CODEC, WoodworkingRecipe::getResult,
                WoodworkingRecipe::new
        );
        @Override public @NotNull MapCodec<WoodworkingRecipe> codec() { return CODEC; }
        @Override public @NotNull StreamCodec<RegistryFriendlyByteBuf, WoodworkingRecipe> streamCodec() { return STREAM_CODEC; }
    }

    public static final RecipeSerializer<WoodworkingRecipe> SERIALIZER = Serializer.INSTANCE;
}
