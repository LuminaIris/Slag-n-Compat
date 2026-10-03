package dev.luminairis.slagcompat.compat.materials.allthemodium;

import dev.lopyluna.slag.content.types.MaterialType;
import dev.lopyluna.slag.register.AllTags;
import dev.lopyluna.slag.register.AllTraits;
import dev.luminairis.slagcompat.SlagCompat;
import dev.luminairis.slagcompat.compat.fluids.mekanism.MekanismFluids;
import dev.luminairis.slagcompat.compat.materials.CompatMaterial;
import dev.luminairis.slagcompat.compat.traits.AllCompatTraits;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * Allthemodium, Vibranium, Unobtainium
 */
@SuppressWarnings("unused")
public class AllthemodiumMaterials extends CompatMaterial {
    // TODO: update stats on these guys
    public static final MaterialType ALLTHEMODIUM = register(new MaterialType.Builder("allthemodium", () -> Ingredient.of(AllTags.itemC("ingots/allthemodium"))).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 9f)
            .trait(AllTraits.DURABILITY, 1024f)
            .trait(AllTraits.MINING_TIER, 5)
            .trait(AllTraits.MINING_SPEED, 8f)
            .trait(AllTraits.ENCHANTABILITY, 5f)
            .trait(AllTraits.ARMOR, 7.5f)
            .trait(AllTraits.ARMOR_TOUGHNESS, 2f)
            .trait(AllCompatTraits.UNBREAKABLE)
            .setTexture("metal")
//            .moltenFluid(MekanismFluids.MOLTEN_REFINED_OBSIDIAN::getSource)
            .modLoaded("allthemodium")
            .register(), true);

    public static final MaterialType VIBRANIUM = register(new MaterialType.Builder("vibranium", () -> Ingredient.of(AllTags.itemC("ingots/vibranium"))).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 9f)
            .trait(AllTraits.DURABILITY, 1024f)
            .trait(AllTraits.MINING_TIER, 5)
            .trait(AllTraits.MINING_SPEED, 8f)
            .trait(AllTraits.ENCHANTABILITY, 5f)
            .trait(AllTraits.ARMOR, 7.5f)
            .trait(AllTraits.ARMOR_TOUGHNESS, 2f)
            .trait(AllCompatTraits.UNBREAKABLE)
            .setTexture("metal")
//            .moltenFluid(MekanismFluids.MOLTEN_REFINED_OBSIDIAN::getSource)
            .modLoaded("allthemodium")
            .register(), true);

    public static final MaterialType UNOBTAINIUM = register(new MaterialType.Builder("unobtainium", () -> Ingredient.of(AllTags.itemC("ingots/unobtainium"))).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 9f)
            .trait(AllTraits.DURABILITY, 1024f)
            .trait(AllTraits.MINING_TIER, 5)
            .trait(AllTraits.MINING_SPEED, 8f)
            .trait(AllTraits.ENCHANTABILITY, 5f)
            .trait(AllTraits.ARMOR, 7.5f)
            .trait(AllTraits.ARMOR_TOUGHNESS, 2f)
            .trait(AllCompatTraits.UNBREAKABLE)
            .setTexture("metal")
//            .moltenFluid(MekanismFluids.MOLTEN_REFINED_OBSIDIAN::getSource)
            .modLoaded("allthemodium")
            .register(), true);

    public static void registerAllthemodiumMaterials() {
        SlagCompat.LOGGER.info("Registering materials for mod: Allthemodium");

    }
}
