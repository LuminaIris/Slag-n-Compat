package dev.luminairis.slagcompat.compat.traits.effects;

import dev.lopyluna.slag.content.traits.Trait;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public interface IDamageEffect {
    float calculateBonusDamage(Trait trait, ItemStack stack, LivingEntity target, LivingEntity attacker);
    float calculateDamageMultiplier(Trait trait, ItemStack stack, LivingEntity target, LivingEntity attacker);
}
