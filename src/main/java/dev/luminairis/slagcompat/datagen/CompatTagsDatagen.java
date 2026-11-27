package dev.luminairis.slagcompat.datagen;

import dev.lopyluna.slag.register.AllTags;
import dev.luminairis.slagcompat.SlagCompat;
import dev.luminairis.slagcompat.compat.fluids.allthemodium.AllthemodiumFluids;
import dev.luminairis.slagcompat.compat.fluids.eternal_starlight.EternalStarlightFluids;
import dev.luminairis.slagcompat.compat.fluids.iceandfire.IceAndFireFluids;
import dev.luminairis.slagcompat.compat.fluids.irons_spellbooks.IronsSpellbooksFluids;
import dev.luminairis.slagcompat.compat.fluids.mekanism.MekanismFluids;
import dev.luminairis.slagcompat.compat.fluids.twilightforest.TwilightForestFluids;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import java.util.concurrent.CompletableFuture;

import static dev.luminairis.slagcompat.compat.AllCompatTags.*;

public class CompatTagsDatagen {
    public static class CompatBlockTagsProvider extends BlockTagsProvider {
        public CompatBlockTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
            super(packOutput, lookupProvider, SlagCompat.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider lookupProvider) {
            tag(INCORRECT_FOR_TIER_1_TOOL).addTag(BlockTags.INCORRECT_FOR_WOODEN_TOOL);
            tag(INCORRECT_FOR_TIER_2_TOOL).addTag(BlockTags.INCORRECT_FOR_GOLD_TOOL);
            tag(INCORRECT_FOR_TIER_3_TOOL).addTag(BlockTags.INCORRECT_FOR_STONE_TOOL);
            tag(INCORRECT_FOR_TIER_4_TOOL).addTag(BlockTags.INCORRECT_FOR_IRON_TOOL);
            tag(INCORRECT_FOR_TIER_5_TOOL).addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL);tag(INCORRECT_FOR_TIER_6_TOOL).addTag(BlockTags.INCORRECT_FOR_NETHERITE_TOOL);
            tag(INCORRECT_FOR_TIER_7_TOOL).addOptionalTag(AllTags.blockC("incorrect_for_allthemodium_tool"));
            tag(INCORRECT_FOR_TIER_8_TOOL).addOptionalTag(AllTags.blockC("incorrect_for_vibranium_tool"));
            tag(INCORRECT_FOR_TIER_9_TOOL).addOptionalTag(AllTags.blockC("incorrect_for_unobtainium_tool"));
        }
    }

    public static class CompatFluidTagsProvider extends FluidTagsProvider {
        public CompatFluidTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
            super(packOutput, lookupProvider, SlagCompat.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider lookupProvider) {
            tag(AllTags.MOLTEN_METALS).add(
                    MekanismFluids.MOLTEN_REFINED_GLOWSTONE.getSource(),
                    MekanismFluids.MOLTEN_REFINED_OBSIDIAN.getSource(),
                    AllthemodiumFluids.MOLTEN_ALLTHEMODIUM.getSource(),
                    AllthemodiumFluids.MOLTEN_VIBRANIUM.getSource(),
                    AllthemodiumFluids.MOLTEN_UNOBTAINIUM.getSource(),
                    IceAndFireFluids.MOLTEN_FIRE_DRAGONSTEEL.getSource(),
                    IceAndFireFluids.MOLTEN_ICE_DRAGONSTEEL.getSource(),
                    IceAndFireFluids.MOLTEN_LIGHTNING_DRAGONSTEEL.getSource(),
                    TwilightForestFluids.MOLTEN_IRONWOOD.getSource(),
                    TwilightForestFluids.MOLTEN_STEELEAF.getSource(),
                    TwilightForestFluids.MOLTEN_KNIGHTMETAL.getSource(),
                    TwilightForestFluids.MOLTEN_FIERY.getSource(),
                    IronsSpellbooksFluids.MOLTEN_ARCANE.getSource(),
                    IronsSpellbooksFluids.MOLTEN_MITHRIL.getSource(),
                    IronsSpellbooksFluids.MOLTEN_PYRIUM.getSource(),
                    EternalStarlightFluids.MOLTEN_DEEPSILVER.getSource(),
                    EternalStarlightFluids.MOLTEN_THERMAL_SPRINGSTONE.getSource(),
                    EternalStarlightFluids.MOLTEN_AMARAMBER.getSource(),
                    EternalStarlightFluids.MOLTEN_AETHERSENT.getSource(),
                    EternalStarlightFluids.MOLTEN_GOLEM_STEEL.getSource(),
                    EternalStarlightFluids.MOLTEN_UNREALIUM.getSource()
            );
            tag(AllTags.MOLTEN_GEMS).add(
                    EternalStarlightFluids.MOLTEN_GLACITE.getSource(),
                    EternalStarlightFluids.MOLTEN_MALARITE.getSource(),
                    EternalStarlightFluids.MOLTEN_STARLIT_DIAMOND.getSource()
            );
            tag(AllTags.MOLTEN_DUSTS).add(
                    EternalStarlightFluids.MOLTEN_FLOWGLAZE.getSource(),
                    EternalStarlightFluids.MOLTEN_STARFIRE.getSource()
            );
            tag(AllTags.MOLTEN_BALLS).add(
                    EternalStarlightFluids.MOLTEN_SOUL_DEW.getSource()
            );
        }
    }
}
