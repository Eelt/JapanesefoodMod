package jp.tdn.japanese_food_mod.entities;

import jp.tdn.japanese_food_mod.init.JPEntities;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.FindWaterGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.Random;

public class CrabEntity extends WaterMobEntity {
    public CrabEntity(EntityType<? extends WaterMobEntity> type, World worldIn){
        super(JPEntities.CRAB, worldIn);
    }

    // handleAirSupply in superclass

    @Override
    public int getAmbientSoundInterval() {
        return 32;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 1.0d));
        this.goalSelector.addGoal(2, new FindWaterGoal(this));
        this.goalSelector.addGoal(3, new PanicGoal(this, 1.5d));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 5.0D).add(Attributes.MOVEMENT_SPEED, 0.3D);
    }

    public static boolean spawnHandler(EntityType<? extends CrabEntity> entityIn, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random){
        return worldIn.getBlockState(pos.below()).getBlock() != Blocks.WATER && worldIn.getFluidState(pos).is(FluidTags.WATER);
    }
}
