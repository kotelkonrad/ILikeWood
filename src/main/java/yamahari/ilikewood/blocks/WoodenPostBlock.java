package yamahari.ilikewood.blocks;

import com.google.common.collect.Maps;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodType;

import java.util.Map;


public class WoodenPostBlock extends RotatedPillarBlock implements IWooden, IWaterLoggable {
    protected static final VoxelShape WOODEN_POST_VERTICAL_AABB = Block.makeCuboidShape(4.f, 0.f, 4.f, 12.f, 16.f, 12.f);
    protected static final VoxelShape WOODEN_POST_NS_AABB = Block.makeCuboidShape(4.f, 4.f, 0.f, 12.f, 12.f, 16.f);
    protected static final VoxelShape WOODEN_POST_EW_AABB = Block.makeCuboidShape(0.f, 4.f, 4.f, 16.f, 12.f, 12.f);
    public static final BooleanProperty NORTH;
    public static final BooleanProperty EAST;
    public static final BooleanProperty SOUTH;
    public static final BooleanProperty WEST;
    public static final BooleanProperty UP;
    public static final BooleanProperty DOWN;
    public static final Map<Direction, BooleanProperty> FACING_TO_PROPERTY_MAP;

    public static final BooleanProperty WATERLOGGED;

    private final WoodType woodType;

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        super.fillStateContainer(p_206840_1_);
        p_206840_1_.add(new IProperty[]{NORTH, EAST, SOUTH, WEST, UP, DOWN, WATERLOGGED});
    }

    @Override
    public IFluidState getFluidState(BlockState p_204507_1_) {
        return (Boolean)p_204507_1_.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(p_204507_1_);
    }

    @Override
    public WoodType getWoodType() {
        return this.woodType;
    }

    protected WoodenPostBlock(Block.Properties properties, WoodType woodType) {
        super(properties);
        this.woodType = woodType;
        this.setDefaultState(
            this.getDefaultState()
                .with(AXIS, Direction.Axis.Y)
                .with(WATERLOGGED, false)
                .with(NORTH, false)
                .with(EAST, false)
                .with(SOUTH, false)
                .with(WEST, false)
                .with(UP, false)
                .with(DOWN, false)
        );
    }

    public static WoodenPostBlock builder(WoodType woodType) {
        switch (woodType) {
            case OAK:
            default:
                return new WoodenPostBlock(Block.Properties.from(Blocks.OAK_LOG), woodType);
            case DARK_OAK:
                return new WoodenPostBlock(Block.Properties.from(Blocks.DARK_OAK_LOG), woodType);
            case SPRUCE:
                return new WoodenPostBlock(Block.Properties.from(Blocks.SPRUCE_LOG), woodType);
            case BIRCH:
                return new WoodenPostBlock(Block.Properties.from(Blocks.BIRCH_LOG), woodType);
            case ACACIA:
                return new WoodenPostBlock(Block.Properties.from(Blocks.ACACIA_LOG), woodType);
            case JUNGLE:
                return new WoodenPostBlock(Block.Properties.from(Blocks.JUNGLE_LOG), woodType);
        }
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        switch (p_220053_1_.get(AXIS)) {
            case X:
            default:
                return WOODEN_POST_EW_AABB;
            case Z:
                return WOODEN_POST_NS_AABB;
            case Y:
                return WOODEN_POST_VERTICAL_AABB;
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        switch (p_220053_1_.get(AXIS)) {
            case X:
            default:
                return WOODEN_POST_EW_AABB;
            case Z:
                return WOODEN_POST_NS_AABB;
            case Y:
                return WOODEN_POST_VERTICAL_AABB;
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        IBlockReader blockReader = p_196258_1_.getWorld();
        BlockPos blockPos = p_196258_1_.getPos();

        Block down = blockReader.getBlockState(blockPos.down()).getBlock();
        Block up = blockReader.getBlockState(blockPos.up()).getBlock();
        Block north = blockReader.getBlockState(blockPos.north()).getBlock();
        Block east = blockReader.getBlockState(blockPos.east()).getBlock();
        Block south = blockReader.getBlockState(blockPos.south()).getBlock();
        Block west = blockReader.getBlockState(blockPos.west()).getBlock();

        Direction.Axis axis = p_196258_1_.getFace().getAxis();
        return this.getDefaultState().with(AXIS, p_196258_1_.getFace().getAxis())
                .with(DOWN, down instanceof WoodenPostBlock && axis != Direction.Axis.Y)
                .with(UP, up instanceof WoodenPostBlock && axis != Direction.Axis.Y)
                .with(NORTH, north instanceof WoodenPostBlock && axis != Direction.Axis.Z)
                .with(EAST, east instanceof WoodenPostBlock && axis != Direction.Axis.X)
                .with(SOUTH, south instanceof WoodenPostBlock && axis != Direction.Axis.Z)
                .with(WEST, west instanceof WoodenPostBlock && axis != Direction.Axis.X);
    }

    @Override
    public BlockState updatePostPlacement(BlockState p_196271_1_, Direction p_196271_2_, BlockState p_196271_3_, IWorld p_196271_4_, BlockPos p_196271_5_, BlockPos p_196271_6_) {
        Block lvt_7_1_ = p_196271_3_.getBlock();
        boolean lvt_8_1_ = lvt_7_1_ instanceof WoodenPostBlock && p_196271_2_.getAxis() != p_196271_1_.get(AXIS);
        return p_196271_1_.with(FACING_TO_PROPERTY_MAP.get(p_196271_2_), lvt_8_1_);
    }

    @Override
    public boolean onBlockActivated(BlockState p_220051_1_, World p_220051_2_, BlockPos p_220051_3_, PlayerEntity p_220051_4_, Hand p_220051_5_, BlockRayTraceResult p_220051_6_) {
        if(p_220051_5_ == Hand.MAIN_HAND && p_220051_4_.getHeldItem(p_220051_5_).getItem() instanceof AxeItem && !(p_220051_1_.getBlock() instanceof WoodenStrippedPostBlock)) {

            p_220051_2_.playSound(p_220051_4_, p_220051_3_, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if(!p_220051_2_.isRemote()) {
                p_220051_2_.setBlockState(p_220051_3_,
                        Constants.POST_STRIPPING_MAP.get(p_220051_1_.getBlock()).getDefaultState()
                                .with(AXIS, p_220051_1_.get(AXIS))
                                .with(WATERLOGGED, p_220051_1_.get(WATERLOGGED))
                                .with(DOWN, p_220051_1_.get(DOWN))
                                .with(UP, p_220051_1_.get(UP))
                                .with(NORTH, p_220051_1_.get(NORTH))
                                .with(EAST, p_220051_1_.get(EAST))
                                .with(SOUTH, p_220051_1_.get(SOUTH))
                                .with(WEST, p_220051_1_.get(WEST)),
                        11);
                p_220051_4_.getHeldItem(p_220051_5_).attemptDamageItem(1, p_220051_2_.rand, (ServerPlayerEntity)p_220051_4_);
            }
            return true;
        }
        return false;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    static {
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
        NORTH = BlockStateProperties.NORTH;
        EAST = BlockStateProperties.EAST;
        SOUTH = BlockStateProperties.SOUTH;
        WEST = BlockStateProperties.WEST;
        UP = BlockStateProperties.UP;
        DOWN = BlockStateProperties.DOWN;
        FACING_TO_PROPERTY_MAP = (Map) Util.make(Maps.newEnumMap(Direction.class), (p_203421_0_) -> {
            p_203421_0_.put(Direction.NORTH, NORTH);
            p_203421_0_.put(Direction.EAST, EAST);
            p_203421_0_.put(Direction.SOUTH, SOUTH);
            p_203421_0_.put(Direction.WEST, WEST);
            p_203421_0_.put(Direction.UP, UP);
            p_203421_0_.put(Direction.DOWN, DOWN);
        });
    }
}
