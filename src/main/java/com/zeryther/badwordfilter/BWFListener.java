package com.zeryther.badwordfilter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class BWFListener implements Listener {

	public BWFListener() {
		Bukkit.getPluginManager().registerEvents(this, BWFPlugin.getInstance());
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		String msg = e.getMessage();
		
		boolean filter = false;
		for(String s : BWFPlugin.getInstance().getBadWords()){
			if(BWFPlugin.getInstance().containsIgnoreCase(msg, s)){
				filter = true;
			}
		}
		
		if(filter && !p.hasPermission(BWFPlugin.PERMISSION_EXEMPT)){
			e.setCancelled(true);
			
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', BWFPlugin.getInstance().getConfig().getString("prefix")) + ChatColor.translateAlternateColorCodes('&', BWFPlugin.getInstance().getConfig().getString("blocked")));
		    for (Player all : BWFPlugin.getInstance().getServer().getOnlinePlayers()) {
		    	if (all.hasPermission(BWFPlugin.PERMISSION_SEE_BADWORDED_MESSAGES)) {
		    		all.sendMessage(ChatColor.translateAlternateColorCodes('&', BWFPlugin.getInstance().getConfig().getString("prefix")) + p.getName() + ChatColor.DARK_GRAY + ": " + ChatColor.WHITE + msg);
		    	}
		    }
		}
	}

}
