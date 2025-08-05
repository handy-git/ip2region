package cn.handyplus.region.constants;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 常量
 *
 * @author handy
 */
public abstract class BaseIpConstants {

    /**
     * 玩家地区
     */
    public final static Map<UUID, String> PLAYER_REGION_MAP = new HashMap<>();

    /**
     * 是否显示
     *
     * @since 1.0.5
     */
    public final static Map<UUID, Boolean> PLAYER_SHOW_MAP = new HashMap<>();

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

}