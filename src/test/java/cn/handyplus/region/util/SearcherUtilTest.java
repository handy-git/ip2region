package cn.handyplus.region.util;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

public class SearcherUtilTest {

    @SneakyThrows
    @BeforeAll
    public static void create() {
        // 获取 classpath 下的资源
        URL resource = SearcherUtilTest.class.getClassLoader().getResource("ip2region_v4.xdb");
        if (resource == null) {
            throw new IllegalStateException("ip2region_v4.xdb 文件未找到！");
        }
        File dbFile = Paths.get(resource.toURI()).toFile();
        ConfigUtil.V4_DB_PATH = dbFile.getPath();

        // 获取 classpath 下的资源
        URL resourceV6 = SearcherUtilTest.class.getClassLoader().getResource("ip2region_v6.xdb");
        if (resourceV6 == null) {
            throw new IllegalStateException("ip2region_v6.xdb 文件未找到！");
        }
        File dbFileV6 = Paths.get(resourceV6.toURI()).toFile();
        ConfigUtil.V6_DB_PATH = dbFileV6.getPath();
    }

    @Test
    public void getIpRegion() {
        // ipv4
        String ipv4 = SearcherUtil.getIpRegion("119.75.217.109");
        Assertions.assertEquals("中国|北京|北京市|电信", ipv4);
        // ipv6
        String ipv6 = SearcherUtil.getIpRegion("240e:e9:6002:1ac:0:ff:b07e:36c5");
        Assertions.assertEquals("中国|北京市|北京市|专线用户", ipv6);
    }

}