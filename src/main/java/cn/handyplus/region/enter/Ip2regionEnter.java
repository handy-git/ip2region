package cn.handyplus.region.enter;

import cn.handyplus.lib.annotation.TableField;
import cn.handyplus.lib.annotation.TableName;
import cn.handyplus.lib.db.enums.IndexEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 玩家IP数据显示记录
 *
 * @author handy
 */
@Getter
@Setter
@TableName(value = "ip2region", comment = "玩家IP数据显示记录")
public class Ip2regionEnter {
    @TableField(value = "id", comment = "ID")
    private Integer id;

    @TableField(value = "player_name", comment = "玩家名称")
    private String playerName;

    @TableField(value = "player_uuid", comment = "玩家uuid", notNull = true, indexEnum = IndexEnum.INDEX)
    private String playerUuid;

    @TableField(value = "show_enable", comment = "是否显示")
    private Boolean showEnable;

    @TableField(value = "ip", comment = "登陆IP")
    private String ip;

    @TableField(value = "ip_type", comment = "IP类型")
    private String ipType;

}
