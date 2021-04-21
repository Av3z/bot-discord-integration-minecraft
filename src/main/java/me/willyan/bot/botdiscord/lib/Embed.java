package me.willyan.bot.botdiscord.lib;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class Embed {

    public static MessageEmbed create(String title, String desc){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle(title);
        embed.setDescription(desc);

        embed.setFooter(ConfigManager.copyRight());

        return embed.build();
    }

    public static MessageEmbed create(String title, String desc, String field, String value){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle(title);
        embed.setColor(Color.RED);
        embed.setDescription(desc);
        embed.addField(field, value, false);

        embed.setFooter(ConfigManager.copyRight());
        return  embed.build();

    }

}
