package cn.handyplus.region.util;

import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.core.HttpUtil;
import cn.handyplus.lib.core.JsonUtil;
import cn.handyplus.lib.core.StrUtil;
import cn.handyplus.region.constants.BaseIpConstants;
import cn.handyplus.region.param.WhoisParam;
import org.bukkit.entity.Player;

/**
 * 对接 太平洋网络IP地址 api
 *
 * @author handy
 * @since 1.3.0
 */
public class WhoisUtil {

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
     * @return 地址
     */
    protected static String getIpRegion(String ip) {
        if (StrUtil.isEmpty(ip)) {
            return null;
        }
        try {
            String json = HttpUtil.get(BaseIpConstants.WHOIS_API + ip);
            // 未获取到数据
            if (StrUtil.isEmpty(json)) {
                return null;
            }
            WhoisParam whoisParam = JsonUtil.toBean(json, WhoisParam.class);
            return IpUtil.getStr("0" + "|" + "0" + "|" + IpUtil.getStr(whoisParam.getPro()) + "|" + IpUtil.getStr(whoisParam.getCity()) + "|" + "0" + "|" + IpUtil.getStr(whoisParam.getRegion()));
        } catch (Exception ignored) {
        }
        return null;
    }

}
