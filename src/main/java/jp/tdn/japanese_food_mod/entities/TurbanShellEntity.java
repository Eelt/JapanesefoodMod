package jp.tdn.japanese_food_mod.entities;

import jp.tdn.japanese_food_mod.init.JPEntities;
import jp.tdn.japanese_food_mod.init.JPItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.world.World;

public class TurbanShellEntity extends ShellfishEntity {
    public TurbanShellEntity(EntityType<? extends WaterMobEntity> type, World worldIn){
        super(JPEntities.TURBAN_SHELL, worldIn, JPItems.TURBAN_SHELL.get());
    }

    protected void updateAir(int p_209207_1_) {
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.MOVEMENT_SPEED, 0.15D);
    }
}
