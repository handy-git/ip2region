package cn.handyplus.region.listener;

import cn.handyplus.lib.annotation.HandyListener;
import cn.handyplus.region.Ip2region;
import cn.handyplus.region.constants.IpConstants;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * 玩家被服务器踢出事件
 * 清理缓存
 *
 * @author handy
 */
@HandyListener
public class PlayerQuitEventListener implements Listener {
    /**
     * 玩家被服务器踢出事件.
     *
     * @param event 事件
     */
    @EventHandler
    public void onKick(PlayerKickEvent event) {
        removeCache(event.getPlayer());
    }

    /**
     * 玩家离开服务器事件.
     *
     * @param event 事件
     */
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        removeCache(event.getPlayer());
    }

    /**
     * 清理缓存
     *
     * @param player 事件
     */
    private void removeCache(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                IpConstants.PLAYER_REGION_MAP.remove(player.getUniqueId());
            }
        }.runTaskAsynchronously(Ip2region.getInstance());
    }

}