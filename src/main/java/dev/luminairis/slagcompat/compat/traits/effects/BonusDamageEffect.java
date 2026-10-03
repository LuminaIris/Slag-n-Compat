package dev.luminairis.slagcompat.compat.traits.effects;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.lopyluna.slag.content.traits.Trait;
import dev.lopyluna.slag.content.traits.TraitEffect;
import dev.lopyluna.slag.content.traits.Traits;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public record BonusDamageEffect(float bonusDamage, ResourceKey<DamageType> damageType, List<TagKey<EntityType<?>>> targetTypes) implements TraitEffect {
    public static final MapCodec<BonusDamageEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.FLOAT.fieldOf("bonus_damage").forGetter(BonusDamageEffect::bonusDamage),
            ResourceKey.codec(Registries.DAMAGE_TYPE).fieldOf("bonus_damage_type").forGetter(BonusDamageEffect::damageType),
            TagKey.codec(Registries.ENTITY_TYPE).listOf().fieldOf("target_types").forGetter(BonusDamageEffect::targetTypes)
    ).apply(instance, BonusDamageEffect::new));

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
        if (attacker instanceof Player player && player.getAttackStrengthScale(0) != 1.0F) return;
        for (TagKey<EntityType<?>> tag : targetTypes) {
            if (target.getType().is(tag)) {
                target.hurt(new DamageSource(getDamageType(attacker, damageType), attacker), bonusDamage);
                break;
            }
        }
    }

    private Holder<DamageType> getDamageType(Entity entity, ResourceKey<DamageType> key) {
        Registry<DamageType> registry = entity.level().damageSources().damageTypes;
        return registry.getHolder(key).orElse(registry.getHolderOrThrow(DamageTypes.FELL_OUT_OF_WORLD));
    }
}
