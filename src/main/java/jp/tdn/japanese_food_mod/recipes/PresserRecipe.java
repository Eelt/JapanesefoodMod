package jp.tdn.japanese_food_mod.recipes;

import com.google.gson.JsonObject;
import jp.tdn.japanese_food_mod.JapaneseFoodMod;
import jp.tdn.japanese_food_mod.init.JPItems;
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
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PresserRecipe implements IRecipe<IInventory> {
    public static final Serializer SERIALIZER = new Serializer();
    public static final IRecipeType<PresserRecipe> RECIPE_TYPE = new IRecipeType<PresserRecipe>() {
    };
    protected final ResourceLocation id;
    protected Ingredient ingredient;
    protected int result;
    protected int cookTime;

    public PresserRecipe(ResourceLocation idIn){
        this.id = idIn;
    }

    public boolean matches(IInventory inventory, @Nonnull World worldIn){
        return this.ingredient.test(inventory.getItem(0));
    }

    @Override
    @Nonnull
    public ItemStack assemble(@Nonnull IInventory inventory) {
        return new ItemStack(JPItems.COOKING_OIL.get());
    }

    public int getResult(){
        return result;
    }

    public boolean canCraftInDimensions(int width, int height){
        return true;
    }

    @Nonnull
    public NonNullList<Ingredient> getIngredients(){
        NonNullList<Ingredient> nonNullList = NonNullList.create();
        nonNullList.add(this.ingredient);
        return nonNullList;
    }

    @Nonnull
    public ItemStack getResultItem(){
        return new ItemStack(JPItems.COOKING_OIL.get());
    }

    public int getCookTime(){
        return cookTime;
    }

    @Nonnull
    public ResourceLocation getId(){
        return id;
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

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<PresserRecipe>{
        Serializer(){
            this.setRegistryName(new ResourceLocation(JapaneseFoodMod.MOD_ID, "pressing"));
        }

        @Override
        @Nonnull
        public PresserRecipe fromJson(@Nonnull ResourceLocation recipeId, @Nonnull JsonObject json) {
            PresserRecipe recipe = new PresserRecipe(recipeId);
            recipe.result = JSONUtils.getAsInt(json, "result");
            recipe.cookTime = JSONUtils.getAsInt(json, "process_time", 50);
            recipe.ingredient = Ingredient.fromJson(JSONUtils.getAsJsonObject(json, "ingredient"));
            return recipe;
        }

        @Nullable
        @Override
        public PresserRecipe fromNetwork(@Nonnull ResourceLocation recipeId, PacketBuffer buffer) {
            PresserRecipe recipe = new PresserRecipe(recipeId);
            recipe.cookTime = buffer.readVarInt();
            recipe.result = buffer.readVarInt();
            recipe.ingredient = Ingredient.fromNetwork(buffer);
            return recipe;
        }

        @Override
        public void toNetwork(PacketBuffer buffer, PresserRecipe recipe) {
            buffer.writeVarInt(recipe.cookTime);
            buffer.writeVarInt(recipe.result);
            recipe.ingredient.toNetwork(buffer);
        }
    }
}
