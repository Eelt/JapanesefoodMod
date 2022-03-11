package jp.tdn.japanese_food_mod.items;

import jp.tdn.japanese_food_mod.init.JPItems;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

import java.util.function.Supplier;

public class SakeItem extends DrinkItem {
    public SakeItem(int hunger, float saturation){
        super(new Item.Properties().tab(JPItems.ItemGroup_Japanese).food(new Food.Builder().nutrition(hunger).saturationMod(saturation)
                .effect(new EffectInstance(Effects.CONFUSION, 600), 0.5f).build()
        ));
    }
}
