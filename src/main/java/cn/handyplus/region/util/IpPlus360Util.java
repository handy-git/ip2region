package cn.handyplus.region.util;

import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.core.HttpUtil;
import cn.handyplus.lib.core.JsonUtil;
import cn.handyplus.lib.core.StrUtil;
import cn.handyplus.lib.util.MessageUtil;
import cn.handyplus.region.constants.BaseIpConstants;
import cn.handyplus.region.enter.Ip2regionEnter;
import cn.handyplus.region.param.IpPlus360Param;
import cn.handyplus.region.service.Ip2regionService;
import org.bukkit.entity.Player;

import java.util.Optional;

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
        String ip = BaseConstants.CONFIG.getString("testIp", IpUtil.getIp(player));
        String region = getIpRegion(ip, IpUtil.getIpType(player));
        BaseIpConstants.PLAYER_REGION_MAP.put(player.getUniqueId(), region);
    }

    /**
     * 获取地址
     *
     * @param ip Ip
     * @return 国家|0|省份|城市|运营商|区县
     * @since 1.1.3
     */
    protected static String getIpRegion(String ip, String ipType) {
        if (StrUtil.isEmpty(ip)) {
            return null;
        }
        try {
            // 没有ip类型处理
            if (StrUtil.isEmpty(ipType)) {
                Optional<Ip2regionEnter> ip2regionOptional = Ip2regionService.getInstance().findByIp(ip);
                if (ip2regionOptional.isPresent()) {
                    ipType = ip2regionOptional.get().getIpType();
                } else {
                    ipType = BaseIpConstants.IPV4;
                }
            }
            String ipPlus360Ipv4Key = BaseConstants.CONFIG.getString("ipPlus360Ipv4Key", "123456");
            String ipPlus360Ipv6Key = BaseConstants.CONFIG.getString("ipPlus360Ipv6Key", "123456");
            String testIp4 = BaseConstants.CONFIG.getString("testIp4", "");
            String testIp6 = BaseConstants.CONFIG.getString("testIp6", "");
            String json = null;
            // 判断是何种类型
            if (StrUtil.isNotEmpty(testIp4) || BaseIpConstants.IPV4.equals(ipType)) {
                // 未填写key
                if (BaseIpConstants.DEFAULT_NUMBER.equals(ipPlus360Ipv4Key)) {
                    return null;
                }
                ip = StrUtil.isNotEmpty(testIp4) ? testIp4 : ip;
                String ipPlus360Ipv4Url = BaseConstants.CONFIG.getString("ipPlus360Ipv4Url", BaseIpConstants.IP_PLUS_360_IPV4);
                json = HttpUtil.get(ipPlus360Ipv4Url + "?key=" + ipPlus360Ipv4Key + "&ip=" + ip + "&coordsys=WGS84");
            } else if (StrUtil.isNotEmpty(testIp6) || BaseIpConstants.IPV6.equals(ipType)) {
                // 未填写key
                if (BaseIpConstants.DEFAULT_NUMBER.equals(ipPlus360Ipv6Key)) {
                    return null;
                }
                ip = StrUtil.isNotEmpty(testIp6) ? testIp6 : ip;
                String ipPlus360Ipv6Url = BaseConstants.CONFIG.getString("ipPlus360Ipv6Url", BaseIpConstants.IP_PLUS_360_IPV6);
                json = HttpUtil.get(ipPlus360Ipv6Url + "?key=" + ipPlus360Ipv6Key + "&ip=" + ip + "&coordsys=WGS84");
            }
            // 未获取到数据
            if (StrUtil.isEmpty(json)) {
                return null;
            }
            IpPlus360Param ipPlus360Param = JsonUtil.toBean(json, IpPlus360Param.class);
            // 转换异常
            if (!BaseIpConstants.SUCCESS.equalsIgnoreCase(ipPlus360Param.getCode())) {
                MessageUtil.sendConsoleMessage(ipPlus360Param.getMsg());
                return null;
            }
            IpPlus360Param.IpPlus360ParamData data = ipPlus360Param.getData();
            return IpUtil.getStr(data.getCountry()) + "|" + IpUtil.getStr(data.getContinent()) + "|" + IpUtil.getStr(data.getProv()) + "|" + IpUtil.getStr(data.getCity()) + "|" + IpUtil.getStr(data.getOwner() + "|" + IpUtil.getStr(data.getDistrict()));
        } catch (Exception ignored) {
        }
        return null;
    }

}
