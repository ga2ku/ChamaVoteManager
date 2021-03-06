package com.github.unchama.chamavotemanager;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.unchama.chamavotemanager.commands.csumCommand;
import com.github.unchama.chamavotemanager.commands.cvoteCommand;
import com.github.unchama.chamavotemanager.commands.cvotedeleteCommand;



public class ChamaVoteManager extends JavaPlugin implements Listener {
	public static FileConfiguration config;
	public static ChamaVoteManager plugin;
	private HashMap<String, TabExecutor> commandlist;

	public ChamaVoteManager() {

	}

	public void onEnable() {
		plugin = this;
		saveDefaultConfig();
		config = getConfig();

		getServer().getPluginManager().registerEvents(this, this);


		//コマンドの登録
		commandlist = new HashMap<String, TabExecutor>();
		commandlist.put("cvote",new cvoteCommand(plugin));
		commandlist.put("csum",new csumCommand(plugin));
		commandlist.put("cvotedelete",new cvotedeleteCommand(plugin));
	}

	public void onDisable() {
		saveConfig();
	}



	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		return commandlist.get(cmd.getName()).onCommand(sender, cmd, label, args);
	}
}
