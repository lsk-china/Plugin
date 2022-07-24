package com.lsk.mc.myplugin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Myplugin extends JavaPlugin {

    public static final Logger LOGGER = LogManager.getLogger("myplugin");
    private static RedisHelper redisHelper;
    private static UDPUtil udpUtil;

    @Override
    public void onEnable() {
        // Plugin startup logic
        // Myplugin.redisHelper = new RedisHelper("localhost", 6379, "lsk123456", 4);
        // Myplugin.udpUtil = new UDPUtil();
        PluginCommand pluginCommand = getCommand(TestCommand.COMMAND_HEADER);
        pluginCommand.setExecutor(new TestCommand());
        getServer().getPluginManager().registerEvents(new EnderCrystalListener(), this);
        // DeathListener deathListener = new DeathListener();
        // deathListener.init();
        // getServer().getPluginManager().registerEvents(deathListener, this);
        // getServer().getPluginManager().registerEvents(new NotifierListener(), this);
        LOGGER.info("my plugin loaded!");
    }

    public static RedisHelper getRedisHelper() {
        return redisHelper;
    }
    public static UDPUtil getUdpUtil() { return udpUtil; }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
//        Myplugin.redisHelper.close();
//        Myplugin.udpUtil.clean();
    }
}
