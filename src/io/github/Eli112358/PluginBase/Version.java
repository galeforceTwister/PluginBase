package io.github.Eli112358.PluginBase;
public enum Version {
	Base(0, 0, 0),
	Command(0, 0, 0),
	Plugin(0, 0, 0),
	Version(0, 0, 0);
	private int Major, Minor, Update;
	Version(int major, int minor, int update) {
		Major = major;
		Minor = minor;
		Update = update;
	}
	public int getMajor() {
		return Major;
	}
	public int getMinor() {
		return Minor;
	}
	public int getUpdate() {
		return Update;
	}
	@Override
	public String toString() {
		return String.format("%s: v%s.%s.%s", name(), getMajor(), getMinor(), getUpdate());
	}
}
