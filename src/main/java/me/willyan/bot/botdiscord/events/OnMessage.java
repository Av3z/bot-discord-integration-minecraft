package me.willyan.bot.botdiscord.events;

import me.willyan.bot.botdiscord.commands.*;
import me.willyan.bot.botdiscord.util.Message;
import me.willyan.bot.botdiscord.lib.ConfigManager;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public class OnMessage extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] args = e.getMessage().getContentRaw().split(" ");
        String[] arg = e.getMessage().getContentRaw().split("-");
        String author = e.getMessage().getAuthor().getAsMention();
        boolean bot = e.getMessage().getAuthor().isBot();
        boolean adm = e.getMember().hasPermission(Permission.ADMINISTRATOR);


        if (args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "say") || args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "falar") || args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "falar")) {
            Message.delete(e);

            if (bot) return;

            if (adm) {
                CommandSay.send(e, args);
                return;

            }
        }

        if (arg[0].equalsIgnoreCase(ConfigManager.get("prefix") + "embed")) {
            Message.delete(e);

            if (bot) return;

            if (adm) {

                if (arg.length == 2) {
                    CommandEmbed.send(e, arg[1]);
                    return;
                } else if (arg.length == 3) {
                    CommandEmbed.send(e, arg[1], arg[2]);
                    return;
                } else if (arg.length == 5) {
                    CommandEmbed.send(e, arg[1], arg[2], arg[3], arg[4]);
                    return;
                } else {
                    e.getChannel().sendMessage(ConfigManager.getWithPrefix("useOne")).queue();
                    e.getChannel().sendMessage(ConfigManager.getWithPrefix("use")).queue();
                    e.getChannel().sendMessage(ConfigManager.getWithPrefix("orUse")).queue();
                    return;
                }
            }

        }

        if (args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "avatar") || args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "icone")) {
            Message.delete(e);

            if (bot) return;

            if (args.length == 2) {
                String avatar = e.getMessage().getMentionedUsers().get(0).getAvatarUrl();
                String target = e.getMessage().getMentionedUsers().get(0).getName();
                CommandAvatar.send(e, avatar, target);
                return;
            } else {
                e.getChannel().sendMessage(ConfigManager.get("useAvatar"));
                return;
            }

        }

        if (args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "help") || args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "ajuda")) {
            Message.delete(e);

            if (bot) return;

            e.getChannel().sendMessage(ConfigManager.get("help")).queue();


        }

        if (args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "att")) {
            Message.delete(e);
            if (bot) return;

            if (adm) {
                CommandAtt.send(e, args);
                return;
            }

        }

        if (args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "ip")) {
            e.getChannel().sendMessage(ConfigManager.get("ip")).queue();
            return;
        }

        if (args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "loja")) {
            e.getChannel().sendMessage(ConfigManager.get("loja")).queue();
            return;
        }

        if (args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "prefix") || args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "prefixo")) {
            e.getChannel().sendMessage(ConfigManager.getWithPrefix("msgPrefix")).queue();
            return;
        }

        if (args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "sugerir")) {
            Message.delete(e);

            if (bot) return;

            CommandSugerir.send(e, "**-----------------------------------------------------------**");
            CommandSugerir.send(e,"ㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤ");
            CommandSugerir.send(e, "> **Sugestão do Membro: **" + author);
            CommandSugerir.send(e,"ㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤ");
            CommandSugerir.send(e, args);
            CommandSugerir.send(e,"ㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤ");
            CommandSugerir.send(e, "**-----------------------------------------------------------**");
        }


        if (args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "form")) {
            Message.delete(e);


            if(args.length == 2){

                if(args[1].equalsIgnoreCase( "mod") || args[1].equalsIgnoreCase("gc")){
                    Message.send(e, "> " + author);
                    Message.send(e, "**Link para o formulário de MOD-GC:** https://forms.gle/JS77HuCCr5XYgPPcA");
                    return;
                } else if (args[1].equalsIgnoreCase("ajudante") || args[1].equalsIgnoreCase("helper")){
                    Message.send(e, "> " + author);
                    Message.send(e, "**Link para o formulário de AJUDANTE:** https://forms.gle/NQb5Ect8YYnnyzm8A");
                    return;
                }

            } else {
                Message.send(e, ConfigManager.get("useForm"));
                return;
            }
        }

        if(args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "clear")){
            Message.delete(e);
            if(bot) return;
            if(adm){
                if(args.length == 2){
                    List<net.dv8tion.jda.api.entities.Message> messages = e.getChannel().getHistory().retrievePast(Integer.valueOf(args[1])).complete();
                    e.getChannel().deleteMessages(messages).queue();
                    Message.send(e, "> **Chat Limpo por:** " + author);
                    return;
                }
                else{
                    Message.send(e, ConfigManager.get("useClear"));
                    return;
                }
            }

        }

        if (args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "ticket")){
            Message.send(e, "> **Comando Ticket ainda está em desenvolvimento.** " + author);
        }

        if(args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "ping")){
            long time = System.currentTimeMillis();
            Message.send(e, ""+ (System.currentTimeMillis() - time) + " **ms**");
        }

    }
}
