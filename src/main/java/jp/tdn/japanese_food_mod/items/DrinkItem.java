package jp.tdn.japanese_food_mod.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;

import javax.annotation.Nonnull;

public class DrinkItem extends FoodItem {
    public DrinkItem(int hunger, float saturation){
        super(hunger, saturation);
    }

    public DrinkItem(Item.Properties properties){
        super(properties);
    }

    @Override
    @Nonnull
    public UseAction getUseAnimation(ItemStack itemStack) {
        return UseAction.DRINK;
    }
}
