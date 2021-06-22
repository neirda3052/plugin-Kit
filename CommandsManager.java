package fr.neirda.kit;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class CommandsManager implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(cmd.getName().equalsIgnoreCase("getListforkit")) {
			if(!(sender instanceof Player)) return false;
			Player player = (Player )sender;
			if(args.length==1) {
				if(args[0].equalsIgnoreCase("potionList")) {
					PotionEffectType[] popoList = PotionEffectType.values();
					System.out.println();
					System.out.println("//// List of potion Effect: ");
					for(PotionEffectType po:popoList) {
						System.out.println(po.getName());
						
					}
					player.sendMessage("§4[kitSelector]§c check Console to watch the list of potionEffect name ");
				}else if(args[0].equalsIgnoreCase("ItemList")) {
					Material[] materialList= Material.values();
					System.out.println();
					System.out.println("//// List of Item name: ");
					for(Material material:materialList) {
						System.out.println(material.name());
					}
					player.sendMessage("§4[kitSelector]§c check Console to watch the list of item name ");
				}else {
					player.sendMessage("§4[kitSelector]§c error argument: potionList or ItemList");
				}
				
			}else {
				player.sendMessage("§4[kitSelector]§c missing argument: potionList or ItemList");
			}
		}
		if(cmd.getName().equalsIgnoreCase("KitSelectorInfo")) {
			if(sender instanceof Player) {
				
			
				Player player = (Player)sender;
				player.sendMessage("§5 ///////////////////");
				player.sendMessage("§a author is Neirda3052");
				player.sendMessage("§c version: 2.0");// oublie pas la version là!
				player.sendMessage("§5 ///////////////////");
			}
		}
		return false;
	}

}
