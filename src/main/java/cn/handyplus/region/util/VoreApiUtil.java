package cn.handyplus.region.util;

import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.core.HttpUtil;
import cn.handyplus.lib.core.JsonUtil;
import cn.handyplus.lib.core.StrUtil;
import cn.handyplus.lib.util.MessageUtil;
import cn.handyplus.region.constants.BaseIpConstants;
import cn.handyplus.region.param.VoreApiParam;
import org.bukkit.entity.Player;

/**
 * 对接 VORE-API
 * <a href="https://api.vore.top">VORE-API</a>
 *
 * @author handy
 * @since 1.3.0
 */
public class VoreApiUtil {

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
            String json = HttpUtil.get(BaseIpConstants.VORE_API + ip);
            // 未获取到数据
            if (StrUtil.isEmpty(json)) {
                return null;
            }
            VoreApiParam voreApiParam = JsonUtil.toBean(json, VoreApiParam.class);
            // 转换异常
            if (!BaseIpConstants.SUCCESS.equalsIgnoreCase(voreApiParam.getMsg())) {
                MessageUtil.sendConsoleMessage(voreApiParam.getMsg());
                return null;
            }
            return IpUtil.getStr("0" + "|" + "0" + "|" + IpUtil.getStr(voreApiParam.getIpdata().getInfo1()) + "|" + IpUtil.getStr(voreApiParam.getIpdata().getInfo1()) + "|" + IpUtil.getStr(voreApiParam.getIpdata().getIsp()) + "|" + IpUtil.getStr(voreApiParam.getIpdata().getInfo3()));
        } catch (Exception ignored) {
        }
        return null;
    }

}
