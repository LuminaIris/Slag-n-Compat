package dev.luminairis.slagcompat.datagen;

import dev.lopyluna.slag.SlagEmbers;
import dev.lopyluna.slag.content.blocks.melter.MelterBE;
import dev.lopyluna.slag.content.datagen.MeltingRecipeBuilder;
import dev.lopyluna.slag.content.types.MaterialType;
import dev.lopyluna.slag.register.AllTags;
import dev.luminairis.slagcompat.SlagCompat;
import dev.luminairis.slagcompat.compat.materials.AllCompatMaterials;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class CompatRecipeDatagen extends RecipeProvider implements IConditionBuilder {
    public CompatRecipeDatagen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        for (MaterialType m : AllCompatMaterials.ALL_COMPAT_MATERIALS) {
            genRecipesForMaterial(m, recipeOutput);
        }
    }

    private void genRecipesForMaterial(MaterialType material, RecipeOutput recipeOutput) {
        // only generate melting recipes if material has a molten fluid
        if (material.moltenFluid != null && material.moltenFluid.get() != null) {
            Fluid molten = material.moltenFluid.get();
            String name = material.id.getPath();
            genMeltingRecipe(name + "_blocks", molten, MelterBE.BLOCK_SIZE, AllTags.itemC("storage_blocks/" + name), recipeOutput);
            genMeltingRecipe(name + "_ingots", molten, MelterBE.INGOT_SIZE, AllTags.itemC("ingots/" + name), recipeOutput);
            genMeltingRecipe(name + "_nuggets", molten, MelterBE.NUGGET_SIZE, AllTags.itemC("nuggets/" + name), recipeOutput);
        }
    }

    private void genMeltingRecipe(String name, Fluid molten, int mb, TagKey<Item> tag, RecipeOutput recipeOutput) {
        MeltingRecipeBuilder.create(molten, mb, tag)
                .unlockedBy("has_meltable_" + name, has(tag))
                .save(recipeOutput.withConditions(AllTags.present(tag)), SlagEmbers.loc(SlagCompat.MODID, "melting/" + name));
    }
}
