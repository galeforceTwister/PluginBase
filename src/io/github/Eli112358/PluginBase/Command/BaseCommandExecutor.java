/*
Copyright 2013 Luke Turner

This file is part of PluginBase.

PluginBase is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

PluginBase is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with PluginBase.  If not, see <http://www.gnu.org/licenses/>.
*/
package io.github.Eli112358.PluginBase.Command;
import io.github.Eli112358.PluginBase.Base;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.logging.Logger;
public class BaseCommandExecutor implements CommandExecutor {
	private static BaseCommandExecutor instance;
	protected ArrayList<String> aliases = new ArrayList<String>();
	protected Base base = Base.getInstance();
	protected String name = "cmd";
	private String method = "";
	protected BaseCommandExecutor() {}
	public static BaseCommandExecutor getInstance() {
		if(instance == null) instance = new BaseCommandExecutor();
		instance.extended("getInstance()", 0);
		return instance;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
		getBase().getPlugin().getLogger().info("s: " + s);
		extended("onCommand(CommandSender, Command, String, String[])", 1);
		return true;
	}
	public String getName() {
		return name;
	}
	public Base getBase() {
		extended("getBase()", 2);
		return base;
	}
	protected void oops(CommandSender sender) {
		getBase().sendMessage(sender, "I'm sorry, but I couldn't understand what you wanted.");
	}
	protected void setName(String a) {
		name = a;
	}
	protected void stats(CommandSender sender) {
		getBase().sendMessage(sender, "stats");
	}
	protected void help(CommandSender sender, String subCmd) {
		extended("help(CommandSender, String)", 1);
	}
	private void extended(String name, int cob) {
		String msg = "";
		Logger logger = getBase().getPlugin().getLogger();
		switch(cob) {
			case 0: msg = "casted"; break;
			case 1: msg = "overridden"; break;
			case 2: msg = "overridden and casted"; break;
			default:
		}
		if(!msg.isEmpty()) {
			logger.info(String.format("This method should be %s:", msg));
			logger.info(String.format("io.github.Eli112358.PluginBase.Command.BaseCommandExecutor.%s", name));
		}
	}
}
