package cn.handyplus.region.util;

import cn.handyplus.lib.InitApi;
import cn.handyplus.lib.util.HandyConfigUtil;

import java.io.File;

/**
 * 配置
 *
 * @author handy
 */
public class ConfigUtil {
    public static String V4_DB_PATH;
    public static String V6_DB_PATH;

    /**
     * 初始化加载文件
     */
    public static void init() {
        // 加载config
        HandyConfigUtil.loadConfig();
        // 加载语言文件
        HandyConfigUtil.loadLangConfig(false);
        // 只加载一次db
        File v4file = new File(InitApi.PLUGIN.getDataFolder(), "ip2region_v4.xdb");
        if (v4file.exists()) {
            V4_DB_PATH = v4file.getPath();
        }
        // 只加载一次db
        File v6file = new File(InitApi.PLUGIN.getDataFolder(), "ip2region_v6.xdb");
        if (v6file.exists()) {
            V6_DB_PATH = v6file.getPath();
        }
    }

}