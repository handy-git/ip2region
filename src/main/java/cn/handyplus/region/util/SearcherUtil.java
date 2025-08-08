package cn.handyplus.region.util;

import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.core.StrUtil;
import cn.handyplus.region.constants.BaseIpConstants;
import org.bukkit.entity.Player;
import org.lionsoul.ip2region.xdb.Searcher;

/**
 * Searcher工具类
 *
 * @author handy
 */
public class SearcherUtil {

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
     * @return 国家|区域|省份|城市|ISP
     * @since 1.0.3
     */
    protected static String getIpRegion(String ip) {
        if (StrUtil.isEmpty(ip)) {
            return null;
        }
        try {
            // 1、创建 searcher 对象
            Searcher searcher = Searcher.newWithFileOnly(ConfigUtil.DB_PATH);
            // 2、查询
            String search = searcher.search(ip);
            // 3、关闭资源
            searcher.close();
            return search;
        } catch (Exception ignored) {
        }
        return null;
    }

}