package yamahari.ilikewood.tileentities.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.block.BlockState;
import net.minecraft.block.LecternBlock;
import net.minecraft.client.renderer.entity.model.BookModel;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.tileentity.LecternTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yamahari.ilikewood.blocks.WoodenLecternBlock;
import yamahari.ilikewood.tileentities.WoodenLecternTileEntity;

@OnlyIn(Dist.CLIENT)
public class WoodenLecternTileEntityRenderer extends TileEntityRenderer<WoodenLecternTileEntity> {
    private static final ResourceLocation field_217655_c = new ResourceLocation("textures/entity/enchanting_table_book.png");
    private final BookModel field_217656_d = new BookModel();

    public WoodenLecternTileEntityRenderer() {
    }

    @Override
    public void func_199341_a(WoodenLecternTileEntity p_199341_1_, double p_199341_2_, double p_199341_4_, double p_199341_6_, float p_199341_8_, int p_199341_9_) {
        BlockState lvt_10_1_ = p_199341_1_.getBlockState();
        if ((Boolean)lvt_10_1_.get(WoodenLecternBlock.HAS_BOOK)) {
            GlStateManager.pushMatrix();
            GlStateManager.translatef((float)p_199341_2_ + 0.5F, (float)p_199341_4_ + 1.0F + 0.0625F, (float)p_199341_6_ + 0.5F);
            float lvt_11_1_ = ((Direction)lvt_10_1_.get(WoodenLecternBlock.FACING)).rotateY().getHorizontalAngle();
            GlStateManager.rotatef(-lvt_11_1_, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotatef(67.5F, 0.0F, 0.0F, 1.0F);
            GlStateManager.translatef(0.0F, -0.125F, 0.0F);
            this.bindTexture(field_217655_c);
            GlStateManager.enableCull();
            this.field_217656_d.func_217103_a(0.0F, 0.1F, 0.9F, 1.2F, 0.0F, 0.0625F);
            GlStateManager.popMatrix();
        }
    }
}
