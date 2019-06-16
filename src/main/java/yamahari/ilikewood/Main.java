package yamahari.ilikewood;


import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.WallBlock;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import yamahari.ilikewood.blocks.BookshelfBlock;
import yamahari.ilikewood.blocks.barrel.*;
import yamahari.ilikewood.blocks.chest.*;
import yamahari.ilikewood.blocks.craftingtable.*;
import yamahari.ilikewood.container.WoodenWorkbenchContainer;
import yamahari.ilikewood.gui.screen.WoodenCraftingScreen;
import yamahari.ilikewood.objectholders.ModBlocks;
import yamahari.ilikewood.objectholders.ModContainerType;
import yamahari.ilikewood.tileentities.barrel.*;
import yamahari.ilikewood.tileentities.chest.*;
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
        ClientRegistry.bindTileEntitySpecialRenderer(OakChestTileEntity.class, new WoodenChestTileEntityRenderer<>());
        ClientRegistry.bindTileEntitySpecialRenderer(DarkOakChestTileEntity.class, new WoodenChestTileEntityRenderer<>());
        ClientRegistry.bindTileEntitySpecialRenderer(SpruceChestTileEntity.class, new WoodenChestTileEntityRenderer<>());
        ClientRegistry.bindTileEntitySpecialRenderer(BirchChestTileEntity.class, new WoodenChestTileEntityRenderer<>());
        ClientRegistry.bindTileEntitySpecialRenderer(JungleChestTileEntity.class, new WoodenChestTileEntityRenderer<>());
        ClientRegistry.bindTileEntitySpecialRenderer(AcaciaChestTileEntity.class, new WoodenChestTileEntityRenderer<>());

        ScreenManager.registerFactory(ModContainerType.wooden_workbench_container, new ScreenManager.IScreenFactory<WoodenWorkbenchContainer, WoodenCraftingScreen>() {
            @Override
            public WoodenCraftingScreen create(WoodenWorkbenchContainer p_create_1_, PlayerInventory p_create_2_, ITextComponent p_create_3_) {
                return new WoodenCraftingScreen(p_create_1_, p_create_2_, p_create_3_);
            }
        });
    }

    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEventHandler {
        @SubscribeEvent
        public static void onRegisterBlock(final RegistryEvent.Register<Block> event) {
            event.getRegistry().registerAll(
                new WallBlock(Block.Properties.from(Blocks.OAK_LOG)).setRegistryName("oak_wall"),
                new WallBlock(Block.Properties.from(Blocks.DARK_OAK_LOG)).setRegistryName("dark_oak_wall"),
                new WallBlock(Block.Properties.from(Blocks.SPRUCE_LOG)).setRegistryName("spruce_wall"),
                new WallBlock(Block.Properties.from(Blocks.BIRCH_LOG)).setRegistryName("birch_wall"),
                new WallBlock(Block.Properties.from(Blocks.ACACIA_LOG)).setRegistryName("acacia_wall"),
                new WallBlock(Block.Properties.from(Blocks.JUNGLE_LOG)).setRegistryName("jungle_wall"),

                new OakBarrelBlock().setRegistryName("oak_barrel"),
                new DarkOakBarrelBlock().setRegistryName("dark_oak_barrel"),
                new SpruceBarrelBlock().setRegistryName("spruce_barrel"),
                new BirchBarrelBlock().setRegistryName("birch_barrel"),
                new JungleBarrelBlock().setRegistryName("jungle_barrel"),
                new AcaciaBarrelBlock().setRegistryName("acacia_barrel"),

                new OakChestBlock().setRegistryName("oak_chest"),
                new DarkOakChestBlock().setRegistryName("dark_oak_chest"),
                new SpruceChestBlock().setRegistryName("spruce_chest"),
                new BirchChestBlock().setRegistryName("birch_chest"),
                new JungleChestBlock().setRegistryName("jungle_chest"),
                new AcaciaChestBlock().setRegistryName("acacia_chest"),

                new OakCraftingTable().setRegistryName("oak_crafting_table"),
                new DarkOakCraftingTable().setRegistryName("dark_oak_crafting_table"),
                new SpruceCraftingTable().setRegistryName("spruce_crafting_table"),
                new BirchCraftingTable().setRegistryName("birch_crafting_table"),
                new JungleCraftingTable().setRegistryName("jungle_crafting_table"),
                new AcaciaCraftingTable().setRegistryName("acacia_crafting_table")
            );

            for(WoodType woodType : WoodType.values()) {
                event.getRegistry().register(new BookshelfBlock().setRegistryName(woodType.getName() + "_bookshelf"));
            }
        }

        @SubscribeEvent
        public static void onRegisterItem(final RegistryEvent.Register<Item> event) {
            event.getRegistry().registerAll(
                new BlockItem(ModBlocks.oak_wall, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName("oak_wall"),
                new BlockItem(ModBlocks.dark_oak_wall, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName("dark_oak_wall"),
                new BlockItem(ModBlocks.spruce_wall, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName("spruce_wall"),
                new BlockItem(ModBlocks.birch_wall, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName("birch_wall"),
                new BlockItem(ModBlocks.acacia_wall, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName("acacia_wall"),
                new BlockItem(ModBlocks.jungle_wall, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName("jungle_wall"),

                new BlockItem(ModBlocks.oak_barrel, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName("oak_barrel"),
                new BlockItem(ModBlocks.dark_oak_barrel, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName("dark_oak_barrel"),
                new BlockItem(ModBlocks.spruce_barrel, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName("spruce_barrel"),
                new BlockItem(ModBlocks.birch_barrel, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName("birch_barrel"),
                new BlockItem(ModBlocks.jungle_barrel, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName("jungle_barrel"),
                new BlockItem(ModBlocks.acacia_barrel, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName("acacia_barrel"),

                new BlockItem(ModBlocks.oak_chest, (new Item.Properties()).group(ItemGroup.DECORATIONS).setTEISR(() -> WoodenChestItemStackTileEntityRenderer::new)).setRegistryName("oak_chest"),
                new BlockItem(ModBlocks.dark_oak_chest, (new Item.Properties()).group(ItemGroup.DECORATIONS).setTEISR(() -> WoodenChestItemStackTileEntityRenderer::new)).setRegistryName("dark_oak_chest"),
                new BlockItem(ModBlocks.spruce_chest, (new Item.Properties()).group(ItemGroup.DECORATIONS).setTEISR(() -> WoodenChestItemStackTileEntityRenderer::new)).setRegistryName("spruce_chest"),
                new BlockItem(ModBlocks.birch_chest, (new Item.Properties()).group(ItemGroup.DECORATIONS).setTEISR(() -> WoodenChestItemStackTileEntityRenderer::new)).setRegistryName("birch_chest"),
                new BlockItem(ModBlocks.jungle_chest, (new Item.Properties()).group(ItemGroup.DECORATIONS).setTEISR(() -> WoodenChestItemStackTileEntityRenderer::new)).setRegistryName("jungle_chest"),
                new BlockItem(ModBlocks.acacia_chest, (new Item.Properties()).group(ItemGroup.DECORATIONS).setTEISR(() -> WoodenChestItemStackTileEntityRenderer::new)).setRegistryName("acacia_chest"),

                new BlockItem(ModBlocks.oak_bookshelf, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName("oak_bookshelf"),
                new BlockItem(ModBlocks.dark_oak_bookshelf, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName("dark_oak_bookshelf"),
                new BlockItem(ModBlocks.spruce_bookshelf, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName("spruce_bookshelf"),
                new BlockItem(ModBlocks.birch_bookshelf, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName("birch_bookshelf"),
                new BlockItem(ModBlocks.jungle_bookshelf, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName("jungle_bookshelf"),
                new BlockItem(ModBlocks.acacia_bookshelf, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName("acacia_bookshelf"),

                new BlockItem(ModBlocks.oak_crafting_table, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName("oak_crafting_table"),
                new BlockItem(ModBlocks.dark_oak_crafting_table, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName("dark_oak_crafting_table"),
                new BlockItem(ModBlocks.spruce_crafting_table, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName("spruce_crafting_table"),
                new BlockItem(ModBlocks.birch_crafting_table, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName("birch_crafting_table"),
                new BlockItem(ModBlocks.jungle_crafting_table, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName("jungle_crafting_table"),
                new BlockItem(ModBlocks.acacia_crafting_table, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName("acacia_crafting_table")
            );
        }

        @SubscribeEvent
        public static void onRegisterTileEntityType(final RegistryEvent.Register<TileEntityType<?>> event) {
            event.getRegistry().registerAll(
                TileEntityType.Builder.create(OakBarrelTileEntity::new, ModBlocks.oak_barrel).build(null).setRegistryName("oak_barrel"),
                TileEntityType.Builder.create(DarkOakBarrelTileEntity::new, ModBlocks.dark_oak_barrel).build(null).setRegistryName("dark_oak_barrel"),
                TileEntityType.Builder.create(SpruceBarrelTileEntity::new, ModBlocks.spruce_barrel).build(null).setRegistryName("spruce_barrel"),
                TileEntityType.Builder.create(BirchBarrelTileEntity::new, ModBlocks.birch_barrel).build(null).setRegistryName("birch_barrel"),
                TileEntityType.Builder.create(JungleBarrelTileEntity::new, ModBlocks.jungle_barrel).build(null).setRegistryName("jungle_barrel"),
                TileEntityType.Builder.create(AcaciaBarrelTileEntity::new, ModBlocks.acacia_barrel).build(null).setRegistryName("acacia_barrel"),

                TileEntityType.Builder.create(OakChestTileEntity::new, ModBlocks.oak_chest).build(null).setRegistryName("oak_chest"),
                TileEntityType.Builder.create(DarkOakChestTileEntity::new, ModBlocks.dark_oak_chest).build(null).setRegistryName("dark_oak_chest"),
                TileEntityType.Builder.create(SpruceChestTileEntity::new, ModBlocks.spruce_chest).build(null).setRegistryName("spruce_chest"),
                TileEntityType.Builder.create(BirchChestTileEntity::new, ModBlocks.birch_chest).build(null).setRegistryName("birch_chest"),
                TileEntityType.Builder.create(JungleChestTileEntity::new, ModBlocks.jungle_chest).build(null).setRegistryName("jungle_chest"),
                TileEntityType.Builder.create(AcaciaChestTileEntity::new, ModBlocks.acacia_chest).build(null).setRegistryName("acacia_chest")
            );
        }

        @SubscribeEvent
        public static void onRegisterContainerType(final RegistryEvent.Register<ContainerType<?>> event) {
            event.getRegistry().register(new ContainerType<>(new ContainerType.IFactory<WoodenWorkbenchContainer>() {
                @Override
                public WoodenWorkbenchContainer create(int p_create_1_, PlayerInventory p_create_2_) {
                    return new WoodenWorkbenchContainer(p_create_1_, p_create_2_);
                }
            }).setRegistryName("wooden_workbench_container"));
        }
    }
}
