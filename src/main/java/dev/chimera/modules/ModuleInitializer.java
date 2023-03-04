package dev.chimera.modules;

import dev.chimera.ChimeraClient;
import dev.chimera.amalthea.EventListener;
import dev.chimera.amalthea.events.misc.TickEvent;
import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;
import java.util.List;

public class ModuleInitializer {
    private static boolean INITIALIZED = false;
    private static List<Module> MODULE_LIST = new ArrayList<>();

    private void buildModuleList() {

        /* Register all modules here in the format `MODULE_LIST.add(new YourModule());` */

        MODULE_LIST.add(new ExampleModule());
        
    }
    public void initializeModules() {
        buildModuleList();

        for (Module module : MODULE_LIST) {
            module.init();
        }

        INITIALIZED = true;

        ChimeraClient.EVENT_BUS.registerListenersInClass(this);
    }

    @EventListener( tag = "start" )
    public static void onTickStart(TickEvent event) {
        if (MinecraftClient.getInstance().player == null) return;
        for (Module module : MODULE_LIST) {
            module.onTickStart(event);
        }
    }

    @EventListener( tag = "end" )
    public static void onTickEnd(TickEvent event) {
        if (MinecraftClient.getInstance().player == null) return;
        for (Module module : MODULE_LIST) {
            module.onTickEnd(event);
        }
    }
}
