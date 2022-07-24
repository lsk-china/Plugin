package com.lsk.mc.myplugin;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {
    private RedisHelper redisHelper;

    public void init() {
        this.redisHelper = Myplugin.getRedisHelper();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void playerDead(PlayerDeathEvent playerDeathEvent) {
        Player player = playerDeathEvent.getPlayer();
        Location location = player.getLocation();
        Myplugin.LOGGER.info("player " + player.getName() + "dead at " + location.getBlockX() + ", " + location.getBlockY() + "," + location.getBlockZ());
        DeathInfo deathInfo = DeathInfo.formLocation(location);
        redisHelper.storeDeathInfo(player.getUniqueId().toString(), deathInfo);
    }
}
