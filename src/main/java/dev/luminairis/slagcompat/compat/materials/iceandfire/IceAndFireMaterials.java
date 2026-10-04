package dev.luminairis.slagcompat.compat.materials.iceandfire;

import com.iafenvoy.iceandfire.registry.IafItems;
import dev.lopyluna.slag.content.types.MaterialType;
import dev.lopyluna.slag.register.AllTags;
import dev.lopyluna.slag.register.AllTraits;
import dev.luminairis.slagcompat.SlagCompat;
import dev.luminairis.slagcompat.compat.materials.CompatMaterial;
import dev.luminairis.slagcompat.compat.traits.iceandfire.IceAndFireTraits;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * IAF Materials: Fire, Ice, and Lightning Dragonsteel
 */
@SuppressWarnings("unused")
public class IceAndFireMaterials extends CompatMaterial {

    public static MaterialType FIRE_DRAGONSTEEL = register(new MaterialType.Builder("fire_dragonsteel", () -> Ingredient.of(IafItems.DRAGONSTEEL_FIRE_INGOT)).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 32f)
            .trait(AllTraits.DURABILITY, 3250f)
            .trait(AllTraits.MINING_TIER, 6)
            .trait(AllTraits.MINING_SPEED, 8f)
            .trait(AllTraits.ENCHANTABILITY, 13f)
            .trait(AllTraits.ARMOR, 10f)
            .trait(AllTraits.ARMOR_TOUGHNESS, 3f)
            .trait(IceAndFireTraits.DRAGONSLAYER)
            .trait(IceAndFireTraits.FIRE_IMBUED)
            .setTexture("metal")
//            .moltenFluid(MekanismFluids.MOLTEN_REFINED_OBSIDIAN::getSource)
            .modLoaded("iceandfire")
            .register(), false);

    public static MaterialType ICE_DRAGONSTEEL = register(new MaterialType.Builder("ice_dragonsteel", () -> Ingredient.of(IafItems.DRAGONSTEEL_ICE_INGOT)).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 28f)
            .trait(AllTraits.DURABILITY, 6500f)
            .trait(AllTraits.MINING_TIER, 6)
            .trait(AllTraits.MINING_SPEED, 8f)
            .trait(AllTraits.ENCHANTABILITY, 13f)
            .trait(AllTraits.ARMOR, 14f)
            .trait(AllTraits.ARMOR_TOUGHNESS, 6f)
            .trait(IceAndFireTraits.DRAGONSLAYER)
            .trait(IceAndFireTraits.ICE_IMBUED)
            .setTexture("metal")
//            .moltenFluid(MekanismFluids.MOLTEN_REFINED_OBSIDIAN::getSource)
            .modLoaded("iceandfire")
            .register(), false);

    public static MaterialType LIGHTNING_DRAGONSTEEL = register(new MaterialType.Builder("lightning_dragonsteel", () -> Ingredient.of(IafItems.DRAGONSTEEL_LIGHTNING_INGOT)).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 38f)
            .trait(AllTraits.DURABILITY, 4096f)
            .trait(AllTraits.MINING_TIER, 6)
            .trait(AllTraits.MINING_SPEED, 16f)
            .trait(AllTraits.ENCHANTABILITY, 13f)
            .trait(AllTraits.ARMOR, 8f)
            .trait(AllTraits.ARMOR_TOUGHNESS, 2f)
            .trait(IceAndFireTraits.DRAGONSLAYER)
            .trait(IceAndFireTraits.LIGHTNING_IMBUED)
            .setTexture("metal")
//            .moltenFluid(MekanismFluids.MOLTEN_REFINED_OBSIDIAN::getSource)
            .modLoaded("iceandfire")
            .register(), false);

    public static void register() {
        SlagCompat.LOGGER.info("Registering materials for mod: Ice and Fire CE");
    }
}
