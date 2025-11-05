package cn.handyplus.region.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VoreApiUtilTest {

    @Test
    public void getIpRegion() {
        // ipv4
        String ipv4 = VoreApiUtil.getIpRegion("119.75.217.109");
        Assertions.assertEquals("0|北京市|海淀区|百度|0", ipv4);
        // ipv6 准确度低
        String ipv6 = VoreApiUtil.getIpRegion("240e:e9:6002:1ac:0:ff:b07e:36c5");
        Assertions.assertEquals("0|江苏省|南京市|电信|江宁区", ipv6);
    }

}
