package jp.tdn.japanese_food_mod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class UnrefinedBlock extends Block {
    public static BooleanProperty SAUCE = BooleanProperty.create("sauce");

    public UnrefinedBlock(){
        super(Properties.of(Material.SAND, MaterialColor.COLOR_BROWN).strength(1.0f).noCollission().randomTicks());
        this.registerDefaultState(this.defaultBlockState().setValue(SAUCE, true));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    public boolean hasUpSideBlock(World world, BlockPos pos){
        BlockPos upSide = pos.above();
        Block up = world.getBlockState(upSide).getBlock();

        return BlockTags.getAllTags().getTagOrEmpty(new ResourceLocation("japanese_food_mod", "heavy")).contains(up);
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(SAUCE);
    }
}
