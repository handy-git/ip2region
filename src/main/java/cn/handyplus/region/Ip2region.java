package cn.handyplus.region;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * 主类
 *
 * @author handy
 */
public class Ip2region extends JavaPlugin {
    private static Ip2region INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
    }

    @Override
    public void onDisable() {
    }

    public static Ip2region getInstance() {
        return INSTANCE;
    }

}