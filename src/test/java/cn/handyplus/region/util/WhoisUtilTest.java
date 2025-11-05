package cn.handyplus.region.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WhoisUtilTest {

    @Test
    public void getIpRegion() {
        // ipv4
        String ipv4 = WhoisUtil.getIpRegion("119.75.217.109");
        Assertions.assertEquals("0|北京市|北京市|0|0", ipv4);
        // ipv6 准确度低
        String ipv6 = WhoisUtil.getIpRegion("240e:e9:6002:1ac:0:ff:b07e:36c5");
        Assertions.assertEquals("0|上海市|上海市|0|闵行区", ipv6);
    }

}
