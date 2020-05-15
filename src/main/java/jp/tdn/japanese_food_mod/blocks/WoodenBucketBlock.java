package jp.tdn.japanese_food_mod.blocks;

import jp.tdn.japanese_food_mod.blocks.tileentity.WoodenBucketTileEntity;
import jp.tdn.japanese_food_mod.init.JPTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
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
import java.util.Random;

public class WoodenBucketBlock extends HorizontalBlock {
    protected static final VoxelShape SHAPE = VoxelShapes.or(Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D));
    public static final DirectionProperty DIRECTION = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty FER = BooleanProperty.create("active");

    public WoodenBucketBlock(){
        super(Properties.create(Material.WOOD, MaterialColor.BROWN).harvestTool(ToolType.AXE).hardnessAndResistance(2.0f));
        this.setDefaultState(this.getDefaultState().with(DIRECTION, Direction.NORTH).with(FER, false));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return JPTileEntities.WOODEN_BUCKET.create();
    }

    @Nonnull
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        return SHAPE;
    }

    @Override
    @Nonnull
    public VoxelShape getCollisionShape(@Nonnull BlockState p_220071_1_, @Nonnull IBlockReader p_220071_2_, @Nonnull BlockPos p_220071_3_, ISelectionContext p_220071_4_) {
        return SHAPE;
    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if(!worldIn.isRemote){
            final TileEntity tileEntity = worldIn.getTileEntity(pos);
            worldIn.playSound((PlayerEntity) null, (double) pos.getX(), (double) pos.getY(), (double) pos.getZ(), SoundEvents.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS, 0.5f, worldIn.rand.nextFloat() * 0.1f + 0.9f);
            if(tileEntity instanceof WoodenBucketTileEntity) NetworkHooks.openGui((ServerPlayerEntity) player, (WoodenBucketTileEntity) tileEntity, pos);
        }
        return true;
    }

    @Override
    public void onReplaced(BlockState oldState, @Nonnull World worldIn, @Nonnull BlockPos pos, BlockState newState, boolean isMoving) {
        if (oldState.getBlock() != newState.getBlock()) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof WoodenBucketTileEntity) {
                final ItemStackHandler inventory = ((WoodenBucketTileEntity) tileEntity).inventory;
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
        if(tileEntity instanceof WoodenBucketTileEntity) return ItemHandlerHelper.calcRedstoneFromInventory(((WoodenBucketTileEntity) tileEntity).inventory);
        return super.getComparatorInputOverride(blockState, worldIn, pos);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(DIRECTION, FER);
    }

    @Override
    @Nonnull
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(DIRECTION, rot.rotate(state.get(DIRECTION)));
    }

    @Override
    @Nonnull
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(DIRECTION)));
    }

    @Override
    public void animateTick(BlockState state, World worldIn, BlockPos pos, Random rand) {
        if(state.get(FER)){
            double posX = (double) pos.getX();
            double posY = (double) pos.getY() + 1.0D;
            double posZ = (double) pos.getZ();
            Direction direction = Direction.UP;
            worldIn.addParticle(ParticleTypes.SMOKE, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
        }
    }

    @Nonnull
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}
