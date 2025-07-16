package cn.handyplus.region.hook;

import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.core.StrUtil;
import cn.handyplus.region.Ip2region;
import cn.handyplus.region.constants.BaseIpConstants;
import cn.handyplus.region.util.IpUtil;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

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
    public @NotNull String getIdentifier() {
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
    public String onRequest(OfflinePlayer player, @NotNull String identifier) {
        if (player == null) {
            return null;
        }
        // %ip2region_ip%
        if (BaseIpConstants.IP.equals(identifier)) {
            if (player.getPlayer() == null) {
                return null;
            }
            return IpUtil.getIp(player.getPlayer());
        }

        String region = BaseIpConstants.PLAYER_REGION_MAP.get(player.getUniqueId());
        if (StrUtil.isEmpty(region)) {
            return BaseIpConstants.UNKNOWN;
        }
        List<String> list = StrUtil.strToStrList(region, "\\|");
        String national = list.get(0);
        String provincial = list.get(2);
        String municipal = list.get(3);
        String serviceProvider = list.get(4);
        String district = "0";
        if (list.size() > 5) {
            district = list.get(5);
        }
        String unknown = BaseConstants.CONFIG.getString("unknown", BaseIpConstants.UNKNOWN);
        String local = BaseConstants.CONFIG.getString("local", BaseIpConstants.LOCAL);

        // 判断是否开启显示
        boolean showEnable = BaseIpConstants.PLAYER_SHOW_MAP.getOrDefault(player.getUniqueId(), true);
        if (!showEnable) {
            return unknown;
        }
        // %ip2region_region%
        if ("region".equals(identifier)) {
            return region;
        }
        // %ip2region_national%
        if ("national".equals(identifier)) {
            return "0".equals(national) ? unknown : national;
        }
        boolean removeProvinceAndCity = BaseConstants.CONFIG.getBoolean("removeProvinceAndCity");
        // %ip2region_provincial%
        if ("provincial".equals(identifier)) {
            String provincialStr = "0".equals(provincial) ? unknown : provincial;
            if (BaseIpConstants.LOCAL.equals(provincialStr)) {
                provincialStr = local;
            }
            return removeProvinceAndCity ? provincialStr.replace("省", "") : provincialStr;
        }
        // %ip2region_municipal%
        if ("municipal".equals(identifier)) {
            String municipalStr = "0".equals(municipal) ? unknown : municipal;
            if (BaseIpConstants.LOCAL.equals(municipalStr)) {
                municipalStr = local;
            }
            return removeProvinceAndCity ? municipalStr.replace("市", "") : municipalStr;
        }
        // %ip2region_serviceProvider%
        if ("serviceProvider".equals(identifier)) {
            return "0".equals(serviceProvider) ? unknown : serviceProvider;
        }
        // %ip2region_district%
        if ("district".equals(identifier)) {
            return "0".equals(district) ? unknown : district;
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
    public @NotNull String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }

    /**
     * 版本
     *
     * @return 结果
     */
    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

}
