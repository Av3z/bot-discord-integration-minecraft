package me.willyan.bot.botdiscord.util;

public class AllArgs {

    public static String build(int start, String[] args){
        String msg = "";

        for (int i = start; i < args.length; i++){
            msg += args[i] + " ";
        }

        return msg;
    }
}
