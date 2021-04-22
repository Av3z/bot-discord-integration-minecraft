package me.willyan.bot.botdiscord.commands;

import me.willyan.bot.botdiscord.util.AllArgs;
import me.willyan.bot.botdiscord.util.Embed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class CommandAtt {

    public static void send(GuildMessageReceivedEvent e, String[] msg){
        String result = AllArgs.build(1, msg);
        e.getChannel().sendMessage(Embed.createAtt(result)).queue();
    }
}
