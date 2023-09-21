package cn.handyplus.region.param;

import lombok.Data;

/**
 * @author handy
 * @since 1.1.0
 */
@Data
public class IpApiParam {

    private String status;
    private String country;
    private String countryCode;
    private String region;
    private String regionName;
    private String city;
    private String zip;
    private Double lat;
    private Double lon;
    private String timezone;
    private String isp;
    private String org;
    private String as;
    private String query;

    private String message;
}


