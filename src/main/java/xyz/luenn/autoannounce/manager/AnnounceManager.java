package xyz.luenn.autoannounce.manager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

import static xyz.luenn.autoannounce.MarpleAutoAnnounce.*;

public class AnnounceManager {

    private final JavaPlugin plugin;

    protected AnnounceManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void start() {

        final int[] index = {0};

        Runnable runnable = () -> {
            List<String> announcements = messages.getMessages("announce.announcements");
            if (messages == null || announcements.isEmpty()) {
                return;
            }

            announce(announcements.get(index[0]));
            index[0] = (index[0] >= announcements.size() - 1 ? 0 : index[0] + 1);
        };

        Bukkit.getScheduler().runTaskTimer(plugin, runnable, 0L, config.getInt("interval") * 20L);
    }

    private void announce(String message) {
        List<String> formatList = messages.getStringList("announce.format");
        for (String s : formatList) {
            Bukkit.broadcastMessage(s.replace("{announcement}", message));
        }
    }

    public void stop() {
        Bukkit.getScheduler().cancelTasks(plugin);
    }
}
