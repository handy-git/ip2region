package cn.handyplus.region.util;

import cn.handyplus.lib.InitApi;
import cn.handyplus.lib.api.MessageApi;
import cn.handyplus.region.Ip2region;
import org.bukkit.entity.Player;
import org.lionsoul.ip2region.xdb.Searcher;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Searcher工具类
 *
 * @author handy
 */
public class SearcherUtil {

    public static Map<UUID, String> PLAYER_REGION_MAP = new HashMap<>();

    private byte[] vIndex;

    private SearcherUtil() {

    }

    private static final SearcherUtil INSTANCE = new SearcherUtil();

    public static SearcherUtil getInstance() {
        return INSTANCE;
    }

    private byte[] getIndex(String dbPath) {
        if (vIndex != null) {
            return vIndex;
        }
        // 1、从 dbPath 中预先加载 VectorIndex 缓存，并且把这个得到的数据作为全局变量，后续反复使用。
        try {
            vIndex = Searcher.loadVectorIndexFromFile(dbPath);
        } catch (Exception e) {
            MessageApi.sendConsoleMessage("未找到文件");
        }
        return vIndex;
    }

    /**
     * 获取地址
     *
     * @param player 玩家
     */
    public void getPlayerRegion(Player player) {
        File file = new File(InitApi.PLUGIN.getDataFolder(), "ip2region.xdb");
        if (!file.exists()) {
            Ip2region.getInstance().saveResource("ip2region.xdb", false);
            file = new File(InitApi.PLUGIN.getDataFolder(), "ip2region.xdb");
        }
        String dbPath = file.getPath();
        byte[] index = this.getIndex(dbPath);
        try {
            // 2、使用全局的 vIndex 创建带 VectorIndex 缓存的查询对象。
            Searcher searcher = Searcher.newWithVectorIndex(dbPath, index);
            // 3、查询
            String hostAddress = player.getAddress().getAddress().getHostAddress();
            String region = searcher.search(hostAddress);
            PLAYER_REGION_MAP.put(player.getUniqueId(), region);
            // 4、关闭资源
            searcher.close();
        } catch (Exception ignored) {
        }
    }

}
