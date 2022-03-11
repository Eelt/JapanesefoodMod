package jp.tdn.japanese_food_mod.client.patchouli.processor;

import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;

public class MicroScopeRecipeProcessor implements IComponentProcessor {
    private IRecipe<?> recipe;

    @Override
    public void setup(IVariableProvider variables) {
        String recipeId = variables.get("recipe").asString();
        RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();
        recipe = manager.byKey(new ResourceLocation(recipeId)).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public IVariable process(String key) {
        if(recipe == null){
            return null;
        }else if(key.equals("heading")){
            return IVariable.wrap(recipe.getResultItem().getDisplayName().getString());
        }else if(key.equals("output")) {
            return IVariable.from(recipe.getResultItem());
        }else if(key.startsWith("input")) {
            return IVariable.from(recipe.getIngredients().get(0));
        }
        return null;
    }
}
