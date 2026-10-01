package dev.luminairis.slagcompat.datagen;

import dev.lopyluna.slag.content.types.MaterialType;
import dev.lopyluna.slag.register.AllRegistries;
import dev.luminairis.slagcompat.SlagCompat;
import dev.luminairis.slagcompat.compat.materials.AllCompatMaterials;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import org.jetbrains.annotations.NotNull;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class CompatMaterialDatagen extends DatapackBuiltinEntriesProvider {

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(AllRegistries.MATERIAL_TYPE_REGISTRY_KEY, b -> { for (var material : AllCompatMaterials.ALL_COMPAT_MATERIALS) registerMaterial(b, material); });

    private static void registerMaterial(BootstrapContext<MaterialType> bootstrap, MaterialType material) {
        var key = ResourceKey.create(AllRegistries.MATERIAL_TYPE_REGISTRY_KEY, material.id);
        bootstrap.register(key, material);
    }

    public CompatMaterialDatagen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of("slag"));
    }

    @Override
    public @NotNull String getName() {
        return SlagCompat.NAME + " Material Datagen";
    }
}
