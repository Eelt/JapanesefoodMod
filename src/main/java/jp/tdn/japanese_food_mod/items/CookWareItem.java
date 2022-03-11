package jp.tdn.japanese_food_mod.items;

import jp.tdn.japanese_food_mod.init.JPItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CookWareItem extends Item {
    public CookWareItem(){
        super(new Item.Properties().stacksTo(1).tab(JPItems.ItemGroup_Japanese));
    }

    @Override
    public ItemStack getContainerItem(ItemStack item){
        return new ItemStack(this);
    }

    /*@Override
    public boolean hasContainerItem(){
        return true;
    }*/
}
