package me.willyan.bot.botdiscord.commands;

import me.willyan.bot.botdiscord.util.AllArgs;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class CommandMsg {

    public static void send(GuildMessageReceivedEvent e, String[] args){
        String msg = AllArgs.build(1, args);
        e.getChannel().sendMessage(msg).complete();
    }

    public static void delete(GuildMessageReceivedEvent e){
        String id = e.getChannel().getLatestMessageId();
        e.getChannel().deleteMessageById(id).complete();
    }


}
