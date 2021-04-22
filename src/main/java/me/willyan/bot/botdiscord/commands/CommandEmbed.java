package me.willyan.bot.botdiscord.commands;

import me.willyan.bot.botdiscord.util.Embed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class CommandEmbed {

    public static void send(GuildMessageReceivedEvent e, String title){
        e.getChannel().sendMessage(Embed.create(title)).complete();
    }

    public static void send(GuildMessageReceivedEvent e, String title, String desc, String field, String value){
        e.getChannel().sendMessage(Embed.create(title, desc, field, value)).complete();
    }

    public static void send(GuildMessageReceivedEvent e, String title, String desc){
        e.getChannel().sendMessage(Embed.create(title, desc)).complete();
    }


}
