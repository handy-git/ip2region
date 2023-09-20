package cn.handyplus.region.util;

import org.bukkit.entity.Player;

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
        String dataSource = ConfigUtil.CONFIG.getString("dataSource", "offline");
        // 离线模式
        if ("offline".equalsIgnoreCase(dataSource)) {
            SearcherUtil.getPlayerRegion(player);
        }
        // 请求ipPlus360模式
        if ("ipPlus360".equalsIgnoreCase(dataSource)) {
            IpPlus360Util.getPlayerRegion(player);
        }
    }

}
