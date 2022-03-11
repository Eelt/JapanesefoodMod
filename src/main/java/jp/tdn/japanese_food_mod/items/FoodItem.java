package jp.tdn.japanese_food_mod.items;

import jp.tdn.japanese_food_mod.init.JPItems;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class FoodItem extends Item {
    public FoodItem(int hunger, float saturation){
        super(new Properties().tab(JPItems.ItemGroup_Japanese).food(new Food.Builder().nutrition(hunger).saturationMod(saturation).build()));
    }

    public FoodItem(Item.Properties properties){
        super(properties);
    }
}
