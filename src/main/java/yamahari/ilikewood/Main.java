package yamahari.ilikewood;


import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.WallBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import yamahari.ilikewood.util.Constants;

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
                new WallBlock(Block.Properties.from(Blocks.JUNGLE_LOG)).setRegistryName("jungle_wall")
            );
        }

        @SubscribeEvent
        public static void onRegisterItem(final RegistryEvent.Register<Item> event) {
            event.getRegistry().registerAll(
                new BlockItem(yamahari.ilikewood.objectholders.Blocks.oak_wall, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName("oak_wall"),
                new BlockItem(yamahari.ilikewood.objectholders.Blocks.dark_oak_wall, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName("dark_oak_wall"),
                new BlockItem(yamahari.ilikewood.objectholders.Blocks.spruce_wall, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName("spruce_wall"),
                new BlockItem(yamahari.ilikewood.objectholders.Blocks.birch_wall, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName("birch_wall"),
                new BlockItem(yamahari.ilikewood.objectholders.Blocks.acacia_wall, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName("acacia_wall"),
                new BlockItem(yamahari.ilikewood.objectholders.Blocks.jungle_wall, (new Item.Properties()).group(ItemGroup.DECORATIONS)).setRegistryName("jungle_wall"));
        }
    }
}
