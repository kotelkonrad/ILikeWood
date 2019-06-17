package yamahari.ilikewood.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.block.material.PushReaction;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodType;

public class WoodenBeamBlock extends DirectionalBlock implements IWooden {
    protected static final VoxelShape WOODEN_BEAM_VERTICAL_AABB = Block.makeCuboidShape(4.f, 0.f, 4.f, 12.f, 16.f, 12.f);
    protected static final VoxelShape WOODEN_BEAM_NS_AABB = Block.makeCuboidShape(4.f, 4.f, 0.f, 12.f, 12.f, 16.f);
    protected static final VoxelShape WOODEN_BEAM_EW_AABB = Block.makeCuboidShape(0.f, 4.f, 4.f, 16.f, 12.f, 12.f);

    private final WoodType woodType;

    private WoodenBeamBlock(Block.Properties properties, WoodType woodType) {
        super(properties);
        this.setDefaultState((BlockState)((BlockState)this.stateContainer.getBaseState()).with(FACING, Direction.UP));
        this.woodType = woodType;
    }

    public static WoodenBeamBlock builder(WoodType woodType) {
        switch (woodType) {
            case OAK:
            default:
                return new WoodenBeamBlock(Block.Properties.from(Blocks.OAK_LOG), woodType);
            case DARK_OAK:
                return new WoodenBeamBlock(Block.Properties.from(Blocks.DARK_OAK_LOG), woodType);
            case SPRUCE:
                return new WoodenBeamBlock(Block.Properties.from(Blocks.SPRUCE_LOG), woodType);
            case BIRCH:
                return new WoodenBeamBlock(Block.Properties.from(Blocks.BIRCH_LOG), woodType);
            case ACACIA:
                return new WoodenBeamBlock(Block.Properties.from(Blocks.ACACIA_LOG), woodType);
            case JUNGLE:
                return new WoodenBeamBlock(Block.Properties.from(Blocks.JUNGLE_LOG), woodType);
        }
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
        return (BlockState)this.getDefaultState().with(FACING, p_196258_1_.getFace());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(new IProperty[]{FACING});
    }

    @Override
    public PushReaction getPushReaction(BlockState p_149656_1_) {
        return PushReaction.NORMAL;
    }

    @Override
    public WoodType getWoodType() {
        return null;
    }
}
