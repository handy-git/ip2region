package cn.handyplus.region.util;

import cn.handyplus.lib.InitApi;
import cn.handyplus.lib.util.HandyConfigUtil;
import cn.handyplus.region.Ip2region;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.Collections;

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
        // 升级配置
        upConfig();
    }

    /**
     * 升级节点处理
     *
     * @since 1.0.0
     */
    public static void upConfig() {
        // 1.0.0 添加数据来源
        HandyConfigUtil.setPathIsNotContains(CONFIG, "dataSource", "offline", Collections.singletonList("数据来源 ( offline 或 ipPlus360 或 ipApi )"), "config.yml");
        HandyConfigUtil.setPathIsNotContains(CONFIG, "ipPlus360Ipv4Key", "123456", Collections.singletonList("ipv4在线归属地(有免费额度) 购买地址: https://mall.ipplus360.com/pros/IPVFourGeoAPI"), "config.yml");
        HandyConfigUtil.setPathIsNotContains(CONFIG, "ipPlus360Ipv6Key", "123456", Collections.singletonList("ipv6在线归属地 购买地址: https://mall.ipplus360.com/pros/IPGeoAPI"), "config.yml");
        CONFIG = HandyConfigUtil.load("config.yml");
    }

}