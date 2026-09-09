package cn.handyplus.region.api;

import cn.handyplus.lib.core.StrUtil;
import cn.handyplus.region.constants.BaseIpConstants;
import cn.handyplus.region.util.IpUtil;

import java.util.List;

/**
 * API
 *
 * @author handy
 * @since 1.0.3
 */
public class Ip2regionApi {

    /**
     * 获取总区域
     *
     * @param ip ip地址
     * @return 例如 中国|北京|北京市|电信
     */
    public static String getRegion(String ip) {
        String ipRegion = IpUtil.getIpRegion(ip);
        if (StrUtil.isEmpty(ipRegion)) {
            return BaseIpConstants.UNKNOWN;
        }
        return ipRegion;
    }

    /**
     * 获取国家
     *
     * @param ip ip地址
     * @return 例如 中国
     */
    public static String getNational(String ip) {
        String region = IpUtil.getIpRegion(ip);
        String national = getRegionPartSafe(region, BaseIpConstants.INDEX_NATIONAL);
        return formatRegionPart(national);
    }

    /**
     * 获取省
     *
     * @param ip ip地址
     * @return 例如 北京
     */
    public static String getProvincial(String ip) {
        String region = IpUtil.getIpRegion(ip);
        String provincial = getRegionPartSafe(region, BaseIpConstants.INDEX_PROVINCE);
        return formatRegionPart(provincial);
    }

    /**
     * 获取市
     *
     * @param ip ip地址
     * @return 例如 北京市
     */
    public static String getMunicipal(String ip) {
        String region = IpUtil.getIpRegion(ip);
        String municipal = getRegionPartSafe(region, BaseIpConstants.INDEX_CITY);
        return formatRegionPart(municipal);
    }

    /**
     * 获取运营商
     *
     * @param ip ip地址
     * @return 例如 电信
     */
    public static String getServiceProvider(String ip) {
        String region = IpUtil.getIpRegion(ip);
        String serviceProvider = getRegionPartSafe(region, BaseIpConstants.INDEX_ISP);
        return formatRegionPart(serviceProvider);
    }

    /**
     * 获取区/县
     *
     * @param ip ip 地址
     * @return 例如 浦东新区
     * @since 2.0.0
     */
    public static String getDistrict(String ip) {
        String region = IpUtil.getIpRegion(ip);
        String district = getRegionPartSafe(region, BaseIpConstants.INDEX_DISTRICT);
        return formatRegionPart(district);
    }

    /**
     * 安全地根据索引获取地区分段
     *
     * @param region 地区字符串
     * @param index  索引
     * @return 地区分段值，如果无法获取则返回 null
     */
    private static String getRegionPartSafe(String region, int index) {
        if (StrUtil.isEmpty(region)) {
            return null;
        }
        List<String> list = StrUtil.strToStrList(region, "\\|");
        if (list.size() <= index) {
            return null;
        }
        return list.get(index);
    }

    /**
     * 根据地区分段获取值，处理 "0" 和空白的情况
     *
     * @param part 地区分段
     * @return 处理后的值
     */
    private static String formatRegionPart(String part) {
        if (StrUtil.isEmpty(part) || "0".equals(part)) {
            return BaseIpConstants.UNKNOWN;
        }
        return part;
    }

}