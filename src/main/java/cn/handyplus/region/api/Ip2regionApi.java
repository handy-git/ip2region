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
     * @return 例如 中国上海上海市电信
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
        if (StrUtil.isEmpty(region)) {
            return BaseIpConstants.UNKNOWN;
        }
        List<String> list = StrUtil.strToStrList(region, "\\|");
        String national = list.get(0);
        return "0".equals(national) ? BaseIpConstants.UNKNOWN : national;
    }

    /**
     * 获取省
     *
     * @param ip ip地址
     * @return 例如 上海
     */
    public static String getProvincial(String ip) {
        String region = IpUtil.getIpRegion(ip);
        if (StrUtil.isEmpty(region)) {
            return BaseIpConstants.UNKNOWN;
        }
        List<String> list = StrUtil.strToStrList(region, "\\|");
        String provincial = list.get(2);
        return "0".equals(provincial) ? BaseIpConstants.UNKNOWN : provincial;
    }

    /**
     * 获取市
     *
     * @param ip ip地址
     * @return 例如 上海市
     */
    public static String getMunicipal(String ip) {
        String region = IpUtil.getIpRegion(ip);
        if (StrUtil.isEmpty(region)) {
            return BaseIpConstants.UNKNOWN;
        }
        List<String> list = StrUtil.strToStrList(region, "\\|");
        String municipal = list.get(3);
        return "0".equals(municipal) ? BaseIpConstants.UNKNOWN : municipal;
    }

    /**
     * 获取运营商
     *
     * @param ip ip地址
     * @return 例如 电信
     */
    public static String getServiceProvider(String ip) {
        String region = IpUtil.getIpRegion(ip);
        if (StrUtil.isEmpty(region)) {
            return BaseIpConstants.UNKNOWN;
        }
        List<String> list = StrUtil.strToStrList(region, "\\|");
        String serviceProvider = list.get(4);
        return "0".equals(serviceProvider) ? BaseIpConstants.UNKNOWN : serviceProvider;
    }

}