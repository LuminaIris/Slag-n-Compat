package dev.luminairis.slagcompat.compat.materials.iceandfire;

import dev.lopyluna.slag.content.types.MaterialType;
import dev.lopyluna.slag.register.AllTags;
import dev.lopyluna.slag.register.AllTraits;
import dev.luminairis.slagcompat.SlagCompat;
import dev.luminairis.slagcompat.compat.materials.CompatMaterial;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * IAF Materials: Fire, Ice, and Lightning Dragonsteel
 */
public class IceAndFireMaterials extends CompatMaterial {
    // TODO: update stats on these guys
    public static MaterialType FIRE_DRAGONSTEEL = register(new MaterialType.Builder("fire_dragonsteel", () -> Ingredient.of(AllTags.itemC("ingots/fire_dragonsteel"))).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 9f)
            .trait(AllTraits.DURABILITY, 1024f)
            .trait(AllTraits.MINING_TIER, 5)
            .trait(AllTraits.MINING_SPEED, 8f)
            .trait(AllTraits.ENCHANTABILITY, 5f)
            .trait(AllTraits.ARMOR, 7.5f)
            .trait(AllTraits.ARMOR_TOUGHNESS, 2f)
            .setTexture("metal")
//            .moltenFluid(MekanismFluids.MOLTEN_REFINED_OBSIDIAN::getSource)
            .modLoaded("iceandfire")
            .register(), false);

    public static MaterialType ICE_DRAGONSTEEL = register(new MaterialType.Builder("ice_dragonsteel", () -> Ingredient.of(AllTags.itemC("ingots/ice_dragonsteel"))).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 9f)
            .trait(AllTraits.DURABILITY, 1024f)
            .trait(AllTraits.MINING_TIER, 5)
            .trait(AllTraits.MINING_SPEED, 8f)
            .trait(AllTraits.ENCHANTABILITY, 5f)
            .trait(AllTraits.ARMOR, 7.5f)
            .trait(AllTraits.ARMOR_TOUGHNESS, 2f)
            .setTexture("metal")
//            .moltenFluid(MekanismFluids.MOLTEN_REFINED_OBSIDIAN::getSource)
            .modLoaded("iceandfire")
            .register(), false);

    public static MaterialType LIGHTNING_DRAGONSTEEL = register(new MaterialType.Builder("lightning_dragonsteel", () -> Ingredient.of(AllTags.itemC("ingots/lightning_dragonsteel"))).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 9f)
            .trait(AllTraits.DURABILITY, 1024f)
            .trait(AllTraits.MINING_TIER, 5)
            .trait(AllTraits.MINING_SPEED, 8f)
            .trait(AllTraits.ENCHANTABILITY, 5f)
            .trait(AllTraits.ARMOR, 7.5f)
            .trait(AllTraits.ARMOR_TOUGHNESS, 2f)
            .setTexture("metal")
//            .moltenFluid(MekanismFluids.MOLTEN_REFINED_OBSIDIAN::getSource)
            .modLoaded("iceandfire")
            .register(), false);

    public static void registerIceAndFireMaterials() {
        SlagCompat.LOGGER.info("Registering materials for mod: Ice and Fire");
    }
}
