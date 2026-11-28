package dev.luminairis.slagcompat.compat.traits.effects;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.lopyluna.slag.content.traits.Trait;
import dev.lopyluna.slag.content.traits.TraitEffect;
import dev.lopyluna.slag.content.traits.Traits;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("unused")
public record BonusDamageEffect(Optional<Float> bonusDamage, Optional<ResourceKey<Attribute>> attributeKey, Optional<Float> attributePercentage, Optional<AttributeTarget> attributeTarget, Optional<List<TagKey<EntityType<?>>>> targetTypes) implements TraitEffect, IDamageEffect {
    public static final MapCodec<BonusDamageEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.FLOAT.optionalFieldOf("bonus_damage").forGetter(BonusDamageEffect::bonusDamage),
            ResourceKey.codec(Registries.ATTRIBUTE).optionalFieldOf("attribute_key").forGetter(BonusDamageEffect::attributeKey),
            Codec.FLOAT.optionalFieldOf("attribute_percentage").forGetter(BonusDamageEffect::attributePercentage),
            AttributeTarget.CODEC.optionalFieldOf("attribute_target").forGetter(BonusDamageEffect::attributeTarget),
            TagKey.codec(Registries.ENTITY_TYPE).listOf().optionalFieldOf("target_types").forGetter(BonusDamageEffect::targetTypes)
    ).apply(instance, BonusDamageEffect::new));

    public BonusDamageEffect(float bonusDamage) {
        this(Optional.of(bonusDamage), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
    }
    public BonusDamageEffect(float bonusDamage, List<TagKey<EntityType<?>>> targetTypes) {
        this(Optional.of(bonusDamage), Optional.empty(), Optional.empty(), Optional.empty(), Optional.of(targetTypes));
    }
    public BonusDamageEffect(ResourceKey<Attribute> attributeKey, float attributePercentage, AttributeTarget attributeTarget) {
        this(Optional.empty(), Optional.of(attributeKey), Optional.of(attributePercentage), Optional.of(attributeTarget), Optional.empty());
    }
    public BonusDamageEffect(ResourceKey<Attribute> attributeKey, float attributePercentage, AttributeTarget attributeTarget, List<TagKey<EntityType<?>>> targetTypes) {
        this(Optional.empty(), Optional.of(attributeKey), Optional.of(attributePercentage), Optional.of(attributeTarget), Optional.of(targetTypes));
    }
    public BonusDamageEffect(float bonusDamage, ResourceKey<Attribute> attributeKey, float attributePercentage, AttributeTarget attributeTarget) {
        this(Optional.of(bonusDamage), Optional.of(attributeKey), Optional.of(attributePercentage), Optional.of(attributeTarget), Optional.empty());
    }
    public BonusDamageEffect(float bonusDamage, ResourceKey<Attribute> attributeKey, float attributePercentage, AttributeTarget attributeTarget, List<TagKey<EntityType<?>>> targetTypes) {
        this(Optional.of(bonusDamage), Optional.of(attributeKey), Optional.of(attributePercentage), Optional.of(attributeTarget), Optional.of(targetTypes));
    }

    @Override
    public MapCodec<? extends TraitEffect> codec() {
        return CODEC;
    }

    @Override
    public void collect(Trait trait, Traits.Builder builder) {
        builder.hurtEnemy(trait, this);
    }

    @Override
    public float calculateBonusDamage(Trait trait, ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof Player player && player.getAttackStrengthScale(0) != 1.0F) return 0f;
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
                    return totalDmg;
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
            return totalDmg;
        }
        return 0f;
    }

    @Override
    public float calculateDamageMultiplier(Trait trait, ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return 1f;
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
