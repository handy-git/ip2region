package cn.handyplus.region;

import cn.handyplus.lib.InitApi;
import cn.handyplus.lib.util.MessageUtil;
import cn.handyplus.region.hook.PlaceholderUtil;
import cn.handyplus.region.util.ConfigUtil;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

/**
 * 主类
 *
 * @author handy
 */
public class Ip2region extends JavaPlugin {
    public static Ip2region INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        // 初始化
        InitApi initApi = InitApi.getInstance(this);
        // 加载配置
        ConfigUtil.init();
        // 加载变量
        new PlaceholderUtil(this).register();
        // 打印 logo
        List<String> asciiArt = Arrays.asList(
                "",
                "  ___      ____                 _             ",
                " |_ _|_ __|___ \\ _ __ ___  __ _(_) ___  _ __  ",
                "  | || '_ \\ __) | '__/ _ \\/ _` | |/ _ \\| '_ \\ ",
                "  | || |_) / __/| | |  __/ (_| | | (_) | | | |",
                " |___| .__/_____|_|  \\___|\\__, |_|\\___/|_| |_|",
                "     |_|                  |___/               "
        );
        for (String line : asciiArt) {
            MessageUtil.sendConsoleMessage(ChatColor.DARK_AQUA + line);
        }
        // 初始化
        initApi.initListener("cn.handyplus.region.listener")
                .initCommand("cn.handyplus.region.command")
                .enableSql("cn.handyplus.region.enter")
                .addMetrics(16650)
                .checkVersion();
        MessageUtil.sendConsoleMessage(ChatColor.GREEN + "已成功载入服务器!");
        MessageUtil.sendConsoleMessage(ChatColor.GREEN + "Author:handy WIKI: https://ricedoc.handyplus.cn/wiki/ip2region/README/");
    }

    @Override
    public void onDisable() {
        InitApi.disable();
    }

}