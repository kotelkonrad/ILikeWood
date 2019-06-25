package yamahari.ilikewood.tileentities.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.model.ChestModel;
import net.minecraft.client.renderer.tileentity.model.LargeChestModel;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import yamahari.ilikewood.blocks.WoodenChestBlock;
import yamahari.ilikewood.objectholders.ModBlocks;
import yamahari.ilikewood.tileentities.WoodenChestTileEntity;
import yamahari.ilikewood.util.Constants;

public class WoodenChestTileEntityRenderer<T extends TileEntity & IChestLid> extends TileEntityRenderer<T> {
    private static ResourceLocation TEXTURE_OAK = new ResourceLocation(Constants.MOD_ID, "textures/entity/chest/oak.png");
    private static ResourceLocation TEXTURE_DARK_OAK = new ResourceLocation(Constants.MOD_ID, "textures/entity/chest/dark_oak.png");
    private static ResourceLocation TEXTURE_SPRUCE = new ResourceLocation(Constants.MOD_ID, "textures/entity/chest/spruce.png");
    private static ResourceLocation TEXTURE_BIRCH = new ResourceLocation(Constants.MOD_ID, "textures/entity/chest/birch.png");
    private static ResourceLocation TEXTURE_JUNGLE = new ResourceLocation(Constants.MOD_ID, "textures/entity/chest/jungle.png");
    private static ResourceLocation TEXTURE_ACACIA = new ResourceLocation(Constants.MOD_ID, "textures/entity/chest/acacia.png");

    private static ResourceLocation TEXTURE_OAK_DOUBLE = new ResourceLocation(Constants.MOD_ID, "textures/entity/chest/oak_double.png");
    private static ResourceLocation TEXTURE_DARK_OAK_DOUBLE = new ResourceLocation(Constants.MOD_ID, "textures/entity/chest/dark_oak_double.png");
    private static ResourceLocation TEXTURE_SPRUCE_DOUBLE = new ResourceLocation(Constants.MOD_ID, "textures/entity/chest/spruce_double.png");
    private static ResourceLocation TEXTURE_BIRCH_DOUBLE = new ResourceLocation(Constants.MOD_ID, "textures/entity/chest/birch_double.png");
    private static ResourceLocation TEXTURE_JUNGLE_DOUBLE = new ResourceLocation(Constants.MOD_ID, "textures/entity/chest/jungle_double.png");
    private static ResourceLocation TEXTURE_ACACIA_DOUBLE = new ResourceLocation(Constants.MOD_ID, "textures/entity/chest/acacia_double.png");

    private final ChestModel simpleChest = new ChestModel();
    private final ChestModel largeChest = new LargeChestModel();

    @Override
    public void func_199341_a(T tileEntityIn, double x, double y, double z, float partialTicks, int destroyStage) {
        GlStateManager.enableDepthTest();
        GlStateManager.depthFunc(515);
        GlStateManager.depthMask(true);
        BlockState lvt_10_1_ = tileEntityIn.hasWorld() ? tileEntityIn.getBlockState() : (BlockState) ModBlocks.oak_chest.getDefaultState().with(ChestBlock.FACING, Direction.SOUTH);

        ChestType lvt_11_1_ = lvt_10_1_.has(WoodenChestBlock.TYPE) ? (ChestType)lvt_10_1_.get(WoodenChestBlock.TYPE) : ChestType.SINGLE;
        if (lvt_11_1_ != ChestType.LEFT) {
            boolean lvt_12_1_ = lvt_11_1_ != ChestType.SINGLE;
            ChestModel lvt_13_1_ = this.func_199347_a(tileEntityIn, destroyStage, lvt_12_1_);
            if (destroyStage >= 0) {
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
            GlStateManager.translatef((float)x, (float)y + 1.0F, (float)z + 1.0F);
            GlStateManager.scalef(1.0F, -1.0F, -1.0F);
            float lvt_14_1_ = ((Direction)lvt_10_1_.get(WoodenChestBlock.FACING)).getHorizontalAngle();
            if ((double)Math.abs(lvt_14_1_) > 1.0E-5D) {
                GlStateManager.translatef(0.5F, 0.5F, 0.5F);
                GlStateManager.rotatef(lvt_14_1_, 0.0F, 1.0F, 0.0F);
                GlStateManager.translatef(-0.5F, -0.5F, -0.5F);
            }

            this.func_199346_a(tileEntityIn, partialTicks, lvt_13_1_);
            lvt_13_1_.renderAll();
            GlStateManager.disableRescaleNormal();
            GlStateManager.popMatrix();
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            if (destroyStage >= 0) {
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
        }
        else if(p_199347_1_ instanceof WoodenChestTileEntity) {
            switch(((WoodenChestTileEntity)p_199347_1_).getWoodType()) {
                case OAK:
                default:
                    lvt_4_5_ = !p_199347_3_ ? TEXTURE_OAK : TEXTURE_OAK_DOUBLE;
                    break;
                case DARK_OAK:
                    lvt_4_5_ = !p_199347_3_ ? TEXTURE_DARK_OAK : TEXTURE_DARK_OAK_DOUBLE;
                    break;
                case SPRUCE:
                    lvt_4_5_ = !p_199347_3_ ? TEXTURE_SPRUCE : TEXTURE_SPRUCE_DOUBLE;
                    break;
                case BIRCH:
                    lvt_4_5_ = !p_199347_3_ ? TEXTURE_BIRCH : TEXTURE_BIRCH_DOUBLE;
                    break;
                case ACACIA:
                    lvt_4_5_ = !p_199347_3_ ? TEXTURE_ACACIA : TEXTURE_ACACIA_DOUBLE;
                    break;
                case JUNGLE:
                    lvt_4_5_ = !p_199347_3_ ? TEXTURE_JUNGLE : TEXTURE_JUNGLE_DOUBLE;
                    break;
            }
        }
        else {
            lvt_4_5_ = !p_199347_3_ ? TEXTURE_OAK : TEXTURE_OAK_DOUBLE;
        }

        this.bindTexture(lvt_4_5_);
        return p_199347_3_ ? this.largeChest : this.simpleChest;
    }

    private void func_199346_a(T p_199346_1_, float p_199346_2_, ChestModel p_199346_3_) {
        float lvt_4_1_ = ((IChestLid)p_199346_1_).getLidAngle(p_199346_2_);
        lvt_4_1_ = 1.0F - lvt_4_1_;
        lvt_4_1_ = 1.0F - lvt_4_1_ * lvt_4_1_ * lvt_4_1_;
        p_199346_3_.getLid().rotateAngleX = -(lvt_4_1_ * 1.5707964F);
    }
}
