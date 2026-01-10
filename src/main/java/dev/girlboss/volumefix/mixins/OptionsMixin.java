package dev.girlboss.volumefix.mixins;

import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.sounds.SoundSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Options.class)
public abstract class OptionsMixin {
    @Shadow public abstract OptionInstance<Double> getSoundSourceOptionInstance(SoundSource category);

    @Inject(
            method = "getSoundSourceVolume",
            at = @At("HEAD"),
            cancellable = true
    )
    private void modifyVolume(SoundSource category, CallbackInfoReturnable<Float> callbackInfo) {
        float volume = this.getSoundSourceOptionInstance(category).get().floatValue();
        callbackInfo.setReturnValue((float) Math.pow(volume, 2));
    }
}
