package cn.handyplus.region;

import cn.handyplus.lib.InitApi;
import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.db.SqlManagerUtil;
import cn.handyplus.lib.util.MessageUtil;
import cn.handyplus.region.constants.IpConstants;
import cn.handyplus.region.hook.PlaceholderUtil;
import cn.handyplus.region.util.ConfigUtil;
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
        // 初始化
        InitApi initApi = InitApi.getInstance(this);
        // 加载配置
        ConfigUtil.init();

        // 加载变量
        new PlaceholderUtil(this).register();
        // 初始化
        initApi.initListener("cn.handyplus.region.listener")
                .initCommand("cn.handyplus.region.command")
                .enableSql("cn.handyplus.region.enter")
                .addMetrics(16650)
                .checkVersion(ConfigUtil.CONFIG.getBoolean(BaseConstants.IS_CHECK_UPDATE), IpConstants.PLUGIN_VERSION_URL);

        MessageUtil.sendConsoleMessage("§a已成功载入服务器！");
        MessageUtil.sendConsoleMessage("§aAuthor:handy 使用文档: https://ricedoc.handyplus.cn/wiki/ip2region/README/");
    }

    @Override
    public void onDisable() {
        // 关闭数据源
        SqlManagerUtil.getInstance().close();
    }

    public static Ip2region getInstance() {
        return INSTANCE;
    }

}