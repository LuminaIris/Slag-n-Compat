package dev.luminairis.slagcompat.compat.materials.allthemodium;

import dev.lopyluna.slag.content.types.MaterialType;
import dev.lopyluna.slag.register.AllTags;
import dev.lopyluna.slag.register.AllTraits;
import dev.luminairis.slagcompat.SlagCompat;
import dev.luminairis.slagcompat.compat.fluids.allthemodium.AllthemodiumFluids;
import dev.luminairis.slagcompat.compat.materials.AllCompatMaterials;
import dev.luminairis.slagcompat.compat.materials.CompatMaterial;
import dev.luminairis.slagcompat.compat.traits.general.GeneralTraits;
import net.minecraft.world.item.crafting.Ingredient;
import java.util.List;

/**
 * Materials: Allthemodium, Vibranium, Unobtainium
 */
@SuppressWarnings("unused")
public class AllthemodiumMaterials extends CompatMaterial {

    public static final MaterialType ALLTHEMODIUM = register(new MaterialType.Builder("allthemodium", () -> Ingredient.of(AllTags.itemC("ingots/allthemodium"))).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 17f)
            .trait(AllTraits.DURABILITY, 9001f)
            .trait(AllTraits.MINING_TIER, 7)
            .trait(AllTraits.MINING_SPEED, 15f)
            .trait(AllTraits.ENCHANTABILITY, 85f)
            .trait(AllTraits.ARMOR, 9f)
            .trait(AllTraits.ARMOR_TOUGHNESS, 5f)
            .trait(AllTraits.KNOCKBACK_RESISTANCE, 0.5f)
            .trait(GeneralTraits.UNBREAKABLE)
            .setTexture("metal")
            .moltenFluid(AllthemodiumFluids.MOLTEN_ALLTHEMODIUM::getSource)
            .modLoaded("allthemodium")
            .register(), INGOTS, true);

    public static final MaterialType VIBRANIUM = register(new MaterialType.Builder("vibranium", () -> Ingredient.of(AllTags.itemC("ingots/vibranium"))).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 35f)
            .trait(AllTraits.DURABILITY, 9001f)
            .trait(AllTraits.MINING_TIER, 8)
            .trait(AllTraits.MINING_SPEED, 25f)
            .trait(AllTraits.ENCHANTABILITY, 100f)
            .trait(AllTraits.ARMOR, 11f)
            .trait(AllTraits.ARMOR_TOUGHNESS, 9f)
            .trait(AllTraits.KNOCKBACK_RESISTANCE, 0.8f)
            .trait(GeneralTraits.UNBREAKABLE)
            .setTexture("base")
            .moltenFluid(AllthemodiumFluids.MOLTEN_VIBRANIUM::getSource)
            .modLoaded("allthemodium")
            .register(), INGOTS, true);

    public static final MaterialType UNOBTAINIUM = register(new MaterialType.Builder("unobtainium", () -> Ingredient.of(AllTags.itemC("ingots/unobtainium"))).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 50f)
            .trait(AllTraits.DURABILITY, 9001f)
            .trait(AllTraits.MINING_TIER, 9)
            .trait(AllTraits.MINING_SPEED, 35f)
            .trait(AllTraits.ENCHANTABILITY, 125f)
            .trait(AllTraits.ARMOR, 13f)
            .trait(AllTraits.ARMOR_TOUGHNESS, 15f)
            .trait(AllTraits.KNOCKBACK_RESISTANCE, 1f)
            .trait(GeneralTraits.UNBREAKABLE)
            .setTexture("soft")
            .moltenFluid(AllthemodiumFluids.MOLTEN_UNOBTAINIUM::getSource)
            .modLoaded("allthemodium")
            .register(), INGOTS, true);

    public static void register() {
        AllCompatMaterials.ONE_INGOT_COMPAT_MATERIALS.addAll(List.of(ALLTHEMODIUM, VIBRANIUM, UNOBTAINIUM));
        SlagCompat.LOGGER.info("Registering materials for mod: allthemodium");

    }
}
