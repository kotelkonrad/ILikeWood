package yamahari.ilikewood.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.LadderBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodType;

public class WoodenLadderBlock extends LadderBlock implements IWooden {
    private final WoodType woodType;

    public WoodenLadderBlock(WoodType woodType) {
        super(Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.4F).sound(SoundType.LADDER));
        this.woodType = woodType;
    }

    @Override
    public WoodType getWoodType() {
        return this.woodType;
    }
}
