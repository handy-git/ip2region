package cn.handyplus.region.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VoreApiUtilTest {

    @Test
    public void getIpRegion() {
        String ipRegion = VoreApiUtil.getIpRegion("119.75.217.109");
        Assertions.assertEquals("0|0|北京市|北京市|百度|0", ipRegion);
    }

}
