package yamahari.ilikewood.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.WallBlock;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodType;

public class WoodenWallBlock extends WallBlock implements IWooden {
    private final WoodType woodType;


    private WoodenWallBlock(Block.Properties properties, WoodType woodType) {
        super(properties);
        this.woodType = woodType;
    }

    public static WoodenWallBlock builder(WoodType woodType) {
        switch (woodType) {
            case OAK:
            default:
                return new WoodenWallBlock(Block.Properties.from(Blocks.OAK_LOG), woodType);
            case DARK_OAK:
                return new WoodenWallBlock(Block.Properties.from(Blocks.DARK_OAK_LOG), woodType);
            case SPRUCE:
                return new WoodenWallBlock(Block.Properties.from(Blocks.SPRUCE_LOG), woodType);
            case BIRCH:
                return new WoodenWallBlock(Block.Properties.from(Blocks.BIRCH_LOG), woodType);
            case ACACIA:
                return new WoodenWallBlock(Block.Properties.from(Blocks.ACACIA_LOG), woodType);
            case JUNGLE:
                return new WoodenWallBlock(Block.Properties.from(Blocks.JUNGLE_LOG), woodType);
        }
    }

    @Override
    public WoodType getWoodType() {
        return woodType;
    }
}
