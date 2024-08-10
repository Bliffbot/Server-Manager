package org.bliffbot.servermanager.menusystem;

import org.bukkit.entity.Player;

public class PlayerMenuUtility {

    private final Player owner;
    private Player selectedPlayer;

    public PlayerMenuUtility(Player p) {
        this.owner = p;
    }

    public Player getOwner() {
        return owner;
    }

    public Player getselectedPlayer() {
        return selectedPlayer;
    }

    public void setSelectedPlayer(Player selectedPlayer) {
        this.selectedPlayer = selectedPlayer;
    }

}