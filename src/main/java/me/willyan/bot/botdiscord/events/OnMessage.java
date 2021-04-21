package me.willyan.bot.botdiscord.events;

import me.willyan.bot.botdiscord.commands.CommandMsg;
import me.willyan.bot.botdiscord.lib.ConfigManager;
import me.willyan.bot.botdiscord.lib.Embed;
import me.willyan.bot.botdiscord.util.AllArgs;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class OnMessage extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] args = e.getMessage().getContentRaw().split("\\s+");

        String author = e.getMessage().getAuthor().getName();

        if (args[0].equalsIgnoreCase(ConfigManager.prefix() + "say")){
            e.getChannel().sendTyping().queue();

            if(e.getMessage().getContentRaw().startsWith(ConfigManager.prefix() + "say")){

                CommandMsg.delete(e);
                CommandMsg.send(e, args);
            }

        }

        if(args[0].equalsIgnoreCase(ConfigManager.prefix() + "embed")){


            if(args.length == 0 ){
                e.getChannel().sendMessage("use o comando correto " + ConfigManager.prefix() + "embed titulo- descrição- field- valor- cor- ").queue();
                e.getChannel().sendMessage("ou use" + ConfigManager.prefix() + "embed titulo -descrição- *cor* ").queue();
            } else {
                String msg = AllArgs.build(2, args);
                String[] arg = msg.split("-");
                if(arg.length > 1){
                    String title = arg[0];
                    String desc = arg[1];
                    String field = arg[2];
                    String valor = arg[3];
                    String cor =  arg[4];

                    e.getChannel().sendMessage(Embed.create(title, desc, field, valor));

                }else{
                    e.getChannel().sendMessage("use o comando correto " + ConfigManager.prefix() + "embed titulo- descrição- field- valor- cor- ").queue();
                }

            }



        }

    }

}
