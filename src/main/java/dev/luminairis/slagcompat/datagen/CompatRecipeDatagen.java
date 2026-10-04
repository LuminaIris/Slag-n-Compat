package dev.luminairis.slagcompat.datagen;

import dev.lopyluna.slag.SlagEmbers;
import dev.lopyluna.slag.content.blocks.melter.MelterBE;
import dev.lopyluna.slag.content.conditions.CastOnlyCondition;
import dev.lopyluna.slag.content.datagen.BasinCastingRecipeBuilder;
import dev.lopyluna.slag.content.datagen.MeltingRecipeBuilder;
import dev.lopyluna.slag.content.datagen.TableCastingRecipeBuilder;
import dev.lopyluna.slag.content.types.Incompatible;
import dev.lopyluna.slag.content.types.MaterialType;
import dev.lopyluna.slag.content.types.PartType;
import dev.lopyluna.slag.register.*;
import dev.luminairis.slagcompat.SlagCompat;
import dev.luminairis.slagcompat.compat.materials.AllCompatMaterials;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CompatRecipeDatagen extends RecipeProvider implements IConditionBuilder {

    public static List<Fluid> BLOCK_COMPAT_FLUIDS = List.of();
    public static List<Fluid> ONE_INGOT_COMPAT_FLUIDS = List.of();

    public CompatRecipeDatagen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        for (MaterialType m : AllCompatMaterials.COMPAT_MATERIALS) {
            genRecipesForMaterial(m, recipeOutput);
        }
    }

    private void genRecipesForMaterial(MaterialType material, RecipeOutput recipeOutput) {
        String name = material.id.getPath();

        // only generate these recipes if material has a molten fluid
        if (material.moltenFluid != null && material.moltenFluid.get() != null) {
            Fluid molten = material.moltenFluid.get();

            // MELTING RECIPES
            generateMeltingRecipe(name + "_blocks", molten, MelterBE.BLOCK_SIZE, AllTags.itemC("storage_blocks/" + name), recipeOutput);
            generateMeltingRecipe(name + "_ingots", molten, MelterBE.INGOT_SIZE, AllTags.itemC("ingots/" + name), recipeOutput);
            generateMeltingRecipe(name + "_nuggets", molten, MelterBE.NUGGET_SIZE, AllTags.itemC("nuggets/" + name), recipeOutput);
            generateMeltingRecipe(name + "_plates", molten, MelterBE.INGOT_SIZE, AllTags.itemC("plates/" + name), recipeOutput);
            generateMeltingRecipe(name + "_rods", molten, MelterBE.INGOT_SIZE / 2, AllTags.itemC("rods/" + name), recipeOutput);
            generateMeltingRecipe(name + "_dusts", molten, MelterBE.INGOT_SIZE, AllTags.itemC("dusts/" + name), recipeOutput);
            generateMeltingRecipe(name + "_wires", molten, MelterBE.INGOT_SIZE / 4, AllTags.itemC("wires/" + name), recipeOutput);
            if (AllCompatMaterials.MATERIAL_HAS_RAW.get(name)) {
                generateMeltingRecipe("raw_" + name + "_blocks", molten, MelterBE.BLOCK_SIZE + MelterBE.INGOT_SIZE * 3, AllTags.itemC("storage_blocks/raw_" + name), recipeOutput);
                generateMeltingRecipe("raw_" + name + "_materials", molten, MelterBE.INGOT_SIZE + MelterBE.NUGGET_SIZE * 3, AllTags.itemC("raw_materials/" + name), recipeOutput);
                generateMeltingRecipe(name + "_ores", molten, MelterBE.INGOT_SIZE + MelterBE.NUGGET_SIZE * 3, AllTags.itemC("ores/" + name), recipeOutput);
                generateMeltingRecipe(name + "_clumps", molten, MelterBE.INGOT_SIZE + MelterBE.NUGGET_SIZE * 3, AllTags.itemC("clumps/" + name), recipeOutput);
            }

            // CASTING RECIPES
            generateTableCastingRecipe("ingot", name, molten, MelterBE.INGOT_SIZE, AllTags.CAST_INGOTS, recipeOutput);
            generateTableCastingRecipe("nugget", name, molten, MelterBE.NUGGET_SIZE, AllTags.CAST_NUGGETS, recipeOutput);
            generateTableCastingRecipe("dust", name, molten, MelterBE.INGOT_SIZE, AllTags.CAST_DUSTS, recipeOutput);
            generateTableCastingRecipe("rod", name, molten, MelterBE.INGOT_SIZE / 2, AllTags.CAST_RODS, recipeOutput);
            generateBasinCastingRecipe(name, molten, recipeOutput);
        }

        // DYNAMIC PART RECIPES
        generateDynamicPartRecipesForCompatMaterial(material, recipeOutput);
    }

    private void generateMeltingRecipe(String name, Fluid molten, int mb, TagKey<Item> tag, RecipeOutput recipeOutput) {
        MeltingRecipeBuilder.create(molten, mb, tag)
                .unlockedBy("has_meltable_" + name, has(tag))
                .save(recipeOutput.withConditions(AllTags.present(tag)), SlagEmbers.loc(SlagCompat.MODID, "melting/" + name));
    }


    private void generateTableCastingRecipe(String key, String type, Fluid molten, int mb, TagKey<Item> castType, RecipeOutput recipeOutput) {
        var result = AllTags.itemC(key + "s/" + type);
        TableCastingRecipeBuilder.create(result, 1, molten, mb, castType)
                .unlockedBy("has_" + key, has(result))
                .save(recipeOutput.withConditions(AllTags.present(result)), SlagEmbers.loc(SlagCompat.MODID, "casting/table/" + type + "_" + key));
    }

    private void generateBasinCastingRecipe(String type, Fluid molten, RecipeOutput recipeOutput) {
        var result = AllTags.itemC("storage_blocks/" + type);
        BasinCastingRecipeBuilder.create(result, 1, molten, MelterBE.BLOCK_SIZE)
                .unlockedBy("has_block", has(result))
                .save(recipeOutput.withConditions(AllTags.present(result)), SlagEmbers.loc(SlagCompat.MODID, "casting/basin/" + type + "_block"));
    }

    private void generateDynamicPartRecipesForCompatMaterial(MaterialType material, RecipeOutput recipeOutput) {
        var item = AllItems.DYNAMIC_PART.get();
        for (var part : AllDynamicTypes.getAllParts()) {
            var partID = part.id.getPath();
            var matID = material.id.getPath();
            if (!Incompatible.compatible(material, part)) continue;
            var output = withConditions(recipeOutput, material, part);
            var castable = castSize(material, part) > 0 && getCast(part) != null;
            var paperOutput = castable ? withConditions(recipeOutput, material, part, new CastOnlyCondition(miningTier(material))) : output;
            var stack = item.getDefaultInstance();
            item.setMaterialType(stack, material);
            item.setPartType(stack, part);

            // part crafting with paper
            if (material.moltenFluid.get() == null || !ONE_INGOT_COMPAT_FLUIDS.contains(material.moltenFluid.get())) {
                buildPattern(ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, stack), part)
                        .define('M', material.repairMaterials.get())
                        .define('R', Items.PAPER)
                        .unlockedBy("has_paper", has(Items.PAPER))
                        .save(paperOutput, SlagEmbers.loc(SlagCompat.MODID, "crafting/parts/" + partID + "_" + matID));
            }

            // casting + melting
            var molten = material.moltenFluid.get();
            if (molten != null) {
                var cast = getCast(part);
                var size = castSize(material, part);

                if (size > 0) {
                    // part casting
                    if (cast != null) TableCastingRecipeBuilder.create(stack, molten, size, cast)
                            .unlockedBy("has_table", has(AllBlocks.TABLE.get()))
                            .save(output, SlagEmbers.loc(SlagCompat.MODID, "casting/table/" + partID + "_" + matID));
                    // part melting
                    MeltingRecipeBuilder.create(molten, size, stack)
                            .unlockedBy("has_melter", has(AllBlocks.MELTER.get()))
                            .save(output, SlagEmbers.loc(SlagCompat.MODID, "melting/" + partID + "_" + matID));
                }
            }
        }
    }

    // helper methods for dynamic part generation
    private RecipeOutput withConditions(RecipeOutput recipeOutput, MaterialType material, PartType part, ICondition... extra) {
        var conditions = new ArrayList<>(material.conditions);
        conditions.addAll(part.conditions);
        conditions.addAll(List.of(extra));
        return conditions.isEmpty() ? recipeOutput : recipeOutput.withConditions(conditions.toArray(ICondition[]::new));
    }

    private int castSize(MaterialType material, PartType part) {
        var fluid = material.moltenFluid.get();
        if (fluid == null) return 0;
        var size = BLOCK_COMPAT_FLUIDS.contains(fluid) ? MelterBE.BLOCK_SIZE : MelterBE.INGOT_SIZE;
        size *= getSize(part);
        if (size > 0 && ONE_INGOT_COMPAT_FLUIDS.contains(fluid)) size = MelterBE.INGOT_SIZE;
        return size;
    }

    private float miningTier(MaterialType material) {
        for (var trait : material.traits) if (trait.trait().equals(AllTraits.MINING_TIER.id)) return trait.value().orElse(0f);
        return 0;
    }

    private int getSize(PartType part) {
        return switch (part.id.getPath()) {
            // update if i ever add any of my own part types
            default -> AllItems.getSize(part);
        };
    }

    private TagKey<Item> getCast(PartType part) {
        return switch (part.id.getPath()) {
            // update if i ever add any of my own part types
            default -> AllItems.getCast(part);
        };
    }

    private ShapedRecipeBuilder buildPattern(ShapedRecipeBuilder value, PartType part) {
        return switch (part.id.getPath()) {
            // update if i ever add any of my own part types
            default -> AllItems.buildPattern(value, part);
        };
    }
}
