package me.willyan.bot.botdiscord.lib;

import me.willyan.bot.botdiscord.core.Main;

public class ConfigManager {

    public static String get(String path){
        return Main.getPlugin(Main.class).getConfig().getString("bot."+path);
    }

    public static String get(String path, String old, String newParam){
        return Main.getPlugin(Main.class).getConfig().getString("bot."+path).replace(old, newParam);
    }

    public static String getWithPrefix(String path){
        return Main.getPlugin(Main.class).getConfig().getString("bot."+path).replace("<prefix>", get("prefix"));
    }


}
