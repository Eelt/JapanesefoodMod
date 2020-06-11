package jp.tdn.japanese_food_mod.blocks;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.PlantType;

public class CropGrassBlock extends BushBlock {
    public CropGrassBlock(){
        super(Properties.create(Material.PLANTS, MaterialColor.GREEN).doesNotBlockMovement().hardnessAndResistance(0f).sound(SoundType.CROP));
    }

    @Override
    public OffsetType getOffsetType() {
        return OffsetType.XZ;
    }

    @Override
    public PlantType getPlantType(IBlockReader world, BlockPos pos) {
        return PlantType.Plains;
    }
}
