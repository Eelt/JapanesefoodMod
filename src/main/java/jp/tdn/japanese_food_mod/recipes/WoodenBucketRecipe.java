package jp.tdn.japanese_food_mod.recipes;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import jp.tdn.japanese_food_mod.JapaneseFoodMod;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.util.RecipeMatcher;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class WoodenBucketRecipe implements IRecipe<IInventory> {
    public static final Serializer SERIALIZER = new Serializer();
    public static final IRecipeType<WoodenBucketRecipe> RECIPE_TYPE = new IRecipeType<WoodenBucketRecipe>() {
    };
    protected final ResourceLocation id;
    protected NonNullList<Ingredient> ingredients;
    protected ItemStack result;
    protected int cookTime;

    public WoodenBucketRecipe(ResourceLocation idIn){
        this.id = idIn;
    }

    public boolean matches(@Nonnull IInventory inventory, @Nonnull World worldIn){
        boolean check;
        List<ItemStack> inputs = Lists.newArrayList();
        for(int i = 0; i < inventory.getContainerSize(); ++i){
            ItemStack stack = inventory.getItem(i);
            if(!stack.isEmpty()){
                inputs.add(stack);
            }
        }

        check = RecipeMatcher.findMatches(inputs, this.ingredients) != null;

        return check;
    }

    @Nonnull
    public ItemStack assemble(@Nonnull IInventory inventory) {
        return this.result.copy();
    }

    public boolean canCraftInDimensions(int width, int height){
        return true;
    }

    @Nonnull
    public NonNullList<Ingredient> getIngredients(){
        return this.ingredients;
    }

    @Nonnull
    public ItemStack getResultItem(){
        return this.result;
    }

    @Nonnull
    public ResourceLocation getId(){
        return id;
    }

    public int getCookTime(){
        return cookTime;
    }

    @Override
    @Nonnull
    public IRecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Nonnull
    public IRecipeType<?> getType(){
        return RECIPE_TYPE;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<WoodenBucketRecipe>{
        Serializer(){
            this.setRegistryName(new ResourceLocation(JapaneseFoodMod.MOD_ID, "fermentation"));
        }

        private static NonNullList<Ingredient> readIngredients(JsonArray ingredients){
            NonNullList<Ingredient> rec = NonNullList.create();

            for(final JsonElement element: ingredients) {
                rec.add(CraftingHelper.getIngredient(element));
            }

            if(rec.isEmpty())
                throw new JsonParseException("No ingredients for shapeless recipe");
            return rec;
        }

        @Override
        @Nonnull
        public WoodenBucketRecipe fromJson(@Nonnull ResourceLocation recipeId, @Nonnull JsonObject json) {
            WoodenBucketRecipe recipe = new WoodenBucketRecipe(recipeId);

            recipe.result = CraftingHelper.getItemStack(JSONUtils.getAsJsonObject(json, "result"), true);
            recipe.cookTime = JSONUtils.getAsInt(json, "process_time");
            recipe.ingredients = readIngredients(JSONUtils.getAsJsonArray(json, "ingredients"));

            return recipe;
        }

        @Nullable
        @Override
        public WoodenBucketRecipe fromNetwork(@Nonnull ResourceLocation recipeId, PacketBuffer buffer) {
            WoodenBucketRecipe recipe = new WoodenBucketRecipe(recipeId);
            final int index = buffer.readVarInt();
            NonNullList<Ingredient> nonnulllist = NonNullList.withSize(index, Ingredient.EMPTY);
            for(int j = 0; j < nonnulllist.size(); ++j) {
                nonnulllist.set(j, Ingredient.fromNetwork(buffer));
            }
            recipe.ingredients = nonnulllist;
            recipe.result = buffer.readItem();
            recipe.cookTime = buffer.readVarInt();
            return recipe;
        }

        @Override
        public void toNetwork(@Nonnull PacketBuffer buffer, WoodenBucketRecipe recipe) {
            buffer.writeVarInt(recipe.ingredients.size());
            for (Ingredient ingredient : recipe.ingredients) {
                ingredient.toNetwork(buffer);
            }
            buffer.writeItem(recipe.result);
            buffer.writeVarInt(recipe.cookTime);
        }
    }
}
