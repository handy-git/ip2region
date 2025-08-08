package cn.handyplus.region.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IpApiUtilTest {

    @Test
    public void getIpRegion() {
        String ipRegion = IpApiUtil.getIpRegion("119.75.217.109");
        Assertions.assertEquals("中国|0|北京市|北京|Beijing Baidu Netcom Science and Technology Co., Ltd.", ipRegion);
    }

}
