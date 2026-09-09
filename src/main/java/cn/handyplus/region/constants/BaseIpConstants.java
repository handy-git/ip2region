package cn.handyplus.region.constants;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 常量
 *
 * @author handy
 */
public abstract class BaseIpConstants {

    /**
     * 玩家地区
     */
    public final static Map<UUID, String> PLAYER_REGION_MAP = new ConcurrentHashMap<>();

    /**
     * 是否显示
     *
     * @since 1.0.5
     */
    public final static Map<UUID, Boolean> PLAYER_SHOW_MAP = new ConcurrentHashMap<>();

    /**
     * ipPlus360 ipv4 api地址
     *
     * @since 1.1.0
     */
    public final static String IP_PLUS_360_IPV4 = "https://api.ipplus360.com/ip/geo/v1/city/";

    /**
     * ipPlus360 ipv6 api地址
     *
     * @since 1.1.0
     */
    public final static String IP_PLUS_360_IPV6 = "https://api.ipplus360.com/ip/geo/v1/ipv6/";

    /**
     * ipApi api地址
     *
     * @since 1.1.0
     */
    public final static String IP_API_IPV4 = "http://ip-api.com/json/";

    /**
     * whois api地址
     *
     * @since 1.3.0
     */
    public final static String WHOIS_API = "https://whois.pconline.com.cn/ipJson.jsp?json=true&ip=";

    /**
     * VORE-API api地址
     *
     * @since 1.3.0
     */
    public final static String VORE_API = "https://api.vore.top/api/IPdata?ip=";

    /**
     * 腾讯地图 IP 定位 api 地址
     */
    public final static String TENCENT_API = "https://apis.map.qq.com/ws/location/v1/ip";

    /**
     * IP
     */
    public final static String IP = "ip";

    /**
     * 未知
     */
    public final static String UNKNOWN = "未知";

    /**
     * 内网IP
     */
    public final static String LOCAL = "内网IP";

    /**
     * Ipv4
     */
    public final static String IPV4 = "Ipv4";

    /**
     * Ipv6
     */
    public final static String IPV6 = "Ipv6";

    /**
     * 是否成功
     */
    public final static String SUCCESS = "success";

    public final static String DEFAULT_NUMBER = "123456";

    /**
     * 总区域列表索引 - 国家
     */
    public final static int INDEX_NATIONAL = 0;

    /**
     * 总区域列表索引 - 省/区域
     */
    public final static int INDEX_PROVINCE = 1;

    /**
     * 总区域列表索引 - 市
     */
    public final static int INDEX_CITY = 2;

    /**
     * 总区域列表索引 - 运营商
     */
    public final static int INDEX_ISP = 3;

    /**
     * 总区域列表索引 - 区县
     */
    public final static int INDEX_DISTRICT = 4;

}
