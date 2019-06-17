package yamahari.ilikewood.tileentities.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import yamahari.ilikewood.blocks.WoodenChestBlock;

public class WoodenChestItemStackTileEntityRenderer extends ItemStackTileEntityRenderer {
    @Override
    public void renderByItem(ItemStack p_179022_1_) {
        Block block = Block.getBlockFromItem(p_179022_1_.getItem());
        if(block instanceof WoodenChestBlock) {
            TileEntityRendererDispatcher.instance.renderAsItem(((WoodenChestBlock)block).getTileEntityType().create());
        } else {
            super.renderByItem(p_179022_1_);
        }
    }
}
