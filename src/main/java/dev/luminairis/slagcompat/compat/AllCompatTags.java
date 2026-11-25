package dev.luminairis.slagcompat.compat;

import dev.luminairis.slagcompat.SlagCompat;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

@SuppressWarnings({"unused", "deprecation"})
public class AllCompatTags {

    public static TagKey<Block> INCORRECT_FOR_TIER_1_TOOL = block("incorrect_for_tier_1_tool");
    public static TagKey<Block> INCORRECT_FOR_TIER_2_TOOL = block("incorrect_for_tier_2_tool");
    public static TagKey<Block> INCORRECT_FOR_TIER_3_TOOL = block("incorrect_for_tier_3_tool");
    public static TagKey<Block> INCORRECT_FOR_TIER_4_TOOL = block("incorrect_for_tier_4_tool");
    public static TagKey<Block> INCORRECT_FOR_TIER_5_TOOL = block("incorrect_for_tier_5_tool");
    public static TagKey<Block> INCORRECT_FOR_TIER_6_TOOL = block("incorrect_for_tier_6_tool");
    public static TagKey<Block> INCORRECT_FOR_TIER_7_TOOL = block("incorrect_for_tier_7_tool");
    public static TagKey<Block> INCORRECT_FOR_TIER_8_TOOL = block("incorrect_for_tier_8_tool");
    public static TagKey<Block> INCORRECT_FOR_TIER_9_TOOL = block("incorrect_for_tier_9_tool");
    public static TagKey<Block> INCORRECT_FOR_TIER_10_TOOL = block("incorrect_for_tier_10_tool");
    public static TagKey<Block> INCORRECT_FOR_TIER_11_TOOL = block("incorrect_for_tier_11_tool");
    public static TagKey<Block> INCORRECT_FOR_TIER_12_TOOL = block("incorrect_for_tier_12_tool");
    public static TagKey<Block> INCORRECT_FOR_TIER_13_TOOL = block("incorrect_for_tier_13_tool");
    public static TagKey<Block> INCORRECT_FOR_TIER_14_TOOL = block("incorrect_for_tier_14_tool");
    public static TagKey<Block> INCORRECT_FOR_TIER_15_TOOL = block("incorrect_for_tier_15_tool");
    public static TagKey<Block> INCORRECT_FOR_TIER_16_TOOL = block("incorrect_for_tier_16_tool");
    public static TagKey<Block> INCORRECT_FOR_TIER_17_TOOL = block("incorrect_for_tier_17_tool");
    public static TagKey<Block> INCORRECT_FOR_TIER_18_TOOL = block("incorrect_for_tier_18_tool");
    public static TagKey<Block> INCORRECT_FOR_TIER_19_TOOL = block("incorrect_for_tier_19_tool");
    public static TagKey<Block> INCORRECT_FOR_TIER_20_TOOL = block("incorrect_for_tier_20_tool");

    public static TagKey<Block> block(String name) { return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(SlagCompat.MODID, name)); }
}
