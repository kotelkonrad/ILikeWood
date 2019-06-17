package yamahari.ilikewood.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class WoodenBookshelfBlock extends Block {
    public WoodenBookshelfBlock() {
        super(Block.Properties.create(Material.WOOD).hardnessAndResistance(1.5f).sound(SoundType.WOOD));
    }

    @Override
    public float getEnchantPowerBonus(BlockState state, IWorldReader world, BlockPos pos) {
        return 1.f;
    }
}
