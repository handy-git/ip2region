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
        // 离线模式
        if (IpGetTypeEnum.OFFLINE.getIpGetType().equalsIgnoreCase(dataSource)) {
            SearcherUtil.getPlayerRegion(player);
        }
        // 请求 ipPlus360 模式
        if (IpGetTypeEnum.IP_PLUS_360.getIpGetType().equalsIgnoreCase(dataSource)) {
            IpPlus360Util.getPlayerRegion(player);
        }
        // 请求 ipApi 模式
        if (IpGetTypeEnum.IP_API.getIpGetType().equalsIgnoreCase(dataSource)) {
            IpApiUtil.getPlayerRegion(player);
        }
        // 请求 whois 模式
        if (IpGetTypeEnum.WHOIS.getIpGetType().equalsIgnoreCase(dataSource)) {
            WhoisUtil.getPlayerRegion(player);
        }
        // 请求 voreApi 模式
        if (IpGetTypeEnum.VORE_API.getIpGetType().equalsIgnoreCase(dataSource)) {
            VoreApiUtil.getPlayerRegion(player);
        }
        // 请求腾讯地图模式
        if (IpGetTypeEnum.TENCENT.getIpGetType().equalsIgnoreCase(dataSource)) {
            TencentIpUtil.getPlayerRegion(player);
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
        // 离线模式
        if (IpGetTypeEnum.OFFLINE.getIpGetType().equalsIgnoreCase(dataSource)) {
            region = SearcherUtil.getIpRegion(ip);
        }
        // 请求 ipPlus360 模式
        if (IpGetTypeEnum.IP_PLUS_360.getIpGetType().equalsIgnoreCase(dataSource)) {
            region = IpPlus360Util.getIpRegion(ip, null);
        }
        // 请求 ipApi 模式
        if (IpGetTypeEnum.IP_API.getIpGetType().equalsIgnoreCase(dataSource)) {
            region = IpApiUtil.getIpRegion(ip);
        }
        // 请求 whois 模式
        if (IpGetTypeEnum.WHOIS.getIpGetType().equalsIgnoreCase(dataSource)) {
            region = WhoisUtil.getIpRegion(ip);
        }
        // 请求 voreApi 模式
        if (IpGetTypeEnum.VORE_API.getIpGetType().equalsIgnoreCase(dataSource)) {
            region = VoreApiUtil.getIpRegion(ip);
        }
        // 请求腾讯地图模式
        if (IpGetTypeEnum.TENCENT.getIpGetType().equalsIgnoreCase(dataSource)) {
            region = TencentIpUtil.getIpRegion(ip);
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
