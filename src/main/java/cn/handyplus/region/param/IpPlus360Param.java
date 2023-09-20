package cn.handyplus.region.param;

import lombok.Data;

/**
 * @author handy
 * @since 1.1.0
 */
@Data
public class IpPlus360Param {

    private String code;
    private IpPlus360ParamData data;
    private String msg;

    @Data
    public static class IpPlus360ParamData {
        /**
         * 国家
         */
        private String country;
        /**
         * 区域
         */
        private String continent;
        /**
         * 归属
         */
        private String owner;
        /**
         * 省
         */
        private String prov;
        /**
         * 市
         */
        private String city;
    }

}


