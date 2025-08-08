package cn.handyplus.region.util;

import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.core.StrUtil;
import cn.handyplus.region.constants.BaseIpConstants;
import cn.handyplus.region.constants.IpGetTypeEnum;
import org.bukkit.entity.Player;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;

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
    }

    /**
     * 获取地址
     *
     * @param ip IP
     * @since 1.1.3
     */
    public static String getIpRegion(String ip) {
        String dataSource = BaseConstants.CONFIG.getString("dataSource", IpGetTypeEnum.OFFLINE.getIpGetType());
        // 离线模式
        if (IpGetTypeEnum.OFFLINE.getIpGetType().equalsIgnoreCase(dataSource)) {
            return SearcherUtil.getIpRegion(ip);
        }
        // 请求 ipPlus360 模式
        if (IpGetTypeEnum.IP_PLUS_360.getIpGetType().equalsIgnoreCase(dataSource)) {
            return IpPlus360Util.getIpRegion(ip, null);
        }
        // 请求 ipApi 模式
        if (IpGetTypeEnum.IP_API.getIpGetType().equalsIgnoreCase(dataSource)) {
            return IpApiUtil.getIpRegion(ip);
        }
        // 请求 whois 模式
        if (IpGetTypeEnum.WHOIS.getIpGetType().equalsIgnoreCase(dataSource)) {
            return WhoisUtil.getIpRegion(ip);
        }
        // 请求 voreApi 模式
        if (IpGetTypeEnum.VORE_API.getIpGetType().equalsIgnoreCase(dataSource)) {
            return VoreApiUtil.getIpRegion(ip);
        }
        return null;
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