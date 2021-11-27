package survivelist.itzshmulik.survivelistcrates.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class KeyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("crates.key")) {
                if (args.length == 0) {

                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have to write the type of key you wish to get!"));

                } else if (args.length == 1) {

                    if (args[0].equalsIgnoreCase("vote")) {

                        ItemStack voteKey = new ItemStack(Material.TRIPWIRE_HOOK);

                        ItemMeta vote_meta = voteKey.getItemMeta();
                        vote_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&e&lVote Key"));
                        voteKey.setItemMeta(vote_meta);

                        player.getInventory().addItem(voteKey);

                    }
                }else{

                    if (args[0].equalsIgnoreCase("vote")) {

                        ItemStack voteKey = new ItemStack(Material.TRIPWIRE_HOOK);

                        ItemMeta vote_meta = voteKey.getItemMeta();
                        vote_meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lVote Key"));
                        voteKey.setItemMeta(vote_meta);

                        Player target = Bukkit.getPlayerExact(args[1]);
                        if(target instanceof Player){
                            target.getInventory().addItem(voteKey);
                        }else{
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThat player does not exist!"));
                        }
                    }
                }
            }
        }
        return false;
    }
}