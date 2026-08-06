package cn.handyplus.region.param;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 腾讯地图 IP 定位结果
 *
 * @author handy
 */
@Data
public class TencentResultParam {

    /**
     * 行政区划信息
     */
    @SerializedName("ad_info")
    private TencentAdInfoParam adInfo;
}
