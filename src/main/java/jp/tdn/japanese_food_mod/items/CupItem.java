package jp.tdn.japanese_food_mod.items;

import jp.tdn.japanese_food_mod.init.JPItems;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.List;

public class CupItem extends SimpleItem {
    public CupItem(){
        super();
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity player, Hand hand) {
        List<AreaEffectCloudEntity> AECEntityList = worldIn.getEntitiesOfClass(AreaEffectCloudEntity.class, player.getBoundingBox().inflate(2.0D), (p_210311_0_) -> {
            return p_210311_0_ != null && p_210311_0_.isAlive() && p_210311_0_.getOwner() instanceof EnderDragonEntity;
        });
        ItemStack inHand = player.getItemInHand(hand);
        if (!AECEntityList.isEmpty()) {
            AreaEffectCloudEntity AECEntity = (AreaEffectCloudEntity)AECEntityList.get(0);
            AECEntity.setRadius(AECEntity.getRadius() - 0.5F);
            worldIn.playSound((PlayerEntity)null, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_FILL_DRAGONBREATH, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            return new ActionResult(ActionResultType.SUCCESS, this.turnBottleIntoItem(inHand, player, new ItemStack(Items.DRAGON_BREATH)));
        } else {
            RayTraceResult rayTrace = getPlayerPOVHitResult(worldIn, player, RayTraceContext.FluidMode.SOURCE_ONLY);
            if (rayTrace.getType() == RayTraceResult.Type.MISS) {
                return new ActionResult(ActionResultType.PASS, inHand);
            } else {
                if (rayTrace.getType() == RayTraceResult.Type.BLOCK) {
                    BlockPos pos = ((BlockRayTraceResult)rayTrace).getBlockPos();
                    if (!worldIn.mayInteract(player, pos)) { // TODO: Originally isBlockModifiable
                        return new ActionResult(ActionResultType.PASS, inHand);
                    }

                    if (worldIn.getFluidState(pos).is(FluidTags.WATER)) {
                        worldIn.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                        return new ActionResult(ActionResultType.SUCCESS, this.turnBottleIntoItem(inHand, player, new ItemStack(JPItems.CUP_WITH_WATER.get())));
                    }
                }

                return new ActionResult(ActionResultType.PASS, inHand);
            }
        }
    }

    @Override
    public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
//        ItemStack inHand = player.getHeldItem(hand);
//        JapaneseFoodMod.LOGGER.info(stack);
//        JapaneseFoodMod.LOGGER.info(entity.getEntityString());
        if(stack.getItem() == JPItems.CUP.get()){
            if(entity.toString().equals("minecraft:cow")){
                turnBottleIntoItem(stack, player, new ItemStack(JPItems.CUP_WITH_MILK.get()));
                player.level.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.COW_MILK, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.PASS;
    }

    protected ItemStack turnBottleIntoItem(ItemStack itemStack, PlayerEntity player, ItemStack itemStack1) {
        itemStack.shrink(1);
        player.awardStat(Stats.ITEM_USED.get(this));
        if (itemStack.isEmpty()) {
            return itemStack1;
        } else {
            if (!player.inventory.add(itemStack1)) {
                player.drop(itemStack1, false);
            }

            return itemStack;
        }
    }
}
