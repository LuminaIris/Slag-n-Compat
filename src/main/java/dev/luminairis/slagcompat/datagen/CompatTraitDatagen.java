package dev.luminairis.slagcompat.datagen;

import dev.lopyluna.slag.content.datagen.TraitDatagen;
import dev.lopyluna.slag.content.traits.TraitType;
import dev.lopyluna.slag.register.AllRegistries;
import dev.luminairis.slagcompat.SlagCompat;
import dev.luminairis.slagcompat.compat.traits.AllCompatTraits;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import javax.annotation.Nonnull;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class CompatTraitDatagen extends DatapackBuiltinEntriesProvider {

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(AllRegistries.TRAIT_TYPE_REGISTRY_KEY, b -> { for (var trait : AllCompatTraits.compatTraits) registerTrait(b, trait); });

    private static void registerTrait(BootstrapContext<TraitType> bootstrap, TraitType trait) {
        var key = ResourceKey.create(AllRegistries.TRAIT_TYPE_REGISTRY_KEY, trait.id);
        bootstrap.register(key, trait);
    }

    public CompatTraitDatagen(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of("slag"));
    }

    @Override
    public  @Nonnull String getName() {
        return SlagCompat.NAME + " Trait Datagen";
    }
}
