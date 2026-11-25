package dev.luminairis.slagcompat.datagen;

import dev.luminairis.slagcompat.SlagCompat;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unused")
public class CompatDatagen {
    public static void gatherData(GatherDataEvent event) {
        if (!event.getMods().contains(SlagCompat.MODID)) return;
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        generator.addProvider(event.includeServer(), new CompatMaterialDatagen(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new CompatTraitDatagen(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new CompatRecipeDatagen(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), new CompatTagsDatagen.CompatBlockTagsProvider(packOutput, lookupProvider, existingFileHelper));
    }
}
