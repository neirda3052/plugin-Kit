package fr.neirda.kit;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Listerners implements Listener {
private Main main;
	public Listerners(Main main) {
		this.main= main;
	}

	@EventHandler
	public void PlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if(event.getClickedBlock()==null) return;
		BlockState bs = event.getClickedBlock().getState();
		if(!(bs instanceof Sign))return;
		Sign sign =(Sign )bs;
		if(sign.getLine(0).equalsIgnoreCase("[KitSelector]")) {
			Inventory inv = Bukkit.createInventory(null, 54, "§cKitSelector");
			Map <String,Material> item = new HashMap<>();
			Material[] materialList= Material.values();
			for(Material material:materialList) {
				item.put(material.name(), material);// nom ; material
			}
			
			for(String stringa:main.getConfig().getConfigurationSection("kit").getKeys(false)) {
				
				for(Entry<String,Material> stringMaterial:item.entrySet()) {
					
					if(stringMaterial.getKey().toUpperCase().equals(main.getConfig().getString("kit."+stringa+".icon.id_item").toUpperCase())) {
						inv.setItem(main.getConfig().getInt("kit."+stringa+".icon.spot"), CreateItem(stringMaterial.getValue(), stringa,1));// pourrais aussi renseinger les items présent dans le kit...
						player.openInventory(inv);
						
					}
				}
					
					
							
						
			
		}
		
		}
	}
	
	@EventHandler
	public void InvClick(InventoryClickEvent event) {
		if(!(event.getWhoClicked()instanceof Player)) return;
		Player player = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		String invName = event.getView().getTitle();
		if(!(invName.equalsIgnoreCase("§cKitSelector"))) return;
		if(current== null)return;
		event.setCancelled(true);
		player.getInventory().clear();
		for(String string: main.getConfig().getConfigurationSection("kit").getKeys(false)){
			
			if(current.getItemMeta().getDisplayName().equals(string)) {
				
				Map <String,Material> item = new HashMap<>();
				Material[] materialList= Material.values();
				for(Material material:materialList) {
					item.put(material.name(), material);// nom ; material
					
				}
//				Map <String,Enchantment> enchant = new HashMap<>();
//				Enchantment[] enchantList = Enchantment.values();
//				for(Enchantment en:enchantList) {
//					enchant.put(en.getName(), en);
//				}
				
				for(Entry<String,Material> MaterialItem :item.entrySet()) {
					
					
					for(String stringinto: main.getConfig().getConfigurationSection("kit."+string+".items").getKeys(false)) {
						
						if(MaterialItem.getKey().toUpperCase().equals(main.getConfig().getString("kit."+string+".items."+stringinto+".id").toUpperCase())) {
							
							switch (stringinto) {
							case "helmet":
								 player.getInventory().setHelmet(CreateItem(MaterialItem.getValue(), "",1));
								 break;
							case "chestplate":
								 player.getInventory().setChestplate(CreateItem(MaterialItem.getValue(), "",1));
								 break;
							case "leggings":
								 player.getInventory().setLeggings(CreateItem(MaterialItem.getValue(), "",1));
								 break;
							case "boots":
								 player.getInventory().setBoots(CreateItem(MaterialItem.getValue(), "",1));
								 break;
							default:
								player.getInventory().setItem(main.getConfig().getInt("kit."+string+".items."+stringinto+".spot"), CreateItem(MaterialItem.getValue(), "",main.getConfig().getInt("kit."+string+".items."+stringinto+".amount")));
								//player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20*30, 3, false));
							}
							
							
					}
						
						if(MaterialItem.getKey().toUpperCase().equals(main.getConfig().getString("kit."+string+"."+stringinto)))
							
							player.getInventory().setItem(main.getConfig().getInt("kit."+string+".items."+stringinto+".spot"), null);
							
						
					}
					
				
				}
				Map <String,PotionEffectType> popo= new HashMap<>();
				PotionEffectType[] popoList = PotionEffectType.values();
				for(PotionEffectType po:popoList) {
					popo.put(po.getName(),po);
					
				}
				for(Entry<String,PotionEffectType> pop:popo.entrySet()) {
					
					for(String effect: main.getConfig().getConfigurationSection("kit."+string+".effects").getKeys(false)) {
						
						if(pop.getKey().toUpperCase().equals(main.getConfig().getString("kit."+string+".effects."+effect+".id").toUpperCase())) {
							
							player.addPotionEffect(new PotionEffect(pop.getValue(), 20*main.getConfig().getInt("kit."+string+".effects."+effect+".duration"), main.getConfig().getInt("kit."+string+".effects."+effect+".level"), false));
						}
						
						
					}
				}
				if(main.getConfig().getBoolean("kit."+string+".teleportation.teleportation_active")) {
					int x = main.getConfig().getInt("kit."+string+".teleportation.x");
					int y= main.getConfig().getInt("kit."+string+".teleportation.y");
					int z= main.getConfig().getInt("kit."+string+".teleportation.z");
					Location location = new Location(player.getWorld(), x, y, z);
					player.teleport(location);
				}
			}
			player.closeInventory();
		}
		
	
	}
	public ItemStack CreateItem(Material material, String name,int a){
		ItemStack item = new ItemStack(material,a);
		
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name.replace("&", "§"));
		item.setItemMeta(meta);
		
		return item;
	}
	
	
		
		
	}
	
