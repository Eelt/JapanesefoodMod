package jp.tdn.japanese_food_mod.blocks;

import jp.tdn.japanese_food_mod.blocks.tileentity.FurnaceCauldronTileEntity;
import jp.tdn.japanese_food_mod.init.JPItemTags;
import jp.tdn.japanese_food_mod.init.JPTileEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
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
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FurnaceCauldronBlock extends JPHorizontalBlock {
    protected static final VoxelShape SHAPE = VoxelShapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 24.0D, 16.0D));
    protected static final VoxelShape COLLISION = VoxelShapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 24.0D, 16.0D));
    public static final IntegerProperty WATER = BlockStateProperties.LEVEL_CAULDRON;

    public FurnaceCauldronBlock(){
        super(Properties.of(Material.METAL, MaterialColor.STONE).strength(2.0f).noOcclusion());
        registerDefaultState(this.defaultBlockState().setValue(DIRECTION, Direction.NORTH).setValue(WATER, 0));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return JPTileEntities.FURNACE_CAULDRON.create();
    }

    @Override
    public VoxelShape getBlockSupportShape(BlockState p_196247_1_, IBlockReader p_196247_2_, BlockPos p_196247_3_) {
        return SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState p_220071_1_, IBlockReader p_220071_2_, BlockPos p_220071_3_, ISelectionContext p_220071_4_) {
        return COLLISION;
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult rayTraceResult) {
        if(!world.isClientSide){
            ItemStack heldItem = playerEntity.getItemInHand(hand);
            if(JPItemTags.WATER.contains(heldItem.getItem())){
                TileEntity blockEntity = world.getBlockEntity(pos);
                if(blockEntity instanceof FurnaceCauldronTileEntity){
                    if(((FurnaceCauldronTileEntity) blockEntity).canAddWater()) {
                        ((FurnaceCauldronTileEntity) blockEntity).addWater(heldItem);
                        if(!playerEntity.abilities.instabuild){
                            playerEntity.setItemInHand(hand, new ItemStack(heldItem.getContainerItem().getItem()));
                        }
                        world.playSound((PlayerEntity)null, pos, SoundEvents.BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    }
                    setWaterLevel(world, pos, state, ((FurnaceCauldronTileEntity) blockEntity).getWaterRemaining(), ((FurnaceCauldronTileEntity) blockEntity).getMaxWater());
                }
            }else{
                final TileEntity tileEntity = world.getBlockEntity(pos);
                if (tileEntity instanceof FurnaceCauldronTileEntity)
                    NetworkHooks.openGui((ServerPlayerEntity) playerEntity, (FurnaceCauldronTileEntity) tileEntity, pos);
            }
        }
        return ActionResultType.SUCCESS;
    }

    public void setWaterLevel(World worldIn, BlockPos pos, BlockState state, int water, int max){
        float per = ((float)water / (float)max);
        int level = 0;
        if(per == 0f){
            level = 0;
        }else if(per >= 0.01f && per < 0.5f){
            level = 1;
        }else if(per >= 0.5f && per < 0.95f){
            level = 2;
        }else if(per >= 0.95f && per <= 1.0f){
            level = 3;
        }
        worldIn.setBlockAndUpdate(pos, state.setValue(WATER, level));
    }

    @Override
    public void onRemove(BlockState oldState, @Nonnull World worldIn, @Nonnull BlockPos pos, BlockState newState, boolean isMoving) {
        if (oldState.getBlock() != newState.getBlock()) {
            TileEntity tileEntity = worldIn.getBlockEntity(pos);
            if (tileEntity instanceof FurnaceCauldronTileEntity) {
                final ItemStackHandler inventory = ((FurnaceCauldronTileEntity) tileEntity).inventory;
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
        if(tileEntity instanceof FurnaceCauldronTileEntity) return ItemHandlerHelper.calcRedstoneFromInventory(((FurnaceCauldronTileEntity) tileEntity).inventory);
        return super.getAnalogOutputSignal(blockState, worldIn, pos);
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WATER);
    }
}
