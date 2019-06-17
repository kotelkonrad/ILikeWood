package yamahari.ilikewood;


import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import yamahari.ilikewood.blocks.*;
import yamahari.ilikewood.container.WoodenWorkbenchContainer;
import yamahari.ilikewood.gui.screen.WoodenCraftingScreen;
import yamahari.ilikewood.objectholders.ModContainerType;
import yamahari.ilikewood.tileentities.WoodenBarrelTileEntity;
import yamahari.ilikewood.tileentities.WoodenChestTileEntity;
import yamahari.ilikewood.tileentities.renderer.WoodenChestItemStackTileEntityRenderer;
import yamahari.ilikewood.tileentities.renderer.WoodenChestTileEntityRenderer;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.WoodType;

@Mod(Constants.MOD_ID)
public class Main {
    public static final Logger logger = LogManager.getLogger(Constants.MOD_ID);

    public Main() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onFMLClientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onFMLCommonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void onFMLCommonSetup(final FMLCommonSetupEvent event) {
        logger.info(Constants.MOD_ID + " : common setup");
    }

    private void onFMLClientSetup(final FMLClientSetupEvent event) {
        logger.info(Constants.MOD_ID + " : client setup");
        ClientRegistry.bindTileEntitySpecialRenderer(WoodenChestTileEntity.class, new WoodenChestTileEntityRenderer<>());

        ScreenManager.registerFactory(ModContainerType.wooden_workbench_container, WoodenCraftingScreen::new);
    }

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEventHandler {
        @SubscribeEvent
        public static void onRegisterBlock(final RegistryEvent.Register<Block> event) {
            IForgeRegistry<Block> registry = event.getRegistry();

            for(WoodType woodType : WoodType.values()) {
                registry.registerAll(
                        new WoodenBookshelfBlock().setRegistryName(woodType.getName() + "_bookshelf"),
                        WoodenWallBlock.builder(woodType).setRegistryName(woodType.getName() + "_wall"),
                        new WoodenBarrelBlock(woodType).setRegistryName(woodType.getName() + "_barrel"),
                        new WoodenChestBlock(woodType).setRegistryName(woodType.getName() + "_chest"),
                        new WoodenCraftingTable(woodType).setRegistryName(woodType.getName() + "_crafting_table"),
                        WoodenBeamBlock.builder(woodType).setRegistryName(woodType.getName() + "_beam"),
                        new RotatedPillarBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.f).sound(SoundType.WOOD)).setRegistryName(woodType.getName() + "_panels")
                );
            }
        }

        @SubscribeEvent
        public static void onRegisterItem(final RegistryEvent.Register<Item> event) {
            IForgeRegistry<Item> registry = event.getRegistry();

            for(Block block : Constants.WALLS) {
                registry.register(new BlockItem(block, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName(block.getRegistryName()));
            }

            for(Block block : Constants.BARRELS) {
                registry.register(new BlockItem(block, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName(block.getRegistryName()));
            }

            for(Block block : Constants.CHESTS) {
                registry.register(new BlockItem(block, (new Item.Properties()).group(ItemGroup.DECORATIONS).setTEISR(() -> WoodenChestItemStackTileEntityRenderer::new)).setRegistryName(block.getRegistryName()));
            }

            for(Block block : Constants.CRAFTING_TABLES) {
                registry.register(new BlockItem(block, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName(block.getRegistryName()));
            }

            for(Block block : Constants.BEAMS) {
                registry.register(new BlockItem(block, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName(block.getRegistryName()));
            }

            for(Block block : Constants.BOOKSHELFS) {
                registry.register(new BlockItem(block, (new Item.Properties()).group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(block.getRegistryName()));
            }

            for(Block block : Constants.PANELS) {
                registry.register(new BlockItem(block, (new Item.Properties()).group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(block.getRegistryName()));
            }
        }

        @SubscribeEvent
        public static void onRegisterTileEntityType(final RegistryEvent.Register<TileEntityType<?>> event) {
            IForgeRegistry<TileEntityType<?>> registry = event.getRegistry();

            for(Block block : Constants.BARRELS) {
                WoodenBarrelBlock woodenBarrelBlock = (WoodenBarrelBlock)block;
                registry.register(
                    TileEntityType.Builder.create(() -> new WoodenBarrelTileEntity(woodenBarrelBlock.getTileEntityType(), woodenBarrelBlock.getWoodType()), block).build(null).setRegistryName(woodenBarrelBlock.getWoodType().getName() + "_barrel")
                );
            }

            for(Block block : Constants.CHESTS) {
                WoodenChestBlock woodenChestBlock = (WoodenChestBlock)block;
                registry.register(
                    TileEntityType.Builder.create(() -> new WoodenChestTileEntity(woodenChestBlock.getTileEntityType(), woodenChestBlock.getWoodType()), block).build(null).setRegistryName(woodenChestBlock.getWoodType().getName() + "_chest")
                );
            }
        }

        @SubscribeEvent
        public static void onRegisterContainerType(final RegistryEvent.Register<ContainerType<?>> event) {
            event.getRegistry().register(
                    new ContainerType<>(WoodenWorkbenchContainer::new).setRegistryName("wooden_workbench_container")
            );
        }
    }
}
