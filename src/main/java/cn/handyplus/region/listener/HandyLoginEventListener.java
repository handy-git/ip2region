package cn.handyplus.region.listener;

import cn.handyplus.lib.annotation.HandyListener;
import cn.handyplus.lib.internal.HandyLoginEvent;
import cn.handyplus.lib.internal.HandySchedulerUtil;
import cn.handyplus.lib.util.HandyHttpUtil;
import cn.handyplus.region.constants.BaseIpConstants;
import cn.handyplus.region.enter.Ip2regionEnter;
import cn.handyplus.region.service.Ip2regionService;
import cn.handyplus.region.util.IpUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Optional;

/**
 * 登录事件
 *
 * @author handy
 */
@HandyListener
public class HandyLoginEventListener implements Listener {

    /**
     * 玩家进入
     *
     * @param event 事件
     */
    @EventHandler
    public void onEvent(HandyLoginEvent event) {
        Player player = event.getPlayer();
        HandySchedulerUtil.runTaskAsynchronously(() -> {
            Optional<Ip2regionEnter> ip2regionEnterOptional = Ip2regionService.getInstance().findByPlayerUuid(player.getUniqueId());
            if (!ip2regionEnterOptional.isPresent()) {
                Ip2regionEnter enter = new Ip2regionEnter();
                enter.setPlayerName(player.getName());
                enter.setPlayerUuid(player.getUniqueId());
                enter.setShowEnable(true);
                enter.setIp(IpUtil.getIp(player));
                enter.setIpType(IpUtil.getIpType(player));
                Ip2regionService.getInstance().add(enter);
                BaseIpConstants.PLAYER_SHOW_MAP.put(player.getUniqueId(), true);
            } else {
                Ip2regionService.getInstance().updateIp(player);
                Ip2regionEnter ip2regionEnter = ip2regionEnterOptional.get();
                BaseIpConstants.PLAYER_SHOW_MAP.put(player.getUniqueId(), Boolean.TRUE.equals(ip2regionEnter.getShowEnable()));
            }
            IpUtil.getPlayerRegion(player);
        });
        // OP 进入服务器发送更新提醒
        HandyHttpUtil.checkVersion(player);
    }

}