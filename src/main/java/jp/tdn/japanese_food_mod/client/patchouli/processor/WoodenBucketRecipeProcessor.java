package jp.tdn.japanese_food_mod.client.patchouli.processor;

/*
public class WoodenBucketRecipeProcessor implements IComponentProcessor {
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
            return IVariable.wrap(I18n.get("jpfood.page.ferment.title", recipe.getResultItem()));
        }else if(key.equals("output")) {
            return IVariable.from(recipe.getResultItem());
        }else if(key.startsWith("input")){
            int requestedIndex = Integer.parseInt(key.substring(5)) - 1;
            int indexOffset = (6 - recipe.getIngredients().size()) / 2;
            int index = requestedIndex - indexOffset;

            if (index < recipe.getIngredients().size() && index >= 0) {
                return IVariable.wrapList(Arrays.stream(recipe.getIngredients().get(index).getItems()).map(IVariable::from).collect(Collectors.toList()));
            } else {
                return null;
            }
        }else if(key.equals("is_offset")){
            return IVariable.wrap(recipe.getIngredients().size() % 2 == 0);
        }
        return null;
    }
}*/
