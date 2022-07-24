package com.lsk.mc.myplugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EnderCrystalListener implements Listener {
    @EventHandler
    public void handleEntity(EntitySpawnEvent e) {
        if (!State.REMOVE_ENDER_CRYSTAL) {
            return;
        }
        if (e.getEntityType().equals(EntityType.ENDER_CRYSTAL)) {
            Location location = e.getLocation();
            Location blockBelowLocation = location.subtract(0, 1, 0);
            Block blockBelow = e.getEntity().getWorld().getBlockAt(blockBelowLocation);
            if (!blockBelow.getType().equals(Material.BEDROCK)) {
                Entity enderCrystal = e.getEntity();
                enderCrystal.remove();
            }
        }
    }
}
