package org.bliffbot.servermanager.menusystem.menus;

import org.bliffbot.servermanager.menusystem.Menu;
import org.bliffbot.servermanager.menusystem.PlayerMenuUtility;
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

        if (event.getSlot() == getSlots() - 5) {
            new MainMenu(playerMenuUtility).open();
        }

    }

    @Override
    public void setMenuItems() {

        insertBar(true);

    }
}