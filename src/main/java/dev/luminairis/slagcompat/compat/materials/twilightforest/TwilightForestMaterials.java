package dev.luminairis.slagcompat.compat.materials.twilightforest;

import dev.lopyluna.slag.content.types.MaterialType;
import dev.lopyluna.slag.register.AllTags;
import dev.lopyluna.slag.register.AllTraits;
import dev.luminairis.slagcompat.SlagCompat;
import dev.luminairis.slagcompat.compat.fluids.twilightforest.TwilightForestFluids;
import dev.luminairis.slagcompat.compat.materials.CompatMaterial;
import dev.luminairis.slagcompat.compat.traits.general.GeneralTraits;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * Materials: Ironwood, Steeleaf, Knightmetal, Fiery
 */
@SuppressWarnings("unused")
public class TwilightForestMaterials extends CompatMaterial {

    public static MaterialType IRONWOOD = register(new MaterialType.Builder("ironwood", () -> Ingredient.of(AllTags.itemC("ingots/ironwood"))).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 5f)
            .trait(AllTraits.DURABILITY, 512f)
            .trait(AllTraits.MINING_TIER, 4)
            .trait(AllTraits.MINING_SPEED, 6.5f)
            .trait(AllTraits.ENCHANTABILITY, 25f)
            .trait(AllTraits.ARMOR, 7f)
            .setTexture("soft")
            .moltenFluid(TwilightForestFluids.MOLTEN_IRONWOOD::getSource)
            .modLoaded("twilightforest")
            .register(), true);

    public static MaterialType STEELEAF = register(new MaterialType.Builder("steeleaf", () -> Ingredient.of(AllTags.itemC("ingots/steeleaf"))).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 6f)
            .trait(AllTraits.DURABILITY, 131f)
            .trait(AllTraits.MINING_TIER, 5)
            .trait(AllTraits.MINING_SPEED, 8f)
            .trait(AllTraits.ENCHANTABILITY, 9f)
            .trait(AllTraits.ARMOR, 8f)
            .setTexture("soft")
            .moltenFluid(TwilightForestFluids.MOLTEN_STEELEAF::getSource)
            .modLoaded("twilightforest")
            .register(), false);

    public static MaterialType KNIGHTMETAL = register(new MaterialType.Builder("knightmetal", () -> Ingredient.of(AllTags.itemC("ingots/knightmetal"))).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 6f)
            .trait(AllTraits.DURABILITY, 512f)
            .trait(AllTraits.MINING_TIER, 5)
            .trait(AllTraits.MINING_SPEED, 8f)
            .trait(AllTraits.ENCHANTABILITY, 8f)
            .trait(AllTraits.ARMOR, 8f)
            .trait(AllTraits.ARMOR_TOUGHNESS, 1f)
            .setTexture("metal")
            .moltenFluid(TwilightForestFluids.MOLTEN_KNIGHTMETAL::getSource)
            .modLoaded("twilightforest")
            .register(), true);

    public static MaterialType FIERY = register(new MaterialType.Builder("fiery", () -> Ingredient.of(AllTags.itemC("ingots/fiery"))).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 7f)
            .trait(AllTraits.DURABILITY, 1024f)
            .trait(AllTraits.MINING_TIER, 6)
            .trait(AllTraits.MINING_SPEED, 9f)
            .trait(AllTraits.ENCHANTABILITY, 10f)
            .trait(AllTraits.ARMOR, 9f)
            .trait(AllTraits.ARMOR_TOUGHNESS, 1.5f)
            .trait(AllTraits.AUTO_SMELTING)
            .trait(AllTraits.FIRE_RESISTANT)
            .trait(GeneralTraits.IGNITING)
            .setTexture("metal")
            .moltenFluid(TwilightForestFluids.MOLTEN_FIERY::getSource)
            .modLoaded("twilightforest")
            .register(), false);

    public static void register() {
        SlagCompat.LOGGER.info("Registering materials for mod: twilightforest");
    }
}
