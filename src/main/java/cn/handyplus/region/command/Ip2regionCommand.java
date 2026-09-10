package cn.handyplus.region.command;

import cn.handyplus.lib.annotation.HandyCommand;
import cn.handyplus.lib.command.HandyCommandWrapper;
import cn.handyplus.lib.util.BaseUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 命令
 *
 * @author handy
 */
@HandyCommand(name = "ip2region")
public class Ip2regionCommand implements TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String @NotNull [] args) {
        // 判断指令是否正确
        if (args.length < 1) {
            return true;
        }
        HandyCommandWrapper.onCommand(sender, cmd, label, args, BaseUtil.getLangMsg("noPermission"));
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String @NotNull [] args) {
        return HandyCommandWrapper.onTabComplete(sender, cmd, label, args);
    }

}