package yamahari.ilikewood;


import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import yamahari.ilikewood.blocks.*;
import yamahari.ilikewood.blocks.torch.WoodenTorchBlock;
import yamahari.ilikewood.blocks.torch.WoodenWallTorchBlock;
import yamahari.ilikewood.container.WoodenLecternContainer;
import yamahari.ilikewood.container.WoodenWorkbenchContainer;
import yamahari.ilikewood.items.*;
import yamahari.ilikewood.objectholders.ModBlocks;
import yamahari.ilikewood.proxy.ClientProxy;
import yamahari.ilikewood.proxy.CommonProxy;
import yamahari.ilikewood.proxy.Proxy;
import yamahari.ilikewood.tileentities.WoodenBarrelTileEntity;
import yamahari.ilikewood.tileentities.WoodenChestTileEntity;
import yamahari.ilikewood.tileentities.WoodenLecternTileEntity;
import yamahari.ilikewood.tileentities.renderer.WoodenChestItemStackTileEntityRenderer;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.WoodType;

import java.util.Map;

@Mod(Constants.MOD_ID)
public class Main {
    public static final Logger logger = LogManager.getLogger(Constants.MOD_ID);

    public static final Proxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public Main() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onFMLClientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onFMLCommonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void onFMLCommonSetup(final FMLCommonSetupEvent event) {
        logger.info(Constants.MOD_ID + " : common setup");
        proxy.onCommonSetup(event);
    }

