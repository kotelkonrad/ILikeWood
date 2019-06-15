package yamahari.ilikewood.tileentities.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.client.renderer.tileentity.ChestTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.model.ChestModel;
import net.minecraft.client.renderer.tileentity.model.LargeChestModel;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.EnderChestTileEntity;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TrappedChestTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/*
@OnlyIn(Dist.CLIENT)
public class WoodenChestTileEntityRenderer<T extends TileEntity & IChestLid> extends TileEntityRenderer<T> {
    private static final ResourceLocation TEXTURE_OAK_NORMAL = new ResourceLocation("ilikewood:textures/entity/chest/oak_chest.png");

    private final ChestModel field_147510_h = new ChestModel();
    private final ChestModel field_147511_i = new LargeChestModel();

    public WoodenChestTileEntityRenderer() {

    }

    public void render(T p_199341_1_, double p_199341_2_, double p_199341_4_, double p_199341_6_, float p_199341_8_, int p_199341_9_) {
        GlStateManager.enableDepthTest();
        GlStateManager.depthFunc(515);
        GlStateManager.depthMask(true);
        BlockState lvt_10_1_ = p_199341_1_.hasWorld() ? p_199341_1_.getBlockState() : (BlockState) ModBlocks.CHEST.getDefaultState().with(ChestBlock.FACING, Direction.SOUTH);
        ChestType lvt_11_1_ = lvt_10_1_.has(ChestBlock.TYPE) ? (ChestType)lvt_10_1_.get(ChestBlock.TYPE) : ChestType.SINGLE;
        if (lvt_11_1_ != ChestType.LEFT) {
            boolean lvt_12_1_ = lvt_11_1_ != ChestType.SINGLE;
            ChestModel lvt_13_1_ = this.func_199347_a(p_199341_1_, p_199341_9_, lvt_12_1_);
            if (p_199341_9_ >= 0) {
                GlStateManager.matrixMode(5890);
                GlStateManager.pushMatrix();
                GlStateManager.scalef(lvt_12_1_ ? 8.0F : 4.0F, 4.0F, 1.0F);
                GlStateManager.translatef(0.0625F, 0.0625F, 0.0625F);
                GlStateManager.matrixMode(5888);
            } else {
                GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            }

            GlStateManager.pushMatrix();
            GlStateManager.enableRescaleNormal();
            GlStateManager.translatef((float)p_199341_2_, (float)p_199341_4_ + 1.0F, (float)p_199341_6_ + 1.0F);
            GlStateManager.scalef(1.0F, -1.0F, -1.0F);
            float lvt_14_1_ = ((Direction)lvt_10_1_.get(ChestBlock.FACING)).getHorizontalAngle();
            if ((double)Math.abs(lvt_14_1_) > 1.0E-5D) {
                GlStateManager.translatef(0.5F, 0.5F, 0.5F);
                GlStateManager.rotatef(lvt_14_1_, 0.0F, 1.0F, 0.0F);
                GlStateManager.translatef(-0.5F, -0.5F, -0.5F);
            }

            this.func_199346_a(p_199341_1_, p_199341_8_, lvt_13_1_);
            lvt_13_1_.renderAll();
            GlStateManager.disableRescaleNormal();
            GlStateManager.popMatrix();
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            if (p_199341_9_ >= 0) {
                GlStateManager.matrixMode(5890);
                GlStateManager.popMatrix();
                GlStateManager.matrixMode(5888);
            }

        }
    }

    private ChestModel func_199347_a(T p_199347_1_, int p_199347_2_, boolean p_199347_3_) {
        ResourceLocation lvt_4_5_;
        if (p_199347_2_ >= 0) {
            lvt_4_5_ = DESTROY_STAGES[p_199347_2_];
        } else if (this.isChristmas) {
            lvt_4_5_ = p_199347_3_ ? TEXTURE_CHRISTMAS_DOUBLE : TEXTURE_CHRISTMAS;
        } else if (p_199347_1_ instanceof TrappedChestTileEntity) {
            lvt_4_5_ = p_199347_3_ ? TEXTURE_TRAPPED_DOUBLE : TEXTURE_TRAPPED;
        } else if (p_199347_1_ instanceof EnderChestTileEntity) {
            lvt_4_5_ = field_199348_i;
        } else {
            lvt_4_5_ = p_199347_3_ ? TEXTURE_NORMAL_DOUBLE : TEXTURE_NORMAL;
        }

        this.bindTexture(lvt_4_5_);
        return p_199347_3_ ? this.field_147511_i : this.field_147510_h;
    }

    private void func_199346_a(T p_199346_1_, float p_199346_2_, ChestModel p_199346_3_) {
        float lvt_4_1_ = ((IChestLid)p_199346_1_).getLidAngle(p_199346_2_);
        lvt_4_1_ = 1.0F - lvt_4_1_;
        lvt_4_1_ = 1.0F - lvt_4_1_ * lvt_4_1_ * lvt_4_1_;
        p_199346_3_.getLid().rotateAngleX = -(lvt_4_1_ * 1.5707964F);
    }
}*/
