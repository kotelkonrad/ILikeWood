package yamahari.ilikewood.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.network.play.server.SChatPacket;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import yamahari.ilikewood.blocks.WoodenScaffoldingBlock;

import javax.annotation.Nullable;

public class WoodenScaffoldingItem extends BlockItem {
    public WoodenScaffoldingItem(Block p_i50039_1_, Properties p_i50039_2_) {
        super(p_i50039_1_, p_i50039_2_);
    }

    @Nullable
    @Override
    public BlockItemUseContext getBlockItemUseContext(BlockItemUseContext p_219984_1_) {
        BlockPos lvt_2_1_ = p_219984_1_.getPos();
        World lvt_3_1_ = p_219984_1_.getWorld();
        BlockState lvt_4_1_ = lvt_3_1_.getBlockState(lvt_2_1_);
        if (!(lvt_4_1_.getBlock() instanceof WoodenScaffoldingBlock)) {
            return WoodenScaffoldingBlock.func_220117_a(lvt_3_1_, lvt_2_1_) == 7 ? null : p_219984_1_;
        } else {
            Direction lvt_6_2_;
            if (p_219984_1_.isPlacerSneaking()) {
                lvt_6_2_ = p_219984_1_.func_221533_k() ? p_219984_1_.getFace().getOpposite() : p_219984_1_.getFace();
            } else {
                lvt_6_2_ = p_219984_1_.getFace() == Direction.UP ? p_219984_1_.getPlacementHorizontalFacing() : Direction.UP;
            }

            int lvt_7_1_ = 0;
            BlockPos.MutableBlockPos lvt_8_1_ = (new BlockPos.MutableBlockPos(lvt_2_1_)).move(lvt_6_2_);

            while(lvt_7_1_ < 7) {
                if (!lvt_3_1_.isRemote && !World.isValid(lvt_8_1_)) {
                    PlayerEntity lvt_9_1_ = p_219984_1_.getPlayer();
                    int lvt_10_1_ = lvt_3_1_.getHeight();
                    if (lvt_9_1_ instanceof ServerPlayerEntity && lvt_8_1_.getY() >= lvt_10_1_) {
                        SChatPacket lvt_11_1_ = new SChatPacket((new TranslationTextComponent("build.tooHigh", lvt_10_1_)).applyTextStyle(TextFormatting.RED), ChatType.GAME_INFO);
                        ((ServerPlayerEntity)lvt_9_1_).connection.sendPacket(lvt_11_1_);
                    }
                    break;
                }

                lvt_4_1_ = lvt_3_1_.getBlockState(lvt_8_1_);
                if (!(lvt_4_1_.getBlock() instanceof WoodenScaffoldingBlock)) {
                    if (lvt_4_1_.isReplaceable(p_219984_1_)) {
                        return BlockItemUseContext.func_221536_a(p_219984_1_, lvt_8_1_, lvt_6_2_);
                    }
                    break;
                }

                lvt_8_1_.move(lvt_6_2_);
                if (lvt_6_2_.getAxis().isHorizontal()) {
                    ++lvt_7_1_;
                }
            }

            return null;
        }
    }

    @Override
    protected boolean func_219987_d() {
        return false;
    }
}
