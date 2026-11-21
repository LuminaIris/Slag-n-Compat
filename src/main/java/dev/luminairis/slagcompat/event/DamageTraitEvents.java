package dev.luminairis.slagcompat.event;

import dev.lopyluna.slag.content.traits.Traits;
import dev.luminairis.slagcompat.SlagCompat;
import dev.luminairis.slagcompat.compat.traits.effects.IDamageEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber(modid = SlagCompat.MODID)
public class DamageTraitEvents {
    @SubscribeEvent
    public static void onIncomingDamage(LivingIncomingDamageEvent event) {
        if (!(event.getSource().getEntity() instanceof LivingEntity attacker)) return;
        if (attacker.level().isClientSide()) return;
        ItemStack stack = event.getSource().getWeaponItem();
        if (stack == null || stack.isEmpty()) return;
        Traits traits = Traits.of(stack);
        if (!traits.tool) return;

        float bonusDamageFromTraits = 0f;
        for (var hook : traits.hurtEnemy) {
            if (hook.effect() instanceof IDamageEffect e) {
                float dmg = e.calculateDamage(hook.trait(), stack, event.getEntity(), attacker);
                bonusDamageFromTraits += dmg;
            }
        }
        event.setAmount(event.getAmount() + bonusDamageFromTraits);
    }
}
