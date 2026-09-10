package cn.handyplus.region.listener;

import cn.handyplus.lib.annotation.HandyListener;
import cn.handyplus.region.constants.BaseIpConstants;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * 玩家被服务器踢出事件
 * 清理缓存
 *
 * @author handy
 */
@HandyListener
public class PlayerQuitEventListener implements Listener {

    /**
     * 玩家离开服务器事件.
     *
     * @param event 事件
     */
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        BaseIpConstants.PLAYER_REGION_MAP.remove(player.getUniqueId());
        BaseIpConstants.PLAYER_SHOW_MAP.remove(player.getUniqueId());
    }

}