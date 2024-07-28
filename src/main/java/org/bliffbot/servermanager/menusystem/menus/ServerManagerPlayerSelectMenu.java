package org.bliffbot.servermanager.menusystem.menus;

import org.bliffbot.servermanager.menusystem.Menu;
import org.bliffbot.servermanager.menusystem.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

import static org.bukkit.Bukkit.getServer;

public class ServerManagerPlayerSelectMenu extends Menu {

    public ServerManagerPlayerSelectMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Server-Manager > Players";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {

        if (event.getSlot() == getSlots() - 9) {
            page = page - 1;
            super.open();
        }

        if (event.getSlot() == getSlots() - 5) {
            new ServerManagerMainMenu(playerMenuUtility).open();
        }

        if (event.getSlot() == getSlots() - 1) {
            page = page + 1;
            super.open();
        }

    }

    @Override
    public void setMenuItems() {

        ArrayList<Player> players = new ArrayList<>(getServer().getOnlinePlayers());
        pageMax = (int) Math.ceil(players.size() / (double) maxItemsPerPage) - 1;
        insertBar(true);

        if (!players.isEmpty()) {
            for (int slot = 0; slot < maxItemsPerPage; slot++) {
                index = maxItemsPerPage * page + slot;
                if (index >= players.size()) break;
                if (players.get(index) != null) {

                    ItemStack playerItem = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                    SkullMeta playerMeta = (SkullMeta) playerItem.getItemMeta();

                    playerMeta.setOwner(players.get(index).getDisplayName());
                    playerMeta.setDisplayName(ChatColor.YELLOW + players.get(index).getDisplayName());
                    Location location = players.get(index).getLocation();

                    ArrayList<String> playerLore = new ArrayList<>();
                    playerLore.add(ChatColor.GRAY + "Gamemode: " + ChatColor.DARK_GRAY + players.get(index).getGameMode().name());
                    playerLore.add(ChatColor.GRAY + "Health: " + ChatColor.DARK_GRAY + players.get(index).getHealth() + " / " + players.get(index).getHealthScale());
                    playerLore.add(ChatColor.GRAY + "Food: " + ChatColor.DARK_GRAY + players.get(index).getFoodLevel());
                    playerLore.add(ChatColor.GRAY + "Saturation: " + ChatColor.DARK_GRAY + players.get(index).getSaturation());
                    playerLore.add(ChatColor.GRAY + "Remaining Air: " + ChatColor.DARK_GRAY + players.get(index).getRemainingAir());
                    playerLore.add(ChatColor.GRAY + "Exp (Total Exp / Level): " + ChatColor.DARK_GRAY + players.get(index).getTotalExperience() + " / " + players.get(index).getLevel());
                    playerLore.add(ChatColor.GRAY + "Location:" + ChatColor.DARK_GRAY + " [X] " + location.getX() + " [Y] " + location.getY() + " [Z] " + location.getZ());
                    playerLore.add(ChatColor.GRAY + "World: " + ChatColor.DARK_GRAY + players.get(index).getWorld().getName());
                    playerLore.add("");
                    playerLore.add(ChatColor.GRAY + "UUID: " + ChatColor.DARK_GRAY + players.get(index).getUniqueId().toString());
                    playerLore.add(ChatColor.GRAY + "Entity ID: " + ChatColor.DARK_GRAY + players.get(index).getEntityId());
                    playerLore.add(ChatColor.GRAY + "Address: " + ChatColor.DARK_GRAY + players.get(index).getAddress().toString());
                    playerLore.add(ChatColor.GRAY + "Locale: " + ChatColor.DARK_GRAY + players.get(index).getLocale());
                    playerLore.add(ChatColor.GRAY + "Item Pickup allowed: " + ChatColor.DARK_GRAY + players.get(index).getCanPickupItems());
                    playerLore.add(ChatColor.GRAY + "Flight allowed: " + ChatColor.DARK_GRAY + players.get(index).getAllowFlight());
                    playerLore.add(ChatColor.GRAY + "Fly Speed: " + ChatColor.DARK_GRAY + players.get(index).getFlySpeed());
                    playerLore.add(ChatColor.GRAY + "Walk Speed: " + ChatColor.DARK_GRAY + players.get(index).getWalkSpeed());
                    playerLore.add(ChatColor.GRAY + "Fire Ticks: " + ChatColor.DARK_GRAY + players.get(index).getFireTicks());
                    playerLore.add(ChatColor.GRAY + "Last Played: " + ChatColor.DARK_GRAY + players.get(index).getLastPlayed());
                    playerLore.add(ChatColor.GRAY + "Time Alive (Ticks): " + ChatColor.DARK_GRAY + players.get(index).getTicksLived());
                    playerMeta.setLore(playerLore);

                    playerItem.setItemMeta(playerMeta);
                    inventory.addItem(playerItem);

                }
            }
        }
    }
}