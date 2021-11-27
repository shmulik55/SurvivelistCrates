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
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CrateCommand implements CommandExecutor {

    List<Inventory> invs = new ArrayList<Inventory>();
    public static ItemStack[] contents;
    private int itemIndex = 0;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        String crateName;
        String titleColor;

        if(sender instanceof Player){
            Player player = (Player) sender;

            if(player.hasPermission("crates.create")){
                if(args.length == 0){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou must enter a name for the crate first!"));
                }else{
                    if(args[0].equalsIgnoreCase("vote")){

                        ItemStack voteCrate = new ItemStack(Material.CHEST);

                        ItemMeta voteCrate_Meta = voteCrate.getItemMeta();
                        voteCrate_Meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6&lVOTE CRATE"));
                        voteCrate.setItemMeta(voteCrate_Meta);

                        player.getInventory().addItem(voteCrate);
                    }
                }

            }

        }else{
            System.out.println("You must be a player to execute that command!");
        }

        return false;
    }

    public void shuffle(Inventory gui){

        if(contents == null){
            ItemStack[] items = new ItemStack[10];

            items[0] = new ItemStack(Material.DIAMOND, 3);
            items[1] = new ItemStack(Material.TRIPWIRE_HOOK, 2); // Vote Keys
            items[2] = new ItemStack(Material.GOLDEN_APPLE, 3);
            items[3] = new ItemStack(Material.ENDER_PEARL, 16);
            items[4] = new ItemStack(Material.TRIPWIRE_HOOK, 1); // Super vote key or whatever
            items[5] = new ItemStack(Material.EMERALD, 5);
            items[6] = new ItemStack(Material.TRIPWIRE_HOOK, 1); // Survivelist Key
            items[7] = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1);
            items[8] = new ItemStack(Material.SPONGE, 16);
            items[9] = new ItemStack(Material.SEA_LANTERN, 32);

            contents = items;

        }

        int StartingIndex = ThreadLocalRandom.current().nextInt(contents.length);

        for(int i = 0; i < StartingIndex; i++){
            for(int itemstacks = 9; itemstacks < 18; itemstacks++){
                gui.setItem(itemstacks, contents[(itemstacks + itemIndex) % contents.length]);
            }
            itemIndex++;
        }

        ItemStack pointer = new ItemStack(Material.REDSTONE_TORCH);
        ItemMeta meta = pointer.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&f"));
        pointer.setItemMeta(meta);
        gui.setItem(4, pointer);
    }

    public void spinGUI(final Player player){
        Inventory gui = Bukkit.createInventory(null, 27, "Survivelist Crates");
        shuffle(gui);
        invs.add(gui);
        player.openInventory(gui);

        Random r = new Random();
        double seconds = 7.0 + (12.0 - 7.0) * r.nextDouble();

        new BukkitRunnable(){
            double delay = 0;
            int ticks = 0;
            boolean done = false;

            public void run() {
                if(done)
                    return;
                ticks++;
                delay += 1 / (20 * seconds);
                if(ticks > delay * 10){
                    ticks = 0;

                    for(int itemstacks = 9; itemstacks < 18; itemstacks++)
                        gui.setItem(itemstacks, contents[(itemstacks + itemIndex) % contents.length]);

                    itemIndex++;

                    if(delay >= 0.5){
                        done = true;
                        new BukkitRunnable(){
                            public void run(){
                                ItemStack item = gui.getItem(13);
                                player.getInventory().addItem(item);
                                player.updateInventory();
                                player.closeInventory();
                                cancel();
                            }
                        }.runTaskLater(Main.getPlugin(Main.class), 50);
                        cancel();
                    }
                }
            }

        }.runTaskTimer(this, 0, 1);
    }
}
