package cn.handyplus.region.listener;

import cn.handyplus.lib.annotation.HandyListener;
import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.expand.adapter.HandySchedulerUtil;
import cn.handyplus.lib.util.HandyHttpUtil;
import cn.handyplus.region.constants.BaseIpConstants;
import cn.handyplus.region.enter.Ip2regionEnter;
import cn.handyplus.region.service.Ip2regionService;
import cn.handyplus.region.util.ConfigUtil;
import cn.handyplus.region.util.IpUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Optional;

/**
 * 登录事件
 *
 * @author handy
 */
@HandyListener
public class PlayerJoinEventListener implements Listener {

    /**
     * 玩家进入
     *
     * @param event 事件
     */
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        HandySchedulerUtil.runTaskAsynchronously(() -> {
            Optional<Ip2regionEnter> ip2regionEnterOptional = Ip2regionService.getInstance().findByPlayerUuid(player.getUniqueId().toString());
            if (!ip2regionEnterOptional.isPresent()) {
                Ip2regionEnter enter = new Ip2regionEnter();
                enter.setPlayerName(player.getName());
                enter.setPlayerUuid(player.getUniqueId().toString());
                enter.setShowEnable(true);
                Ip2regionService.getInstance().add(enter);
                BaseIpConstants.PLAYER_SHOW_MAP.put(player.getUniqueId(), true);
            } else {
                Ip2regionEnter ip2regionEnter = ip2regionEnterOptional.get();
                BaseIpConstants.PLAYER_SHOW_MAP.put(player.getUniqueId(), ip2regionEnter.getShowEnable());
            }
            IpUtil.getPlayerRegion(player);
        });
    }

    /**
     * op进入服务器发送更新提醒
     *
     * @param event 事件
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onOpPlayerJoin(PlayerJoinEvent event) {
        // op登录发送更新提醒
        if (!ConfigUtil.CONFIG.getBoolean(BaseConstants.IS_CHECK_UPDATE_TO_OP_MSG)) {
            return;
        }
        HandyHttpUtil.checkVersion(event.getPlayer(), BaseIpConstants.PLUGIN_VERSION_URL);
    }

}