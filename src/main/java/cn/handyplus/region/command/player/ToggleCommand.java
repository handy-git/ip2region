package cn.handyplus.region.command.player;

import cn.handyplus.lib.command.IHandyCommandEvent;
import cn.handyplus.lib.util.AssertUtil;
import cn.handyplus.region.Ip2region;
import cn.handyplus.region.constants.IpConstants;
import cn.handyplus.region.enter.Ip2regionEnter;
import cn.handyplus.region.service.Ip2regionService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * 切换
 *
 * @author handy
 */
public class ToggleCommand implements IHandyCommandEvent {

    @Override
    public String command() {
        return "toggle";
    }

    @Override
    public String permission() {
        return "ip2region.toggle";
    }

    @Override
    public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // 是否为玩家
        Player player = AssertUtil.notPlayer(sender, "&4该命令只能玩家执行");
        new BukkitRunnable() {
            @Override
            public void run() {
                Ip2regionEnter ip2regionEnter = Ip2regionService.getInstance().findByPlayerUuid(player.getUniqueId().toString());
                Ip2regionService.getInstance().update(player.getUniqueId().toString(), !ip2regionEnter.getShowEnable());
                IpConstants.PLAYER_SHOW_MAP.put(player.getUniqueId(), !ip2regionEnter.getShowEnable());
            }
        }.runTaskAsynchronously(Ip2region.getInstance());
    }

}