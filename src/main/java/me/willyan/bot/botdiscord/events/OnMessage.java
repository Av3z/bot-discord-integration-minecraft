package me.willyan.bot.botdiscord.events;

import me.willyan.bot.botdiscord.commands.*;
import me.willyan.bot.botdiscord.lib.ConfigManager;
import me.willyan.bot.botdiscord.lib.OsManager;
import me.willyan.bot.botdiscord.util.Embed;
import me.willyan.bot.botdiscord.util.IMessage;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

public class OnMessage extends ListenerAdapter {



    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {

        String[] args = e.getMessage().getContentRaw().split(" ");
        String[] arg = e.getMessage().getContentRaw().split("-");
        String author = e.getMessage().getAuthor().getAsMention();
        boolean bot = e.getMessage().getAuthor().isBot();
        boolean adm = Objects.requireNonNull(e.getMember()).hasPermission(Permission.ADMINISTRATOR);
        Guild guild = e.getGuild();
        Member member = e.getMember();


        if(args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "host")){

            if(args.length == 3){
                int seconds = Integer.parseInt(args[2]);

                if(args[1].equalsIgnoreCase("memory")){
                    for(int i = 0; i < seconds; i++){
                        e.getChannel().sendMessage("Sua host está usando: **" + OsManager.getMemory() + "** MBs").queue();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    }
                } else if(args[1].equalsIgnoreCase("cpu")) {
                    for(int i = 0; i < seconds; i++){
                        e.getChannel().sendMessage("A CPU da sua host está usando: **" + OsManager.getCpu()  + "**%").queue();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    }
                }
            } else {
                IMessage.send(e, "> Por favor use "+ConfigManager.get("prefix")+"host <cpu / memory> <tempo>");
            }
        }


