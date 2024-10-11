package dev.girlboss.volumefix.mixins;

import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PositionedSoundInstance.class)
public abstract class PositionedSoundInstanceMixin {
    @Shadow
    public static PositionedSoundInstance master(SoundEvent soundEvent, float f, float g) {
        return null;
    }

    @Redirect(
            method = "master(Lnet/minecraft/sound/SoundEvent;F)Lnet/minecraft/client/sound/PositionedSoundInstance;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/sound/PositionedSoundInstance;master(Lnet/minecraft/sound/SoundEvent;FF)Lnet/minecraft/client/sound/PositionedSoundInstance;"
            )
    )
    private static PositionedSoundInstance fixMasterVolume(SoundEvent soundEvent, float pitch, float volume) {
        return master(soundEvent, pitch, (float) Math.sqrt(volume));
    }
}
