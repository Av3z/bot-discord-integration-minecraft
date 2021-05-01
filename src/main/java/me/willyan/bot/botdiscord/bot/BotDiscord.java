package me.willyan.bot.botdiscord.bot;

import me.willyan.bot.botdiscord.core.Main;
import me.willyan.bot.botdiscord.events.OnMessage;
import me.willyan.bot.botdiscord.events.OnReaction;
import me.willyan.bot.botdiscord.lib.ConfigManager;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class BotDiscord {

    public Main plugin;
    private JDABuilder builder;

    public BotDiscord (Main main){
        this.plugin = main;
        start();
    }

    private void start(){
        try {
            builder = JDABuilder.createDefault(ConfigManager.get("token"));

            builder.enableIntents(
                    GatewayIntent.GUILD_MESSAGES,
                    GatewayIntent.GUILD_MESSAGE_REACTIONS,
                    GatewayIntent.GUILD_VOICE_STATES);

            builder.setActivity(Activity.playing(ConfigManager.get("playing")));
            builder.setAutoReconnect(true);

            builder.disableCache(
                    CacheFlag.CLIENT_STATUS,
                    CacheFlag.ACTIVITY,
                    CacheFlag.EMOTE);

            registerEvents();
            builder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    public void registerEvents(){
        builder.addEventListeners(new OnMessage());
        builder.addEventListeners(new OnReaction());
    }
}
