package dev.luminairis.slagcompat.datagen;

import dev.lopyluna.slag.register.AllTags;
import dev.luminairis.slagcompat.SlagCompat;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import java.util.concurrent.CompletableFuture;

import static dev.luminairis.slagcompat.compat.AllCompatTags.*;

public class CompatTagsDatagen {
    public static class CompatBlockTagsProvider extends BlockTagsProvider {
        public CompatBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, SlagCompat.MODID, existingFileHelper);
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
}
