package cn.handyplus.region.util;

import cn.handyplus.lib.InitApi;
import cn.handyplus.lib.util.HandyConfigUtil;
import cn.handyplus.region.Ip2region;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

/**
 * 配置
 *
 * @author handy
 */
public class ConfigUtil {
    public static FileConfiguration CONFIG, LANG_CONFIG;
    public static String DB_PATH;

    /**
     * 初始化加载文件
     */
    public static void init() {
        // 加载config
        CONFIG = HandyConfigUtil.loadConfig();
        // 只加载一次db
        File file = new File(InitApi.PLUGIN.getDataFolder(), "ip2region.xdb");
        if (!file.exists()) {
            Ip2region.getInstance().saveResource("ip2region.xdb", false);
            file = new File(InitApi.PLUGIN.getDataFolder(), "ip2region.xdb");
        }
        DB_PATH = file.getPath();
        // 加载语言文件
        LANG_CONFIG = HandyConfigUtil.loadLangConfig(CONFIG.getString("language", "zh_CN"));
    }

}