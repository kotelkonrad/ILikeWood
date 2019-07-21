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
    public WoodenScaffoldingItem(Block blockIn, Properties properties) {
        super(blockIn, properties);
    }

    @Nullable
    @Override
    public BlockItemUseContext getBlockItemUseContext(BlockItemUseContext blockItemUseContextIn) {
        BlockPos blockPos = blockItemUseContextIn.getPos();
        World world = blockItemUseContextIn.getWorld();
        BlockState blockState = world.getBlockState(blockPos);
        if (!(blockState.getBlock() instanceof WoodenScaffoldingBlock)) {
            return WoodenScaffoldingBlock.getDistance(world, blockPos) == 7 ? null : blockItemUseContextIn;
        } else {
            Direction direction;
            if (blockItemUseContextIn.isPlacerSneaking()) {
                direction = blockItemUseContextIn.func_221533_k() ? blockItemUseContextIn.getFace().getOpposite() : blockItemUseContextIn.getFace();
            } else {
                direction = blockItemUseContextIn.getFace() == Direction.UP ? blockItemUseContextIn.getPlacementHorizontalFacing() : Direction.UP;
            }

            int distance = 0;
            BlockPos.MutableBlockPos mutableBlockPos = (new BlockPos.MutableBlockPos(blockPos)).move(direction);

            while(distance < 7) {
                if (!world.isRemote && !World.isValid(mutableBlockPos)) {
                    PlayerEntity playerEntity = blockItemUseContextIn.getPlayer();
                    int height = world.getHeight();
                    if (playerEntity instanceof ServerPlayerEntity && mutableBlockPos.getY() >= height) {
                        SChatPacket sChatPacket = new SChatPacket((new TranslationTextComponent("build.tooHigh", height)).applyTextStyle(TextFormatting.RED), ChatType.GAME_INFO);
                        ((ServerPlayerEntity)playerEntity).connection.sendPacket(sChatPacket);
                    }
                    break;
                }

                blockState = world.getBlockState(mutableBlockPos);
                if (!(blockState.getBlock() instanceof WoodenScaffoldingBlock)) {
                    if (blockState.isReplaceable(blockItemUseContextIn)) {
                        return BlockItemUseContext.func_221536_a(blockItemUseContextIn, mutableBlockPos, direction);
                    }
                    break;
                }

                mutableBlockPos.move(direction);
                if (direction.getAxis().isHorizontal()) {
                    ++distance;
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
