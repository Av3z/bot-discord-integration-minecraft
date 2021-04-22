package me.willyan.bot.botdiscord.lib;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class Embed {

    public static MessageEmbed create(String title, String desc){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle(title);
        embed.setAuthor("CookieMC");
        embed.setThumbnail("https://i.pinimg.com/originals/b4/c6/0a/b4c60a5c4b34cd2e9b16bb25314e9a7e.png");
        embed.setDescription(desc);
        embed.setColor(Color.ORANGE);
        embed.setFooter(ConfigManager.copyRight());

        return embed.build();
    }

    public static MessageEmbed create(String title, String desc, String field, String value){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle(title);
        embed.setAuthor("CookieMC");
        embed.setThumbnail("https://i.pinimg.com/originals/b4/c6/0a/b4c60a5c4b34cd2e9b16bb25314e9a7e.png");
        embed.setColor(Color.ORANGE);
        embed.setDescription(desc);
        embed.addField(field, value, false);

        embed.setFooter(ConfigManager.copyRight());
        return  embed.build();

    }

}
