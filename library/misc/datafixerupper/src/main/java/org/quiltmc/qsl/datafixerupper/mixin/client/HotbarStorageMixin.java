/*
 * Copyright 2022 QuiltMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.quiltmc.qsl.datafixerupper.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.minecraft.client.option.HotbarStorage;
import net.minecraft.nbt.NbtCompound;

import org.quiltmc.qsl.datafixerupper.impl.QuiltDataFixesInternals;

@Mixin(HotbarStorage.class)
public abstract class HotbarStorageMixin {
	@Inject(
			method = "save",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/NbtIo;write(Lnet/minecraft/nbt/NbtCompound;Ljava/io/File;)V"),
			locals = LocalCapture.CAPTURE_FAILHARD
	)
	private void addModDataVersions(CallbackInfo ci, NbtCompound compound) {
		QuiltDataFixesInternals.get().addModDataVersions(compound);
	}
}
