package me.willyan.bot.botdiscord.commands;

import me.willyan.bot.botdiscord.lib.ConfigManager;
import me.willyan.bot.botdiscord.util.AllArgs;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class CommandSugerir {

    public static void send(GuildMessageReceivedEvent e, String[] args){
        String msg = AllArgs.build(1, args);
        e.getJDA().getTextChannelById(ConfigManager.getChannelSugerir()).sendMessage(msg).queue();
    }

    public static void send(GuildMessageReceivedEvent e, String msg){
        e.getJDA().getTextChannelById(ConfigManager.getChannelSugerir()).sendMessage(msg).queue();
    }

}
