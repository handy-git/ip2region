package cn.handyplus.region;

import cn.handyplus.lib.InitApi;
import cn.handyplus.lib.api.MessageApi;
import cn.handyplus.region.hook.PlaceholderUtil;
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
        InitApi initApi = InitApi.getInstance(this);

        new PlaceholderUtil(this).register();

        initApi.initListener("cn.handyplus.region.listener");

        MessageApi.sendConsoleMessage("§a已成功载入服务器！");
        MessageApi.sendConsoleMessage("§aAuthor:handy QQ群:1064982471");
    }

    @Override
    public void onDisable() {
        MessageApi.sendConsoleMessage("§a已成功卸载！");
        MessageApi.sendConsoleMessage("§aAuthor:handy QQ群:1064982471");
    }

    public static Ip2region getInstance() {
        return INSTANCE;
    }

}