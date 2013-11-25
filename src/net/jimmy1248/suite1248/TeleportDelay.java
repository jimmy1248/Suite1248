package net.jimmy1248.suite1248;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TeleportDelay extends BukkitRunnable{
        private Location location;
        private Player player;
        private String message;  
        
        public TeleportDelay(Suite1248 plugin,Player player,Location location,
                        long delay, String message) {
                this.location = location;
                this.player = player;
                this.message = message;
                player.sendMessage("You will be teleported in " + delay + " seconds");
                this.runTaskLater(plugin,delay*20);
        }
        @Override
        public void run() {
                player.teleport(location);
                player.sendMessage(message);
                
        }

}