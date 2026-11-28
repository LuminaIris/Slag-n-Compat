package dev.luminairis.slagcompat.compat.traits.iceandfire;

import com.iafenvoy.iceandfire.item.ability.BuiltinAbilities;
import com.iafenvoy.iceandfire.registry.tag.IafEntityTags;
import dev.lopyluna.slag.content.traits.TraitType;
import dev.luminairis.slagcompat.SlagCompat;
import dev.luminairis.slagcompat.compat.traits.CompatTrait;
import dev.luminairis.slagcompat.compat.traits.effects.BonusDamageEffect;
import dev.luminairis.slagcompat.compat.traits.effects.OnHitAbility;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import java.util.List;

@SuppressWarnings("unused")
public class IceAndFireTraits extends CompatTrait {

    public static final TraitType DRAGONSLAYER = register(new TraitType.Builder("dragonslayer").modLoaded("iceandfire").color(0xE6A895)
            .effect(new BonusDamageEffect(4f, List.of(IafEntityTags.FIRE_DRAGON, IafEntityTags.ICE_DRAGON, IafEntityTags.LIGHTNING_DRAGON))));

    public static final TraitType FIRE_IMBUED = register(new TraitType.Builder("fire_imbued").modLoaded("iceandfire").color(0xFF0000)
            .effect(new OnHitAbility("slagcompat:fire_imbued")));

    public static final TraitType ICE_IMBUED = register(new TraitType.Builder("ice_imbued").modLoaded("iceandfire").color(0xADD8E6)
            .effect(new OnHitAbility("slagcompat:ice_imbued")));

    public static final TraitType LIGHTNING_IMBUED = register(new TraitType.Builder("lightning_imbued").modLoaded("iceandfire").color(0xAF32D9)
            .effect(new OnHitAbility("slagcompat:lightning_imbued")));

    public static void register() {
        OnHitAbility.registerOperation("slagcompat:fire_imbued", IceAndFireTraits::fireDragonsteelAbility);
        OnHitAbility.registerOperation("slagcompat:ice_imbued", IceAndFireTraits::iceDragonsteelAbility);
        OnHitAbility.registerOperation("slagcompat:lightning_imbued", IceAndFireTraits::lightningDragonsteelAbility);
        SlagCompat.LOGGER.info("Registering traits for mod: iceandfire");
    }

    private static void fireDragonsteelAbility(ItemStack stack, LivingEntity target, LivingEntity attacker, List<String> args) {
        BuiltinAbilities.DRAGONSTEEL_FIRE_TOOL.active(stack, target, attacker);
    }
    private static void iceDragonsteelAbility(ItemStack stack, LivingEntity target, LivingEntity attacker, List<String> args) {
        BuiltinAbilities.DRAGONSTEEL_ICE_TOOL.active(stack, target, attacker);
    }
    private static void lightningDragonsteelAbility(ItemStack stack, LivingEntity target, LivingEntity attacker, List<String> args) {
        BuiltinAbilities.DRAGONSTEEL_LIGHTNING_TOOL.active(stack, target, attacker);
    }
}
