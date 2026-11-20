package dev.luminairis.slagcompat.compat.materials.irons_spellbooks;

import dev.lopyluna.slag.content.types.MaterialType;
import dev.lopyluna.slag.register.AllTags;
import dev.lopyluna.slag.register.AllTraits;
import dev.luminairis.slagcompat.SlagCompat;
import dev.luminairis.slagcompat.compat.fluids.irons_spellbooks.IronsSpellbooksFluids;
import dev.luminairis.slagcompat.compat.materials.CompatMaterial;
import dev.luminairis.slagcompat.compat.traits.irons_spellbooks.IronsSpellbooksTraits;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * Materials: Arcane, Mithril, Pyrium
 */
public class IronsSpellbooksMaterials extends CompatMaterial {

    public static MaterialType ARCANE = register(new MaterialType.Builder("arcane", () -> Ingredient.of(AllTags.itemC("ingots/arcane"))).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 3f)
            .trait(AllTraits.DURABILITY, 256f)
            .trait(AllTraits.MINING_TIER, 4)
            .trait(AllTraits.MINING_SPEED, 6f)
            .trait(AllTraits.ENCHANTABILITY, 128f)
            .trait(AllTraits.ARMOR, 3f)
            .trait(IronsSpellbooksTraits.ARCANE)
            .setTexture("soft")
            .moltenFluid(IronsSpellbooksFluids.MOLTEN_ARCANE::getSource)
            .modLoaded("irons_spellbooks")
            .register(), false);

    public static MaterialType MITHRIL = register(new MaterialType.Builder("mithril", () -> Ingredient.of(AllTags.itemC("ingots/mithril"))).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 7f)
            .trait(AllTraits.DURABILITY, 256f)
            .trait(AllTraits.MINING_TIER, 6)
            .trait(AllTraits.MINING_SPEED, 6f)
            .trait(AllTraits.ENCHANTABILITY, 128f)
            .trait(AllTraits.ARMOR, 3f)
            .setTexture("shiny")
            .moltenFluid(IronsSpellbooksFluids.MOLTEN_MITHRIL::getSource)
            .modLoaded("irons_spellbooks")
            .register(), true);

    public static MaterialType PYRIUM = register(new MaterialType.Builder("pyrium", () -> Ingredient.of(AllTags.itemC("ingots/pyrium"))).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 12f)
            .trait(AllTraits.DURABILITY, 2048)
            .trait(AllTraits.MINING_TIER, 6)
            .trait(AllTraits.MINING_SPEED, 4f)
            .trait(AllTraits.ENCHANTABILITY, 64f)
            .trait(AllTraits.ARMOR, 8f)
            .setTexture("metal")
            .moltenFluid(IronsSpellbooksFluids.MOLTEN_PYRIUM::getSource)
            .modLoaded("irons_spellbooks")
            .register(), false);

    public static void register() {
        SlagCompat.LOGGER.info("Registering materials for mod: irons_spellbooks");
    }
}
