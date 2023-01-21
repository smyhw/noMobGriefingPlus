package online.smyhw.noMobGriefingPlus;

import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class listener implements Listener{

    @EventHandler
    public void Enderman(EntityChangeBlockEvent event) {
        if (event.getEntity() == null)
            return;
        if (event.getEntity().getType() != EntityType.ENDERMAN) {
            return;
        }
        if (!config.enableWorldsEnderman.contains(event.getBlock().getWorld().getName())){
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void Creeper(EntityExplodeEvent event) {
        if (event.getEntity() == null)
            return;
        if (event.getEntity().getType() != EntityType.CREEPER) {
            return;
        }
        event.setCancelled(true);
        if (config.preventCreeperExplode){
            return;
        }
        //生成替代爆炸
        event.getLocation().createExplosion(event.getEntity(),config.CreeperExplodePower,config.CreeperExplodeSetFire,config.CreeperExplodeGroundBreaking);
    }

}
