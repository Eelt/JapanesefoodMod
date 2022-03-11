package jp.tdn.japanese_food_mod.items;

import jp.tdn.japanese_food_mod.init.JPItems;
import net.minecraft.item.Item;

public class SimpleItem extends Item {
    public SimpleItem(){
        super(new Item.Properties().tab(JPItems.ItemGroup_Japanese));
    }
}
