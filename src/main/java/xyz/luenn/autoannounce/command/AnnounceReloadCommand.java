package xyz.luenn.autoannounce.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import static xyz.luenn.autoannounce.MarpleAutoAnnounce.*;

public class AnnounceReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof ConsoleCommandSender) && !sender.hasPermission("autoannouncement.announce.reload")) {
            sender.sendMessage(messages.getMessage("errorMessages.noPermission"));
            return true;
        }

        messages.reloadConfig();
        config.reloadConfig();
        sender.sendMessage(messages.getMessage("messages.reload"));

        Bukkit.getScheduler().cancelTasks(getInstance());
        getAnnounceManager().start();

        return true;

    }
}
