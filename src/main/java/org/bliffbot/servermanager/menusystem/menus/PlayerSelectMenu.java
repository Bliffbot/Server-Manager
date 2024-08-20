package org.bliffbot.servermanager.menusystem.menus;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bliffbot.servermanager.Server_Manager;
import org.bliffbot.servermanager.menusystem.Menu;
import org.bliffbot.servermanager.menusystem.PlayerMenuUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class PlayerSelectMenu extends Menu {

    public PlayerSelectMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "SM > Players";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        UUID uuid;

        if (event.getSlot() < getSlots() - 9 && inventory.getItem(event.getSlot()) != null) {

            PlayerMenuUtility playerMenuUtility = Server_Manager.getPlayerMenuUtility(player);
            SkullMeta skullMeta = (SkullMeta) inventory.getItem(event.getSlot()).getItemMeta();

            net.minecraft.server.v1_12_R1.ItemStack clickedNMSStack = CraftItemStack.asNMSCopy(inventory.getItem(event.getSlot()));

            if (clickedNMSStack.hasTag()) {
                uuid = UUID.fromString(clickedNMSStack.getTag().getCompound("org.bliffbot.servermanager.menusystem.menus.PlayerSelectMenu").getString("uuid"));

            } else {
                ItemStack offlinePlayerItem = inventory.getItem(event.getSlot());
                ArrayList<String> offlinePlayerLore = new ArrayList<>();
                offlinePlayerLore.add(ChatColor.RED + "An unexpected error occurred while getting the UUID of this player! Try again in a moment or reopen this menu.");
                offlinePlayerLore.add("");
                offlinePlayerLore.addAll(skullMeta.getLore());
                skullMeta.setLore(offlinePlayerLore);

                offlinePlayerItem.setItemMeta(skullMeta);
                inventory.setItem(event.getSlot(), offlinePlayerItem);
                return;
            }

            if (Bukkit.getPlayer(uuid) == null) {

                if (skullMeta.getLore().get(0).equals(ChatColor.RED + "This player could not be found and can therefore not be modified!")) return;
                ItemStack offlinePlayerItem = inventory.getItem(event.getSlot());

                ArrayList<String> offlinePlayerLore = new ArrayList<>();
                offlinePlayerLore.add(ChatColor.RED + "This player could not be found and can therefore not be modified!");
                offlinePlayerLore.add("");
                offlinePlayerLore.addAll(skullMeta.getLore());
                skullMeta.setLore(offlinePlayerLore);

                offlinePlayerItem.setItemMeta(skullMeta);
                inventory.setItem(event.getSlot(), offlinePlayerItem);

            } else if (!Bukkit.getPlayer(uuid).isOnline()) {
                if (skullMeta.getLore().get(0).equals(ChatColor.RED + "This player could not be found and can therefore not be modified!")) return;
                ItemStack offlinePlayerItem = inventory.getItem(event.getSlot());

                ArrayList<String> offlinePlayerLore = new ArrayList<>();
                offlinePlayerLore.add(ChatColor.RED + "This player could not be found and can therefore not be modified!");
                offlinePlayerLore.add("");
                offlinePlayerLore.addAll(skullMeta.getLore());
                skullMeta.setLore(offlinePlayerLore);

                offlinePlayerItem.setItemMeta(skullMeta);
                inventory.setItem(event.getSlot(), offlinePlayerItem);

            } else {
                playerMenuUtility.setSelectedPlayer(Bukkit.getPlayer(uuid));
                new PlayerMenu(playerMenuUtility).open();
            }
        }

        if (event.getSlot() == getSlots() - 9 && page > 0) {
            page = page - 1;
            super.open();
        }

        if (event.getSlot() == getSlots() - 8) {
            new MainMenu(playerMenuUtility).open();
        }

        if (event.getSlot() == getSlots() - 7) {
            new PlayerSelectFilterMenu(playerMenuUtility).open();
        }

        if (event.getSlot() == getSlots() - 1 && pageMax > page) {
            page = page + 1;
            super.open();
        }

    }

    @Override
    public void setMenuItems() {

        ArrayList<Player> players = new ArrayList<>(getServer().getOnlinePlayers());
        pageMax = (int) Math.ceil(players.size() / (double) maxItemsPerPage) - 1;

        if (!players.isEmpty()) {
            for (int slot = 0; slot < maxItemsPerPage; slot++) {
                index = maxItemsPerPage * page + slot;
                if (index >= players.size()) break;
                if (players.get(index) == null) break;

                ItemStack playerItem = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                SkullMeta playerMeta = (SkullMeta) playerItem.getItemMeta();

                playerMeta.setOwningPlayer(players.get(index));
                playerMeta.setDisplayName(ChatColor.YELLOW + players.get(index).getDisplayName());
                Location location = players.get(index).getLocation();

                ArrayList<String> playerLore = new ArrayList<>();
                playerLore.add(ChatColor.GRAY + "Operator: " + ChatColor.DARK_GRAY + players.get(index).isOp());
                playerLore.add(ChatColor.GRAY + "Gamemode: " + ChatColor.DARK_GRAY + players.get(index).getGameMode().name());
                playerLore.add(ChatColor.GRAY + "Health: " + ChatColor.DARK_GRAY + players.get(index).getHealth() + " / " + players.get(index).getHealthScale());
                playerLore.add(ChatColor.GRAY + "Food: " + ChatColor.DARK_GRAY + players.get(index).getFoodLevel());
                playerLore.add(ChatColor.GRAY + "Saturation: " + ChatColor.DARK_GRAY + players.get(index).getSaturation());
                playerLore.add(ChatColor.GRAY + "Remaining Air: " + ChatColor.DARK_GRAY + players.get(index).getRemainingAir());
                playerLore.add(ChatColor.GRAY + "Exp (Total Exp / Level): " + ChatColor.DARK_GRAY + players.get(index).getTotalExperience() + " / " + players.get(index).getLevel());
                playerLore.add(ChatColor.GRAY + "Location: " + ChatColor.DARK_GRAY + String.format("[X] %.3f", location.getX()) + String.format(" [Y] %.3f", location.getY()) + String.format(" [Z] %.3f", location.getZ()));
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
                playerLore.add("");
                playerLore.add(ChatColor.GRAY + "Online: " + ChatColor.DARK_GRAY + players.get(index).isOnline());
                playerLore.add(ChatColor.GRAY + "Flying: " + ChatColor.DARK_GRAY + players.get(index).isFlying());
                playerLore.add(ChatColor.GRAY + "Sleeping: " + ChatColor.DARK_GRAY + players.get(index).isSleeping());
                playerLore.add(ChatColor.GRAY + "Sneaking: " + ChatColor.DARK_GRAY + players.get(index).isSneaking());
                playerLore.add(ChatColor.GRAY + "Sprinting: " + ChatColor.DARK_GRAY + players.get(index).isSprinting());
                playerLore.add(ChatColor.GRAY + "Can See You: " + ChatColor.DARK_GRAY + players.get(index).canSee(playerMenuUtility.getOwner()));
                playerLore.add(ChatColor.GRAY + "Banned: " + ChatColor.DARK_GRAY + players.get(index).isBanned());
                playerLore.add(ChatColor.GRAY + "Blocking: " + ChatColor.DARK_GRAY + players.get(index).isBlocking());
                playerLore.add(ChatColor.GRAY + "Collidable: " + ChatColor.DARK_GRAY + players.get(index).isCollidable());
                playerLore.add(ChatColor.GRAY + "Conversing: " + ChatColor.DARK_GRAY + players.get(index).isConversing());
                playerLore.add(ChatColor.GRAY + "Dead: " + ChatColor.DARK_GRAY + players.get(index).isDead());
                playerLore.add(ChatColor.GRAY + "Gliding: " + ChatColor.DARK_GRAY + players.get(index).isGliding());
                playerLore.add(ChatColor.GRAY + "Glowing: " + ChatColor.DARK_GRAY + players.get(index).isGlowing());
                playerLore.add(ChatColor.GRAY + "Hand Raised: " + ChatColor.DARK_GRAY + players.get(index).isHandRaised());
                playerLore.add(ChatColor.GRAY + "Inside Vehicle: " + ChatColor.DARK_GRAY + players.get(index).isInsideVehicle());
                playerLore.add(ChatColor.GRAY + "Vehicle: " + ChatColor.DARK_GRAY + players.get(index).getVehicle());
                playerLore.add(ChatColor.GRAY + "Invulnerable: " + ChatColor.DARK_GRAY + players.get(index).isInvulnerable());
                playerLore.add(ChatColor.GRAY + "Leashed: " + ChatColor.DARK_GRAY + players.get(index).isLeashed());
                playerLore.add(ChatColor.GRAY + "On Ground: " + ChatColor.DARK_GRAY + players.get(index).isOnGround());
                playerLore.add(ChatColor.GRAY + "Silent: " + ChatColor.DARK_GRAY + players.get(index).isSilent());
                playerLore.add(ChatColor.GRAY + "Valid: " + ChatColor.DARK_GRAY + players.get(index).isValid());
                playerLore.add(ChatColor.GRAY + "Whitelisted: " + ChatColor.DARK_GRAY + players.get(index).isWhitelisted());
                playerLore.add(ChatColor.GRAY + "Fall Distance: " + ChatColor.DARK_GRAY + players.get(index).getFallDistance());
                playerLore.add(ChatColor.GRAY + "Velocity: " + ChatColor.DARK_GRAY + players.get(index).getVelocity());
                playerMeta.setLore(playerLore);

                playerItem.setItemMeta(playerMeta);

                net.minecraft.server.v1_12_R1.ItemStack playerNMSStack = CraftItemStack.asNMSCopy(playerItem);
                NBTTagCompound playerCompound = (playerNMSStack.hasTag()) ? playerNMSStack.getTag() : new NBTTagCompound();
                NBTTagCompound playerCustomTag = new NBTTagCompound();
                playerCustomTag.setString("uuid", players.get(index).getUniqueId().toString());
                playerCompound.set("org.bliffbot.servermanager.menusystem.menus.PlayerSelectMenu", playerCustomTag);
                playerNMSStack.setTag(playerCompound);
                ItemStack playerItemModified = CraftItemStack.asBukkitCopy(playerNMSStack);


                inventory.addItem(playerItemModified);
            }
        }

        if (page > 0) {
            inventory.setItem(getSlots() - 9, getBasicItem(Material.ARROW, ChatColor.WHITE + "Last Page"));
        }

        inventory.setItem(getSlots() - 8, getBasicItem(Material.SPECTRAL_ARROW, ChatColor.WHITE + "Go Back"));

        ItemStack filterItem = new ItemStack(Material.HOPPER, 1);
        ItemMeta filterMeta = filterItem.getItemMeta();
        filterMeta.setDisplayName(ChatColor.WHITE + "Filter");
        ArrayList<String> filterLore = new ArrayList<>();
        filterLore.add(ChatColor.GRAY + "Filter the list by the properties of the players");
        filterMeta.setLore(filterLore);
        filterItem.setItemMeta(filterMeta);
        inventory.setItem(getSlots() - 7, filterItem);

        ItemStack infoItem = new ItemStack(Material.PAPER, 1);
        ItemMeta infoMeta = infoItem.getItemMeta();
        infoMeta.setDisplayName(ChatColor.WHITE + "Info");
        ArrayList<String> infoLore = new ArrayList<>();
        infoLore.add(ChatColor.GRAY + "Change which properties you would like to see");
        infoMeta.setLore(infoLore);
        infoItem.setItemMeta(infoMeta);
        inventory.setItem(getSlots() - 6, infoItem);

        if (page < pageMax) {
            inventory.setItem(getSlots() - 1, getBasicItem(Material.ARROW, ChatColor.WHITE + "Next Page"));
        }

        for (int i = getSlots() - 9; i < getSlots(); i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, getFillerGlass());
            }
        }

    }
}