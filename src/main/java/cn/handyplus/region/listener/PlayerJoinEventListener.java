package cn.handyplus.region.listener;

import cn.handyplus.lib.annotation.HandyListener;
import cn.handyplus.region.Ip2region;
import cn.handyplus.region.util.SearcherUtil;
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
    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent event) {
        new BukkitRunnable() {
            @Override
            public void run() {
                SearcherUtil.getInstance().getPlayerRegion(event.getPlayer());
            }
        }.runTaskAsynchronously(Ip2region.getInstance());
    }

}