package me.willyan.bot.botdiscord.events;

import me.willyan.bot.botdiscord.commands.CommandAtt;
import me.willyan.bot.botdiscord.commands.CommandAvatar;
import me.willyan.bot.botdiscord.commands.CommandEmbed;
import me.willyan.bot.botdiscord.commands.CommandSay;
import me.willyan.bot.botdiscord.util.Message;
import me.willyan.bot.botdiscord.lib.ConfigManager;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class OnMessage extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] args = e.getMessage().getContentRaw().split("\\s+");
        String[] arg = e.getMessage().getContentRaw().split("-");
        boolean bot = e.getMessage().getAuthor().isBot();
        boolean adm = e.getMember().hasPermission(Permission.ADMINISTRATOR);



        if (args[0].equalsIgnoreCase(ConfigManager.prefix() + "say")) {
            Message.delete(e);

            if (bot) return;

            if (adm) {
                if (e.getMessage().getContentRaw().startsWith(ConfigManager.prefix() + "say")) {
                    CommandSay.send(e, args);
                    return;
                }

            }
        }

        if (arg[0].equalsIgnoreCase(ConfigManager.prefix() + "embed")) {
            Message.delete(e);

            if (bot) return;

            if (adm) {

                if(arg.length == 2){
                    CommandEmbed.send(e, arg[1]);
                    return;
                }
                else if (arg.length == 3) {
                    CommandEmbed.send(e, arg[1], arg[2]);
                    return;
                }
                else if (arg.length == 5) {
                    CommandEmbed.send(e, arg[1], arg[2], arg[3], arg[4]);
                    return;
                }
                else {
                    e.getChannel().sendMessage(ConfigManager.useOneReplaced()).queue();
                    e.getChannel().sendMessage(ConfigManager.useReplaced()).queue();
                    e.getChannel().sendMessage(ConfigManager.orUseReplaced()).queue();
                    return;
                }
            }

        }

        if(args[0].equalsIgnoreCase(ConfigManager.prefix() + "avatar")){
            Message.delete(e);

            if(bot) return;

            if(args.length == 2){
                String avatar = e.getMessage().getMentionedUsers().get(0).getAvatarUrl();
                CommandAvatar.send(e,avatar);
            }else {
                e.getChannel().sendMessage(ConfigManager.useAvatar());
            }

        }

        if(args[0].equalsIgnoreCase(ConfigManager.prefix() + "help") || args[0].equalsIgnoreCase(ConfigManager.prefix() + "ajuda")){
            Message.delete(e);

            if(bot) return;

            e.getChannel().sendMessage("> **[!say !embed !help]**").queue();


        }

        if(args[0].equalsIgnoreCase(ConfigManager.prefix() + "att")){
            Message.delete(e);
            if(bot) return;

            if(adm) {
                CommandAtt.send(e, args);
            }

        }

        if(args[0].equalsIgnoreCase(ConfigManager.prefix() + "ip")){
            e.getChannel().sendMessage(ConfigManager.getIp()).queue();
        }

        if(args[0].equalsIgnoreCase(ConfigManager.prefix() + "loja")){
            e.getChannel().sendMessage(ConfigManager.getShop()).queue();
        }

        if(args[0].equalsIgnoreCase(ConfigManager.prefix() + "prefix")){
            e.getChannel().sendMessage(ConfigManager.getMsgPrefix()).queue();
        }


    }

}
