package cn.handyplus.region.command.player;

import cn.handyplus.lib.command.IHandyCommandEvent;
import cn.handyplus.lib.util.AssertUtil;
import cn.handyplus.lib.util.BaseUtil;
import cn.handyplus.lib.util.MessageUtil;
import cn.handyplus.region.constants.BaseIpConstants;
import cn.handyplus.region.service.Ip2regionService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
    public boolean isAsync() {
        return true;
    }

    @Override
    public void onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // 是否为玩家
        Player player = AssertUtil.notPlayer(sender, BaseUtil.getMsgNotColor("noPlayerFailureMsg"));
        Ip2regionService.getInstance().update(player.getUniqueId().toString(), true);
        BaseIpConstants.PLAYER_SHOW_MAP.put(player.getUniqueId(), true);
        MessageUtil.sendMessage(player, BaseUtil.getMsgNotColor("showMsg"));
    }

}