package dev.girlboss.volumefix.mixins;

import net.minecraft.client.sound.Source;
import org.lwjgl.openal.AL10;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Source.class)
public class SourceMixin {
    @Redirect(
            method = "setVolume",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/lwjgl/openal/AL10;alSourcef(IIF)V"
            )
    )
    private void modifyVolume(int source, int param, float value) {
        AL10.alSourcef(source, param, (float) Math.pow(value, 2));
    }
}
