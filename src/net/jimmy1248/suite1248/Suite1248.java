package net.jimmy1248.suite1248;

import org.bukkit.plugin.java.JavaPlugin;

public class Suite1248 extends JavaPlugin{
        @Override
        public void onEnable() {
                this.saveDefaultConfig();

                new PlayerListener(this);
                new CommandListener(this);
                
                getLogger().info("Easy Spawn Plus has Loaded");
                
        }
        @Override
        public void onDisable() {
        }
}