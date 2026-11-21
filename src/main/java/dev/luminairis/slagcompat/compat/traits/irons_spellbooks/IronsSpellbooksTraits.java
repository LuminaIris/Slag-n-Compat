package dev.luminairis.slagcompat.compat.traits.irons_spellbooks;

import dev.lopyluna.slag.content.traits.TraitType;
import dev.luminairis.slagcompat.SlagCompat;
import dev.luminairis.slagcompat.compat.mobeffect.AllCompatMobEffects;
import dev.luminairis.slagcompat.compat.traits.CompatTrait;
import dev.luminairis.slagcompat.compat.traits.effects.BonusDamageEffect;
import dev.luminairis.slagcompat.compat.traits.effects.OnHitAbility;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.entity.spells.fireball.MagicFireball;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import java.util.Optional;

@SuppressWarnings("unused")
public class IronsSpellbooksTraits extends CompatTrait {

    public static final TraitType ARCANE = register(new TraitType.Builder("arcane").modLoaded("irons_spellbooks").color(0x99E9FF)
            .effect(new BonusDamageEffect(Optional.empty(), AttributeRegistry.MAX_MANA.unwrapKey(), Optional.of(0.03f), Optional.of(BonusDamageEffect.AttributeTarget.SELF), Optional.empty())));
    public static final TraitType HAVOC = register(new TraitType.Builder("havoc").color(0xB9892E)
            .effect(new OnHitAbility(OnHitAbility.HitAbility.HAVOC)));

    public static void register() {
        OnHitAbility.registerOperation(OnHitAbility.HitAbility.HAVOC, IronsSpellbooksTraits::havocExplosion);
        SlagCompat.LOGGER.info("Registering traits for mod: irons_spellbooks");
    }

    public static void havocExplosion(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target.hasEffect(AllCompatMobEffects.UNSTABLE)) {
            var effect = target.getEffect(AllCompatMobEffects.UNSTABLE);
            if (effect != null) {
                int amplifier = effect.getAmplifier() + 1;
                if (amplifier >= 2) {
                    Level world = target.level();
                    MagicFireball fireball = new MagicFireball(world, attacker);
                    Vec3 origin = target.getEyePosition();
                    fireball.setDamage(13f);
                    fireball.setExplosionRadius(5);
                    fireball.setPos(origin);
                    fireball.shoot(new Vec3(0, -1, 0));
                    world.addFreshEntity(fireball);
                    target.removeEffect(AllCompatMobEffects.UNSTABLE);
                } else {
                    target.addEffect(new MobEffectInstance(AllCompatMobEffects.UNSTABLE, 120, amplifier, false, true, false));
                }
            }
        } else {
            target.addEffect(new MobEffectInstance(AllCompatMobEffects.UNSTABLE, 120, 0, false, true, false));
        }
    }
}
