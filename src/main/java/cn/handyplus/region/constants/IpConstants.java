package cn.handyplus.region.constants;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 常量
 *
 * @author handy
 */
public abstract class IpConstants {

    /**
     * 检查更新的版本url
     */
    public final static String PLUGIN_VERSION_URL = "https://gitee.com/api/v5/repos/server-ct/ip2region/releases/latest";

    /**
     * 玩家地区
     */
    public static Map<UUID, String> PLAYER_REGION_MAP = new HashMap<>();

}