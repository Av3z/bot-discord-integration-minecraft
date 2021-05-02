package me.willyan.bot.botdiscord.events;

import me.willyan.bot.botdiscord.lib.ConfigManager;
import me.willyan.bot.botdiscord.util.Embed;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;
import java.util.Random;

public class OnReaction extends ListenerAdapter {


    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent e) {
        if (e.getMember().getUser().isBot()) return;

        MessageReaction.ReactionEmote reaction = e.getReaction().getReactionEmote();

        if(reaction.getName().equalsIgnoreCase("✅")) {
            if (e.getTextChannel().getId().equals(ConfigManager.get("captcha.channel"))) {
                e.getGuild().addRoleToMember(e.getMember(), e.getJDA().getRoleById(ConfigManager.get("memberRoleId"))).queue();
            }
        }

        if(e.getTextChannel().getName().startsWith("ticket-")){

            if(reaction.getName().equalsIgnoreCase("🔒")){
                e.getTextChannel().delete().queue();
            }

        }

        Category category = e.getGuild().getCategoryById("834614398583963669");

        int random = new Random().nextInt(2000)+ new Random().nextInt(3000) + 999;

        if(e.getTextChannel().getId().equals(ConfigManager.get("ticketId"))){

            if(reaction.getName().equalsIgnoreCase("🔓")){
                TextChannel channel = e.getGuild().createTextChannel("ticket-" + random, category)
                        .addPermissionOverride(e.getGuild().getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                        .addPermissionOverride(e.getMember(), EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_EXT_EMOJI, Permission.MESSAGE_READ, Permission.MESSAGE_ADD_REACTION, Permission.MESSAGE_WRITE), null)
                        .complete();
                channel.sendMessage(Embed.create("Sistema de Atendimento", e.getMember().getAsMention() + " Seu ticket foi criado com sucesso aguarde alguém da equipe de suporte para lhe ajudar. ", "Sua duvida ou ticket foi resolvido?", "Então clique no emoji abaixo para fechar o seu ticket! 🔒")).complete().addReaction("🔒").queue();

                e.getReaction().removeReaction().queue();

            }

        }

    }

}
