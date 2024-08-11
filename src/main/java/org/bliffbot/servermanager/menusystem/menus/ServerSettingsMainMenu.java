package org.bliffbot.servermanager.menusystem.menus;

import org.bliffbot.servermanager.menusystem.Menu;
import org.bliffbot.servermanager.menusystem.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ServerSettingsMainMenu extends Menu {

    public ServerSettingsMainMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "SM > Server Settings";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {

        if (event.getSlot() == getSlots() - 8) {
            new MainMenu(playerMenuUtility).open();
        }

    }

    @Override
    public void setMenuItems() {

        if (page > 0) {
            inventory.setItem(getSlots() - 9, makeItem(Material.ARROW, ChatColor.GRAY + "Last Page"));
        }

        inventory.setItem(getSlots() - 8, makeItem(Material.SPECTRAL_ARROW, ChatColor.GRAY + "Go Back"));

        if (page < pageMax) {
            inventory.setItem(getSlots() - 1, makeItem(Material.ARROW, ChatColor.GRAY + "Next Page"));
        }

        for (int i = getSlots() - 9; i < getSlots(); i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, FILLER_GLASS);
            }
        }

    }
}