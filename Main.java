package fr.neirda.kit;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		saveDefaultConfig();
		getServer().getPluginManager().registerEvents(new Listerners(this), this);
		getCommand("getListforkit").setExecutor(new CommandsManager());
		getCommand("KitSelectorInfo").setExecutor(new CommandsManager());
		
	}

}
