package yamahari.ilikewood.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodType;

public class WoodenComposterBlock extends ComposterBlock implements IWooden {
    private final WoodType woodType;

    public WoodenComposterBlock(WoodType woodType) {
        super(Block.Properties.create(Material.WOOD).hardnessAndResistance(0.6F).sound(SoundType.WOOD));
        this.woodType = woodType;
    }

    @Override
    public WoodType getWoodType() {
        return this.woodType;
    }
}
