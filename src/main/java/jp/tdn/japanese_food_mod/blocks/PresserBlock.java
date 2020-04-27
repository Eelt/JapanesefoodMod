package jp.tdn.japanese_food_mod.blocks;

import jp.tdn.japanese_food_mod.blocks.tileentity.PresserTileEntity;
import jp.tdn.japanese_food_mod.blocks.tileentity.WoodenBucketTileEntity;
import jp.tdn.japanese_food_mod.init.JPTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.*;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.Random;

public class PresserBlock extends HorizontalBlock {
    protected static final VoxelShape SHAPE = VoxelShapes.or(Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D));
    public static final DirectionProperty DIRECTION = BlockStateProperties.HORIZONTAL_FACING;
    public static final IntegerProperty OIL = BlockStateProperties.LEVEL_0_3;
    public static final BooleanProperty PRESSING = BooleanProperty.create("pressing");

    public PresserBlock(){
        super(Properties.create(Material.WOOD, MaterialColor.BROWN).harvestTool(ToolType.AXE).hardnessAndResistance(2.0f));
        this.setDefaultState(this.getDefaultState().with(DIRECTION, Direction.NORTH).with(OIL, 0).with(PRESSING, false));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return JPTileEntities.PRESSER.create();
    }

    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState p_220071_1_, IBlockReader p_220071_2_, BlockPos p_220071_3_, ISelectionContext p_220071_4_) {
        return SHAPE;
    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if(!worldIn.isRemote){
            final TileEntity tileEntity = worldIn.getTileEntity(pos);
            if(tileEntity instanceof PresserTileEntity) NetworkHooks.openGui((ServerPlayerEntity) player, (PresserTileEntity) tileEntity, pos);
        }
        return true;
    }

    @Override
    public void onReplaced(BlockState oldState, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (oldState.getBlock() != newState.getBlock()) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof PresserTileEntity) {
                final ItemStackHandler inventory = ((PresserTileEntity) tileEntity).inventory;
                for(int index = 0; index < inventory.getSlots(); ++index){
                    InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(index));
                }
            }
            super.onReplaced(oldState, worldIn, pos, newState, isMoving);
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(DIRECTION, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) {
        final TileEntity tileEntity = worldIn.getTileEntity(pos);
        if(tileEntity instanceof PresserTileEntity) return ItemHandlerHelper.calcRedstoneFromInventory(((PresserTileEntity) tileEntity).inventory);
        return super.getComparatorInputOverride(blockState, worldIn, pos);
    }

    public void setOil(World worldIn, BlockPos pos, BlockState state, int level){
        worldIn.setBlockState(pos, state.with(OIL, MathHelper.clamp(level, 0, 3)), 2);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(DIRECTION, OIL, PRESSING);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(DIRECTION, rot.rotate(state.get(DIRECTION)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(DIRECTION)));
    }

    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}
