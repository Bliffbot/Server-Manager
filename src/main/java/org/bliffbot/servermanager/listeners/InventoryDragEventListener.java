package org.bliffbot.servermanager.listeners;

import org.bliffbot.servermanager.menusystem.Menu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.InventoryHolder;

public class InventoryDragEventListener implements Listener {

    @EventHandler
    public void onEvent(InventoryDragEvent event) {

        InventoryHolder holder = event.getInventory().getHolder();

        if (holder instanceof Menu) {
            Menu menu = (Menu) holder;
            for (int slot = 0; slot < menu.getSlots(); slot++) {
                if (event.getRawSlots().contains(slot)) {
                    event.setCancelled(true);
                }
            }
        }

    }

}