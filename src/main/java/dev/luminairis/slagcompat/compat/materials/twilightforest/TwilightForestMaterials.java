package dev.luminairis.slagcompat.compat.materials.twilightforest;

import dev.lopyluna.slag.content.types.MaterialType;
import dev.lopyluna.slag.register.AllTraits;
import dev.luminairis.slagcompat.SlagCompat;
import dev.luminairis.slagcompat.compat.materials.CompatMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import twilightforest.data.tags.ItemTagGenerator;

@SuppressWarnings("unused")
public class TwilightForestMaterials extends CompatMaterial {

    public static MaterialType IRONWOOD = register(new MaterialType.Builder("ironwood", () -> Ingredient.of(ItemTagGenerator.IRONWOOD_INGOTS)).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 9f)
            .trait(AllTraits.DURABILITY, 4096f)
            .trait(AllTraits.MINING_TIER, 5)
            .trait(AllTraits.MINING_SPEED, 8f)
            .trait(AllTraits.ENCHANTABILITY, 5f)
            .trait(AllTraits.ARMOR, 7.5f)
            .trait(AllTraits.ARMOR_TOUGHNESS, 2f)
            .setTexture("metal")
//            .moltenFluid(MekanismFluids.MOLTEN_REFINED_OBSIDIAN::getSource)
            .modLoaded("twilightforest")
            .register(), true);

    public static MaterialType STEELEAF = register(new MaterialType.Builder("steeleaf", () -> Ingredient.of(ItemTagGenerator.STEELEAF_INGOTS)).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 9f)
            .trait(AllTraits.DURABILITY, 4096f)
            .trait(AllTraits.MINING_TIER, 5)
            .trait(AllTraits.MINING_SPEED, 8f)
            .trait(AllTraits.ENCHANTABILITY, 5f)
            .trait(AllTraits.ARMOR, 7.5f)
            .trait(AllTraits.ARMOR_TOUGHNESS, 2f)
            .setTexture("metal")
//            .moltenFluid(MekanismFluids.MOLTEN_REFINED_OBSIDIAN::getSource)
            .modLoaded("twilightforest")
            .register(), false);

    public static MaterialType KNIGHTMETAL = register(new MaterialType.Builder("knightmetal", () -> Ingredient.of(ItemTagGenerator.KNIGHTMETAL_INGOTS)).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 9f)
            .trait(AllTraits.DURABILITY, 4096f)
            .trait(AllTraits.MINING_TIER, 5)
            .trait(AllTraits.MINING_SPEED, 8f)
            .trait(AllTraits.ENCHANTABILITY, 5f)
            .trait(AllTraits.ARMOR, 7.5f)
            .trait(AllTraits.ARMOR_TOUGHNESS, 2f)
            .setTexture("metal")
//            .moltenFluid(MekanismFluids.MOLTEN_REFINED_OBSIDIAN::getSource)
            .modLoaded("twilightforest")
            .register(), true);

    public static MaterialType FIERY = register(new MaterialType.Builder("fiery", () -> Ingredient.of(ItemTagGenerator.FIERY_INGOTS)).setSortOrder(sortOrder)
            .trait(AllTraits.ATTACK_DAMAGE, 9f)
            .trait(AllTraits.DURABILITY, 4096f)
            .trait(AllTraits.MINING_TIER, 5)
            .trait(AllTraits.MINING_SPEED, 8f)
            .trait(AllTraits.ENCHANTABILITY, 5f)
            .trait(AllTraits.ARMOR, 7.5f)
            .trait(AllTraits.ARMOR_TOUGHNESS, 2f)
            .setTexture("metal")
//            .moltenFluid(MekanismFluids.MOLTEN_REFINED_OBSIDIAN::getSource)
            .modLoaded("twilightforest")
            .register(), false);

    public static void register() {
        SlagCompat.LOGGER.info("Registering materials for mod: Twilight Forest");
    }
}
