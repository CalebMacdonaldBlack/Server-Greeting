package gigabytedx;



import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin implements Listener{
	
	public final Logger logger = Logger.getLogger("Minecraft");
	public static Main plugin;
	String greeting;
	int x,y,z;
	
	
	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Has Been Disabled!");
	}

	@Override
	public void onEnable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " Has Been Enabled!");
		Bukkit.getPluginManager().registerEvents(this, this);
		this.saveDefaultConfig();
		greeting = this.getConfig().getString("greeting");
		x = this.getConfig().getInt("x");
		y = this.getConfig().getInt("y");
		z = this.getConfig().getInt("z");
	}
	
	@EventHandler
	public void onJoin(final PlayerJoinEvent e){
		e.setJoinMessage(ChatColor.DARK_GREEN + e.getPlayer().getName() + ChatColor.GOLD + " Has joined!");
		
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

			public void run() {
				e.getPlayer().sendMessage(ChatColor.GOLD + greeting);
				e.getPlayer().teleport(new Location(e.getPlayer().getWorld(), x, y, z));
				
			}
		},20);		
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e){
		e.setQuitMessage(ChatColor.DARK_GREEN + e.getPlayer().getName() + ChatColor.GOLD + " Has disconnected!");
	}
}
