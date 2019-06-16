package yamahari.ilikewood.tileentities.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import yamahari.ilikewood.blocks.chest.WoodenChestBlock;
import yamahari.ilikewood.tileentities.chest.*;

public class WoodenChestItemStackTileEntityRenderer extends ItemStackTileEntityRenderer {
    @Override
    public void renderByItem(ItemStack p_179022_1_) {
        Block block = Block.getBlockFromItem(p_179022_1_.getItem());
        if(block instanceof WoodenChestBlock) {
            switch (((WoodenChestBlock)block).getWoodType()) {
                case OAK:
                    TileEntityRendererDispatcher.instance.renderAsItem(new OakChestTileEntity());
                    break;
                case DARK_OAK:
                    TileEntityRendererDispatcher.instance.renderAsItem(new DarkOakChestTileEntity());
                    break;
                case SPRUCE:
                    TileEntityRendererDispatcher.instance.renderAsItem(new SpruceChestTileEntity());
                    break;
                case BIRCH:
                    TileEntityRendererDispatcher.instance.renderAsItem(new BirchChestTileEntity());
                    break;
                case ACACIA:
                    TileEntityRendererDispatcher.instance.renderAsItem(new AcaciaChestTileEntity());
                    break;
                case JUNGLE:
                    TileEntityRendererDispatcher.instance.renderAsItem(new JungleChestTileEntity());
                    break;
            }
        } else {
            super.renderByItem(p_179022_1_);
        }
    }
}
