package dev.luminairis.slagcompat.compat.materials.irons_spellbooks;

import dev.lopyluna.slag.content.types.MaterialType;
import dev.lopyluna.slag.register.AllTags;
import dev.lopyluna.slag.register.AllTraits;
import dev.luminairis.slagcompat.SlagCompat;
import dev.luminairis.slagcompat.compat.fluids.irons_spellbooks.IronsSpellbooksFluids;
import dev.luminairis.slagcompat.compat.materials.AllCompatMaterials;
import dev.luminairis.slagcompat.compat.materials.CompatMaterial;
import dev.luminairis.slagcompat.compat.traits.irons_spellbooks.IronsSpellbooksTraits;
import net.minecraft.world.item.crafting.Ingredient;
import java.util.List;

/**
 * Materials: Arcane, Mithril, Pyrium
 */
@SuppressWarnings("unused")
public class IronsSpellbooksMaterials extends CompatMaterial {

    public static MaterialType ARCANE = register(new MaterialType.Builder("arcane", () -> Ingredient.of(AllTags.itemC("ingots/arcane"))).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 1f)
            .trait(AllTraits.DURABILITY, 256f)
            .trait(AllTraits.MINING_TIER, 4)
            .trait(AllTraits.MINING_SPEED, 6f)
            .trait(AllTraits.ENCHANTABILITY, 128f)
            .trait(AllTraits.ARMOR, 3f)
            .trait(IronsSpellbooksTraits.MANA, 100)
            .trait(IronsSpellbooksTraits.ARCANE)
            .setTexture("soft")
            .moltenFluid(IronsSpellbooksFluids.MOLTEN_ARCANE::getSource)
            .modLoaded("irons_spellbooks")
            .register(), INGOTS, false);

    public static MaterialType MITHRIL = register(new MaterialType.Builder("mithril", () -> Ingredient.of(AllTags.itemC("ingots/mithril"))).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 7f)
            .trait(AllTraits.DURABILITY, 256f)
            .trait(AllTraits.MINING_TIER, 6)
            .trait(AllTraits.MINING_SPEED, 6f)
            .trait(AllTraits.ENCHANTABILITY, 128f)
            .trait(AllTraits.ARMOR, 8.8f)
            .trait(AllTraits.ARMOR_TOUGHNESS, 2f)
            .trait(IronsSpellbooksTraits.SPELL_RESIST, 0.1f)
            .setTexture("shiny")
            .moltenFluid(IronsSpellbooksFluids.MOLTEN_MITHRIL::getSource)
            .modLoaded("irons_spellbooks")
            .register(), INGOTS, true);

    public static MaterialType PYRIUM = register(new MaterialType.Builder("pyrium", () -> Ingredient.of(AllTags.itemC("ingots/pyrium"))).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 12f)
            .trait(AllTraits.DURABILITY, 2048)
            .trait(AllTraits.MINING_TIER, 6)
            .trait(AllTraits.MINING_SPEED, 4f)
            .trait(AllTraits.ENCHANTABILITY, 64f)
            .trait(AllTraits.ARMOR, 7.5f)
            .trait(AllTraits.ARMOR_TOUGHNESS, 1f)
            .trait(IronsSpellbooksTraits.SPELL_POWER, 0.1f)
            .trait(AllTraits.FIRE_RESISTANT)
            .trait(IronsSpellbooksTraits.HAVOC)
            .setTexture("metal")
            .moltenFluid(IronsSpellbooksFluids.MOLTEN_PYRIUM::getSource)
            .modLoaded("irons_spellbooks")
            .register(), INGOTS, false);

    public static void register() {
        AllCompatMaterials.ONE_INGOT_COMPAT_MATERIALS.addAll(List.of(MITHRIL, PYRIUM));
        SlagCompat.LOGGER.info("Registering materials for mod: irons_spellbooks");
    }
}
