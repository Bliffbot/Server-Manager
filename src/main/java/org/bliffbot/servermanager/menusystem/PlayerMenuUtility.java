package org.bliffbot.servermanager.menusystem;

import org.bukkit.entity.Player;

public class PlayerMenuUtility {

    private final Player owner;
    private Player selectedPlayer;
    private Player selectedPlayerTest;

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

    public Player getSelectedPlayerTest() {
        return selectedPlayerTest;
    }

    public void setSelectedPlayerTest(Player selectedPlayerTest) {
        this.selectedPlayerTest = selectedPlayerTest;
    }
}