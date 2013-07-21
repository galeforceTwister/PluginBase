package io.github.Eli112358.PluginBase;
import org.bukkit.plugin.java.JavaPlugin;
public class EliPlugin extends JavaPlugin {
	protected static EliPlugin instance = null;
	protected EliBase base;
	protected EliPlugin() {
		super();
		base = EliBase.getInstance();
		instance = this;
	}
	public EliBase getBase() {
		return base;
	}
	public static EliPlugin getInstance() {
		if(EliBase.plugin == null) instance = new EliPlugin();
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
