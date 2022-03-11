package jp.tdn.japanese_food_mod.items;

import jp.tdn.japanese_food_mod.init.JPItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.extensions.IForgeItem;

public class ContainerItem extends Item implements IForgeItem {
    private static Item container;
    public ContainerItem(Item container){
        super(new Properties().tab(JPItems.ItemGroup_Japanese));
        ContainerItem.container = container;
    }

    @Override
    public ItemStack getContainerItem(ItemStack item){
        return new ItemStack(container);
    }

    /*@Override // TODO: Look at what this was in 1.16.1
    public boolean hasContainerItem(){
        return true;
    }*/
}