    private void onFMLClientSetup(final FMLClientSetupEvent event) {
        logger.info(Constants.MOD_ID + " : client setup");
        proxy.onClientSetup(event);
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
                        new RotatedPillarBlock(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.f).sound(SoundType.WOOD)).setRegistryName(woodType.getName() + "_panels"),
                        WoodenPostBlock.builder(woodType).setRegistryName(woodType.getName() + "_post"),
                        WoodenStrippedPostBlock.builder(woodType).setRegistryName("stripped_" + woodType.getName() + "_post"),
                        new WoodenLecternBlock(woodType).setRegistryName(woodType.getName() + "_lectern"),
                        new WoodenScaffoldingBlock(woodType).setRegistryName(woodType.getName() + "_scaffolding"),
                        new WoodenLadderBlock(woodType).setRegistryName(woodType.getName() + "_ladder"),
                        new WoodenComposterBlock(woodType).setRegistryName(woodType.getName() + "_composter"),
                        new WoodenLogPileBlock(woodType).setRegistryName(woodType.getName() + "_log_pile"),
                        new WoodenTorchBlock(woodType).setRegistryName(woodType.getName() + "_torch"),
                        new WoodenWallTorchBlock(woodType).setRegistryName(woodType.getName() + "_wall_torch")
                );
            }

            for(DyeColor dyeColor : DyeColor.values()) {
                for(WoodType woodType : WoodType.values()) {
                    registry.register(
                            new WoodenBedBlock(woodType, dyeColor).setRegistryName(dyeColor.getName() + "_" + woodType.getName() + "_bed")
                    );
                }
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

            for(Block block : Constants.BOOKSHELFS) {
                registry.register(new BlockItem(block, (new Item.Properties()).group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(block.getRegistryName()));
            }

            for(Block block : Constants.PANELS) {
                registry.register(new BlockItem(block, (new Item.Properties()).group(ItemGroup.BUILDING_BLOCKS)).setRegistryName(block.getRegistryName()));
            }

            for(Block block : Constants.POSTS) {
                registry.register(new BlockItem(block, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName(block.getRegistryName()));
            }

            for(Block block : Constants.STRIPPED_POSTS) {
                registry.register(new BlockItem(block, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName(block.getRegistryName()));
            }

            for(Block block : Constants.LECTERNS) {
                registry.register(new BlockItem(block, (new Item.Properties()).group(ItemGroup.REDSTONE)).setRegistryName(block.getRegistryName()));
            }

            for(Block block : Constants.BEDS) {
                registry.register(new BedItem(block, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName(block.getRegistryName()));
            }

            for(Block block : Constants.SCAFFOLDINGS) {
                registry.register(new WoodenScaffoldingItem(block, new Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName(block.getRegistryName()));
            }

            for(Block block : Constants.LADDERS) {
                registry.register(new BlockItem(block, new Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName(block.getRegistryName()));
            }

            for(Block block : Constants.COMPOSTERS) {
                registry.register(new BlockItem(block, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(block.getRegistryName()));
            }

            for(Block block : Constants.LOG_PILES) {
                registry.register(new BlockItem(block, new Item.Properties().group(ItemGroup.DECORATIONS)).setRegistryName(block.getRegistryName()));
            }

            for(Map.Entry<Block, Block> entry : Constants.TORCHES.entrySet()) {
                registry.register(new WallOrFloorItem(entry.getKey(), entry.getValue(), (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName(entry.getKey().getRegistryName()));
            }

            for(WoodType woodType : WoodType.values()) {
                registry.register(new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName(woodType.getName() + "_stick"));
                for(String tier : Constants.ITEM_TIER_MAP.keySet()) {
                    if(tier.equals("wooden")) {
                        for(WoodType w : WoodType.values()) {
                            registry.registerAll(
                                    WoodenAxeItem.builder(woodType, Constants.ITEM_TIER_MAP.get(tier)).setRegistryName(w.getName() + "_" + tier + "_" + woodType.getName() + "_axe"),
                                    new WoodenPickaxeItem(woodType, Constants.ITEM_TIER_MAP.get(tier)).setRegistryName(w.getName() + "_" + tier + "_" + woodType.getName() + "_pickaxe"),
                                    new WoodenShovelItem(woodType, Constants.ITEM_TIER_MAP.get(tier)).setRegistryName(w.getName() + "_" + tier + "_" + woodType.getName() + "_shovel"),
                                    new WoodenSwordItem(woodType, Constants.ITEM_TIER_MAP.get(tier)).setRegistryName(w.getName() + "_" + tier + "_" + woodType.getName() + "_sword"),
                                    WoodenHoeItem.builder(woodType, Constants.ITEM_TIER_MAP.get(tier)).setRegistryName(w.getName() + "_" + tier + "_" + woodType.getName() + "_hoe")
                            );
                        }
                    }
                    else {
                        registry.registerAll(
                                WoodenAxeItem.builder(woodType, Constants.ITEM_TIER_MAP.get(tier)).setRegistryName(tier + "_" + woodType.getName() + "_axe"),
                                new WoodenPickaxeItem(woodType, Constants.ITEM_TIER_MAP.get(tier)).setRegistryName(tier + "_" + woodType.getName() + "_pickaxe"),
                                new WoodenShovelItem(woodType, Constants.ITEM_TIER_MAP.get(tier)).setRegistryName(tier + "_" + woodType.getName() + "_shovel"),
                                new WoodenSwordItem(woodType, Constants.ITEM_TIER_MAP.get(tier)).setRegistryName(tier + "_" + woodType.getName() + "_sword"),
                                WoodenHoeItem.builder(woodType, Constants.ITEM_TIER_MAP.get(tier)).setRegistryName(tier + "_" + woodType.getName() + "_hoe")
                        );
                    }
                }
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

            for(Block block : Constants.LECTERNS) {
                WoodenLecternBlock woodenLecternBlock = (WoodenLecternBlock)block;
                registry.register(
                        TileEntityType.Builder.create(() -> new WoodenLecternTileEntity(woodenLecternBlock.getTileEntityType(), woodenLecternBlock.getWoodType()), block).build(null).setRegistryName(woodenLecternBlock.getWoodType().getName() + "_lectern")
                );
            }
        }

        @SubscribeEvent
        public static void onRegisterContainerType(final RegistryEvent.Register<ContainerType<?>> event) {
            event.getRegistry().registerAll(
                    new ContainerType<>(WoodenWorkbenchContainer::new).setRegistryName("wooden_workbench_container"),
                    new ContainerType<>((p_221504_0_, p_221504_1_) -> new WoodenLecternContainer(p_221504_0_)).setRegistryName("wooden_lectern_container")
            );
        }
    }
}
