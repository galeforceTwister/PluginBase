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
