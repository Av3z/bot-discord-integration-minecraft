package me.willyan.bot.botdiscord.commands;

import me.willyan.bot.botdiscord.util.Embed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class CommandAvatar {

    public static void send(GuildMessageReceivedEvent e, String url, String author){
        e.getChannel().sendMessage(Embed.createAvatar(url, author)).queue();
    }

}
