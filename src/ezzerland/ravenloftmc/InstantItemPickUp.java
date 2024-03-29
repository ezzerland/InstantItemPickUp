package ezzerland.ravenloftmc;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class InstantItemPickUp extends JavaPlugin implements Listener
{
  public List<Integer> ignore;
  public void onEnable()
  {
    getServer().getPluginManager().registerEvents(this, this);
    ignore = new ArrayList<Integer>();
  }
  
  @EventHandler(ignoreCancelled = false, priority = EventPriority.HIGHEST)
  public void onPlayerDropItem (PlayerDropItemEvent e)
  { // If player drops item, add to ignore list, otherwise player can't drop item
    ignore.add(Integer.valueOf(e.getItemDrop().getEntityId()));
  }
  
  @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
  public void onItemSpawn(ItemSpawnEvent e)
  { // When an item spawns, set it's pick up delay to 0, unless we should ignore it
    if (ignore.contains(Integer.valueOf(e.getEntity().getEntityId())))
    {
      ignore.remove(Integer.valueOf(e.getEntity().getEntityId()));
      return;
    }
    e.getEntity().setPickupDelay(0);
  }
}