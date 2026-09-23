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
    public native static String getRegion(String ip);

    /**
     * 获取国家
     *
     * @param ip ip地址
     * @return 例如 中国
     */
    public native static String getNational(String ip);

    /**
     * 获取省
     *
     * @param ip ip地址
     * @return 例如 北京
     */
    public native static String getProvincial(String ip);

    /**
     * 获取市
     *
     * @param ip ip地址
     * @return 例如 北京市
     */
    public native static String getMunicipal(String ip);

    /**
     * 获取运营商
     *
     * @param ip ip地址
     * @return 例如 电信
     */
    public native static String getServiceProvider(String ip);

    /**
     * 获取区/县
     *
     * @param ip ip 地址
     * @return 例如 浦东新区
     * @since 2.0.0
     */
    public native static String getDistrict(String ip);
}