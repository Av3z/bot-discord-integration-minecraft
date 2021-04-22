package me.willyan.bot.botdiscord.util;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class Message {

    public static void delete(GuildMessageReceivedEvent e){
        String id = e.getChannel().getLatestMessageId();
        e.getChannel().deleteMessageById(id).complete();
    }

    public static void send(GuildMessageReceivedEvent e, String msg){
        e.getChannel().sendMessage(msg).queue();
    }
}
