package survivelist.itzshmulik.survivelistcrates;

import org.bukkit.plugin.java.JavaPlugin;
import survivelist.itzshmulik.survivelistcrates.Commands.CrateCommand;
import survivelist.itzshmulik.survivelistcrates.Commands.KeyCommand;

public final class SurvivelistCrates extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getCommand("sck").setExecutor(new KeyCommand());
        getCommand("scc").setExecutor(new CrateCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
