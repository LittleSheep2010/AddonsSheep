package ChestGUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.smartsheep.addonssheep.MainClass;

import java.util.List;

public class CoreGiftpack {
    private static final MainClass PluginClass = MainClass.getPlugin(MainClass.class);

    public static void CannotSummonGiftBox(int ErrorCode, Player ExecPlayer) {
        if(ErrorCode == 404) ExecPlayer.sendMessage("§6[AddonsSheep] §cCannot found GiftBox.");
        if(ErrorCode == 500) ExecPlayer.sendMessage("§6[AddonsSheep] §cCannot claim GiftBox, Because you Already received!");
        if(ErrorCode == 502) ExecPlayer.sendMessage("§6[AddonsSheep] §cCannot found GiftBox Item, please cell server admin.");
        if(ErrorCode == 501) ExecPlayer.sendMessage("§6[AddonsSheep] §cCannot load GiftBox Item count, please cell server admin.");
    }

    public static void SummonGiftBox(Player ExecPlayer) {
        List<String> ClaimedPlayers = MainClass.MainConfig.getStringList("giftBoxConfig.claimedPlayers");
        List<String> GiftBoxItems = MainClass.MainConfig.getStringList("giftBoxConfig.giftBoxItems");
        List<Integer> GiftBoxItemsCount = MainClass.MainConfig.getIntegerList("giftBoxConfig.giftBoxItemsCount");
        String GiftBoxTitle = MainClass.MainConfig.getString("giftBoxConfig.giftBoxTitle");
        int GiftBoxUnlimitedClaim = MainClass.MainConfig.getInt("giftBoxConfig.giftBoxUnlimitedClaim");
        int GiftBoxSlot = MainClass.MainConfig.getInt("giftBoxConfig.giftBoxSlot");

        // 如果没有 Title
        if(GiftBoxTitle.equalsIgnoreCase("") || GiftBoxTitle == null) GiftBoxTitle = "GiftBox";

        // 如果没有物品
        if(GiftBoxItems.size() == 0 || GiftBoxItems == null)
        { CannotSummonGiftBox(502, ExecPlayer); return; }

        // 如果某个物品没有数量 / 数量错误
        // Step 1
        if(GiftBoxItemsCount.size() != GiftBoxItems.size())
        { CannotSummonGiftBox(501, ExecPlayer); }
        // Step 2
        for(Integer GiftBoxItemsCountChecker : GiftBoxItemsCount) {
            if(GiftBoxItemsCountChecker <= 0 || GiftBoxItemsCountChecker > 64)
            { CannotSummonGiftBox(501, ExecPlayer); }
        }

        // 检查是否有 GiftBox / 启用 GiftBox
        if(MainClass.MainConfig.getInt("giftBoxConfig.enableGiftBox") != 0)
        { CannotSummonGiftBox(404, ExecPlayer); return; }

        // 需要检查是否开过
        if(GiftBoxUnlimitedClaim == 0) {
            for(String PlayerCheck : ClaimedPlayers) {
                if(PlayerCheck.equalsIgnoreCase(ExecPlayer.getName())) { CannotSummonGiftBox(500, ExecPlayer); return; }
            }
        }

        // 创建 GUI
        final Inventory GiftBoxInventory;
        GiftBoxInventory = Bukkit.createInventory(null, GiftBoxSlot, GiftBoxTitle);

        // 初始化物品
        int ProcessCount = 0;
        for(String MaterialID : GiftBoxItems) {
            ItemStack AddItem = new ItemStack(Material.valueOf(MaterialID.toUpperCase()));
            AddItem.setAmount(GiftBoxItemsCount.get(ProcessCount));

            GiftBoxInventory.addItem(AddItem); ProcessCount++;
        }

        // 打开物品栏
        ExecPlayer.openInventory(GiftBoxInventory); ClaimedPlayers.add(ExecPlayer.getName());

        if(GiftBoxUnlimitedClaim == 0)
            MainClass.MainConfig.set("giftBoxConfig.claimedPlayers", ClaimedPlayers);

        Bukkit.getLogger().info(String.format("[AddonsSheep](GiftBox-Opened) %s, Opened GiftBox!", ExecPlayer.getName()));
        Bukkit.getLogger().info("[AddonsSheep](GiftBox-Opened) Saving Config...");

        PluginClass.saveConfig();
    }
}
