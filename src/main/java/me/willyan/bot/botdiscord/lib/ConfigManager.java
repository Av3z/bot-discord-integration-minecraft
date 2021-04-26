package me.willyan.bot.botdiscord.lib;

import me.willyan.bot.botdiscord.core.Main;

public class ConfigManager {

    public static final String TOKEN = "NzM4NjI0MTI0NjA3NTI5MDAw.XyOnUg.dHCSmbelDPiWttUGS6OxbO0ojng";

    public static final String CATEGORY = get("bot.category");

    public static String get(String path){
        String result = Main.getPlugin(Main.class).getConfig().getString("bot."+path);
        return result;
    }

    public static String get(String path, String old, String newParam){
        String result = Main.getPlugin(Main.class).getConfig().getString("bot."+path).replace(old, newParam);
        return result;
    }

    public static String getWithPrefix(String path){
        String result = Main.getPlugin(Main.class).getConfig().getString("bot."+path).replace("<prefix>", get("prefix"));
        return result;
    }


}
