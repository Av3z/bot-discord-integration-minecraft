package me.willyan.bot.botdiscord.events;

import me.willyan.bot.botdiscord.commands.CommandEmbed;
import me.willyan.bot.botdiscord.commands.CommandSay;
import me.willyan.bot.botdiscord.lib.ConfigManager;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class OnMessage extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] args = e.getMessage().getContentRaw().split("\\s+");
        String[] arg = e.getMessage().getContentRaw().split("-");

        if (args[0].equalsIgnoreCase(ConfigManager.prefix() + "say")) {
            e.getChannel().sendTyping().queue();
            if (e.getMessage().getContentRaw().startsWith(ConfigManager.prefix() + "say")) {
                CommandSay.delete(e);
                CommandSay.send(e, args);
            }

        }

        if (arg[0].equalsIgnoreCase(ConfigManager.prefix() + "embed")) {
            e.getChannel().sendTyping().queue();
            if (arg.length == 5) {
                CommandSay.delete(e);
                CommandEmbed.send(e, arg[1], arg[2], arg[3], arg[4]);
            } else if (arg.length == 3) {
                CommandSay.delete(e);
                CommandEmbed.send(e, arg[1], arg[2]);
            } else {
                CommandSay.delete(e);
                e.getChannel().sendMessage(ConfigManager.useReplaced()).queue();
                e.getChannel().sendMessage(ConfigManager.orUseReplaced()).queue();
            }


        }

    }

}
