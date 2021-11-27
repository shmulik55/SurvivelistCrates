package survivelist.itzshmulik.survivelistcrates.Events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import sun.jvm.hotspot.oops.Metadata;

public class OnlyOnCrate implements Listener {

    @EventHandler
    public void OnClick(PlayerInteractEvent e){
            Player player = (Player) e.getPlayer();
            Block block = e.getClickedBlock();

            if(e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

            if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
                if(e.getClickedBlock().getType() == Material.CHEST){
                    Chest chestState = (Chest) block.getState();
                }
            }
    }
}
