package xyz.luenn.template;

import org.bukkit.plugin.java.JavaPlugin;

public final class MarpleTemplate extends JavaPlugin {

    private static MarpleTemplate instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static MarpleTemplate getInstance() {
        return instance;
    }
}
