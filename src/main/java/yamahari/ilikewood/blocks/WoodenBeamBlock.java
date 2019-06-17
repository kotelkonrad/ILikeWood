package yamahari.ilikewood.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.block.material.PushReaction;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class WoodenBeamBlock extends DirectionalBlock {
    protected static final VoxelShape WOODEN_BEAM_VERTICAL_AABB = Block.makeCuboidShape(4.f, 0.f, 4.f, 12.f, 16.f, 12.f);
    protected static final VoxelShape WOODEN_BEAM_NS_AABB = Block.makeCuboidShape(4.f, 4.f, 0.f, 12.f, 12.f, 16.f);
    protected static final VoxelShape WOODEN_BEAM_EW_AABB = Block.makeCuboidShape(0.f, 4.f, 4.f, 16.f, 12.f, 12.f);

    public WoodenBeamBlock(Block.Properties properties) {
        super(properties);
        this.setDefaultState((BlockState)((BlockState)this.stateContainer.getBaseState()).with(FACING, Direction.UP));
    }

    @Override
    public BlockState rotate(BlockState p_185499_1_, Rotation p_185499_2_) {
        return (BlockState)p_185499_1_.with(FACING, p_185499_2_.rotate((Direction)p_185499_1_.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState p_185471_1_, Mirror p_185471_2_) {
        return (BlockState)p_185471_1_.with(FACING, p_185471_2_.mirror((Direction)p_185471_1_.get(FACING)));
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        switch (((Direction) p_220053_1_.get(FACING)).getAxis()) {
            case X:
            default:
                return WOODEN_BEAM_EW_AABB;
            case Z:
                return WOODEN_BEAM_NS_AABB;
            case Y:
                return WOODEN_BEAM_VERTICAL_AABB;
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        switch (((Direction) p_220053_1_.get(FACING)).getAxis()) {
            case X:
            default:
                return WOODEN_BEAM_EW_AABB;
            case Z:
                return WOODEN_BEAM_NS_AABB;
            case Y:
                return WOODEN_BEAM_VERTICAL_AABB;
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        Direction lvt_2_1_ = p_196258_1_.getFace();
        BlockState lvt_3_1_ = p_196258_1_.getWorld().getBlockState(p_196258_1_.getPos().offset(lvt_2_1_.getOpposite()));
        return lvt_3_1_.getBlock() == this && lvt_3_1_.get(FACING) == lvt_2_1_ ? (BlockState)this.getDefaultState().with(FACING, lvt_2_1_.getOpposite()) : (BlockState)this.getDefaultState().with(FACING, lvt_2_1_);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(new IProperty[]{FACING});
    }

    @Override
    public PushReaction getPushReaction(BlockState p_149656_1_) {
        return PushReaction.NORMAL;
    }
}
