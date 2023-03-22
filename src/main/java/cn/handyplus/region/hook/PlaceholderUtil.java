package cn.handyplus.region.hook;

import cn.handyplus.lib.core.StrUtil;
import cn.handyplus.region.Ip2region;
import cn.handyplus.region.constants.IpConstants;
import cn.handyplus.region.util.ConfigUtil;
import cn.handyplus.region.util.SearcherUtil;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;

import java.util.List;

/**
 * 变量扩展
 *
 * @author handy
 */
public class PlaceholderUtil extends PlaceholderExpansion {
    private final Ip2region plugin;

    public PlaceholderUtil(Ip2region plugin) {
        this.plugin = plugin;
    }

    /**
     * 变量前缀
     *
     * @return 结果
     */
    @Override
    public String getIdentifier() {
        return "ip2region";
    }

    /**
     * 注册变量
     *
     * @param player     玩家
     * @param identifier 变量
     * @return 结果
     */
    @Override
    public String onRequest(OfflinePlayer player, String identifier) {
        if (player == null) {
            return null;
        }
        String region = IpConstants.PLAYER_REGION_MAP.get(player.getUniqueId());
        if (StrUtil.isEmpty(region)) {
            return plugin.getConfig().getString(identifier, "未知");
        }
        List<String> list = SearcherUtil.strToStrList(region);
        String national = list.get(0);
        String provincial = list.get(2);
        String municipal = list.get(3);
        String serviceProvider = list.get(4);

        String unknown = ConfigUtil.CONFIG.getString("unknown", "未知");
        String local = ConfigUtil.CONFIG.getString("local", "内网IP");

        // 判断是否开启显示
        boolean showEnable = IpConstants.PLAYER_SHOW_MAP.getOrDefault(player.getUniqueId(), true);
        if (!showEnable) {
            return plugin.getConfig().getString(identifier, unknown);
        }
        // %ip2region_region%
        if ("region".equals(identifier)) {
            return plugin.getConfig().getString(identifier, region);
        }
        // %ip2region_national%
        if ("national".equals(identifier)) {
            return plugin.getConfig().getString(identifier, "0".equals(national) ? unknown : national);
        }
        boolean removeProvinceAndCity = ConfigUtil.CONFIG.getBoolean("removeProvinceAndCity");
        // %ip2region_provincial%
        if ("provincial".equals(identifier)) {
            String provincialStr = "0".equals(provincial) ? unknown : provincial;
            if ("内网IP".equals(provincialStr)) {
                provincialStr = local;
            }
            return plugin.getConfig().getString(identifier, removeProvinceAndCity ? provincialStr.replace("省", "") : provincialStr);
        }
        // %ip2region_municipal%
        if ("municipal".equals(identifier)) {
            String municipalStr = "0".equals(municipal) ? unknown : municipal;
            if ("内网IP".equals(municipalStr)) {
                municipalStr = local;
            }
            return plugin.getConfig().getString(identifier, removeProvinceAndCity ? municipalStr.replace("市", "") : municipalStr);
        }
        // %ip2region_serviceProvider%
        if ("serviceProvider".equals(identifier)) {
            return plugin.getConfig().getString(identifier, "0".equals(serviceProvider) ? unknown : serviceProvider);
        }
        return null;
    }

    /**
     * 因为这是一个内部类，
     * 你必须重写这个方法，让PlaceholderAPI知道不要注销你的扩展类
     *
     * @return 结果
     */
    @Override
    public boolean persist() {
        return true;
    }

    /**
     * 因为这是一个内部类，所以不需要进行这种检查
     * 我们可以简单地返回{@code true}
     *
     * @return 结果
     */
    @Override
    public boolean canRegister() {
        return true;
    }

    /**
     * 作者
     *
     * @return 结果
     */
    @Override
    public String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }

    /**
     * 版本
     *
     * @return 结果
     */
    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }
}
