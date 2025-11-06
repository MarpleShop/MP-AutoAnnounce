package xyz.luenn.autoannounce;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.luenn.autoannounce.command.AnnounceCommand;
import xyz.luenn.autoannounce.command.AnnounceReloadCommand;
import xyz.luenn.autoannounce.manager.AnnounceAPI;
import xyz.luenn.autoannounce.manager.AnnounceManager;
import xyz.luenn.framework.data.Config;



public class MarpleAutoAnnounce extends JavaPlugin {

    @Getter
    private static MarpleAutoAnnounce instance;
    @Getter
    private static AnnounceManager announceManager;
    public static Config messages;
    public static Config config;

    @Override
    public void onEnable() {

        if (getServer().getPluginManager().getPlugin("MP-FrameWork") == null) {
            getLogger().warning("MP-FrameWork 플러그인이 적용되지 않았습니다! 플러그인을 비활성화합니다.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        if (getServer().getPluginManager().getPlugin("MP-AutoAnnouncePro") != null) {
            getLogger().warning("MP-AutoAnnouncePro 플러그인이 적용되어 있습니다! 플러그인을 비활성화합니다.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // INSTANCE
        instance = this;

        // CONFIG
        messages = new Config("messages", this);
        messages.setPrefix("prefix");
        messages.loadDefaultConfig();

        config = new Config("config", this);
        config.loadDefaultConfig();

        // ANNOUNCEMENT
        AnnounceAPI.init();

        announceManager = AnnounceAPI.get();
        announceManager.start();

        // COMMAND
        getServer().getPluginCommand("공지").setExecutor(new AnnounceCommand());
        getServer().getPluginCommand("공지리로드").setExecutor(new AnnounceReloadCommand());
    }

    @Override
    public void onDisable() {
        AnnounceAPI.get().stop();
    }
}