        if (args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "captcha")){
            IMessage.delete(e);

            if (bot) return;

            if(adm){
                TextChannel textChannel = e.getGuild().getTextChannelById(ConfigManager.get("captcha.channel"));

                textChannel.sendMessage(Embed.create("Sistema de Segurança",
                        "Por favor siga os passos para ser redirecionado para o discord, " +
                                "no momento você está em uma aba que mantem o nosso discord seguro contra bots.",
                        "Como concluir a etapa de segurança ?", "Para concluir o passo de verificação basta apenas clicar no emoji abaixo ✅")).complete().addReaction("✅").queue();
                return;
            }

            IMessage.send(e, ConfigManager.get("noPerm", "<user>", author));
        }


        if (args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "say") || args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "falar") || args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "falar")) {
            IMessage.delete(e);

            if (bot) return;

            if (adm) {
                CommandSay.send(e, args);
                return;
            }

            IMessage.send(e, ConfigManager.get("noPerm", "<user>", author));
        }

        if (arg[0].equalsIgnoreCase(ConfigManager.get("prefix") + "embed")) {
            IMessage.delete(e);

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

            IMessage.send(e, ConfigManager.get("noPerm", "<user>", author));

        }

        if (args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "avatar") || args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "icone")) {
            IMessage.delete(e);

            if (bot) return;

            if (args.length == 2) {
                String avatar = e.getMessage().getMentionedUsers().get(0).getAvatarUrl();
                String target = e.getMessage().getMentionedUsers().get(0).getName();
                CommandAvatar.send(e, avatar, target);
                return;
            }

            IMessage.send(e, ConfigManager.get("useAvatar", "<user>", author));

        }

        if (args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "help") || args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "ajuda")) {
            IMessage.delete(e);

            if (bot) return;

            e.getChannel().sendMessage(ConfigManager.get("help")).queue();


        }

        if (args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "att")) {
            IMessage.delete(e);
            if (bot) return;

            if (adm) {
                CommandAtt.send(e, args);
                return;
            }

            IMessage.send(e, ConfigManager.get("useAvatar", "<user>", author));

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
            IMessage.delete(e);

            if (bot) return;

            CommandSugerir.send(e, "**-----------------------------------------------------------**");
            CommandSugerir.send(e, "ㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤ");
            CommandSugerir.send(e, "> **Sugestão do Membro: **" + author);
            CommandSugerir.send(e, "ㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤ");
            CommandSugerir.send(e, args);
            CommandSugerir.send(e, "ㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤ");
            CommandSugerir.send(e, "**-----------------------------------------------------------**");
        }


        if (args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "form")) {
            IMessage.delete(e);


            if (args.length == 2) {

                if (args[1].equalsIgnoreCase("mod") || args[1].equalsIgnoreCase("gc")) {
                    IMessage.send(e, "> " + author);
                    IMessage.send(e, "**Link para o formulário de MOD-GC:** https://forms.gle/JS77HuCCr5XYgPPcA");
                    return;
                } else if (args[1].equalsIgnoreCase("ajudante") || args[1].equalsIgnoreCase("helper")) {
                    IMessage.send(e, "> " + author);
                    IMessage.send(e, "**Link para o formulário de AJUDANTE:** https://forms.gle/NQb5Ect8YYnnyzm8A");
                    return;
                }

            } else {
                IMessage.send(e, ConfigManager.get("useForm"));
                return;
            }
        }

        if (args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "clear")) {
            IMessage.delete(e);
            if (bot) return;
            if (adm) {
                if(args.length == 2) {
                    int total = Integer.parseInt(args[1]);
                    List<Message> messages = e.getChannel().getHistory().retrievePast(total).complete();
                    e.getChannel().deleteMessages(messages).queue();
                    IMessage.send(e, "> **Chat Limpo por:** " + author);
                } else {
                    IMessage.send(e, ConfigManager.get("useClear"));
                }
            }

        }

        if (args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "ticket")) {
            IMessage.delete(e);

            if (bot) return;


            if (args[1].equalsIgnoreCase("create") || args[1].equalsIgnoreCase("criar")) {

                for (TextChannel tChannel : guild.getTextChannels()) {
                    if (tChannel.getName().equalsIgnoreCase("ticket-" + member.getId())) {
                        IMessage.send(e, "" + ConfigManager.get("ticketIsOpened", "<user>", member.getAsMention()));
                        return;
                    }
                }

                Category category = guild.getCategoryById(ConfigManager.get("category"));

                TextChannel textChannel = guild.createTextChannel("ticket-" + member.getId(), category)
                        .addPermissionOverride(member, EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_WRITE, Permission.MESSAGE_READ, Permission.MESSAGE_HISTORY, Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_ATTACH_FILES, Permission.MESSAGE_ADD_REACTION, Permission.MESSAGE_EXT_EMOJI), null)
                        .addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                        .addPermissionOverride(Objects.requireNonNull(guild.getRoleById(ConfigManager.get("idRoleSuporte"))), EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_WRITE, Permission.MESSAGE_READ, Permission.MESSAGE_HISTORY, Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_ATTACH_FILES, Permission.MESSAGE_ADD_REACTION, Permission.MESSAGE_EXT_EMOJI), null)
                        .complete();

                textChannel.sendMessage("" + ConfigManager.get("ticketSuccess", "<user>", member.getAsMention())).queue();
                textChannel.sendMessage("" + ConfigManager.get("ticketSuccess2", "<suporte>" , Objects.requireNonNull(guild.getRoleById(ConfigManager.get("idRoleSuporte"))).getAsMention())).queue();
                textChannel.sendMessage("" + ConfigManager.getWithPrefix("ticketSuccess3")).queue();

            }

            if (args[1].equalsIgnoreCase("ok")) {

                List<TextChannel> ticket = guild.getTextChannelsByName("ticket-"+member.getId(), true);

                if(ticket.size() > 0){
                    member.getUser().openPrivateChannel().queue(channel ->
                            channel.sendMessage("" + ConfigManager.get("ticketSolved", "<user>", author)).queue());

                    ticket.get(0).delete().queue();
                } else {
                    IMessage.send(e, "" + ConfigManager.get("noTickets", "<user>", member.getAsMention()));
                }

            }

        }

        if (args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "ping")) {
            e.getJDA().getRestPing().queue(
                    (ping) -> e.getChannel()
                    .sendMessageFormat("> " + author + " Você está com %s **ms**", ping).queue()
            );
        }

    }
}
