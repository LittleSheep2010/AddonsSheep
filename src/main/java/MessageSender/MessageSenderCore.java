package MessageSender;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class MessageSenderCore {
    public static void SendActionbarTitle(ConsoleCommandSender ConsoleCommandSender, Player Player, String Message) {
        Bukkit.dispatchCommand(ConsoleCommandSender, String.format("title %s actionbar \"%s\"", Player.getName(), Message));
    }
}
