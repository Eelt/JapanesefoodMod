package jp.tdn.japanese_food_mod.entities;

import jp.tdn.japanese_food_mod.init.JPEntities;
import jp.tdn.japanese_food_mod.init.JPItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.world.World;

public class ClamEntity extends ShellfishEntity {
    public ClamEntity(EntityType<? extends WaterMobEntity> type, World worldIn){
        super(JPEntities.CLAM, worldIn, JPItems.CLAM.get());
    }
}
