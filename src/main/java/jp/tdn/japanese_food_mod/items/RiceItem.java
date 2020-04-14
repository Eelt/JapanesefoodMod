package jp.tdn.japanese_food_mod.items;

import jp.tdn.japanese_food_mod.JapaneseFoodMod;
import jp.tdn.japanese_food_mod.lists.ItemList;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class RiceItem extends Item {
    public RiceItem(){
        super(new Properties()
                .food(new Food.Builder().hunger(1).saturation(0.5f).build())
                .group(ItemList.ItemGroup_Japanese));

        this.setRegistryName(JapaneseFoodMod.MOD_ID, "rice");
    }
}
