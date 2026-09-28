package dev.luminairis.slagcompat;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(SlagCompat.MODID)
public class SlagCompat {
    public static final String MODID = "slagcompat";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SlagCompat(IEventBus modEventBus, ModContainer modContainer) {
    }
}
