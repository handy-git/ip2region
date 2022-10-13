package cn.handyplus.region.util;

import cn.handyplus.region.constants.IpConstants;
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
    public static void getPlayerRegion(Player player) {
        try {
            // 1、创建 searcher 对象
            Searcher searcher = Searcher.newWithFileOnly(ConfigUtil.DB_PATH);
            // 2、查询
            String ip = player.getAddress().getAddress().getHostAddress();
            String region = searcher.search(ip);
            IpConstants.PLAYER_REGION_MAP.put(player.getUniqueId(), region);
            // 3、关闭资源
            searcher.close();
        } catch (Exception ignored) {
        }
    }

}
