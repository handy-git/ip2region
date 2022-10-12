package cn.handyplus.region.util;

import cn.handyplus.lib.util.HandyConfigUtil;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * 配置
 *
 * @author handy
 */
public class ConfigUtil {
    public static FileConfiguration CONFIG;

    /**
     * 初始化加载文件
     */
    public static void init() {
        // 加载config
        CONFIG = HandyConfigUtil.loadConfig();
    }

}