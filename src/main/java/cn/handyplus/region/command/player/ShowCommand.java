package cn.handyplus.region.command.player;

import cn.handyplus.lib.api.MessageApi;
import cn.handyplus.lib.command.IHandyCommandEvent;
import cn.handyplus.lib.util.AssertUtil;
import cn.handyplus.region.Ip2region;
import cn.handyplus.region.constants.IpConstants;
import cn.handyplus.region.service.Ip2regionService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * 显示
 *
 * @author handy
 */
public class ShowCommand implements IHandyCommandEvent {

    @Override
    public String command() {
        return "show";
    }

    @Override
    public String permission() {
        return "ip2region.show";
    }

    @Override
    public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // 是否为玩家
        Player player = AssertUtil.notPlayer(sender, "&4该命令只能玩家执行");
        new BukkitRunnable() {
            @Override
            public void run() {
                Ip2regionService.getInstance().update(player.getUniqueId().toString(), true);
                IpConstants.PLAYER_SHOW_MAP.put(player.getUniqueId(), true);
                MessageApi.sendMessage(player, "&a执行成功");
            }
        }.runTaskAsynchronously(Ip2region.getInstance());
    }

}