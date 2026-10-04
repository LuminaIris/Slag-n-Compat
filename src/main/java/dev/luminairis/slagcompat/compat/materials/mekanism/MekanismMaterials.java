package dev.luminairis.slagcompat.compat.materials.mekanism;

import dev.lopyluna.slag.content.types.MaterialType;
import dev.lopyluna.slag.register.AllTags;
import dev.lopyluna.slag.register.AllTraits;
import dev.luminairis.slagcompat.SlagCompat;
import dev.luminairis.slagcompat.compat.fluids.mekanism.MekanismFluids;
import dev.luminairis.slagcompat.compat.materials.CompatMaterial;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * Mekanism Materials: Refined Obsidian, Refined Glowstone
 */
@SuppressWarnings("unused")
public class MekanismMaterials extends CompatMaterial {

    public static final MaterialType REFINED_OBSIDIAN = register(new MaterialType.Builder("refined_obsidian", () -> Ingredient.of(AllTags.itemC("ingots/refined_obsidian"))).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 9f)
            .trait(AllTraits.DURABILITY, 4096f)
            .trait(AllTraits.MINING_TIER, 5)
            .trait(AllTraits.MINING_SPEED, 8f)
            .trait(AllTraits.ENCHANTABILITY, 5f)
            .trait(AllTraits.ARMOR, 7.5f)
            .trait(AllTraits.ARMOR_TOUGHNESS, 2f)
            .setTexture("metal")
            .moltenFluid(MekanismFluids.MOLTEN_REFINED_OBSIDIAN::getSource)
            .modLoaded("mekanism")
            .register(), false);

    public static final MaterialType REFINED_GLOWSTONE = register(new MaterialType.Builder("refined_glowstone", () -> Ingredient.of(AllTags.itemC("ingots/refined_glowstone"))).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 6f)
            .trait(AllTraits.DURABILITY, 40f)
            .trait(AllTraits.MINING_TIER, 3)
            .trait(AllTraits.MINING_SPEED, 30f)
            .trait(AllTraits.ENCHANTABILITY, 24)
            .trait(AllTraits.ARMOR, 3f)
            .setTexture("shiny")
            .moltenFluid(MekanismFluids.MOLTEN_REFINED_GLOWSTONE::getSource)
            .modLoaded("mekanism")
            .register(), false);

    public static void register() {
        SlagCompat.LOGGER.info("Registering materials for mod: Mekanism");
    }
}
