package dev.girlboss.volumefix.mixins;

import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.sound.SoundCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameOptions.class)
public abstract class GameOptionsMixin {
    @Shadow public abstract SimpleOption<Double> getSoundVolumeOption(SoundCategory category);

    @Inject(
            method = "getSoundVolume",
            at = @At("HEAD"),
            cancellable = true
    )
    private void modifyVolume(SoundCategory category, CallbackInfoReturnable<Float> callbackInfo) {
        float volume = this.getSoundVolumeOption(category).getValue().floatValue();
        callbackInfo.setReturnValue((float) Math.pow(volume, 2));
    }
}
