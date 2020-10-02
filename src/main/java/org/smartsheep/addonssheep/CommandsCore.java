package org.smartsheep.addonssheep;

import ChestGUI.CoreGiftpack;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class CommandsCore implements CommandExecutor {

    private final MainClass PluginClass = MainClass.getPlugin(MainClass.class);

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player CommandSenderPlayer;

        Bukkit.getLogger().info("[AddonsSheep](Command-Debug) Command string argv is: " + Arrays.toString(strings));

        if(command.getName().equalsIgnoreCase("movement")) {

            // 处理没有 subCommands
            if (strings.length <= 0) return false;

            // 判断执行者是否是玩家
            boolean CommandSenderIsPlayer = commandSender instanceof Player;
            if (!CommandSenderIsPlayer) {
                commandSender.sendMessage("§6[AddonsSheep] §cYou must be a player");
                return true;
            } else CommandSenderPlayer = (Player) commandSender;

            // 处理 fly 命令
            if (strings[0].equalsIgnoreCase("fly")) {

                // 如果玩家不是 Op
                if (!CommandSenderPlayer.isOp() && MainClass.MainConfig.getInt("movementConfig.flyNeedAdmin") == 1) {
                    CommandSenderPlayer.sendMessage("§6[AddonsSheep] §cYou are not Op inoperable");
                    return true;
                }

                // 启动 fly
                else if (strings.length > 1 && strings[1].equalsIgnoreCase("on")) {
                    CommandSenderPlayer.setAllowFlight(true);
                    CommandSenderPlayer.setFlying(true);

                    Bukkit.getLogger().info("[AddonsSheep](Fly-Tips) Set the " + commandSender.getName() + "'s flight mode to True, Check it !");
                    CommandSenderPlayer.sendMessage("§6[AddonsSheep] §bSet the §e" + commandSender.getName() + "§b's flight mode to §eTrue");
                }

                // 关闭 fly
                else if (strings.length > 1 && strings[1].equalsIgnoreCase("off")) {
                    CommandSenderPlayer.setAllowFlight(false);
                    CommandSenderPlayer.setFlying(false);

                    Bukkit.getLogger().info("[AddonsSheep](Fly|Tips) Set the " + commandSender.getName() + "'s flight mode to False, Check it !");
                    CommandSenderPlayer.sendMessage("§6[AddonsSheep] §bSet the §e" + commandSender.getName() + "§b's flight mode to §eFalse");
                }

                // 设置 fly 速度
                else if (strings.length > 1 && strings[1].equalsIgnoreCase("speed")) {
                    // 判断是否有 speed 输入
                    if (strings.length > 3) {
                        CommandSenderPlayer.sendMessage("§6[AddonsSheep] §4You did not enter the speed parameter, cannot operate");
                        return true;
                    }

                    // 判断 speed 是否过于庞大
                    if (Float.parseFloat(strings[2]) > 1.0f && Float.parseFloat(strings[2]) < 0.0f) {
                        CommandSenderPlayer.sendMessage("§6[AddonsSheep] §bThe speed you entered is too large, the maximum value is 1, the minimum value is 0");
                        return true;
                    }

                    // 处理 speed 是 0 的情况
                    if (Float.parseFloat(strings[2]) == 0.0f) {
                        CommandSenderPlayer.sendMessage("§6[AddonsSheep] §bThe speed you entered is 0, which is automatically converted to 0.1");
                        CommandSenderPlayer.setFlySpeed(Float.parseFloat("0.1"));
                        commandSender.sendMessage("§6[AddonsSheep] §bSet the §e" + commandSender.getName() + "§b's flight speed to §e0.1");
                        return true;
                    }

                    CommandSenderPlayer.setFlySpeed(Float.parseFloat(strings[2]));
                    commandSender.sendMessage("§6[AddonsSheep] §bSet the §e" + commandSender.getName() + "§b's flight speed to §e" + strings[2]);
                }

                // 未知 fly 命令
                else {
                    commandSender.sendMessage("§6[AddonsSheep] §bYou not Enter fly subcommand using subcommand.");

                    // 处理可用命令
                    StringBuilder availableCommandStringBuilder = new StringBuilder();
                    String availableCommandStringList;
                    for (int i = 0; i < MovementCommandWareHouse.FlyCommandValue1.length; i++) {
                        if (i != MovementCommandWareHouse.FlyCommandValue1.length - 1)
                            availableCommandStringBuilder.append(MovementCommandWareHouse.FlyCommandValue1[i]).append(", ");
                        else
                            availableCommandStringBuilder.append(MovementCommandWareHouse.FlyCommandValue1[i]);
                    }

                    // 转换 StringBuilder
                    availableCommandStringList = availableCommandStringBuilder.toString();
                    commandSender.sendMessage("§6[AddonsSheep] §bAvailable now command is: §2" + availableCommandStringList);
                }
            } else if (strings[0].equalsIgnoreCase("lobby")) {

                // 启用主城命令
                if (MainClass.MainConfig.getInt("movementConfig.enableMainCity") == 0) {

                    if (strings.length == 1) {
                        if (commandSender instanceof Player) {
                            Location MainCityLocation = new Location(Bukkit.getWorld("world"),
                                    MainClass.MainConfig.getInt("movementConfig.mainCityX"),
                                    MainClass.MainConfig.getInt("movementConfig.mainCityY"),
                                    MainClass.MainConfig.getInt("movementConfig.mainCityZ"));
                            CommandSenderPlayer.teleport(MainCityLocation);
                            commandSender.sendMessage("§6[AddonsSheep] §bWELCOME TO §l[§r§eMainCity§l]§r §b!");

                        } else {
                            commandSender.sendMessage("§6[AddonsSheep] §cYou must a player!");
                            return true;
                        }
                    } else if (strings.length == 2 && strings[1].equalsIgnoreCase("set")) {
                        if (commandSender instanceof Player) {
                            Location NewMainCityLocation = CommandSenderPlayer.getLocation();

                            MainClass.MainConfig.set("movementConfig.mainCityX", ((int) NewMainCityLocation.getX()));
                            MainClass.MainConfig.set("movementConfig.mainCityY", ((int) NewMainCityLocation.getY()));
                            MainClass.MainConfig.set("movementConfig.mainCityZ", ((int) NewMainCityLocation.getZ()));

                            commandSender.sendMessage("§6[AddonsSheep] §bSET §l[§r§eMainCity§l]§r §bCOMPLETE !");

                            PluginClass.saveConfig();
                            commandSender.sendMessage("§6[AddonsSheep] §bSAVE §l[§r§eMainCity§l]§r §bLOCATION COMPLETE !");

                        } else {
                            commandSender.sendMessage("§6[AddonsSheep] §cYou must a player!");
                            return true;
                        }
                    }

                    else if(strings.length == 2 && strings[1].equalsIgnoreCase("reset")) {
                        Location NewMainCityLocation = new Location(Bukkit.getWorld("world"), 0, 100, 0);

                        MainClass.MainConfig.set("movementConfig.mainCityX", ((int) NewMainCityLocation.getX()));
                        MainClass.MainConfig.set("movementConfig.mainCityY", ((int) NewMainCityLocation.getY()));
                        MainClass.MainConfig.set("movementConfig.mainCityZ", ((int) NewMainCityLocation.getZ()));

                        commandSender.sendMessage("§6[AddonsSheep] §bSET §l[§r§eMainCity§l]§r §bCOMPLETE !");

                        PluginClass.saveConfig();
                        commandSender.sendMessage("§6[AddonsSheep] §bSAVE §l[§r§eMainCity§l]§r §bLOCATION COMPLETE !");
                    }

                } else {
                    commandSender.sendMessage("§6[AddonsSheep] §cMain City Send is Disable");
                    return true;
                }

//            else if(strings[1].equalsIgnoreCase("shout")) {
//                String ShoutMessage = ""; ShoutMessage.join()
//            }

            } else {
                commandSender.sendMessage("§6[AddonsSheep] §cUnknown movement subcommand");
                return true;
            }

            return true;
        }

        else if(command.getName().equalsIgnoreCase("open")) {

            // 处理没有 OpenBoxName
            if (strings.length <= 0) return false;

            // 判断执行者是否是玩家
            boolean CommandSenderIsPlayer = commandSender instanceof Player;
            if (!CommandSenderIsPlayer) {
                commandSender.sendMessage("§6[AddonsSheep] §cYou must be a player");
                return true;
            } else CommandSenderPlayer = (Player) commandSender;

            CoreGiftpack.SummonGiftBox(CommandSenderPlayer); return true;
        }

        return false;
    }
}
