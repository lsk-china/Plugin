package com.lsk.mc.myplugin;

import lombok.*;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DeathInfo {
    private Integer x;
    private Integer y;
    private Integer z;
    private String worldName;

    public static DeathInfo formLocation(Location location) {
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        World world = location.getWorld();
        return new DeathInfo(x, y, z, world.getName());
    }
    public Location toLocation(Server server) {
        Location location = new Location(server.getWorld(this.worldName), this.x.intValue(), this.y.intValue(), this.z.intValue());
        return location;
    }
}
