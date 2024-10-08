package org.bliffbot.servermanager.menusystem.menus;

import org.bliffbot.servermanager.menusystem.Menu;
import org.bliffbot.servermanager.menusystem.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class PlayerSelectFilterMenu extends Menu {

    public PlayerSelectFilterMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "SM > Player Filter";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {

        if (event.getSlot() == getSlots() - 9 && page > 0) {
            page = page - 1;
            super.open();
        }

        if (event.getSlot() == getSlots() - 8) {
            new PlayerSelectMenu(playerMenuUtility).open();
        }

        if (event.getSlot() == getSlots() - 1 && pageMax > page) {
            page = page + 1;
            super.open();
        }

    }

    @Override
    public void setMenuItems() {

        ItemStack bannedItem = new ItemStack(Material.BARRIER, 1, (short) 3);
        ItemMeta bannedMeta = bannedItem.getItemMeta();
        bannedMeta.setDisplayName(ChatColor.DARK_RED + "Banned Players");
        ArrayList<String> bannedLore = new ArrayList<>();
        bannedLore.add(ChatColor.BLUE + "> Both");
        bannedLore.add(ChatColor.GRAY + "  Yes");
        bannedLore.add(ChatColor.GRAY + "  No");
        bannedMeta.setLore(bannedLore);
        bannedItem.setItemMeta(bannedMeta);
        inventory.setItem(11, bannedItem);

        ItemStack whitelistedItem = new ItemStack(Material.PAPER, 1);
        ItemMeta whitelistedMeta = whitelistedItem.getItemMeta();
        whitelistedMeta.setDisplayName(ChatColor.WHITE + "Whitelisted Players");
        ArrayList<String> whitelistedLore = new ArrayList<>();
        whitelistedLore.add(ChatColor.BLUE + "> Both");
        whitelistedLore.add(ChatColor.GRAY + "  Yes");
        whitelistedLore.add(ChatColor.GRAY + "  No");
        whitelistedMeta.setLore(whitelistedLore);
        whitelistedItem.setItemMeta(whitelistedMeta);
        inventory.setItem(13, whitelistedItem);

        ItemStack onlineItem = new ItemStack(Material.EYE_OF_ENDER, 1);
        ItemMeta onlineMeta = onlineItem.getItemMeta();
        onlineMeta.setDisplayName(ChatColor.DARK_GREEN + "Online Players");
        ArrayList<String> onlineLore = new ArrayList<>();
        onlineLore.add(ChatColor.BLUE + "> Both");
        onlineLore.add(ChatColor.GRAY + "  Online");
        onlineLore.add(ChatColor.GRAY + "  Offline");
        onlineMeta.setLore(onlineLore);
        onlineItem.setItemMeta(onlineMeta);
        inventory.setItem(15, onlineItem);

        ItemStack worldItem = new ItemStack(Material.GRASS, 1);
        ItemMeta worldMeta = worldItem.getItemMeta();
        worldMeta.setDisplayName(ChatColor.GREEN + "World");
        ArrayList<String> worldLore = new ArrayList<>();
        worldLore.add(ChatColor.BLUE + "> All");
        worldLore.add(ChatColor.GRAY + "  Custom");
        worldMeta.setLore(worldLore);
        worldItem.setItemMeta(worldMeta);
        inventory.setItem(29, worldItem);

        ItemStack opItem = new ItemStack(Material.COMMAND, 1);
        ItemMeta opMeta = opItem.getItemMeta();
        opMeta.setDisplayName(ChatColor.RED + "Operator");
        ArrayList<String> opLore = new ArrayList<>();
        opLore.add(ChatColor.BLUE + "> Both");
        opLore.add(ChatColor.GRAY + "  Yes");
        opLore.add(ChatColor.GRAY + "  No");
        opMeta.setLore(opLore);
        opItem.setItemMeta(opMeta);
        inventory.setItem(31, opItem);

        ItemStack nameItem = new ItemStack(Material.NAME_TAG, 1);
        ItemMeta nameMeta = nameItem.getItemMeta();
        nameMeta.setDisplayName(ChatColor.WHITE + "Playername");
        ArrayList<String> nameLore = new ArrayList<>();
        nameLore.add(ChatColor.GRAY + "Show players that have specific string in their name");
        nameLore.add(ChatColor.GRAY + "");
        nameLore.add(ChatColor.YELLOW + "Shift + Click to reset (only show when text selected)");
        nameMeta.setLore(nameLore);
        nameItem.setItemMeta(nameMeta);
        inventory.setItem(33, nameItem);

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
