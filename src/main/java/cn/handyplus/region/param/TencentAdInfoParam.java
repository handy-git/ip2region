package cn.handyplus.region.param;

import lombok.Data;

/**
 * 腾讯地图行政区划信息
 *
 * @author handy
 */
@Data
public class TencentAdInfoParam {

    /**
     * 国家
     */
    private String nation;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区县
     */
    private String district;
}
