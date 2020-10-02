package org.smartsheep.addonssheep;

import MessageSender.MessageSenderCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ListenerCore implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent eventJoin) {

        // WelcomeMessage 输出
        if(MainClass.MainConfig.getInt("welcomeMessage.enableWelcomeMessage") == 0) {
            String ServerName = MainClass.MainConfig.getString("welcomeMessage.serverName");

            Location PlayerLocation = eventJoin.getPlayer().getLocation();
            String PlayerX = String.valueOf((int) PlayerLocation.getX());
            String PlayerY = String.valueOf((int) PlayerLocation.getY());
            String PlayerZ = String.valueOf((int) PlayerLocation.getZ());
            String PlayerD = eventJoin.getPlayer().getWorld().getEnvironment().name();

            if(eventJoin.getPlayer().isOp()) {
                eventJoin.getPlayer().sendMessage("§7§l==========");
                eventJoin.getPlayer().sendMessage("§bWELCOME TO §6§l"+ServerName+"§r");
                eventJoin.getPlayer().sendMessage("§bYou are §c§lServer admin,§r§b dear "+eventJoin.getPlayer().getName());
                eventJoin.getPlayer().sendMessage("§bYou are at §c[X]§e"+PlayerX+" §2[Y]§e"+PlayerY+" §9[Z]§e"+PlayerZ+" §8[DIM]§e"+PlayerD);
                eventJoin.getPlayer().sendMessage("§7§l==========");
            } else {
                eventJoin.getPlayer().sendMessage("§7§l==========");
                eventJoin.getPlayer().sendMessage("§bWELCOME TO §6§l"+ServerName+"§r");
                eventJoin.getPlayer().sendMessage("§bWelcome, §a§l"+eventJoin.getPlayer().getName());
                eventJoin.getPlayer().sendMessage("§bYou are at §c[X]§e"+PlayerX+" §2[Y]§e"+PlayerY+" §9[Z]§e"+PlayerZ+" §8[DIM]§e"+PlayerD);
                eventJoin.getPlayer().sendMessage("§7§l==========");
            }

            // 如果要发送信息
            if(MainClass.MainConfig.getInt("welcomeMessage.enablePlayerLoginTitle") == 0) {
                eventJoin.getPlayer().sendTitle(MainClass.MainConfig.getString("welcomeMessage.playerLoginTitleContents"),
                                                MainClass.MainConfig.getString("welcomeMessage.playerLoginSubtitleContents"));
            }

            // 全局信息
            eventJoin.setJoinMessage("§6[AddonsSheep] §a§l"+eventJoin.getPlayer().getName()+"§r§7[§a+§7]§b joined §a§l"+ServerName);

            // 玩家信息
            MessageSenderCore.SendActionbarTitle(Bukkit.getConsoleSender(), eventJoin.getPlayer(), "§c§lWELCOME§r");
        }
    }

    @EventHandler
    public void onPlayerExit(PlayerQuitEvent event) {
        String ServerName = MainClass.MainConfig.getString("welcomeMessage.serverName");
        event.setQuitMessage("§6[AddonsSheep] §a§l"+event.getPlayer().getName()+"§r§7[§c-§7]§b leave §a§l"+ServerName);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String playerChatMessage = event.getMessage();
        String playerLevelString = String.valueOf(event.getPlayer().getLevel());
        Player playerIO = event.getPlayer();

        // 启用 autoFormat message
        if(MainClass.MainConfig.getInt("chatMessage.autoFormatEnable") == 0) {
            // 取消原版消息
            event.setCancelled(true);

            // 启用 adminPrefix
            if(playerIO.isOp() && MainClass.MainConfig.getInt("chatMessage.showAdminPrefix") == 0) {
                // 启用 playerLevelPrefix
                if(MainClass.MainConfig.getInt("chatMessage.showLevelPrefix") == 0)
                    Bukkit.broadcastMessage("§6["+playerIO.getName()+"]§9[Lv."+playerLevelString+"]§c[OP] §e§l>§r §7"+playerChatMessage);
                // 禁用 playerLevelPrefix
                else
                    Bukkit.broadcastMessage("§6["+playerIO.getName()+"]§c[OP] §e§l>§r §7"+playerChatMessage);
            } else {
                // 启用 playerLevelPrefix
                if(MainClass.MainConfig.getInt("chatMessage.showLevelPrefix") == 0)
                    Bukkit.broadcastMessage("§6["+playerIO.getName()+"]§9[Lv."+playerLevelString+"] §e§l>§r §7"+playerChatMessage);
                    // 禁用 playerLevelPrefix
                else
                    Bukkit.broadcastMessage("§6["+playerIO.getName()+"] §e§l>§r §7"+playerChatMessage);
            }
        }
    }
}
