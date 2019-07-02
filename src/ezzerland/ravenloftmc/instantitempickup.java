package ezzerland.ravenloftmc;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class instantitempickup extends JavaPlugin implements Listener
{
  public void onEnable() { getServer().getPluginManager().registerEvents(this, this); }
  // When an item spawns, set it's pick up delay to 0.
  @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
  public void onItemSpawn(ItemSpawnEvent e) { e.getEntity().setPickupDelay(0); }
}