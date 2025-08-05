package cn.handyplus.region.param;

import lombok.Data;

/**
 * 对接 VORE-API
 * <a href="https://api.vore.top">VORE-API</a>
 *
 * @author handy
 * @since 1.3.0
 */
@Data
public class VoreApiParam {

    /**
     * 返回状态码，例如 200 表示成功
     */
    private int code;

    /**
     * 返回信息，例如 "SUCCESS"
     */
    private String msg;

    /**
     * IP 地理位置信息
     */
    private IpData ipdata;

    /**
     * 内部类：封装 IP 的地理信息详情
     */
    @Data
    public static class IpData {
        /**
         * 一级地域信息，例如省份 "河北省"
         */
        private String info1;

        /**
         * 二级地域信息，例如城市 "廊坊市"
         */
        private String info2;

        /**
         * 三级地域信息，例如县区 "固安"
         */
        private String info3;

        /**
         * 运营商信息，例如 "电信"
         */
        private String isp;
    }

}
