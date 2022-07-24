package com.lsk.mc.myplugin;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TestCommand implements CommandExecutor {
    public static final String COMMAND_HEADER = "helper";

    private boolean help(CommandSender commandSender) {
        commandSender.sendMessage("Usage: /helper [verb] <args>");
        commandSender.sendMessage("Valid verbs:");
        commandSender.sendMessage("[WIP] find_diamond: locate the diamond ore by the Lapis Lazuli you are pointing");
        commandSender.sendMessage("ttd: teleport to your last death");
        return true;
    }

    private boolean teleportToDeath(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            RedisHelper redisHelper = Myplugin.getRedisHelper();
            DeathInfo deathInfo = redisHelper.getDeathInfo(player.getUniqueId().toString());
            if (deathInfo == null) {
                commandSender.sendMessage("You haven't dead before");
                return true;
            }
            // player.teleport(deathInfo.toLocation());
        } else {
            commandSender.sendMessage("This command can only be used by a player");
        }
        return true;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (strings.length < 1) {
            return help(commandSender);
        }
        switch(strings[0]) {
            case "find_diamond":
                commandSender.sendMessage("WIP!");
                return true;
            case "ttd":
                return teleportToDeath(commandSender, command, s, strings);
            case "ender_crystal":
                if (strings.length < 2) {
                    commandSender.sendMessage("Usage: /helper ender_crystal enable|disable|state");
                    return true;
                }
                switch(strings[1]) {
                    case "state":
                        commandSender.sendMessage("Using ender crystal is " + (State.REMOVE_ENDER_CRYSTAL ? "disabled" : "enabled"));
                        return true;
                    case "enable":
                        State.REMOVE_ENDER_CRYSTAL = false;
                        commandSender.sendMessage("Using ender crystal is now enabled.");
                        return true;
                    case "disable":
                        State.REMOVE_ENDER_CRYSTAL = true;
                        commandSender.sendMessage("Using ender crystal is now disabled.");
                        return true;
                    default:
                        commandSender.sendMessage("Usage: /helper ender_crystal enable|disable|state");
                        return true;
                }
            default:
                return help(commandSender);
        }
    }
}
