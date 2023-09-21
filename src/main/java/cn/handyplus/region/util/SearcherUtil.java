package cn.handyplus.region.util;

import cn.handyplus.lib.core.StrUtil;
import cn.handyplus.region.constants.IpConstants;
import org.bukkit.entity.Player;
import org.lionsoul.ip2region.xdb.Searcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        String ip = player.getAddress().getAddress().getHostAddress();
        ip = ConfigUtil.CONFIG.getString("testIp4", ip);
        String region = getIpRegion(ip);
        IpConstants.PLAYER_REGION_MAP.put(player.getUniqueId(), region);
    }

    /**
     * 获取地址
     *
     * @param ip Ip
     * @return 地址
     * @since 1.0.3
     */
    public static String getIpRegion(String ip) {
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

    /**
     * 切割地址
     *
     * @param str 地址
     * @return 分区地址
     */
    public static List<String> strToStrList(String str) {
        List<String> list = new ArrayList<>();
        if (StrUtil.isEmpty(str)) {
            return list;
        }
        return Arrays.stream(str.split("\\|")).map(String::trim).collect(Collectors.toList());
    }

}