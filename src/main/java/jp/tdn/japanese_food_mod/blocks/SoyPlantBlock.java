package jp.tdn.japanese_food_mod.blocks;

import jp.tdn.japanese_food_mod.init.JPItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class SoyPlantBlock extends CropsBlock{
    public SoyPlantBlock(){
        super(Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0f).sound(SoundType.CROP));
    }

    @Override
    @Nonnull
    protected IItemProvider getSeedsItem(){
        return JPItems.SOY_BEANS;
    }

    @Override
    public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if(!world.isRemote){
            if(this.isMaxAge(state)){
                world.addEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(JPItems.SOY_BEANS,1)));
                world.setBlockState(pos, this.withAge(0));
                return true;
            }else if(this.getAge(state) >= 5 && this.getAge(state) <= 6){
                world.addEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(JPItems.EDAMAME, 1)));
                world.setBlockState(pos, this.withAge(0));
                return true;
            }
        }
        return false;
    }
}
