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

import java.util.*;

public class OnMessage extends ListenerAdapter {


    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {

        String[] args = e.getMessage().getContentRaw().split(" ");
        String[] arg = e.getMessage().getContentRaw().split("-");
        String[] splited = e.getMessage().getContentRaw().split(" !");
        String author = e.getMessage().getAuthor().getAsMention();
        boolean bot = e.getMessage().getAuthor().isBot();
        boolean adm = Objects.requireNonNull(e.getMember()).hasPermission(Permission.ADMINISTRATOR);

        Map<String, String> map = new HashMap<>();

        String perguntaAtiva = "";

        if(args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "rm")){
            if (bot) return;

            if (adm) {
                String idRole = args[1].replace("<@&", "").replace(">", "");
                String uid = args[2].replace("<@!", "").replace(">", "");
                Role role = e.getGuild().getRoleById(idRole);
                e.getGuild().removeRoleFromMember(uid, role).queue();

                e.getChannel().sendMessage(Embed.create("Cargos", "O administrador " + e.getAuthor().getAsMention() + " Removeu o cargo " + args[1] + " do usuário " + args[2])).queue();

            }
        }

        if(args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "add")){
            if (bot) return;

            if (adm){

                String idRole = args[1].replace("<@&", "").replace(">", "");
                String uid = args[2].replace("<@!", "").replace(">", "");

                List<Role> roles = e.getGuild().getMemberById(uid).getRoles();
                for(int i = 0; i < roles.size(); i++){
                    if(roles.get(i).getId() == idRole){
                        e.getChannel().sendMessage(Embed.create("Cargos", "" + e.getAuthor().getAsMention() + " O usuário " + args[2] + " já tem o cargo " + args[1])).queue();
                        return;
                    }
                }


                Role role = e.getGuild().getRoleById(idRole);
                e.getGuild().addRoleToMember(uid, role).queue();



                e.getChannel().sendMessage(Embed.create("Cargos", "O administrador " + e.getAuthor().getAsMention() + " Adicionou o cargo " + args[1] + " para o usuário " + args[2])).queue();
            }

        }

        if(args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "roll")){
            if (bot) return;

            int random = new Random().nextInt(6);

            IMessage.send(e, "> " + e.getAuthor().getAsMention() + " seu dado rolou e caiu " + random);
        }

        if(args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "anc")){
            IMessage.delete(e);
            if (bot) return;
            if (adm){
                if (args.length > 2){
                    for(int i = 0; i < e.getGuild().getMembers().size(); i++){
                        if (e.getGuild().getMembers().get(i).getUser().isBot()){
                            continue;
                        }
                        e.getGuild().getMembers().get(i).getUser().openPrivateChannel().complete().sendMessage("> " + e.getMember().getAsMention() + " Você tem uma nova mensagem do servidor!").queue();
                        e.getGuild().getMembers().get(i).getUser().openPrivateChannel().complete().sendMessage(e.getMessage().getContentRaw().replace(ConfigManager.get("prefix") + "anc", "")).queue();
                    }
                }
            }
        }

        if(args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "i")){
            if (bot) return;
            String invite = e.getChannel().createInvite().complete().toString().replace("Invite(", "").replace(")", "");
            IMessage.send(e, "> " + e.getMember().getUser().getAsMention() + " ** Aqui seu codigo de convite ** https://discord.gg/" + invite);

        }

        if(args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "mute")){
            if (bot) return;
            if (adm){
                String uid = args[1].replace("<@!" , "").replace(">", "");
                Role role = e.getGuild().getRoleById("841380088858542080");
                if(uid != null){
                    e.getGuild().addRoleToMember(uid, role).queue();
                    e.getChannel().sendMessage("Jogador " + args[1] + " foi mutado").queue();
                }else {
                    IMessage.send(e, "> O membro não foi encontrado");
                }

            }

        }

        if(args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "unmute")){
            if (bot) return;
            if (adm){
                String uid = args[1].replace("<@!" , "").replace(">", "");
                Role role = e.getGuild().getRoleById("841380088858542080");
                if(uid != null){
                    e.getGuild().removeRoleFromMember(uid, role).queue();
                    e.getChannel().sendMessage("Jogador " + args[1] + " foi desmutado").queue();
                }else {
                    IMessage.send(e, "> O membro não foi encontrado");
                }

            }

        }

        if(args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "ban") || args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "banir")){
            IMessage.delete(e);
            if (bot) return;
            if (adm){
                String uid = args[1].replace("<@!" , "").replace(">", "");
                int delay = Integer.parseInt(args[2]);
                e.getGuild().ban(uid, delay).queue();
                e.getChannel().sendMessage(Embed.create("Banimentos", "o usuario "+ args[1] +" foi banido por " + args[2] + " dias")).queue();
                e.getGuild().getMemberById(uid).getUser().openPrivateChannel().complete().sendMessage("Você foi banido do servidor " + e.getGuild().getName() + " por " + e.getAuthor().getName()).queue();
            }
        }

        if(args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "kick")){
            IMessage.delete(e);
            if (bot) return;
            if (adm){
                String uid = args[1].replace("<@!" , "").replace(">", "");
                e.getGuild().kick(uid).queue();
                e.getChannel().sendMessage(Embed.create("Kick", "o usuario "+ args[1] +" foi kickado! ")).queue();
            }
        }

        if(args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "membros")){
            IMessage.send(e, "> O discord tem um total de **" + e.getGuild().getMemberCount() + "**");
        }

        if(args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "sorteio")){
            int rand = new Random().nextInt(e.getGuild().getMemberCount());
            String winner = e.getGuild().getMembers().get(rand).getAsMention();
            e.getChannel().sendMessage(Embed.create("Sorteio", "O usuário ganhador do sorteio foi: " + winner )).queue();
        }

        if(args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "e")){
            if(bot) return;
            if (adm){

                perguntaAtiva = splited[1].toLowerCase();
                String resposta = splited[2].toLowerCase();

                map.put(perguntaAtiva, resposta);
                IMessage.send(e, "> Evento QUIZ iniciado para participar digite !resposta !<resposta>");
                IMessage.send(e, "> A pergunta é: " + perguntaAtiva);
                return;
            }

        }

        if(args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "resp")){
            if (splited[0].equalsIgnoreCase(map.get(perguntaAtiva))){
                if(map.get(perguntaAtiva).isEmpty())return;
                IMessage.send(e, "O ganhador foi: " + e.getMessage().getAuthor().getAsMention() + " a resposta era : " + splited[0].toLowerCase());
            }
        }

        if(args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "cargos") || args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "roles") ){
            String resultRoles = "";
            for(int i = 0; i < e.getGuild().getRoles().size(); i++){
                    resultRoles += "[" + e.getGuild().getRoles().get(i).getName() + "] ";

            }

            e.getChannel().sendMessage(Embed.create("Cargos do Servidor", "Os cargos são: " + resultRoles)).queue();

        }

        if(args[0].equalsIgnoreCase(ConfigManager.get("prefix") + "host")){

            if(adm){
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


            if(adm){
                if (args[1].equalsIgnoreCase("create") || args[1].equalsIgnoreCase("criar")) {

                    TextChannel channel = e.getGuild().getTextChannelById(ConfigManager.get("ticketId"));
                    channel.sendMessage(Embed.create("Sistema de Atendimento", "Este canal é reservado para os tickets, caso queira tirar alguma duvida, ou resolver algum problema clique no emoji abaixo 🔓")).complete().addReaction("🔓").queue();

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
