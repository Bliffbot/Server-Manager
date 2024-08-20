package org.bliffbot.servermanager;

import org.bliffbot.servermanager.commands.CommandServerManager;
import org.bliffbot.servermanager.listeners.InventoryDragEventListener;
import org.bliffbot.servermanager.listeners.MenuListener;
import org.bliffbot.servermanager.menusystem.PlayerMenuUtility;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;

public final class Server_Manager extends JavaPlugin {

    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();
    private final Server_Manager plugin = this;

    public Server_Manager getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        getCommand("server-manager").setExecutor(new CommandServerManager());

        getServer().getPluginManager().registerEvents(new MenuListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryDragEventListener(), this);

        this.saveDefaultConfig();

        System.out.println("Plugin loaded");
    }

    @Override
    public void onDisable() {}

    public static PlayerMenuUtility getPlayerMenuUtility(Player player) {
        PlayerMenuUtility playerMenuUtility;
        if (!(playerMenuUtilityMap.containsKey(player))) {
            playerMenuUtility = new PlayerMenuUtility(player);
            playerMenuUtilityMap.put(player, playerMenuUtility);
            return playerMenuUtility;
        } else {
            return playerMenuUtilityMap.get(player);
        }
    }

}
