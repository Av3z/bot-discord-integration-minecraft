package me.willyan.bot.botdiscord.lib;

import me.willyan.bot.botdiscord.core.Main;

public class ConfigManager {

    public static String noPerm(){
        String noPerm = Main.getPlugin(Main.class).getConfig().getString("bot.noPerm");

        return noPerm;
    }

    public static String say(String msg){
        String say = Main.getPlugin(Main.class).getConfig().getString("bot.say").replace("%msg%", msg);
        return say;
    }

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

    public static String use(){
        String use = Main.getPlugin(Main.class).getConfig().getString("bot.use");
        return use;
    }

    public static String useOne(){
        String use = Main.getPlugin(Main.class).getConfig().getString("bot.useOne");
        return use;
    }

    public static String orUse(){
        String orUse = Main.getPlugin(Main.class).getConfig().getString("bot.orUse");
        return orUse;
    }

    public static String useOneReplaced(){
        String useOne = Main.getPlugin(Main.class).getConfig().getString("bot.useOne").replace("<prefix>", prefix());
        return useOne;
    }

    public static String useReplaced(){
        String use = Main.getPlugin(Main.class).getConfig().getString("bot.use").replace("<prefix>", prefix());
        return use;
    }

    public static String orUseReplaced(){
        String orUse = Main.getPlugin(Main.class).getConfig().getString("bot.orUse").replace("<prefix>", prefix());
        return orUse;
    }

    public static String onEnable(){
        String enable = Main.getPlugin(Main.class).getConfig().getString("bot.onEnable");
        return enable;
    }

    public static String onDisable(){
        String disable = Main.getPlugin(Main.class).getConfig().getString("bot.onDisable");
        return disable;
    }

}
