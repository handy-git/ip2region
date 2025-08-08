package cn.handyplus.region.util;

import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.core.HttpUtil;
import cn.handyplus.lib.core.JsonUtil;
import cn.handyplus.lib.core.StrUtil;
import cn.handyplus.lib.util.MessageUtil;
import cn.handyplus.region.constants.BaseIpConstants;
import cn.handyplus.region.param.IpApiParam;
import org.bukkit.entity.Player;

/**
 * 对接 ip-api.com api
 *
 * @author handy
 * @since 1.1.0
 */
public class IpApiUtil {

    /**
     * 获取地址
     *
     * @param player 玩家
     */
    protected static void getPlayerRegion(Player player) {
        String ip = BaseConstants.CONFIG.getString("testIp", IpUtil.getIp(player));
        String region = getIpRegion(ip);
        BaseIpConstants.PLAYER_REGION_MAP.put(player.getUniqueId(), region);
    }

    /**
     * 获取地址
     *
     * @param ip Ip
     * @return 国家|0|省份|城市|运营商
     * @since 1.1.3
     */
    protected static String getIpRegion(String ip) {
        if (StrUtil.isEmpty(ip)) {
            return null;
        }
        try {
            String json = HttpUtil.get(BaseIpConstants.IP_API_IPV4 + ip + "?lang=zh-CN");
            // 未获取到数据
            if (StrUtil.isEmpty(json)) {
                return null;
            }
            IpApiParam ipApiParam = JsonUtil.toBean(json, IpApiParam.class);
            // 转换异常
            if (!BaseIpConstants.SUCCESS.equalsIgnoreCase(ipApiParam.getStatus())) {
                MessageUtil.sendConsoleMessage(ipApiParam.getMessage());
                return null;
            }
            return IpUtil.getStr(ipApiParam.getCountry()) + "|" + "0" + "|" + IpUtil.getStr(ipApiParam.getRegionName()) + "|" + IpUtil.getStr(ipApiParam.getCity()) + "|" + IpUtil.getStr(ipApiParam.getIsp());
        } catch (Exception ignored) {
        }
        return null;
    }

}
