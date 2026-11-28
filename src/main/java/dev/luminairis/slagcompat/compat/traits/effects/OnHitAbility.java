package dev.luminairis.slagcompat.compat.traits.effects;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.lopyluna.slag.content.traits.Trait;
import dev.lopyluna.slag.content.traits.TraitEffect;
import dev.lopyluna.slag.content.traits.Traits;
import dev.luminairis.slagcompat.SlagCompat;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record OnHitAbility(String abilityKey, List<String> args) implements TraitEffect {
    public static final MapCodec<OnHitAbility> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
        Codec.STRING.fieldOf("ability_key").forGetter(OnHitAbility::abilityKey),
        Codec.STRING.listOf().fieldOf("args").forGetter(OnHitAbility::args)
    ).apply(instance, OnHitAbility::new));

    private static final Map<String, Operation> OPERATION_MAP = new HashMap<>();

    public OnHitAbility(String abilityKey) {
        this(abilityKey, List.of());
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
    public void onHurtEnemy(Trait trait, ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Operation o = getOperation(abilityKey);
        if (o != null) {
            o.execute(stack, target, attacker, args);
        } else {
            SlagCompat.LOGGER.warn("No registered OnHitAbility operation for key: " + abilityKey);
        }
    }

    public static void registerOperation(String abilityKey, Operation operation) {
        OPERATION_MAP.put(abilityKey, operation);
    }

    private Operation getOperation(String abilityKey) {
        return OPERATION_MAP.get(abilityKey);
    }

    @FunctionalInterface
    public interface Operation {
        void execute(ItemStack stack, LivingEntity target, LivingEntity attacker, List<String> args);
    }
}
