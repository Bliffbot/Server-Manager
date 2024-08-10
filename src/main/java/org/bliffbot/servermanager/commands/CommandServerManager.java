package org.bliffbot.servermanager.commands;

import org.bliffbot.servermanager.Server_Manager;
import org.bliffbot.servermanager.menusystem.menus.MainMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandServerManager implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player) {
            new MainMenu(Server_Manager.getPlayerMenuUtility((Player) commandSender)).open();
        }

        return true;
    }
}