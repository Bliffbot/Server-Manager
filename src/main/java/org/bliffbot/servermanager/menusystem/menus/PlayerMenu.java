package org.bliffbot.servermanager.menusystem.menus;

import org.bliffbot.servermanager.Server_Manager;
import org.bliffbot.servermanager.menusystem.Menu;
import org.bliffbot.servermanager.menusystem.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class PlayerMenu extends Menu {

    public PlayerMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "SM > " + playerMenuUtility.getselectedPlayer().getDisplayName();
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();

        if (event.getSlot() < getSlots() - 9) {
            PlayerMenuUtility playerMenuUtility = Server_Manager.getPlayerMenuUtility(player);
            new PlayerMenu(playerMenuUtility).open();
        }

        if (event.getSlot() == getSlots() - 5) {
            new PlayerSelectMenu(playerMenuUtility).open();
        }

    }

    @Override
    public void setMenuItems() {

        page = 0;
        pageMax = 0;
        insertBar(true);

        ItemStack placeholderItem = new ItemStack(Material.PAPER, 1);
        ItemMeta placeholderMeta = placeholderItem.getItemMeta();

        placeholderMeta.setDisplayName("Hi " + playerMenuUtility.getOwner().getDisplayName());

        placeholderItem.setItemMeta(placeholderMeta);
        inventory.addItem(placeholderItem);

        setFillerGlass();

    }

}
