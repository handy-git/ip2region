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
        URL resource = SearcherUtilTest.class.getClassLoader().getResource("ip2region.xdb");
        if (resource == null) {
            throw new IllegalStateException("ip2region.xdb 文件未找到！");
        }
        File dbFile = Paths.get(resource.toURI()).toFile();
        ConfigUtil.DB_PATH = dbFile.getPath();
    }

    @Test
    public void getIpRegion() {
        // 查询百度首页的 ip
        String ipRegion = SearcherUtil.getIpRegion("119.75.217.109");
        Assertions.assertEquals("中国|0|北京|北京市|电信", ipRegion);
    }

}
