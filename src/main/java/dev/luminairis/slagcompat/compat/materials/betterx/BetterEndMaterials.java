package dev.luminairis.slagcompat.compat.materials.betterx;

import dev.lopyluna.slag.content.types.MaterialType;
import dev.lopyluna.slag.register.AllTags;
import dev.lopyluna.slag.register.AllTraits;
import dev.luminairis.slagcompat.SlagCompat;
import dev.luminairis.slagcompat.compat.materials.CompatMaterial;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * Better end materials: thallasium, terminite, aeternium
 */
@SuppressWarnings("unused")
public class BetterEndMaterials extends CompatMaterial {
    public static final MaterialType THALLASIUM = register(new MaterialType.Builder("thallasium", () -> Ingredient.of(AllTags.itemC("ingots/thallasium")))
            .trait(AllTraits.ATTACK_DAMAGE, 11f)
            .trait(AllTraits.DURABILITY, 4096f)
            .trait(AllTraits.MINING_TIER, 6)
            .trait(AllTraits.MINING_SPEED, 8f)
            .trait(AllTraits.ENCHANTABILITY, 5f)
            .trait(AllTraits.ARMOR, 7.5f)
            .setTexture("base")
            .modLoaded("betterend")
            .register(), true);

    public static final MaterialType TERMINITE = register(new MaterialType.Builder("terminite", () -> Ingredient.of(AllTags.itemC("ingots/terminite")))
            .trait(AllTraits.ATTACK_DAMAGE, 11f)
            .trait(AllTraits.DURABILITY, 4096f)
            .trait(AllTraits.MINING_TIER, 6)
            .trait(AllTraits.MINING_SPEED, 8f)
            .trait(AllTraits.ENCHANTABILITY, 5f)
            .trait(AllTraits.ARMOR, 7.5f)
            .setTexture("base")
            .modLoaded("betterend")
            .register(), true);

    public static final MaterialType AETERNIUM = register(new MaterialType.Builder("aeternium", () -> Ingredient.of(AllTags.itemC("ingots/aeternium")))
            .trait(AllTraits.ATTACK_DAMAGE, 11f)
            .trait(AllTraits.DURABILITY, 4096f)
            .trait(AllTraits.MINING_TIER, 6)
            .trait(AllTraits.MINING_SPEED, 8f)
            .trait(AllTraits.ENCHANTABILITY, 5f)
            .trait(AllTraits.ARMOR, 7.5f)
            .setTexture("base")
            .modLoaded("betterend")
            .register(), false);

    public static void register() {
        SlagCompat.LOGGER.info("Registering materials for mod: Better End New Dawn");
    }
}
