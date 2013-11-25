package net.jimmy1248.suite1248;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class PlayerListener implements Listener{
        Suite1248 plugin;
        public PlayerListener(Suite1248 plugin) {
                this.plugin = plugin;
                plugin.getServer().getPluginManager().registerEvents(this,plugin);
                plugin.getLogger().info("PlayerListener");
                
        }
        
        
        @EventHandler
        public void PlayerJoinEvent(PlayerJoinEvent event){
                Player player = event.getPlayer();
                if(plugin.getConfig().getBoolean("deop on login")){
                        player.setOp(false);
                        player.sendMessage("You have been deopped");
                }
        }
}