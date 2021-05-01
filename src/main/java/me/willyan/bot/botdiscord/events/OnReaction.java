package me.willyan.bot.botdiscord.events;

import me.willyan.bot.botdiscord.lib.ConfigManager;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

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
    }

}
