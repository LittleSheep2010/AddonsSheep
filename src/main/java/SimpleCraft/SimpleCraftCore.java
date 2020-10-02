package SimpleCraft;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SimpleCraftCore {

    public static void InitSimpleCraft_Stage1() {
        /*
         * Stage one "Materials"
         * Stage one init craft Recipe list:
         *  1. Chest (Log*8 -> Chest*4)
         *  2. Stick (Log*2 -> Stick*16)
         *  3. Gravel (Dirt*2 + Flint*2 -> Gravel*2)
         *  4. Flint (Gravel*4 -> Flint*1)
         *  5. Cobblestone (Stone*1 -> Cobblestone*1)
         *  6. Stone (Cobblestone*1 + Coal*1)
         */

        // 初始化箱子
        ItemStack CraftRecipeResult1_ItemStack = new ItemStack(Material.CHEST);
        String[] CraftRecipeShapedList1_List = {"WWW", "W W", "WWW"};
        CraftRecipeResult1_ItemStack.setAmount(4);

        // 材料列表
        Material[] CraftRecipeMaterials1_Materials = {
                Material.OAK_LOG,
                Material.ACACIA_LOG,
                Material.DARK_OAK_LOG,
                Material.JUNGLE_LOG,
                Material.SPRUCE_LOG,
                Material.BIRCH_LOG,
                Material.STRIPPED_OAK_LOG,
                Material.STRIPPED_ACACIA_LOG,
                Material.STRIPPED_DARK_OAK_LOG,
                Material.STRIPPED_JUNGLE_LOG,
                Material.STRIPPED_SPRUCE_LOG,
                Material.STRIPPED_BIRCH_LOG
        };

        // 添加合成表
        SimpleCraftRecipeManager.AddRecipeFormOneMaterial_ShapedRecipe(CraftRecipeResult1_ItemStack, CraftRecipeShapedList1_List,
                'W', CraftRecipeMaterials1_Materials);


        // 初始化木棍
        ItemStack CraftRecipeResult2_ItemStack = new ItemStack(Material.STICK);
        CraftRecipeResult2_ItemStack.setAmount(16);

        // 材料列表
        Material[] CraftRecipeMaterials2_Materials = {
                Material.OAK_LOG,
                Material.ACACIA_LOG,
                Material.DARK_OAK_LOG,
                Material.JUNGLE_LOG,
                Material.SPRUCE_LOG,
                Material.BIRCH_LOG,
                Material.STRIPPED_OAK_LOG,
                Material.STRIPPED_ACACIA_LOG,
                Material.STRIPPED_DARK_OAK_LOG,
                Material.STRIPPED_JUNGLE_LOG,
                Material.STRIPPED_SPRUCE_LOG,
                Material.STRIPPED_BIRCH_LOG
        };

        // 添加合成表
        SimpleCraftRecipeManager.AddRecipeFromOneMaterial_ShapelessRecipe(CraftRecipeResult2_ItemStack, 2, CraftRecipeMaterials2_Materials);



        // 初始化碎石
        ItemStack CraftRecipeResult3_ItemStack = new ItemStack(Material.GRAVEL);
        CraftRecipeResult3_ItemStack.setAmount(4);

        // 设置合成表
        ShapelessRecipe CraftRecipeShapelessRecipe3_ShapelessRecipe = new ShapelessRecipe(CraftRecipeResult3_ItemStack);

        CraftRecipeShapelessRecipe3_ShapelessRecipe.addIngredient(2, Material.DIRT);
        CraftRecipeShapelessRecipe3_ShapelessRecipe.addIngredient(2, Material.FLINT);

        Bukkit.getServer().addRecipe(CraftRecipeShapelessRecipe3_ShapelessRecipe);



        // 初始化燧石
        ItemStack CraftRecipeResult4_ItemStack = new ItemStack(Material.FLINT);
        CraftRecipeResult4_ItemStack.setAmount(1);

        // 设置合成表
        ShapelessRecipe CraftRecipeShapelessRecipe4_ShapelessRecipe = new ShapelessRecipe(CraftRecipeResult4_ItemStack);

        CraftRecipeShapelessRecipe4_ShapelessRecipe.addIngredient(4, Material.GRAVEL);

        Bukkit.getServer().addRecipe(CraftRecipeShapelessRecipe4_ShapelessRecipe);



        // 初始化园石
        ItemStack CraftRecipeResult5_ItemStack = new ItemStack(Material.COBBLESTONE);
        CraftRecipeResult5_ItemStack.setAmount(1);

        // 材料列表
        Material[] CraftRecipeMaterials5_Materials = {
                Material.STONE
        };

        // 设置合成表
        SimpleCraftRecipeManager.AddRecipeFromOneMaterial_ShapelessRecipe(CraftRecipeResult5_ItemStack, 1, CraftRecipeMaterials5_Materials);



        // 初始化石头
        ItemStack CraftRecipeResult6_ItemStack = new ItemStack(Material.STONE);
        CraftRecipeResult6_ItemStack.setAmount(1);

        // 设置合成表
        ShapelessRecipe CraftRecipeShapelessRecipe6_ShapelessRecipe = new ShapelessRecipe(CraftRecipeResult6_ItemStack);

        CraftRecipeShapelessRecipe6_ShapelessRecipe.addIngredient(1, Material.COBBLESTONE);
        CraftRecipeShapelessRecipe6_ShapelessRecipe.addIngredient(1, Material.COAL);

        Bukkit.getServer().addRecipe(CraftRecipeShapelessRecipe6_ShapelessRecipe);
    }

    public static void InitSimpleCraft_Stage2() {

        /*
         * Stage two "Tools"
         * Stage two init craft recipe list:
         *  1. Diamond pickaxe plus[Unbreakable diamond pickaxe] (Diamond pickaxe*1 + Diamond block*8 -> Diamond pickaxe plus*1)
         *  2. Diamond sword plus[Unbreakable diamond sword] (Diamond sword*1 + Diamond block*8 -> Diamond sword plus*1)
         *  3. Diamond shovel plus[Unbreakable diamond shovel] (Diamond shovel*1 + Diamond block*8 -> Diamond shovel plus*1)
         *  4. Diamond axe plus[Unbreakable diamond axe] (Diamond axe*1 + Diamond block*8 -> Diamond axe plus*1)
         *  5. Diamond hoe plus[Unbreakable diamond hoe] (Diamond hoe*1 + Diamond block*8 -> Diamond hoe plus*1)
         */

        // 初始化 Diamond pickaxe plus
        ItemStack CraftRecipeResult1_ItemStack = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta CraftRecipeMeta1_ItemMeta = CraftRecipeResult1_ItemStack.getItemMeta();

        // 设置属性
        List<String> CraftRecipeItem1_ReadingList = new ArrayList<>();
        CraftRecipeItem1_ReadingList.add("A very hard pickaxe. ");
        CraftRecipeItem1_ReadingList.add("It's very powerful in your hand. ");
        CraftRecipeItem1_ReadingList.add("But you feel that this is not his final form... ");

        CraftRecipeMeta1_ItemMeta.setUnbreakable(true);
        CraftRecipeMeta1_ItemMeta.setLore(CraftRecipeItem1_ReadingList);
        CraftRecipeMeta1_ItemMeta.setDisplayName("Diamond pickaxe plus");

        CraftRecipeResult1_ItemStack.setItemMeta(CraftRecipeMeta1_ItemMeta);

        // 设置合成表
        ShapedRecipe CraftRecipeShapedRecipe1_ShapedRecipe = new ShapedRecipe(CraftRecipeResult1_ItemStack);

        CraftRecipeShapedRecipe1_ShapedRecipe.shape("DDD", "DPD", "DDD");
        CraftRecipeShapedRecipe1_ShapedRecipe.setIngredient('D', Material.DIAMOND_BLOCK);
        CraftRecipeShapedRecipe1_ShapedRecipe.setIngredient('P', Material.DIAMOND_PICKAXE);

        // 添加到服务器
        Bukkit.getServer().addRecipe(CraftRecipeShapedRecipe1_ShapedRecipe);
    }
}
