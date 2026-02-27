package com.gogoclient;

import com.gogoclient.gui.GogoGui;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class GogoClient implements ClientModInitializer {
    public static boolean tcpBypass = false;
    public static boolean nbtSword = false;

    private static KeyBinding menuKey;

    @Override
    public void onInitializeClient() {
        // Регистрация клавиши Правый Shift для открытия GUI
        menuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.gogoclient.menu", 
                InputUtil.Type.KEYSYM, 
                GLFW.GLFW_KEY_RIGHT_SHIFT, 
                "category.gogoclient"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (menuKey.wasPressed()) {
                client.setScreen(GogoGui.createConfigScreen(client.currentScreen));
            }
        });
    }
}
