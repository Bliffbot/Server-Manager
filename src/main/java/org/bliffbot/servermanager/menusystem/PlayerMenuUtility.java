package org.bliffbot.servermanager.menusystem;

import org.bukkit.entity.Player;

public class PlayerMenuUtility {

    private final Player owner;
    private Player selectedPlayer;
    private Boolean playerSelectShowSelfFirst = true;

    public PlayerMenuUtility(Player p) {
        this.owner = p;
    }

    public Player getOwner() {
        return owner;
    }

    public Player getSelectedPlayer() {
        return selectedPlayer;
    }

    public void setSelectedPlayer(Player selectedPlayer) {
        this.selectedPlayer = selectedPlayer;
    }

    public Boolean getPlayerSelectShowSelfFirst() {
        return playerSelectShowSelfFirst;
    }

    public void setPlayerSelectShowSelfFirst(Boolean playerSelectShowSelfFirst) {
        this.playerSelectShowSelfFirst = playerSelectShowSelfFirst;
    }
}