package SimpleCraft;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public class SimpleCraftRecipeManager {

    public static void AddRecipeFormOneMaterial_ShapedRecipe(ItemStack AddRecipeResult, String[] AddRecipeShapedKeys, char MaterialsKey, Material[] MaterialsList) {

        // 循环添加
        int count = 0;
        for(Material material : MaterialsList) {

            // 增加计数器
            count++;

            // 创建 ShapedRecipe
            ShapedRecipe AddShapedRecipe = new ShapedRecipe(AddRecipeResult);

            // 设置合成表
            AddShapedRecipe.shape(AddRecipeShapedKeys[0], AddRecipeShapedKeys[1], AddRecipeShapedKeys[2]);

            // 设置物品
            AddShapedRecipe.setIngredient(MaterialsKey, material);

            // 添加合成表到 Server
            Bukkit.getServer().addRecipe(AddShapedRecipe);

            Bukkit.getLogger().info(String.format("[SimpleCraftRecipeManager] Recipe(Shaped) materials(%d) is add in server!", count));
        }
    }

    public static void AddRecipeFromOneMaterial_ShapelessRecipe(ItemStack AddRecipeResult, int ItemCount, Material[] MaterialsList) {

        // 循环添加
        int count = 0;
        for(Material material : MaterialsList) {
            // 增加计数器
            count++;

            // 创建 ShapelessRecipe
            ShapelessRecipe AddShapelessRecipe = new ShapelessRecipe(AddRecipeResult);

            // 设置物品
            AddShapelessRecipe.addIngredient(ItemCount, material);

            // 添加合成表到 Server
            Bukkit.getServer().addRecipe(AddShapelessRecipe);

            Bukkit.getLogger().info(String.format("[SimpleCraftRecipeManager] Recipe(Shapeless) materials(%d) is add in server!", count));
        }
    }
}
