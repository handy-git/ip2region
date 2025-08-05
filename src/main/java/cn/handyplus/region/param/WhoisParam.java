package cn.handyplus.region.param;

import lombok.Data;

/**
 * 太平洋网络IP地址
 * <a href="https://whois.pconline.com.cn/">太平洋网络IP地址</a>
 *
 * @author handy
 * @since 1.3.0
 */
@Data
public class WhoisParam {

    /**
     * 查询的 IP 地址
     */
    private String ip;

    /**
     * 所在省份名称，例如："上海市"
     */
    private String pro;

    /**
     * 省份代码，例如："310000"
     */
    private String proCode;

    /**
     * 所在城市名称，例如："上海市"
     */
    private String city;

    /**
     * 城市代码，例如："310000"
     */
    private String cityCode;

    /**
     * 所在地区（区或县）名称，例如："浦东新区"
     */
    private String region;

    /**
     * 地区代码，例如："310115"
     */
    private String regionCode;

    /**
     * 完整地址描述（含运营商信息），例如："上海市浦东新区 电信ADSL"
     */
    private String addr;

    /**
     * 地区的全称路径，部分接口可能为空，保留字段
     */
    private String regionNames;

    /**
     * 错误信息字段，正常时为空，出错时可能包含错误提示
     */
    private String err;
}
