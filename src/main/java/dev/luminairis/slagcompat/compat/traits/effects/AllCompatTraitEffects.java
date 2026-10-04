package dev.luminairis.slagcompat.compat.traits.effects;

import com.mojang.serialization.MapCodec;
import dev.lopyluna.slag.content.traits.TraitEffect;
import dev.luminairis.slagcompat.SlagCompat;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static dev.lopyluna.slag.register.AllTraitEffects.REGISTRY_KEY;

import java.util.function.Supplier;

public class AllCompatTraitEffects {
    private static final DeferredRegister<MapCodec<? extends  TraitEffect>> EFFECTS = DeferredRegister.create(REGISTRY_KEY, SlagCompat.MODID);

    public static Holder<OnHitEffect> ON_HIT_EFFECT = register("on_hit_effect", () -> OnHitEffect.CODEC);
    public static Holder<BonusDamageEffect> BONUS_DAMAGE_EFFECT = register("bonus_damage_effect", () -> BonusDamageEffect.CODEC);

    private static <T extends TraitEffect> Holder<T> register(String name, Supplier<MapCodec<T>> codec) {
        return new Holder<>(EFFECTS.register(name, codec));
    }

    public static void register(IEventBus bus) {
        EFFECTS.register(bus);
    }

    public record Holder<A extends TraitEffect>(DeferredHolder<MapCodec<? extends TraitEffect>, MapCodec<A>> holder) implements Supplier<MapCodec<A>> {
        @Override public MapCodec<A> get() { return holder.get(); }
    }
}
