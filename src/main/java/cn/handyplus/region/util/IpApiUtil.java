package cn.handyplus.region.util;

import cn.handyplus.lib.core.HttpUtil;
import cn.handyplus.lib.core.JsonUtil;
import cn.handyplus.lib.core.StrUtil;
import cn.handyplus.lib.util.MessageUtil;
import cn.handyplus.region.constants.IpConstants;
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
        try {
            // IP地址
            String ip = player.getAddress().getAddress().getHostAddress();
            String testIp = ConfigUtil.CONFIG.getString("testIp", ip);
            String json = HttpUtil.get(IpConstants.IP_API_IPV4 + testIp + "?lang=zh-CN");
            // 未获取到数据
            if (StrUtil.isEmpty(json)) {
                return;
            }
            IpApiParam ipApiParam = JsonUtil.toBean(json, IpApiParam.class);
            // 转换异常
            if (!"success".equalsIgnoreCase(ipApiParam.getStatus())) {
                MessageUtil.sendConsoleMessage(ipApiParam.getMessage());
                return;
            }
            String region = IpUtil.getStr(ipApiParam.getCountry()) + "|" + IpUtil.getStr(ipApiParam.getAs()) + "|" + IpUtil.getStr(ipApiParam.getRegionName()) + "|" + IpUtil.getStr(ipApiParam.getCity()) + "|" + IpUtil.getStr(ipApiParam.getIsp());
            IpConstants.PLAYER_REGION_MAP.put(player.getUniqueId(), region);
        } catch (Exception ignored) {
        }
    }


}
