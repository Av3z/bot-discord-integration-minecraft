package me.willyan.bot.botdiscord.commands;

import me.willyan.bot.botdiscord.util.Embed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class CommandAvatar {

    public static void send(GuildMessageReceivedEvent e, String url){
        e.getChannel().sendMessage(Embed.createAvatar(url)).queue();
    }

}
