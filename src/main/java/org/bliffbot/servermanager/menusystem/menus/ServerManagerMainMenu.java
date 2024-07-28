package org.bliffbot.servermanager.menusystem.menus;

import org.bliffbot.servermanager.Server_Manager;
import org.bliffbot.servermanager.menusystem.Menu;
import org.bliffbot.servermanager.menusystem.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;

public class ServerManagerMainMenu extends Menu {

    public ServerManagerMainMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Server-Manager > Main Menu";
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();

        if (event.getSlot() == 11) {

            PlayerMenuUtility playerMenuUtility = Server_Manager.getPlayerMenuUtility(player);
            new ServerManagerPlayerSelectMenu(playerMenuUtility).open();

        } else if (event.getSlot() == 13) {

            PlayerMenuUtility playerMenuUtility = Server_Manager.getPlayerMenuUtility(player);
            new ServerManagerWorldSelectMenu(playerMenuUtility).open();

        } else if (event.getSlot() == 15) {

            PlayerMenuUtility playerMenuUtility = Server_Manager.getPlayerMenuUtility(player);
            new ServerManagerServerSettingsMainMenu(playerMenuUtility).open();

        }

    }

    @Override
    public void setMenuItems() {

        ItemStack playerItem = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        ItemMeta playerMeta = playerItem.getItemMeta();
        playerMeta.setDisplayName(ChatColor.BLUE + "Players");
        ArrayList<String> playerLore = new ArrayList<>();
        playerLore.add(ChatColor.GRAY + "Select a player to do something to them");
        playerMeta.setLore(playerLore);
        playerItem.setItemMeta(playerMeta);
        inventory.setItem(11, playerItem);

        ItemStack worldItem = new ItemStack(Material.EMPTY_MAP, 1);
        ItemMeta worldMeta = worldItem.getItemMeta();
        worldMeta.setDisplayName(ChatColor.GREEN + "Worlds");
        ArrayList<String> worldLore = new ArrayList<>();
        worldLore.add(ChatColor.GRAY + "Select a world to change its properties");
        worldMeta.setLore(worldLore);
        worldItem.setItemMeta(worldMeta);
        inventory.setItem(13, worldItem);

        ItemStack serverItem = new ItemStack(Material.REDSTONE_COMPARATOR, 1);
        ItemMeta serverMeta = serverItem.getItemMeta();
        serverMeta.setDisplayName(ChatColor.RED + "Server");
        ArrayList<String> serverLore = new ArrayList<>();
        serverLore.add(ChatColor.GRAY + "Change properties of this server");
        serverMeta.setLore(serverLore);
        serverItem.setItemMeta(serverMeta);
        inventory.setItem(15, serverItem);

        setFillerGlass();

    }
}