package cn.handyplus.region.util;

import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.core.StrUtil;
import cn.handyplus.lib.util.HandyConfigUtil;
import cn.handyplus.region.constants.BaseIpConstants;
import cn.handyplus.region.constants.IpGetTypeEnum;
import lombok.SneakyThrows;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.lionsoul.ip2region.xdb.Version;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Map;

/**
 * 获取ip地址
 *
 * @author handy
 * @since 1.1.0
 */
public class IpUtil {

    /**
     * 获取地址
     *
     * @param player 玩家
     */
    public static void getPlayerRegion(Player player) {
        String dataSource = BaseConstants.CONFIG.getString("dataSource", IpGetTypeEnum.OFFLINE.getIpGetType());
        switch (IpGetTypeEnum.fromType(dataSource)) {
            // 离线模式
            case OFFLINE:
                SearcherUtil.getPlayerRegion(player);
                break;
            // 请求 ipPlus360 模式
            case IP_PLUS_360:
                IpPlus360Util.getPlayerRegion(player);
                break;
            // 请求 ipApi 模式
            case IP_API:
                IpApiUtil.getPlayerRegion(player);
                break;
            // 请求 Whois 模式
            case WHOIS:
                WhoisUtil.getPlayerRegion(player);
                break;
            // 请求 voreApi 模式
            case VORE_API:
                VoreApiUtil.getPlayerRegion(player);
                break;
            // 请求腾讯地图模式
            case TENCENT:
                TencentIpUtil.getPlayerRegion(player);
                break;
            default:
                break;
        }
        String region = convertRegion(BaseIpConstants.PLAYER_REGION_MAP.get(player.getUniqueId()));
        if (StrUtil.isEmpty(region)) {
            return;
        }
        BaseIpConstants.PLAYER_REGION_MAP.put(player.getUniqueId(), region);
    }

    /**
     * 获取地址
     *
     * @param ip IP
     * @since 1.1.3
     */
    public static String getIpRegion(String ip) {
        String dataSource = BaseConstants.CONFIG.getString("dataSource", IpGetTypeEnum.OFFLINE.getIpGetType());
        String region = null;
        switch (IpGetTypeEnum.fromType(dataSource)) {
            // 离线模式
            case OFFLINE:
                region = SearcherUtil.getIpRegion(ip);
                break;
            // 请求 ipPlus360 模式
            case IP_PLUS_360:
                region = IpPlus360Util.getIpRegion(ip, null);
                break;
            // 请求 ipApi 模式
            case IP_API:
                region = IpApiUtil.getIpRegion(ip);
                break;
            // 请求 Whois 模式
            case WHOIS:
                region = WhoisUtil.getIpRegion(ip);
                break;
            // 请求 voreApi 模式
            case VORE_API:
                region = VoreApiUtil.getIpRegion(ip);
                break;
            // 请求腾讯地图模式
            case TENCENT:
                region = TencentIpUtil.getIpRegion(ip);
                break;
            default:
                break;
        }
        return convertRegion(region);
    }

    /**
     * 转换地区格式
     *
     * @param region 地区
     * @return 转换后的地区
     */
    private static String convertRegion(String region) {
        if (StrUtil.isEmpty(region)) {
            return region;
        }
        Map<String, Object> valueMapping = HandyConfigUtil.getChildMap(BaseConstants.CONFIG, "valueMapping");
        String[] values = region.split("\\|", -1);
        for (int i = 0; i < values.length; i++) {
            Object mappedValue = valueMapping.get(values[i]);
            values[i] = mappedValue == null ? values[i] : String.valueOf(mappedValue);
        }
        return String.join("|", values);
    }

    /**
     * 获取ip
     *
     * @param player 玩家
     * @return ip
     */
    public static String getIp(Player player) {
        InetSocketAddress address = player.getAddress();
        if (address == null) {
            return null;
        }
        return address.getAddress().getHostAddress();
    }

    /**
     * 获取ip 类型
     *
     * @param ip IP 地址
     * @return ip
     */
    @SneakyThrows
    public static @NotNull Version getIpVersion(@NotNull String ip) {
        InetAddress inetAddress = InetAddress.getByName(ip);
        return (inetAddress instanceof Inet6Address) ? Version.IPv6 : Version.IPv4;
    }

    /**
     * 获取ip
     *
     * @param player 玩家
     * @return ip
     */
    public static String getIpType(Player player) {
        InetSocketAddress address = player.getAddress();
        if (address == null) {
            return BaseIpConstants.IPV4;
        }
        InetAddress inetAddress = address.getAddress();
        if (inetAddress instanceof Inet4Address) {
            return BaseIpConstants.IPV4;
        }
        if (inetAddress instanceof Inet6Address) {
            return BaseIpConstants.IPV6;
        }
        return BaseIpConstants.IPV4;
    }

    /**
     * 兼容默认值
     *
     * @param str 值
     * @return 默认值
     */
    protected static String getStr(String str) {
        return StrUtil.isNotEmpty(str) ? str : "0";
    }

}
