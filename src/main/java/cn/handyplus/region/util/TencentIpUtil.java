package cn.handyplus.region.util;

import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.core.HttpUtil;
import cn.handyplus.lib.core.JsonUtil;
import cn.handyplus.lib.core.SecureUtil;
import cn.handyplus.lib.core.StrUtil;
import cn.handyplus.region.constants.BaseIpConstants;
import cn.handyplus.region.param.TencentAdInfoParam;
import cn.handyplus.region.param.TencentIpParam;
import org.bukkit.entity.Player;

/**
 * 对接腾讯地图 IP 定位 api
 * <a href="https://lbs.qq.com/service/webService/webServiceGuide/webServiceIp">腾讯地图 IP 定位</a>
 *
 * @author handy
 */
public class TencentIpUtil {

    private static final String SUCCESS = "Success";

    /**
     * 获取玩家地址
     *
     * @param player 玩家
     */
    protected static void getPlayerRegion(Player player) {
        String ip = BaseConstants.CONFIG.getString("testIp", IpUtil.getIp(player));
        String region = getIpRegion(ip);
        BaseIpConstants.PLAYER_REGION_MAP.put(player.getUniqueId(), region);
    }

    /**
     * 获取 IP 地址
     *
     * @param ip IP 地址
     * @return 国家|省份|城市|运营商|区县
     */
    protected static String getIpRegion(String ip) {
        if (StrUtil.isEmpty(ip)) {
            return null;
        }
        String appKey = BaseConstants.CONFIG.getString("tencentAppKey", "");
        String appSecret = BaseConstants.CONFIG.getString("tencentAppSecret", "");
        if (StrUtil.isEmpty(appKey) || StrUtil.isEmpty(appSecret)) {
            return null;
        }
        try {
            String json = HttpUtil.get(getLocationUrl(ip, appKey, appSecret));
            return parseRegion(json);
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * 解析腾讯地图响应
     *
     * @param json 响应 JSON
     * @return 国家|省份|城市|运营商|区县
     */
    protected static String parseRegion(String json) {
        if (!JsonUtil.isTypeJsonObject(json)) {
            return null;
        }
        TencentIpParam param = JsonUtil.toBean(json, TencentIpParam.class);
        if (param == null || param.getStatus() != 0 || !SUCCESS.equalsIgnoreCase(param.getMessage()) || param.getResult() == null || param.getResult().getAdInfo() == null) {
            return null;
        }
        TencentAdInfoParam adInfo = param.getResult().getAdInfo();
        return IpUtil.getStr(adInfo.getNation()) + "|" + IpUtil.getStr(adInfo.getProvince()) + "|" + IpUtil.getStr(adInfo.getCity()) + "|0|" + IpUtil.getStr(adInfo.getDistrict());
    }

    /**
     * 生成腾讯地图请求地址
     *
     * @param ip        IP 地址
     * @param appKey    腾讯地图 Key
     * @param appSecret 腾讯地图 SecretKey
     * @return 请求地址
     */
    private static String getLocationUrl(String ip, String appKey, String appSecret) {
        return BaseIpConstants.TENCENT_API + "?ip=" + ip + "&key=" + appKey + "&sig=" + getSn(ip, appKey, appSecret);
    }

    /**
     * 生成腾讯地图请求签名
     *
     * @param ip        IP 地址
     * @param appKey    腾讯地图 Key
     * @param appSecret 腾讯地图 SecretKey
     * @return MD5 签名
     */
    protected static String getSn(String ip, String appKey, String appSecret) {
        String query = "ip=" + ip + "&key=" + appKey;
        return SecureUtil.md5Str("/ws/location/v1/ip?" + query + appSecret);
    }

}
