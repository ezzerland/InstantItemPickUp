package ezzerland.ravenloftmc;

import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class instantitempickup extends JavaPlugin implements Listener
{
  public void onEnable() { getServer().getPluginManager().registerEvents(this, this); }
  public List<Integer> ignore;
  
  @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
  public void onPlayerDropItem (PlayerDropItemEvent e)
  { // If player drops item, add to ignore list, otherwise player can't drop item
    ignore.add(e.getItemDrop().getEntityId());
  }
  
  @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
  public void onItemSpawn(ItemSpawnEvent e)
  { // When an item spawns, set it's pick up delay to 0, unless we should ignore it
    if (ignore.contains(e.getEntity().getEntityId()))
    {
      ignore.remove(e.getEntity().getEntityId());
      return;
    }
    e.getEntity().setPickupDelay(0);
  }
}