package yamahari.ilikewood.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import yamahari.ilikewood.util.WoodType;

public class WoodenStrippedPostBlock extends WoodenPostBlock {
    private final WoodType woodType;

    protected WoodenStrippedPostBlock(Block.Properties properties, WoodType woodType) {
        super(properties, woodType);
        this.woodType = woodType;
    }

    public static WoodenStrippedPostBlock builder(WoodType woodType) {
        switch (woodType) {
            case OAK:
            default:
                return new WoodenStrippedPostBlock(Block.Properties.from(Blocks.STRIPPED_OAK_LOG), woodType);
            case DARK_OAK:
                return new WoodenStrippedPostBlock(Block.Properties.from(Blocks.STRIPPED_DARK_OAK_LOG), woodType);
            case SPRUCE:
                return new WoodenStrippedPostBlock(Block.Properties.from(Blocks.STRIPPED_SPRUCE_LOG), woodType);
            case BIRCH:
                return new WoodenStrippedPostBlock(Block.Properties.from(Blocks.STRIPPED_BIRCH_LOG), woodType);
            case ACACIA:
                return new WoodenStrippedPostBlock(Block.Properties.from(Blocks.STRIPPED_ACACIA_LOG), woodType);
            case JUNGLE:
                return new WoodenStrippedPostBlock(Block.Properties.from(Blocks.STRIPPED_JUNGLE_LOG), woodType);
        }
    }

    @Override
    public WoodType getWoodType() {
        return this.woodType;
    }
}
