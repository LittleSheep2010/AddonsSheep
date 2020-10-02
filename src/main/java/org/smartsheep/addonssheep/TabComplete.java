package org.smartsheep.addonssheep;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TabComplete implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        // Movement 命令处理
        if(command.getName().equalsIgnoreCase("movement")) {

            // 返回 1 Subcommand 列表
            if(strings.length == 1)
                return Arrays.asList(MovementCommandWareHouse.Subcommands);
            // 返回 2 Value 列表(Fly)
            if(strings.length == 2 && strings[0].equalsIgnoreCase(MovementCommandWareHouse.Subcommands[0]))
                return Arrays.asList(MovementCommandWareHouse.FlyCommandValue1);
            // 返回 2 Value 列表(Lobby)
            if(strings.length == 2 && strings[0].equalsIgnoreCase(MovementCommandWareHouse.Subcommands[1]) && commandSender.isOp())
                return Arrays.asList(MovementCommandWareHouse.LobbyCommandValue1);
            // 返回 ? 空列表
            return new ArrayList<>();
        }

        // Open 命令处理
        else if(command.getName().equalsIgnoreCase("open")) {

            // 返回 1 OpenBoxName 列表
            if(strings.length == 1)
                return Arrays.asList(OpenCommandWareHouse.OpenBoxName);
            // 返回 ? 空列表
            return new ArrayList<>();
        }

        // 其他命令处理
        return new ArrayList<>();
    }
}

class MovementCommandWareHouse {
    // @TODO "shout", "announcement"
    public static final String[] Subcommands = {"fly", "lobby"};

    // Fly 命令 Value
    public static final String[] FlyCommandValue1 = {"on", "off", "speed"};

    // Lobby 命令 Value
    public static final String[] LobbyCommandValue1 = {"set", "reset"};
}

class OpenCommandWareHouse {
    public static final String[] OpenBoxName = {"gift"};
}