package jp.tdn.japanese_food_mod;

import jp.tdn.japanese_food_mod.init.JPItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class JapaneseFoodItemGroup extends ItemGroup {
    public JapaneseFoodItemGroup(){
        super("japanese_food_mod");
    }

    @Override
    @Nonnull
    public ItemStack makeIcon(){
        return new ItemStack(JPItems.ONIGIRI.get());
    }
}
