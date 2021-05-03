package me.willyan.bot.botdiscord.core;

import me.willyan.bot.botdiscord.bot.BotDiscord;
import me.willyan.bot.botdiscord.lib.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public ConsoleCommandSender console;

    @Override
    public void onEnable() {
        console = Bukkit.getServer().getConsoleSender();
        new BotDiscord(this);
        saveDefaultConfig();
        reloadConfig();
        console.sendMessage(ConfigManager.get("onEnable"));
    }

    @Override
    public void onDisable() {
        console.sendMessage(ConfigManager.get("onDisable"));
    }
}
