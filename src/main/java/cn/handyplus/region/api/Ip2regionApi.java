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
     * @return 例如 中国上海上海市电信
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
     * @return 例如 上海
     */
    public static String getProvincial(String ip) {
        return "0";
    }

    /**
     * 获取市
     *
     * @param ip ip地址
     * @return 例如 上海市
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

}