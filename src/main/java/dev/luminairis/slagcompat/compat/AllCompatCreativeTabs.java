package dev.luminairis.slagcompat.compat;

import dev.lopyluna.slag.register.AllCreativeTabs;
import dev.luminairis.slagcompat.compat.fluids.AllCompatFluids;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@SuppressWarnings("unused")
public class AllCompatCreativeTabs {
    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(AllCreativeTabs.BASE_TAB.getKey())) {
            for (var fluid: AllCompatFluids.ALL_COMPAT_FLUIDS) event.accept(new ItemStack(fluid.getSource().getBucket()), CreativeModeTab.TabVisibility.SEARCH_TAB_ONLY);
        }
    }
}
