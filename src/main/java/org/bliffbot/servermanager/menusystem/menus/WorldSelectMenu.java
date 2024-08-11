package org.bliffbot.servermanager.menusystem.menus;

import org.bliffbot.servermanager.menusystem.Menu;
import org.bliffbot.servermanager.menusystem.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;
import static org.bukkit.Bukkit.getServer;

public class WorldSelectMenu extends Menu {

    public WorldSelectMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "SM > Worlds";
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
            new MainMenu(playerMenuUtility).open();
        }

        if (event.getSlot() == getSlots() - 1 && pageMax > page) {
            page = page + 1;
            super.open();
        }

    }

    @Override
    public void setMenuItems() {

        ArrayList<World> worlds = new ArrayList<>(getServer().getWorlds());
        pageMax = (int) Math.ceil(worlds.size() / (double) maxItemsPerPage) - 1;

        if (!worlds.isEmpty()) {
            for (int slot = 0; slot < maxItemsPerPage; slot++) {
                index = maxItemsPerPage * page + slot;
                if (index >= worlds.size()) break;
                if (worlds.get(index) != null) {

                    Location spawn = worlds.get(index).getSpawnLocation();

                    ItemStack worldItem = new ItemStack(Material.EMPTY_MAP, 1);
                    ItemMeta worldMeta = worldItem.getItemMeta();

                    worldMeta.setDisplayName(ChatColor.YELLOW + worlds.get(index).getName());

                    ArrayList<String> worldLore = new ArrayList<>();
                    worldLore.add(ChatColor.GRAY + "UID: " + ChatColor.DARK_GRAY + worlds.get(index).getUID().toString());
                    worldLore.add(ChatColor.GRAY + "Seed: " + ChatColor.DARK_GRAY + worlds.get(index).getSeed());
                    worldLore.add(ChatColor.GRAY + "Difficulty: " + ChatColor.DARK_GRAY + worlds.get(index).getDifficulty().toString());
                    worldLore.add(ChatColor.GRAY + "Environment: " + ChatColor.DARK_GRAY + worlds.get(index).getEnvironment().toString());
                    worldLore.add(ChatColor.GRAY + "World Type: " + ChatColor.DARK_GRAY + worlds.get(index).getWorldType().toString());
                    worldLore.add(ChatColor.GRAY + "Time (clock): " + ChatColor.DARK_GRAY + worlds.get(index).getTime());
                    worldLore.add(ChatColor.GRAY + "Time (passed): " + ChatColor.DARK_GRAY + worlds.get(index).getFullTime());
                    worldLore.add(ChatColor.GRAY + "Spawn Location:" + ChatColor.DARK_GRAY + " [X] " + spawn.getX() + " [Y] " + spawn.getY() + " [Z] " + spawn.getZ());
                    worldMeta.setLore(worldLore);

                    worldItem.setItemMeta(worldMeta);
                    inventory.addItem(worldItem);

                }
            }
        }

        if (page > 0) {
            inventory.setItem(getSlots() - 9, getBasicItem(Material.ARROW, ChatColor.GRAY + "Last Page"));
        }

        inventory.setItem(getSlots() - 8, getBasicItem(Material.SPECTRAL_ARROW, ChatColor.GRAY + "Go Back"));

        if (page < pageMax) {
            inventory.setItem(getSlots() - 1, getBasicItem(Material.ARROW, ChatColor.GRAY + "Next Page"));
        }

        for (int i = getSlots() - 9; i < getSlots(); i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, getFillerGlass());
            }
        }

    }
}