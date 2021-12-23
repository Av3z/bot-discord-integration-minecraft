package me.willyan.bot.botdiscord.util;

import me.willyan.bot.botdiscord.lib.ConfigManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class Embed {

    public static MessageEmbed createAtt(String desc){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Anúncio");
        embed.setAuthor("CookieMC", "https://i.pinimg.com/originals/eb/78/29/eb7829e121a016db7c0e3d76af9105d2.png");
        embed.setImage("https://i.pinimg.com/originals/eb/78/29/eb7829e121a016db7c0e3d76af9105d2.png");
        embed.setDescription(desc);
        embed.setThumbnail("https://cdn.discordapp.com/attachments/863408986942210109/923706038543335445/LogoNovaCookie.png ");
        embed.setColor(Color.ORANGE);
        embed.setFooter(ConfigManager.get("copyRight"));

        return embed.build();
    }

    public static MessageEmbed createAvatar(String url, String author){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Icone do " + author);
        embed.setAuthor("CookieMC");
        embed.setImage(url);
        embed.setThumbnail("https://cdn.discordapp.com/attachments/863408986942210109/923706038543335445/LogoNovaCookie.png ");
        embed.setColor(Color.ORANGE);
        embed.setFooter(ConfigManager.get("copyRight"));

        return embed.build();
    }

    public static MessageEmbed create(String title){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle(title);
        embed.setAuthor("CookieMC");
        embed.setThumbnail("https://cdn.discordapp.com/attachments/863408986942210109/923706038543335445/LogoNovaCookie.png ");
        embed.setColor(Color.ORANGE);
        embed.setFooter(ConfigManager.get("copyRight"));

        return embed.build();
    }


    public static MessageEmbed create(String title, String desc){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle(title);
        embed.setAuthor("CookieMC");
        embed.setThumbnail("https://cdn.discordapp.com/attachments/863408986942210109/923706038543335445/LogoNovaCookie.png ");
        embed.setDescription(desc);
        embed.setColor(Color.ORANGE);
        embed.setFooter(ConfigManager.get("copyRight"));

        return embed.build();
    }

    public static MessageEmbed create(String title, String desc, String field, String value){
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle(title);
        embed.setAuthor("CookieMC");
        embed.setThumbnail("https://cdn.discordapp.com/attachments/863408986942210109/923706038543335445/LogoNovaCookie.png ");
        embed.setColor(Color.ORANGE);
        embed.setDescription(desc);
        embed.addField(field, value, false);

        embed.setFooter(ConfigManager.get("copyRight"));
        return  embed.build();

    }

}
