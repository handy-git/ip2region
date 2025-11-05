package cn.handyplus.region.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IpApiUtilTest {

    @Test
    public void getIpRegion() {
        // ipv4
        String ipv4 = IpApiUtil.getIpRegion("119.75.217.109");
        Assertions.assertEquals("中国|北京市|北京|Beijing Baidu Netcom Science and Technology Co., Ltd.", ipv4);
        // ipv6
        String ipv6 = IpApiUtil.getIpRegion("240e:e9:6002:1ac:0:ff:b07e:36c5");
        Assertions.assertEquals("中国|北京市|北京|China Telecom", ipv6);
    }

}
