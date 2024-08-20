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

public class PlayerMenu extends Menu {

    public PlayerMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "SM > " + playerMenuUtility.getSelectedPlayer().getDisplayName();
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

        if (event.getSlot() == getSlots() - 8) {
            new PlayerSelectMenu(playerMenuUtility).open();
        }

    }

    @Override
    public void setMenuItems() {

        ItemStack placeholderItem = new ItemStack(Material.PAPER, 1);
        ItemMeta placeholderMeta = placeholderItem.getItemMeta();

        placeholderMeta.setDisplayName("Hi " + playerMenuUtility.getOwner().getDisplayName());

        ArrayList<String> placeholderLore = new ArrayList<>();
        placeholderLore.add(playerMenuUtility.getSelectedPlayer().getDisplayName());
        placeholderLore.add(playerMenuUtility.getSelectedPlayer().getUniqueId().toString());
        placeholderMeta.setLore(placeholderLore);

        placeholderItem.setItemMeta(placeholderMeta);
        inventory.addItem(placeholderItem);

        if (page > 0) {
            inventory.setItem(getSlots() - 9, getBasicItem(Material.ARROW, ChatColor.WHITE + "Last Page"));
        }

        inventory.setItem(getSlots() - 8, getBasicItem(Material.SPECTRAL_ARROW, ChatColor.WHITE + "Go Back"));

        if (page < pageMax) {
            inventory.setItem(getSlots() - 1, getBasicItem(Material.ARROW, ChatColor.WHITE + "Next Page"));
        }

        for (int i = getSlots() - 9; i < getSlots(); i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, getFillerGlass());
            }
        }

        setFillerGlass();

    }

}