package dev.luminairis.slagcompat.compat.traits.effects;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.lopyluna.slag.content.traits.Trait;
import dev.lopyluna.slag.content.traits.TraitEffect;
import dev.lopyluna.slag.content.traits.Traits;
import dev.luminairis.slagcompat.SlagCompat;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public record BonusDamageEffect(Optional<Float> bonusDamage, Optional<ResourceKey<Attribute>> attributeKey, Optional<Float> attributePercentage, Optional<AttributeTarget> attributeTarget, Optional<List<TagKey<EntityType<?>>>> targetTypes, ResourceKey<DamageType> damageType) implements TraitEffect {
    public static final MapCodec<BonusDamageEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.FLOAT.optionalFieldOf("bonus_damage").forGetter(BonusDamageEffect::bonusDamage),
            ResourceKey.codec(Registries.ATTRIBUTE).optionalFieldOf("attribute_key").forGetter(BonusDamageEffect::attributeKey),
            Codec.FLOAT.optionalFieldOf("attribute_percentage").forGetter(BonusDamageEffect::attributePercentage),
            AttributeTarget.CODEC.optionalFieldOf("attribute_target").forGetter(BonusDamageEffect::attributeTarget),
            TagKey.codec(Registries.ENTITY_TYPE).listOf().optionalFieldOf("target_types").forGetter(BonusDamageEffect::targetTypes),
            ResourceKey.codec(Registries.DAMAGE_TYPE).fieldOf("bonus_damage_type").forGetter(BonusDamageEffect::damageType)
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
        if (targetTypes.isPresent()) {
            for (TagKey<EntityType<?>> tag : targetTypes.get()) {
                if (target.getType().is(tag)) {
                    float totalDmg = bonusDamage.orElse(0f);
                    if (attributeKey.isPresent() && attributePercentage.isPresent() && attributeTarget.isPresent()) {
                        float attribute = 0f;
                        switch (attributeTarget.get()) {
                            case SELF ->
                                    attribute = (float) Objects.requireNonNull(attacker.getAttribute(BuiltInRegistries.ATTRIBUTE.getHolderOrThrow(attributeKey.get()))).getValue();
                            case TARGET ->
                                    attribute = (float) Objects.requireNonNull(target.getAttribute(BuiltInRegistries.ATTRIBUTE.getHolderOrThrow(attributeKey.get()))).getValue();
                        }
                        totalDmg += attribute * attributePercentage.get();
                    }
                    if (totalDmg != 0f) {
                        target.hurt(new DamageSource(getDamageType(attacker, damageType), attacker), totalDmg);
                        SlagCompat.LOGGER.info("Bonus damage: " + totalDmg);
                    }
                    break;
                }
            }
        } else {
            float totalDmg = bonusDamage.orElse(0f);
            if (attributeKey.isPresent() && attributePercentage.isPresent() && attributeTarget.isPresent()) {
                float attribute = 0f;
                switch (attributeTarget.get()) {
                    case SELF -> attribute = (float) Objects.requireNonNull(attacker.getAttribute(BuiltInRegistries.ATTRIBUTE.getHolderOrThrow(attributeKey.get()))).getValue();
                    case TARGET -> attribute = (float) Objects.requireNonNull(target.getAttribute(BuiltInRegistries.ATTRIBUTE.getHolderOrThrow(attributeKey.get()))).getValue();
                }
                totalDmg += attribute * attributePercentage.get();
            }
            if (totalDmg != 0f) {
                target.hurt(new DamageSource(getDamageType(attacker, damageType), attacker), totalDmg);
                SlagCompat.LOGGER.info("Bonus damage of: " + totalDmg);
            }
        }
    }

    private Holder<DamageType> getDamageType(Entity entity, ResourceKey<DamageType> key) {
        Registry<DamageType> registry = entity.level().damageSources().damageTypes;
        return registry.getHolder(key).orElse(registry.getHolderOrThrow(DamageTypes.FELL_OUT_OF_WORLD));
    }

    public enum AttributeTarget implements StringRepresentable {
        SELF("self"),
        TARGET("target");

        public static final Codec<AttributeTarget> CODEC = StringRepresentable.fromEnum(AttributeTarget::values);

        private final String name;

        AttributeTarget(String name) {
            this.name = name;
        }

        @Override
        public @Nonnull String getSerializedName() {
            return name;
        }
    }
}
