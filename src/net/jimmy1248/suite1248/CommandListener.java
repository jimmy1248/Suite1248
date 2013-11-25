package net.jimmy1248.suite1248;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CommandListener implements CommandExecutor{
        private Suite1248 plugin;
        
        public CommandListener(Suite1248 plugin) {
                this.plugin = plugin;
                plugin.getCommand("setspawn").setExecutor(this);
                plugin.getCommand("spawn").setExecutor(this);
                plugin.getCommand("bed").setExecutor(this);
                plugin.getCommand("op").setExecutor(this);
                plugin.getCommand("warp").setExecutor(this);
        }
        
        @Override
        public boolean onCommand(CommandSender sender, Command cmd, 
                        String label, String[] args) {
                //player only commands
                if(sender instanceof Player){
                        Player player = (Player) sender;
                        //spawn teleport
                        
                        if(cmd.getName().equalsIgnoreCase("spawn")){
                                new TeleportDelay(plugin, player, 
                                                player.getWorld().getSpawnLocation(),
                                                plugin.getConfig().getInt("teleport delay"), 
                                                "You have teleported to spawn");
                                return true;
                        }
                        
                        //bed teleport
                        if(cmd.getName().equalsIgnoreCase("bed")){
                                
                                Location bed = player.getBedSpawnLocation();
                                if(bed == null)
                                        player.sendMessage("Your bed cannot be found");
                                else{
                                        new TeleportDelay(plugin, player, bed,
                                                        plugin.getConfig().getInt("teleport delay"),
                                                        "You have been teleported to your bed.");
                                        
                                }                
                                return true;
                        }
                        
                        //op
                        if(cmd.getName().equalsIgnoreCase("op")){
                                if(plugin.getConfig().getBoolean("console op only"))
                                        player.sendMessage("Only console can op players.");
                                else 
                                        Op(args[0],sender);
                                return true;
                        }
                        
                        //warp
                        if(cmd.getName().equalsIgnoreCase("warp")){
                                int radius,xoffset,zoffset;
                                Location location;
                                Random rand = new Random();
                                World world = player.getWorld();
                                Location spawn = world.getSpawnLocation();
                                radius = plugin.getConfig().getInt("warp radius");
                                xoffset = rand.nextInt(radius*2)-radius;
                                zoffset = rand.nextInt(radius*2)-radius;
                                location = new Location(world,
                                                spawn.getX() + (double)xoffset,
                                                1,spawn.getZ() + (double)zoffset);
                                location.setY(location.getWorld().getHighestBlockYAt(location));
                                new TeleportDelay(plugin, player, location,
                                                plugin.getConfig().getInt("teleport delay"),
                                                "You have teleported to a random location.");
                                return true;
                        }
                        
                        
                        
                
                        
                //console command
                }else{
                        if(cmd.getName().equalsIgnoreCase("op") && args.length == 1){
                                Op(args[0],sender);
                                return true;
                        }
                        
                }
                
        return false;
        }
        private void Op(String name, CommandSender sender){
                Player player = plugin.getServer().getPlayer(name);
                player.setOp(true);
                sender.sendMessage("Opped " + player.getName());
        }
}