package dev.girlboss.volumefix.mixins;

import net.minecraft.client.sound.Listener;
import org.lwjgl.openal.AL10;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Listener.class)
public class ListenerMixin {
    @Redirect(
            method = "setVolume",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/lwjgl/openal/AL10;alListenerf(IF)V"
            )
    )
    private void modifyVolume(int paramName, float value) {
        AL10.alListenerf(paramName, (float) Math.pow(value, 2));
    }
}
