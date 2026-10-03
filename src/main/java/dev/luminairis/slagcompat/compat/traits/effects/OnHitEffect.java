package dev.luminairis.slagcompat.compat.traits.effects;

import com.iafenvoy.iceandfire.item.ability.BuiltinAbilities;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import dev.lopyluna.slag.content.traits.Trait;
import dev.lopyluna.slag.content.traits.TraitEffect;
import dev.lopyluna.slag.content.traits.Traits;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import javax.annotation.Nonnull;

public record OnHitEffect(HitEffect onHitEffect) implements TraitEffect {
    public static final MapCodec<OnHitEffect> CODEC = HitEffect.CODEC.fieldOf("hit_effect").xmap(OnHitEffect::new, OnHitEffect::onHitEffect);

    public static final OnHitEffect FIRE_IMBUED = new OnHitEffect(HitEffect.FIRE_IMBUED);
    public static final OnHitEffect ICE_IMBUED = new OnHitEffect(HitEffect.ICE_IMBUED);
    public static final OnHitEffect LIGHTNING_IMBUED = new OnHitEffect(HitEffect.LIGHTNING_IMBUED);

    @Override
    public MapCodec<? extends TraitEffect> codec() {
        return CODEC;
    }

    @Override
    public void collect(Trait trait, Traits.Builder builder) {
        builder.hurtEnemy(trait, this);
    }

    @Override
    public void onHurtEnemy(Trait trait, ItemStack stack, LivingEntity target, LivingEntity attacker) {
        getOperation(onHitEffect).execute(stack, target, attacker);
    }

    private Operation getOperation(HitEffect effect) {
        return switch (effect) {
            case FIRE_IMBUED -> BuiltinAbilities.DRAGONSTEEL_FIRE_TOOL::active;
            case ICE_IMBUED -> BuiltinAbilities.DRAGONSTEEL_ICE_TOOL::active;
            case LIGHTNING_IMBUED -> BuiltinAbilities.DRAGONSTEEL_LIGHTNING_TOOL::active;
        };
    }

    public enum HitEffect implements StringRepresentable {
        FIRE_IMBUED("fire_imbued"),
        ICE_IMBUED("ice_imbued"),
        LIGHTNING_IMBUED("lightning_imbued");

        public static final Codec<HitEffect> CODEC = StringRepresentable.fromEnum(HitEffect::values);

        private final String name;

        HitEffect(String name) {
            this.name = name;
        }

        @Override
        public @Nonnull String getSerializedName() {
            return name;
        }
    }

    @FunctionalInterface
    private interface Operation {
        void execute(ItemStack stack, LivingEntity target, LivingEntity attacker);
    }
}
