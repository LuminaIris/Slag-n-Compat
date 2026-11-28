package dev.luminairis.slagcompat.compat.traits.irons_spellbooks;

import dev.lopyluna.slag.SlagEmbers;
import dev.lopyluna.slag.content.traits.TraitType;
import dev.lopyluna.slag.content.traits.effects.AttributeEffect;
import dev.luminairis.slagcompat.SlagCompat;
import dev.luminairis.slagcompat.compat.mobeffect.AllCompatMobEffects;
import dev.luminairis.slagcompat.compat.traits.CompatTrait;
import dev.luminairis.slagcompat.compat.traits.effects.BonusDamageEffect;
import dev.luminairis.slagcompat.compat.traits.effects.BonusDamageEffect.AttributeTarget;
import dev.luminairis.slagcompat.compat.traits.effects.OnHitAbility;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import io.redspace.ironsspellbooks.entity.spells.fireball.MagicFireball;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import java.util.List;

@SuppressWarnings("unused")
public class IronsSpellbooksTraits extends CompatTrait {

    public static final TraitType MANA = register(new TraitType.Builder("mana").modLoaded("irons_spellbooks").hidden()
            .effect(AttributeEffect.equipped(AttributeRegistry.MAX_MANA, SlagEmbers.locMC("armor")).rounded()));
    public static final TraitType SPELL_RESIST = register(new TraitType.Builder("spell_resist").modLoaded("irons_spellbooks").hidden()
            .effect(AttributeEffect.equipped(AttributeRegistry.SPELL_RESIST, SlagEmbers.locMC("armor"))));
    public static final TraitType SPELL_POWER = register(new TraitType.Builder("spell_power").modLoaded("irons_spellbooks").hidden()
            .effect(AttributeEffect.equipped(AttributeRegistry.SPELL_POWER, SlagEmbers.locMC("armor"))));
    public static final TraitType ARCANE = register(new TraitType.Builder("arcane").modLoaded("irons_spellbooks").color(0x99E9FF)
            .effect(new BonusDamageEffect(AttributeRegistry.MAX_MANA.getKey(), 0.03f, AttributeTarget.SELF)));
    public static final TraitType HAVOC = register(new TraitType.Builder("havoc").modLoaded("irons_spellbooks").color(0xB9892E)
            .effect(new OnHitAbility("slagcompat:havoc", List.of("3", "13", "5"))));

    public static void register() {
        OnHitAbility.registerOperation("slagcompat:havoc", IronsSpellbooksTraits::havocExplosion);
        SlagCompat.LOGGER.info("Registering traits for mod: irons_spellbooks");
    }

    private static void havocExplosion(ItemStack stack, LivingEntity target, LivingEntity attacker, List<String> args) {
        int maxStacks;
        float damage, radius;
        try {
            maxStacks = Integer.parseInt(args.get(0));
            damage = Float.parseFloat(args.get(1));
            radius = Float.parseFloat(args.get(2));
        } catch (NumberFormatException e) {
            SlagCompat.LOGGER.error("Cannot run onHitAbility 'havocExplosion', incorrect args\n{}", String.valueOf(e));
            return;
        }
        if (target.hasEffect(AllCompatMobEffects.UNSTABLE)) {
            var effect = target.getEffect(AllCompatMobEffects.UNSTABLE);
            if (effect != null) {
                int amplifier = effect.getAmplifier() + 1;
                if (amplifier >= (maxStacks - 1)) {
                    Level world = target.level();
                    MagicFireball fireball = new MagicFireball(world, attacker);
                    Vec3 origin = target.getEyePosition();
                    fireball.setDamage(damage);
                    fireball.setExplosionRadius(radius);
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
