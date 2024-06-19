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

    IP_API("ipApi");

    public final String ipGetType;

}
