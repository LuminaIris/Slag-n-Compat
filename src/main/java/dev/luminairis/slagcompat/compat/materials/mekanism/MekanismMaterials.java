package dev.luminairis.slagcompat.compat.materials.mekanism;

import dev.lopyluna.slag.content.types.MaterialType;
import dev.lopyluna.slag.register.AllTags;
import dev.lopyluna.slag.register.AllTraits;
import dev.luminairis.slagcompat.compat.materials.CompatMaterial;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * Mekanism Materials: Refined Obsidian, Refined Glowstone
 */
@SuppressWarnings("unused")
public class MekanismMaterials extends CompatMaterial {

    public static final MaterialType REFINED_OBSIDIAN = register(new MaterialType.Builder("refined_obsidian", () -> Ingredient.of(AllTags.itemC("ingots/refined_obsidian"))).setSortOrder(1000)
//    public static final MaterialType REFINED_OBSIDIAN = register(new MaterialType.Builder("refined_obsidian", () -> Ingredient.of(MekanismItems.REFINED_OBSIDIAN_INGOT)).setSortOrder(1000)
            .trait(AllTraits.ATTACK_DAMAGE, 9f)
            .trait(AllTraits.DURABILITY, 1024f)
            .trait(AllTraits.MINING_TIER, 5)
            .trait(AllTraits.MINING_SPEED, 8f)
            .trait(AllTraits.ENCHANTABILITY, 5f)
            .trait(AllTraits.ARMOR, 7.5f)
            .trait(AllTraits.ARMOR_TOUGHNESS, 2f)
            .setTexture("metal")
            .modLoaded("mekanism")
            .register());
    public static final MaterialType REFINED_GLOWSTONE = null;

    public static void registerMekanismMaterials() {}
}
