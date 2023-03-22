package cn.handyplus.region.command;

import cn.handyplus.lib.annotation.HandyCommand;
import cn.handyplus.lib.command.HandyCommandFactory;
import cn.handyplus.lib.util.BaseUtil;
import cn.handyplus.region.constants.TabListEnum;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 命令
 *
 * @author handy
 */
@HandyCommand(name = "ip2region")
public class Ip2regionCommand implements TabExecutor {
    private final static String PERMISSION = "ip2region.reload";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // 判断指令是否正确
        if (args.length < 1) {
            return sendHelp(sender);
        }
        boolean rst = HandyCommandFactory.getInstance().onCommand(sender, cmd, label, args, BaseUtil.getLangMsg("noPermission"));
        if (!rst) {
            return sendHelp(sender);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> commands;
        if (!sender.hasPermission(PERMISSION)) {
            commands = new ArrayList<>();
        } else {
            commands = TabListEnum.returnList(args, args.length);
        }
        if (commands == null) {
            return null;
        }
        StringUtil.copyPartialMatches(args[args.length - 1].toLowerCase(), commands, completions);
        Collections.sort(completions);
        return completions;
    }

    /**
     * 发送帮助
     *
     * @param sender 发送人
     * @return 消息
     */
    private Boolean sendHelp(CommandSender sender) {
        if (!sender.hasPermission(PERMISSION)) {
            return true;
        }
        List<String> helps = new ArrayList<>();
        for (String help : helps) {
            sender.sendMessage(BaseUtil.replaceChatColor(help));
        }
        return true;
    }

}