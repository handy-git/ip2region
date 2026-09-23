package cn.handyplus.region.param;

import lombok.Data;

/**
 * 腾讯地图 IP 定位响应
 * <a href="https://lbs.qq.com/service/webService/webServiceGuide/webServiceIp">腾讯地图 IP 定位</a>
 *
 * @author handy
 */
@Data
public class TencentIpParam {

    /**
     * 状态码，0 表示成功
     */
    private int status;

    /**
     * 状态说明
     */
    private String message;

    /**
     * 定位结果
     */
    private TencentResultParam result;
}
