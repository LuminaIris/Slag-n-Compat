package dev.luminairis.slagcompat.compat.materials.eternal_starlight;

import cn.leolezury.eternalstarlight.common.registry.ESItems;
import dev.lopyluna.slag.content.types.MaterialType;
import dev.lopyluna.slag.register.AllTags;
import dev.lopyluna.slag.register.AllTraits;
import dev.luminairis.slagcompat.SlagCompat;
import dev.luminairis.slagcompat.compat.materials.AllCompatMaterials;
import dev.luminairis.slagcompat.compat.materials.CompatMaterial;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * Materials: Deepsilver, Glacite, Thermal Springstone, Malarite, Starlit Diamond
 *          Starfire, Flowglaze, Amaramber, Aethersent, Golem Steel, Unrealium
 */
@SuppressWarnings("unused")
public class EternalStarlightMaterials extends CompatMaterial {

    public static MaterialType DEEPSILVER = register(new MaterialType.Builder("deepsilver", () -> Ingredient.of(AllTags.itemC("ingots/deepsilver"))).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 1f)
            .trait(AllTraits.DURABILITY, 99f)
            .trait(AllTraits.MINING_TIER, 1)
            .trait(AllTraits.MINING_SPEED, 1f)
            .trait(AllTraits.ENCHANTABILITY, 1f)
            .modLoaded("eternal_starlight")
            .register(), INGOTS, false);

    public static MaterialType GLACITE = register(new MaterialType.Builder("glacite", () -> Ingredient.of(AllTags.itemC("gems/glacite"))).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 1f)
            .trait(AllTraits.DURABILITY, 99f)
            .trait(AllTraits.MINING_TIER, 1)
            .trait(AllTraits.MINING_SPEED, 1f)
            .trait(AllTraits.ENCHANTABILITY, 1f)
            .modLoaded("eternal_starlight")
            .register(), GEMS, false);

    public static MaterialType THERMAL_SPRINGSTONE = register(new MaterialType.Builder("thermal_springstone", () -> Ingredient.of(AllTags.itemC("ingots/thermal_springstone"))).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 1f)
            .trait(AllTraits.DURABILITY, 99f)
            .trait(AllTraits.MINING_TIER, 1)
            .trait(AllTraits.MINING_SPEED, 1f)
            .trait(AllTraits.ENCHANTABILITY, 1f)
            .modLoaded("eternal_starlight")
            .register(), INGOTS, false);

    public static MaterialType MALARITE = register(new MaterialType.Builder("malarite", () -> Ingredient.of(AllTags.itemC("gems/malarite"))).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 1f)
            .trait(AllTraits.DURABILITY, 99f)
            .trait(AllTraits.MINING_TIER, 1)
            .trait(AllTraits.MINING_SPEED, 1f)
            .trait(AllTraits.ENCHANTABILITY, 1f)
            .modLoaded("eternal_starlight")
            .register(), GEMS, false);

    public static MaterialType STARLIT_DIAMOND = register(new MaterialType.Builder("starlit_diamond", () -> Ingredient.of(AllTags.itemC("gems/starlit_diamond"))).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 1f)
            .trait(AllTraits.DURABILITY, 99f)
            .trait(AllTraits.MINING_TIER, 1)
            .trait(AllTraits.MINING_SPEED, 1f)
            .trait(AllTraits.ENCHANTABILITY, 1f)
            .modLoaded("eternal_starlight")
            .register(), GEMS, false);

    public static MaterialType STARFIRE = register(new MaterialType.Builder("starfire", () -> Ingredient.of(ESItems.STARFIRE.get())).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 1f)
            .trait(AllTraits.DURABILITY, 99f)
            .trait(AllTraits.MINING_TIER, 1)
            .trait(AllTraits.MINING_SPEED, 1f)
            .trait(AllTraits.ENCHANTABILITY, 1f)
            .modLoaded("eternal_starlight")
            .register(), MISC, false);

    public static MaterialType FLOWGLAZE = register(new MaterialType.Builder("flowglaze", () -> Ingredient.of(ESItems.FLOWGLAZE.get())).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 1f)
            .trait(AllTraits.DURABILITY, 99f)
            .trait(AllTraits.MINING_TIER, 1)
            .trait(AllTraits.MINING_SPEED, 1f)
            .trait(AllTraits.ENCHANTABILITY, 1f)
            .modLoaded("eternal_starlight")
            .register(), BLOCKS, false);

    public static MaterialType AMARAMBER = register(new MaterialType.Builder("amaramber", () -> Ingredient.of(AllTags.itemC("ingots/amaramber"))).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 1f)
            .trait(AllTraits.DURABILITY, 99f)
            .trait(AllTraits.MINING_TIER, 1)
            .trait(AllTraits.MINING_SPEED, 1f)
            .trait(AllTraits.ENCHANTABILITY, 1f)
            .modLoaded("eternal_starlight")
            .register(), INGOTS, true);

    public static MaterialType AETHERSENT = register(new MaterialType.Builder("aethersent", () -> Ingredient.of(AllTags.itemC("ingots/aethersent"))).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 1f)
            .trait(AllTraits.DURABILITY, 99f)
            .trait(AllTraits.MINING_TIER, 1)
            .trait(AllTraits.MINING_SPEED, 1f)
            .trait(AllTraits.ENCHANTABILITY, 1f)
            .modLoaded("eternal_starlight")
            .register(), INGOTS, true);

    public static MaterialType GOLEM_STEEL = register(new MaterialType.Builder("golem_steel", () -> Ingredient.of(AllTags.itemC("ingots/golem_steel"))).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 1f)
            .trait(AllTraits.DURABILITY, 99f)
            .trait(AllTraits.MINING_TIER, 1)
            .trait(AllTraits.MINING_SPEED, 1f)
            .trait(AllTraits.ENCHANTABILITY, 1f)
            .modLoaded("eternal_starlight")
            .register(), INGOTS, true);

    public static MaterialType UNREALIUM = register(new MaterialType.Builder("unrealium", () -> Ingredient.of(AllTags.itemC("ingots/unrealium"))).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 1f)
            .trait(AllTraits.DURABILITY, 99f)
            .trait(AllTraits.MINING_TIER, 1)
            .trait(AllTraits.MINING_SPEED, 1f)
            .trait(AllTraits.ENCHANTABILITY, 1f)
            .modLoaded("eternal_starlight")
            .register(), INGOTS, true);

    public static void register() {
        AllCompatMaterials.BLOCK_COMPAT_MATERIALS.add(FLOWGLAZE);
        SlagCompat.LOGGER.info("Registering materials for mod: eternal_starlight");
    }
}
