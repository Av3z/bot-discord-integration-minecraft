package me.willyan.bot.botdiscord.util;

import me.willyan.bot.botdiscord.lib.ConfigManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class Embed {

    public static MessageEmbed createAtt(String desc){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Anúncio");
        embed.setAuthor(ConfigManager.get("serverName"), ConfigManager.get("serverImageAtt"));
        embed.setImage(ConfigManager.get("serverImageAtt"));
        embed.setDescription(desc);
        embed.setThumbnail(ConfigManager.get("serverIcon"));
        embed.setColor(Color.ORANGE);
        embed.setFooter(ConfigManager.get("copyRight"));

        return embed.build();
    }

    public static MessageEmbed createAvatar(String url, String author){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Icone do " + author);
        embed.setAuthor(ConfigManager.get("serverName"));
        embed.setImage(url);
        embed.setThumbnail(ConfigManager.get("serverIcon"));
        embed.setColor(Color.ORANGE);
        embed.setFooter(ConfigManager.get("copyRight"));

        return embed.build();
    }

    public static MessageEmbed create(String title){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle(title);
        embed.setAuthor(ConfigManager.get("serverName"));
        embed.setThumbnail(ConfigManager.get("serverIcon"));
        embed.setColor(Color.ORANGE);
        embed.setFooter(ConfigManager.get("copyRight"));

        return embed.build();
    }


    public static MessageEmbed create(String title, String desc){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle(title);
        embed.setAuthor(ConfigManager.get("serverName"));
        embed.setThumbnail(ConfigManager.get("serverIcon"));
        embed.setDescription(desc);
        embed.setColor(Color.ORANGE);
        embed.setFooter(ConfigManager.get("copyRight"));

        return embed.build();
    }

    public static MessageEmbed create(String title, String desc, String field, String value){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle(title);
        embed.setAuthor(ConfigManager.get("serverName"));
        embed.setThumbnail(ConfigManager.get("serverIcon"));
        embed.setColor(Color.ORANGE);
        embed.setDescription(desc);
        embed.addField(field, value, false);

        embed.setFooter(ConfigManager.get("copyRight"));
        return  embed.build();

    }

}
