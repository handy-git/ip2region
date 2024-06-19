package cn.handyplus.region.util;

import cn.handyplus.lib.core.HttpUtil;
import cn.handyplus.lib.core.JsonUtil;
import cn.handyplus.lib.core.StrUtil;
import cn.handyplus.lib.util.MessageUtil;
import cn.handyplus.region.constants.BaseIpConstants;
import cn.handyplus.region.param.IpPlus360Param;
import org.bukkit.entity.Player;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;

/**
 * 对接 ipPlus360.com api
 *
 * @author handy
 * @since 1.1.0
 */
public class IpPlus360Util {

    /**
     * 获取地址
     *
     * @param player 玩家
     */
    protected static void getPlayerRegion(Player player) {
        try {
            String ipPlus360Ipv4Key = ConfigUtil.CONFIG.getString("ipPlus360Ipv4Key", "123456");
            String ipPlus360Ipv6Key = ConfigUtil.CONFIG.getString("ipPlus360Ipv6Key", "123456");
            // IP地址
            InetAddress inetAddress = player.getAddress().getAddress();
            String ip = inetAddress.getHostAddress();
            String json = null;

            String testIp4 = ConfigUtil.CONFIG.getString("testIp4", "");
            String testIp6 = ConfigUtil.CONFIG.getString("testIp6", "");
            // 判断是何种类型
            if (StrUtil.isNotEmpty(testIp4) || inetAddress instanceof Inet4Address) {
                // 未填写key
                if ("123456".equals(ipPlus360Ipv4Key)) {
                    return;
                }
                ip = StrUtil.isNotEmpty(testIp4) ? testIp4 : ip;
                String ipPlus360Ipv4Url = ConfigUtil.CONFIG.getString("ipPlus360Ipv4Url", BaseIpConstants.IP_PLUS_360_IPV4);
                json = HttpUtil.get(ipPlus360Ipv4Url + "?key=" + ipPlus360Ipv4Key + "&ip=" + ip + "&coordsys=WGS84");
            } else if (StrUtil.isNotEmpty(testIp6) || inetAddress instanceof Inet6Address) {
                // 未填写key
                if ("123456".equals(ipPlus360Ipv6Key)) {
                    return;
                }
                ip = StrUtil.isNotEmpty(testIp6) ? testIp6 : ip;
                String ipPlus360Ipv6Url = ConfigUtil.CONFIG.getString("ipPlus360Ipv6Url", BaseIpConstants.IP_PLUS_360_IPV6);
                json = HttpUtil.get(ipPlus360Ipv6Url + "?key=" + ipPlus360Ipv6Key + "&ip=" + ip + "&coordsys=WGS84");
            }
            // 未获取到数据
            if (StrUtil.isEmpty(json)) {
                return;
            }
            IpPlus360Param ipPlus360Param = JsonUtil.toBean(json, IpPlus360Param.class);
            // 转换异常
            if (!"Success".equalsIgnoreCase(ipPlus360Param.getCode())) {
                MessageUtil.sendConsoleMessage(ipPlus360Param.getMsg());
                return;
            }
            IpPlus360Param.IpPlus360ParamData data = ipPlus360Param.getData();
            String region = IpUtil.getStr(data.getCountry()) + "|" + IpUtil.getStr(data.getContinent()) + "|" + IpUtil.getStr(data.getProv()) + "|" + IpUtil.getStr(data.getCity()) + "|" + IpUtil.getStr(data.getOwner() + "|" + IpUtil.getStr(data.getDistrict()));
            BaseIpConstants.PLAYER_REGION_MAP.put(player.getUniqueId(), region);
        } catch (Exception ignored) {
        }
    }

}
