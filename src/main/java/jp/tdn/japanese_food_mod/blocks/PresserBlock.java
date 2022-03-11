package jp.tdn.japanese_food_mod.blocks;

import jp.tdn.japanese_food_mod.blocks.tileentity.PresserTileEntity;
import jp.tdn.japanese_food_mod.init.JPTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PresserBlock extends HorizontalBlock {
    protected static final VoxelShape SHAPE = VoxelShapes.or(Block.box(2.0D, 0.0D, 1.0D, 14.0D, 13.0D, 14.0D));
    public static final DirectionProperty DIRECTION = BlockStateProperties.HORIZONTAL_FACING;
    public static final IntegerProperty OIL = BlockStateProperties.LEVEL_CAULDRON;
    public static final BooleanProperty PRESSING = BooleanProperty.create("pressing");

    public PresserBlock(){
        super(Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).harvestTool(ToolType.AXE).strength(2.0f));
        this.registerDefaultState(this.defaultBlockState().setValue(DIRECTION, Direction.NORTH).setValue(OIL, 0).setValue(PRESSING, false));
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

    @Nonnull
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return SHAPE;
    }

    @Nonnull
    @Override
    public VoxelShape getCollisionShape(@Nonnull BlockState p_220071_1_, @Nonnull IBlockReader p_220071_2_, @Nonnull BlockPos p_220071_3_, ISelectionContext p_220071_4_) {
        return SHAPE;
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if(!worldIn.isClientSide){
            final TileEntity tileEntity = worldIn.getBlockEntity(pos);
            if(tileEntity instanceof PresserTileEntity) NetworkHooks.openGui((ServerPlayerEntity) player, (PresserTileEntity) tileEntity, pos);
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public void onRemove(BlockState oldState, @Nonnull World worldIn, @Nonnull BlockPos pos, BlockState newState, boolean isMoving) {
        if (oldState.getBlock() != newState.getBlock()) {
            TileEntity tileEntity = worldIn.getBlockEntity(pos);
            if (tileEntity instanceof PresserTileEntity) {
                final ItemStackHandler inventory = ((PresserTileEntity) tileEntity).inventory;
                for(int index = 0; index < inventory.getSlots(); ++index){
                    InventoryHelper.dropItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(index));
                }
            }
            super.onRemove(oldState, worldIn, pos, newState, isMoving);
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState().setValue(DIRECTION, context.getNearestLookingDirection().getOpposite());
    }

    @Override
    public int getAnalogOutputSignal(BlockState blockState, World worldIn, BlockPos pos) {
        final TileEntity tileEntity = worldIn.getBlockEntity(pos);
        if(tileEntity instanceof PresserTileEntity) return ItemHandlerHelper.calcRedstoneFromInventory(((PresserTileEntity) tileEntity).inventory);
        return super.getAnalogOutputSignal(blockState, worldIn, pos);
    }

    public void setOil(World worldIn, BlockPos pos, BlockState state, int level){
        worldIn.setBlock(pos, state.setValue(OIL, MathHelper.clamp(level, 0, 3)), 2);
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(DIRECTION, OIL, PRESSING);
    }

    @Override
    @Nonnull
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(DIRECTION, rot.rotate(state.getValue(DIRECTION)));
    }

    @Override
    @Nonnull
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.setValue(DIRECTION, mirrorIn.mirror(state.getValue(DIRECTION)));
    }

    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
