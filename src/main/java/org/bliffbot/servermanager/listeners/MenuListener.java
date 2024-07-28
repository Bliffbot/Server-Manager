package org.bliffbot.servermanager.listeners;

import org.bliffbot.servermanager.menusystem.Menu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

public class MenuListener implements Listener {

    @EventHandler
    public void onEvent(InventoryClickEvent event) {

        InventoryHolder holder = event.getInventory().getHolder();

        if (holder instanceof Menu) {
            Menu menu = (Menu) holder;

            if (event.getCurrentItem() != null) {
                if (event.isShiftClick()) {
                    event.setCancelled(true);
                }

                if (event.getClickedInventory().getHolder() == menu.getInventory().getHolder()) {
                    event.setCancelled(true);
                    menu.handleMenu(event);
                }
            }
        }

    }

}