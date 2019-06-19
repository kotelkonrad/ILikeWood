package yamahari.ilikewood.proxy;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import yamahari.ilikewood.gui.screen.WoodenCraftingScreen;
import yamahari.ilikewood.objectholders.ModContainerType;
import yamahari.ilikewood.tileentities.WoodenChestTileEntity;
import yamahari.ilikewood.tileentities.renderer.WoodenChestTileEntityRenderer;

public class ClientProxy implements Proxy {
    @Override
    public void onClientSetup(FMLClientSetupEvent event) {
        ClientRegistry.bindTileEntitySpecialRenderer(WoodenChestTileEntity.class, new WoodenChestTileEntityRenderer<>());
        ScreenManager.registerFactory(ModContainerType.wooden_workbench_container, WoodenCraftingScreen::new);
    }

    @Override
    public void onCommonSetup(FMLCommonSetupEvent event) {

    }
}
