package cn.handyplus.region.util;

import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.core.StrUtil;
import org.bukkit.configuration.file.YamlConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * 腾讯地图 IP 定位测试
 *
 * @author handy
 */
public class TencentIpUtilTest {

    private final static String APP_KEY = "";
    private final static String APP_SECRET = "";

    /**
     * 初始化腾讯地图测试配置
     */
    @BeforeAll
    public static void create() {
        BaseConstants.CONFIG = new YamlConfiguration();
        BaseConstants.CONFIG.set("tencentAppKey", APP_KEY);
        BaseConstants.CONFIG.set("tencentAppSecret", APP_SECRET);
    }

    /**
     * 配置凭证后验证真实接口
     */
    @Test
    public void getIpRegion() {
        if (StrUtil.isEmpty(BaseConstants.CONFIG.getString("tencentAppKey"))) {
            return;
        }
        String region = TencentIpUtil.getIpRegion("119.75.217.109");
        Assertions.assertEquals("中国|北京市|北京市|0|0", region);
    }

}
