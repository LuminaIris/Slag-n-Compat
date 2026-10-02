package dev.luminairis.slagcompat.datagen;

import dev.luminairis.slagcompat.SlagCompat;
import net.minecraft.data.DataGenerator;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import static dev.luminairis.slagcompat.SlagCompat.REG;

@SuppressWarnings("unused")
public class CompatDatagen {
    public static void gatherData(GatherDataEvent event) {
        if (!event.getMods().contains(SlagCompat.MODID)) return;
        DataGenerator generator = event.getGenerator();
        generator.addProvider(event.includeServer(), new CompatMaterialDatagen(generator.getPackOutput(), event.getLookupProvider()));
        generator.addProvider(event.includeServer(), new CompatRecipeDatagen(generator.getPackOutput(), event.getLookupProvider()));
    }
}
