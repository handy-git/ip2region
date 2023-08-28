package cn.handyplus.region.command.player;

import cn.handyplus.lib.command.IHandyCommandEvent;
import cn.handyplus.lib.util.AssertUtil;
import cn.handyplus.lib.util.BaseUtil;
import cn.handyplus.lib.util.MessageUtil;
import cn.handyplus.region.Ip2region;
import cn.handyplus.region.constants.IpConstants;
import cn.handyplus.region.service.Ip2regionService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * 隐藏
 *
 * @author handy
 */
public class HideCommand implements IHandyCommandEvent {

    @Override
    public String command() {
        return "hide";
    }

    @Override
    public String permission() {
        return "ip2region.hide";
    }

    @Override
    public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // 是否为玩家
        Player player = AssertUtil.notPlayer(sender, BaseUtil.getLangMsg("noPlayerFailureMsg"));
        new BukkitRunnable() {
            @Override
            public void run() {
                Ip2regionService.getInstance().update(player.getUniqueId().toString(), false);
                IpConstants.PLAYER_SHOW_MAP.put(player.getUniqueId(), false);
                MessageUtil.sendMessage(player, BaseUtil.getLangMsg("hideMsg"));
            }
        }.runTaskAsynchronously(Ip2region.getInstance());
    }

}