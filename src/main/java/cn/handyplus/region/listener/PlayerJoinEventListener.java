package cn.handyplus.region.listener;

import cn.handyplus.lib.annotation.HandyListener;
import cn.handyplus.lib.constants.BaseConstants;
import cn.handyplus.lib.util.HandyHttpUtil;
import cn.handyplus.region.Ip2region;
import cn.handyplus.region.constants.IpConstants;
import cn.handyplus.region.enter.Ip2regionEnter;
import cn.handyplus.region.service.Ip2regionService;
import cn.handyplus.region.util.ConfigUtil;
import cn.handyplus.region.util.SearcherUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

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
        new BukkitRunnable() {
            @Override
            public void run() {
                Ip2regionEnter ip2regionEnter = Ip2regionService.getInstance().findByPlayerUuid(player.getUniqueId().toString());
                if (ip2regionEnter == null) {
                    Ip2regionEnter enter = new Ip2regionEnter();
                    enter.setPlayerName(player.getName());
                    enter.setPlayerUuid(player.getUniqueId().toString());
                    enter.setShowEnable(true);
                    Ip2regionService.getInstance().add(enter);
                    IpConstants.PLAYER_SHOW_MAP.put(player.getUniqueId(), true);
                } else {
                    IpConstants.PLAYER_SHOW_MAP.put(player.getUniqueId(), ip2regionEnter.getShowEnable());
                }
                SearcherUtil.getPlayerRegion(player);
            }
        }.runTaskAsynchronously(Ip2region.getInstance());
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
        HandyHttpUtil.checkVersion(event.getPlayer(), IpConstants.PLUGIN_VERSION_URL);
    }

}