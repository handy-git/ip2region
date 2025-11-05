package cn.handyplus.region.api;

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
        return "0";
    }

    /**
     * 获取国家
     *
     * @param ip ip地址
     * @return 例如 中国
     */
    public static String getNational(String ip) {
        return "0";
    }

    /**
     * 获取省
     *
     * @param ip ip地址
     * @return 例如 北京
     */
    public static String getProvincial(String ip) {
        return "0";
    }

    /**
     * 获取市
     *
     * @param ip ip地址
     * @return 例如 北京市
     */
    public static String getMunicipal(String ip) {
        return "0";
    }

    /**
     * 获取运营商
     *
     * @param ip ip地址
     * @return 例如 电信
     */
    public static String getServiceProvider(String ip) {
        return "0";
    }

    /**
     * 获取区/县
     *
     * @param ip ip 地址
     * @return 例如 浦东新区
     * @since 2.0.0
     */
    public static String getDistrict(String ip) {
        return "0";
    }

}