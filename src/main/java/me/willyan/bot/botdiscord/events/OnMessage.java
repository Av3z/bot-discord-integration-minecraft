package me.willyan.bot.botdiscord.events;

import me.willyan.bot.botdiscord.commands.CommandEmbed;
import me.willyan.bot.botdiscord.commands.CommandSay;
import me.willyan.bot.botdiscord.lib.ConfigManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class OnMessage extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] args = e.getMessage().getContentRaw().split("\\s+");
        String[] arg = e.getMessage().getContentRaw().split("-");
        String author = e.getMessage().getAuthor().getName();
        boolean bot = e.getMessage().getAuthor().isBot();
        boolean adm = e.getMember().hasPermission(Permission.ADMINISTRATOR);



        if (args[0].equalsIgnoreCase(ConfigManager.prefix() + "say")) {
            CommandSay.delete(e);

            if (bot) return;

            if (adm) {
                if (e.getMessage().getContentRaw().startsWith(ConfigManager.prefix() + "say")) {
                    CommandSay.send(e, args);
                    return;
                }

            }
        }

        if (arg[0].equalsIgnoreCase(ConfigManager.prefix() + "embed")) {
            CommandSay.delete(e);

            if (bot) return;

            if (adm) {
                if (arg.length == 5) {
                    CommandEmbed.send(e, arg[1], arg[2], arg[3], arg[4]);
                    return;
                } else if (arg.length == 3) {
                    CommandEmbed.send(e, arg[1], arg[2]);
                    return;
                } else {
                    e.getChannel().sendMessage(ConfigManager.useReplaced()).queue();
                    e.getChannel().sendMessage(ConfigManager.orUseReplaced()).queue();
                    return;
                }
            }

        }

        if(args[0].equalsIgnoreCase(ConfigManager.prefix() + "info")){
            EmbedBuilder embed = new EmbedBuilder();
            Member member = e.getMessage().getMentionedMembers().get(0);
            String img = member.getUser().getAvatarUrl();
            String name = member.getUser().getName();
            String status = String.valueOf(member.getUser().getTimeCreated());
            embed.setTitle(name);
            embed.setImage(img);
            embed.setColor(Color.GREEN);
            embed.setDescription(status);

            e.getChannel().sendMessage(embed.build()).queue();
        }

    }

}
