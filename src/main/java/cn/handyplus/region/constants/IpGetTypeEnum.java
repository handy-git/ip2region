package cn.handyplus.region.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 获取ip的类型
 *
 * @author handy
 * @since 1.1.3
 */
@Getter
@RequiredArgsConstructor
public enum IpGetTypeEnum {

    /**
     * 各种渠道
     */
    OFFLINE("offline"),

    IP_PLUS_360("ipPlus360"),

    IP_API("ipApi"),

    WHOIS("whois"),

    VORE_API("voreApi"),

    TENCENT("tencent"),
    ;

    public final String ipGetType;

    /**
     * 根据配置的数据源字符串获取枚举，忽略大小写
     *
     * @param type 数据源字符串
     * @return 对应的枚举，未匹配到时默认返回 OFFLINE
     * @since 2.4.0
     */
    public static IpGetTypeEnum fromType(String type) {
        IpGetTypeEnum[] values = values();
        for (IpGetTypeEnum value : values) {
            if (value.getIpGetType().equalsIgnoreCase(type)) {
                return value;
            }
        }
        return OFFLINE;
    }

}
