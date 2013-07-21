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
package io.github.Eli112358.PluginBase;
import org.bukkit.plugin.java.JavaPlugin;
public class Plugin extends JavaPlugin {
	protected static Plugin instance = null;
	protected Base base;
	protected Plugin() {
		super();
		base = Base.getInstance();
		instance = this;
	}
	public Base getBase() {
		return base;
	}
	public static Plugin getInstance() {
		if(Base.plugin == null) instance = new Plugin();
		return instance;
	}
	@Override
	public void onDisable() {
		getBase().onDisable();
	}
	@Override
	public void onEnable() {
		getBase().onEnable();
	}
}
