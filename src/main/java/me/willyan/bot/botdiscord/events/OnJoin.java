package me.willyan.bot.botdiscord.events;

import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class OnJoin extends ListenerAdapter {

    private int count = 0;

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent e) {
        this.count = e.getGuild().getMemberCount();
        e.getJDA().getPresence().setActivity(Activity.playing("membros: " + getCount()));
    }

    @Override
    public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent e) {
        this.count = e.getGuild().getMemberCount();
        e.getJDA().getPresence().setActivity(Activity.playing("membros: " + getCount()));
    }

    public int getCount(){
        return count;
    }
}
