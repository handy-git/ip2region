package cn.handyplus.region.util;

import cn.handyplus.lib.core.StrUtil;
import cn.handyplus.region.constants.IpGetTypeEnum;
import org.bukkit.entity.Player;

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
        String dataSource = ConfigUtil.CONFIG.getString("dataSource", IpGetTypeEnum.OFFLINE.getIpGetType());
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
     * 兼容默认值
     *
     * @param str 值
     * @return 默认值
     */
    protected static String getStr(String str) {
        return StrUtil.isNotEmpty(str) ? str : "0";
    }

}