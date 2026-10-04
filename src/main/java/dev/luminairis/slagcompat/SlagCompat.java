package dev.luminairis.slagcompat;

import dev.lopyluna.slag.content.utils.Registration;
import dev.luminairis.slagcompat.compat.fluids.AllCompatFluids;
import dev.luminairis.slagcompat.compat.traits.effects.AllCompatTraitEffects;
import dev.luminairis.slagcompat.compat.traits.AllCompatTraits;
import dev.luminairis.slagcompat.datagen.CompatDatagen;
import dev.luminairis.slagcompat.compat.materials.AllCompatMaterials;
import net.neoforged.bus.api.EventPriority;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(SlagCompat.MODID)
public class SlagCompat {
    public static final String NAME = "Slag n' Compat";
    public static final String MODID = "slagcompat";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static Registration REG = new Registration(MODID);

    public SlagCompat(IEventBus modEventBus, ModContainer modContainer) {
        REG.registerEventListeners(modEventBus);
        AllCompatTraitEffects.register(modEventBus);
        AllCompatFluids.register();
        AllCompatMaterials.register();
        AllCompatTraits.register();
        modEventBus.addListener(EventPriority.LOWEST, CompatDatagen::gatherData);
    }
}
