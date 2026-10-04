package dev.luminairis.slagcompat.compat.materials.allthemodium;

import com.thevortex.allthemodium.registry.TagRegistry;
import dev.lopyluna.slag.content.types.MaterialType;
import dev.lopyluna.slag.register.AllTraits;
import dev.luminairis.slagcompat.SlagCompat;
import dev.luminairis.slagcompat.compat.materials.CompatMaterial;
import dev.luminairis.slagcompat.compat.traits.general.GeneralTraits;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * Allthemodium, Vibranium, Unobtainium
 */
@SuppressWarnings("unused")
public class AllthemodiumMaterials extends CompatMaterial {

    public static final MaterialType ALLTHEMODIUM = register(new MaterialType.Builder("allthemodium", () -> Ingredient.of(TagRegistry.ALLTHEMODIUM_INGOT)).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 18f)
            .trait(AllTraits.DURABILITY, 9001f)
            .trait(AllTraits.MINING_TIER, 7) // I have no idea what the mining tiers are for allthemodium so I'm just gonna trial and error this
            .trait(AllTraits.MINING_SPEED, 15f)
            .trait(AllTraits.ENCHANTABILITY, 85f)
            .trait(AllTraits.ARMOR, 9f)
            .trait(AllTraits.ARMOR_TOUGHNESS, 5f)
            .trait(AllTraits.KNOCKBACK_RESISTANCE, 0.5f)
            .trait(GeneralTraits.UNBREAKABLE)
            .setTexture("metal")
//            .moltenFluid(MekanismFluids.MOLTEN_REFINED_OBSIDIAN::getSource)
            .modLoaded("allthemodium")
            .register(), true);

    public static final MaterialType VIBRANIUM = register(new MaterialType.Builder("vibranium", () -> Ingredient.of(TagRegistry.VIBRANIUM_INGOT)).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 36f)
            .trait(AllTraits.DURABILITY, 9001f)
            .trait(AllTraits.MINING_TIER, 8)
            .trait(AllTraits.MINING_SPEED, 25f)
            .trait(AllTraits.ENCHANTABILITY, 100f)
            .trait(AllTraits.ARMOR, 11f)
            .trait(AllTraits.ARMOR_TOUGHNESS, 9f)
            .trait(AllTraits.KNOCKBACK_RESISTANCE, 0.8f)
            .trait(GeneralTraits.UNBREAKABLE)
            .setTexture("metal")
//            .moltenFluid(MekanismFluids.MOLTEN_REFINED_OBSIDIAN::getSource)
            .modLoaded("allthemodium")
            .register(), true);

    public static final MaterialType UNOBTAINIUM = register(new MaterialType.Builder("unobtainium", () -> Ingredient.of(TagRegistry.UNOBTAINIUM_INGOT)).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 51f)
            .trait(AllTraits.DURABILITY, 9001f)
            .trait(AllTraits.MINING_TIER, 9)
            .trait(AllTraits.MINING_SPEED, 35f)
            .trait(AllTraits.ENCHANTABILITY, 125f)
            .trait(AllTraits.ARMOR, 13f)
            .trait(AllTraits.ARMOR_TOUGHNESS, 15f)
            .trait(AllTraits.KNOCKBACK_RESISTANCE, 1f)
            .trait(GeneralTraits.UNBREAKABLE)
            .setTexture("metal")
//            .moltenFluid(MekanismFluids.MOLTEN_REFINED_OBSIDIAN::getSource)
            .modLoaded("allthemodium")
            .register(), true);

    public static void register() {
        SlagCompat.LOGGER.info("Registering materials for mod: Allthemodium");

    }
}
