package xyz.luenn.autoannounce.command;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import xyz.luenn.framework.util.ColorUtil;

import static xyz.luenn.autoannounce.MarpleAutoAnnounce.config;
import static xyz.luenn.autoannounce.MarpleAutoAnnounce.messages;


public class AnnounceCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof ConsoleCommandSender) && !sender.hasPermission("autoannouncement.announce.announce")) {
            sender.sendMessage(messages.getMessage("errorMessages.noPermission"));
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(messages.getMessage("errorMessages.noContent"));
            return true;
        }

        String message = String.join(" ", args);
        messages.getStringList("announce.format").forEach(s -> {
            Bukkit.broadcastMessage(s.replace("{announcement}", ColorUtil.color(messages.getString("prefix")) + message));
        });

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player.getLocation(), Sound.valueOf(config.getString("sound.sound").toUpperCase()), SoundCategory.MASTER, config.getFloat("sound.volume"), config.getFloat("sound.pitch"));
        }

        return true;

    }
}
