package me.willyan.bot.botdiscord.lib;

import me.willyan.bot.botdiscord.core.Main;

public class ConfigManager {

    public static String prefix(){
        String prefix = Main.getPlugin(Main.class).getConfig().getString("bot.prefix");
        return prefix;
    }

    public static String playing(){
        String playing = Main.getPlugin(Main.class).getConfig().getString("bot.playing");
        return playing;
    }

    public static String copyRight(){
        String copy = Main.getPlugin(Main.class).getConfig().getString("bot.copyRight");
        return copy;
    }

}
