package me.willyan.bot.botdiscord.lib;

import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;

public class OsManager {

    private static final com.sun.management.OperatingSystemMXBean OS_BEAN =
            (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    private static double cpu;
    private static long total, free, used;
    private static DecimalFormat doubleFormatted;

    private static int mb = 1024*1024;


    public static String getMemory(){
        total = OS_BEAN.getTotalPhysicalMemorySize();
        free = OS_BEAN.getFreePhysicalMemorySize();
        used = total - free;
        return  used / mb  + " / " + total / mb;
    }

    public static String getCpu(){
        cpu = OS_BEAN.getSystemCpuLoad() * 100;
        doubleFormatted = new DecimalFormat("#.##");
        String resultCpu = doubleFormatted.format(cpu);
        return resultCpu;
    }
}
