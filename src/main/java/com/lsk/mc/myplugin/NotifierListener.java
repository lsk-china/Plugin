package com.lsk.mc.myplugin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;

public class NotifierListener implements Listener {
    private static final Logger log = LogManager.getLogger("NotifierListener");

    @EventHandler
    public void onCommand(PlayerCommandSendEvent e) {
        log.info("test");
        System.out.println(e.getCommands());
    }
}