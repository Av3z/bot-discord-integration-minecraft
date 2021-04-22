package me.willyan.bot.botdiscord.bot;

import me.willyan.bot.botdiscord.core.Main;
import me.willyan.bot.botdiscord.events.OnMessage;
import me.willyan.bot.botdiscord.lib.ConfigManager;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

public class BotDiscord {

    public Main plugin;
    private JDABuilder builder;
    private String token = "NzM4NjI0MTI0NjA3NTI5MDAw.XyOnUg.te0SXqvQWYpc53R5T2UQM4dyGSg";


    public BotDiscord (Main main){
        this.plugin = main;
        start();
    }


    private void start(){

        try {

            builder = JDABuilder.createDefault(token);
            builder.setActivity(Activity.playing(ConfigManager.playing()));
            builder.addEventListeners(new OnMessage());
            builder.build();

        } catch (LoginException e) {
            e.printStackTrace();
        }


    }

}
