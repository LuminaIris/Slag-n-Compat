package dev.luminairis.slagcompat.compat.traits.effects;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import dev.lopyluna.slag.content.traits.Trait;
import dev.lopyluna.slag.content.traits.TraitEffect;
import dev.lopyluna.slag.content.traits.Traits;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public record OnHitEffect(HitEffect onHitEffect) implements TraitEffect {
    public static final MapCodec<OnHitEffect> CODEC = HitEffect.CODEC.fieldOf("hit_effect").xmap(OnHitEffect::new, OnHitEffect::onHitEffect);

    private static final Map<HitEffect, Operation> OPERATION_MAP = new HashMap<>();

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

    public static void registerOperation(HitEffect effect, Operation operation) {
        OPERATION_MAP.put(effect, operation);
    }

    private Operation getOperation(HitEffect effect) {
        return OPERATION_MAP.get(effect);
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
    public interface Operation {
        void execute(ItemStack stack, LivingEntity target, LivingEntity attacker);
    }
}
