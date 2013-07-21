package io.github.Eli112358.PluginBase;
import com.platymuus.bukkit.permissions.PermissionsPlugin;
import io.github.Eli112358.PluginBase.Command.BaseCommandExecutor;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Logger;
public class EliBase {
	protected static EliPlugin plugin = EliPlugin.getInstance();
	protected static EliBase base = EliBase.getInstance();
	protected HashMap<CommandSender, String> players = new HashMap<CommandSender, String>();
	protected HashMap<String, JavaPlugin> hooks = new HashMap<String, JavaPlugin>();
	private Version versions[] = Version.values();
	protected EliBase() {}
	public static EliBase getInstance() {
		if(base == null) new EliBase();
		assert base != null;
		base.extended("getInstance()", 0);
		return base;
	}
	public EliBase getBase() {
		extended("getBase()", 2);
		return base;
	}
	public EliPlugin getPlugin() {
		extended("getPlugin()", 2);
		return plugin;
	}
	public boolean enabled(CommandSender sender) {
		boolean isEnabled = players.containsKey(sender) || sender instanceof ConsoleCommandSender;
		if(!isEnabled) sender.sendMessage(getPluginPrefix() + "&cYou do not have permission for that.");
		return isEnabled;
	}
	public boolean isHooked(String name) {
		return hooks.get(name) == null;
	}
	public String getFullName() {
		extended("getFullName()", 1);
		return "Plugin Base";
	}
	public String getPluginPrefix() {
		extended("getPluginPrefix()", 1);
		return "Base: ";
	}
	public String getPluginName() {
		extended("getPluginName()", 1);
		return "PluginBase";
	}
	public Version[] getVersions() {
		return versions;
	}
	public void broadcastToAdmins(String msg) {
		getPlugin().getServer().broadcast(getPluginPrefix() + msg, Server.BROADCAST_CHANNEL_ADMINISTRATIVE);
	}
	public void displayVersions(CommandSender sender) {
		String ver;
		for(Version version : getVersions()) {
			ver = version.toString();
			if(sender instanceof ConsoleCommandSender) getPlugin().getLogger().info(ver);
			else sendMessage(sender, ver);
		}
	}
	public void error(Object a, String b, Exception e) {
		Logger logger = getPlugin().getLogger();
		broadcastToAdmins("&cInternal error! Someone please check the console.");
		logger.severe(String.format("There is a problem in %s!", b));
		logger.throwing(a.toString(), b, e.getCause());
		logger.severe("Stack trace of " + e);
		logger.severe(Arrays.toString(e.getStackTrace()));
	}
	public void onDisable() {
		getPlugin().getLogger().info("Shutting down plugin...");
		getPlugin().saveConfig();
		getPlugin().getLogger().info("Good bye.");
	}
	public void onEnable() {
		onEnableStart();
		onEnableEnd();
	}
	public void onEnableStart() {
		setupConfig();
		setupHooks();
		setupCmds();
		getOnlinePlayers();
	}
	public void onEnableEnd() {
		getPlugin().getLogger().info("I've been enabled!");
	}
	public void sendMessage(CommandSender sender, String msg) {
		sender.sendMessage(getPluginPrefix() + msg);
	}
	public void toggleVision(CommandSender sender, boolean show) {
		String msg;
		if(enabled(sender)) {
			players.remove(sender);
			msg = "has been disabled";
		} else {
			players.put(sender, "");
			msg = "has been enabled";
		}
		if(show) sendMessage(sender, getPluginPrefix() + " " + msg);
	}
	protected void addHook(String name) {
		getPlugin().getLogger().info("Attempting to hook " + name + "...");
		hooks.put(name, (PermissionsPlugin)getPlugin().getServer().getPluginManager().getPlugin(name));
		if(isHooked(name)) getPlugin().getLogger().info("Successfully hooked into " + name + ".");
		else getPlugin().getLogger().info("Failed to hook " + name + "!");
	}
	protected void getOnlinePlayers() {
		for(Player player : getPlugin().getServer().getOnlinePlayers()) toggleVision(player, false);
	}
	protected void setupCmds() {
		getPlugin().getCommand("Base").setExecutor(BaseCommandExecutor.getInstance());
		extended("setupCmds()", 1);
	}
	protected void setupConfig() {
		File configFile = new File(getPlugin().getDataFolder(), "config.yml");
		if(!configFile.exists() || configFile.length() == 0) getPlugin().saveResource(configFile.getName(), true);
		getPlugin().reloadConfig();
	}
	protected void setupHooks() {
		extended("setupHooks()", 1);
		//addHook("PluginName");
	}
	private void extended(String name, int cob) {
		String msg = "";
		Logger logger = getPlugin().getLogger();
		switch(cob) {
			case 0: msg = "casted"; break;
			case 1: msg = "overridden"; break;
			case 2: msg = "overridden and casted"; break;
			default:
		}
		if(!msg.isEmpty()) {
			logger.info(String.format("This method should be %s:", msg));
			logger.info(String.format("io.github.Eli112358.PluginBase.EliBase.%s", name));
		}
	}
}
