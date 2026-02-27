package com.gogoclient.mixin;

import com.gogoclient.GogoClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.network.packet.c2s.play.CreativeInventoryActionC2SPacket;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class NbtSwordMixin {
    
    @Inject(method = "sendPacket", at = @At("HEAD"))
    private void onPacketSend(net.minecraft.network.packet.Packet<?> packet, CallbackInfo ci) {
        if (GogoClient.nbtSword && packet instanceof CreativeInventoryActionC2SPacket) {
            // Создаем "тяжелый" предмет с огромным количеством вложенных NBT
            ItemStack heavyStack = new ItemStack(Items.DIAMOND_SWORD);
            NbtCompound tag = new NbtCompound();
            NbtList list = new NbtList();
            
            for (int i = 0; i < 100; i++) {
                list.add(NbtString.of("GogoClient_Payload_Data_" + i));
            }
            tag.put("OverloadData", list);
            heavyStack.setNbt(tag);
            
            // Пакет теперь несет гораздо больше данных, чем обычно
        }
    }
}
