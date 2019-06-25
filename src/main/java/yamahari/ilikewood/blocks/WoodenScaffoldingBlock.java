package yamahari.ilikewood.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import yamahari.ilikewood.util.Constants;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodType;

import java.util.Iterator;
import java.util.Random;

public class WoodenScaffoldingBlock extends Block implements IWooden, IWaterLoggable {
    private static final VoxelShape STABLE_SHAPE;
    private static final VoxelShape UNSTABLE_SHAPE;
    private static final VoxelShape field_220123_f = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
    private static final VoxelShape field_220124_g = VoxelShapes.fullCube().withOffset(0.0D, -1.0D, 0.0D);
    public static final IntegerProperty DISTANCE;
    public static final BooleanProperty WATERLOGGED;
    public static final BooleanProperty BOTTOM;
    private final WoodType woodType;

    public WoodenScaffoldingBlock(WoodType woodType) {
        super(Block.Properties.create(Material.WOOD).sound(SoundType.SCAFFOLDING).doesNotBlockMovement().variableOpacity());
        this.setDefaultState(this.stateContainer.getBaseState().with(DISTANCE, 7).with(WATERLOGGED, false).with(BOTTOM, false));
        this.woodType = woodType;
    }

    @Override
    public WoodType getWoodType() {
        return this.woodType;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(DISTANCE, WATERLOGGED, BOTTOM);
    }

    @Override
    public VoxelShape getShape(BlockState blockStateIn, IBlockReader blockReaderIn, BlockPos blockPosIn, ISelectionContext selectionContextIn) {
        boolean flag = false;
        for(Block block : Constants.SCAFFOLDINGS) {
            if(selectionContextIn.hasItem(Item.getItemFromBlock(block))) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            return blockStateIn.get(BOTTOM) ? UNSTABLE_SHAPE : STABLE_SHAPE;
        } else {
            return VoxelShapes.fullCube();
        }
    }

    @Override
    public VoxelShape getRaytraceShape(BlockState blockStateIn, IBlockReader blockReaderIn, BlockPos blockPosIn) {
        return VoxelShapes.fullCube();
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isReplaceable(BlockState blockStateIn, BlockItemUseContext blockItemUseContextIn) {
        //return blockItemUseContextIn.getItem().getItem() == this.asItem();
        for(Block block : Constants.SCAFFOLDINGS) {
            if(blockItemUseContextIn.getItem().getItem() == Item.getItemFromBlock(block)) return true;
        }
        return false;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext blockItemUseContextIn) {
        BlockPos blockPos = blockItemUseContextIn.getPos();
        World world = blockItemUseContextIn.getWorld();
        int distance = getDistance(world, blockPos);
        return this.getDefaultState().with(WATERLOGGED, world.getFluidState(blockPos).getFluid() == Fluids.WATER).with(DISTANCE, distance).with(BOTTOM, this.checkUnstable(world, blockPos, distance));
    }

    @Override
    public void onBlockAdded(BlockState blockStateIn0, World worldIn, BlockPos blockPosIn, BlockState blockStateIn1, boolean p_220082_5_) {
        if (!worldIn.isRemote) {
            worldIn.getPendingBlockTicks().scheduleTick(blockPosIn, this, 1);
        }

    }

    @Override
    public BlockState updatePostPlacement(BlockState blockStateIn0, Direction direction, BlockState blockStateIn1, IWorld worldIn, BlockPos blockPosIn0, BlockPos blockPosIn1) {
        if (blockStateIn0.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(blockPosIn0, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }

        if (!worldIn.isRemote()) {
            worldIn.getPendingBlockTicks().scheduleTick(blockPosIn0, this, 1);
        }

        return blockStateIn0;
    }

    @Override
    public void tick(BlockState blockStateIn, World worldIn, BlockPos blockPosIn, Random rand) {
        int distance = getDistance(worldIn, blockPosIn);
        BlockState blockState = blockStateIn.with(DISTANCE, distance).with(BOTTOM, this.checkUnstable(worldIn, blockPosIn, distance));
        if (blockState.get(DISTANCE) == 7) {
            if (blockStateIn.get(DISTANCE) == 7) {
                worldIn.addEntity(new FallingBlockEntity(worldIn, (double) blockPosIn.getX() + 0.5D, (double) blockPosIn.getY(), (double) blockPosIn.getZ() + 0.5D, (BlockState) blockState.with(WATERLOGGED, false)));
            } else {
                worldIn.destroyBlock(blockPosIn, true);
            }
        } else if (blockStateIn != blockState) {
            worldIn.setBlockState(blockPosIn, blockState, 3);
        }

    }

    @Override
    public boolean isValidPosition(BlockState blockStateIn, IWorldReader worldReaderIn, BlockPos blockPosIn) {
        return getDistance(worldReaderIn, blockPosIn) < 7;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState blockStateIn, IBlockReader blockReaderIn, BlockPos blockPosIn, ISelectionContext selectionContextIn) {
        if (selectionContextIn.func_216378_a(VoxelShapes.fullCube(), blockPosIn, true) && !selectionContextIn.isSneaking()) {
            return STABLE_SHAPE;
        } else {
            return blockStateIn.get(DISTANCE) != 0 && blockStateIn.get(BOTTOM) && selectionContextIn.func_216378_a(field_220124_g, blockPosIn, true) ? field_220123_f : VoxelShapes.empty();
        }
    }

    @Override
    public IFluidState getFluidState(BlockState blockStateIn) {
        return blockStateIn.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(blockStateIn);
    }

    private boolean checkUnstable(IBlockReader blockReaderIn, BlockPos blockPosIn, int distance) {
        return distance > 0 && !(blockReaderIn.getBlockState(blockPosIn.down()).getBlock() instanceof WoodenScaffoldingBlock);
    }

    public static int getDistance(IBlockReader blockReaderIn, BlockPos blockPosIn) {
        BlockPos.MutableBlockPos mutableBlockPos = (new BlockPos.MutableBlockPos(blockPosIn)).move(Direction.DOWN);
        BlockState blockState0 = blockReaderIn.getBlockState(mutableBlockPos);
        int distance = 7;
        if (blockState0.getBlock() instanceof WoodenScaffoldingBlock) {
            distance = blockState0.get(DISTANCE);
        } else if (Block.hasSolidSide(blockState0, blockReaderIn, mutableBlockPos, Direction.UP)) {
            return 0;
        }

        Iterator it = Direction.Plane.HORIZONTAL.iterator();

        while (it.hasNext()) {
            Direction direction = (Direction) it.next();
            BlockState blockState1 = blockReaderIn.getBlockState(mutableBlockPos.func_189533_g(blockPosIn).move(direction));
            if (blockState1.getBlock() instanceof WoodenScaffoldingBlock) {
                distance = Math.min(distance, blockState1.get(DISTANCE) + 1);
                if (distance == 1) {
                    break;
                }
            }
        }

        return distance;
    }

    static {
        DISTANCE = BlockStateProperties.DISTANCE_0_7;
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
        BOTTOM = BlockStateProperties.BOTTOM;
        VoxelShape lvt_0_1_ = Block.makeCuboidShape(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        VoxelShape lvt_1_1_ = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 2.0D);
        VoxelShape lvt_2_1_ = Block.makeCuboidShape(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
        VoxelShape lvt_3_1_ = Block.makeCuboidShape(0.0D, 0.0D, 14.0D, 2.0D, 16.0D, 16.0D);
        VoxelShape lvt_4_1_ = Block.makeCuboidShape(14.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
        STABLE_SHAPE = VoxelShapes.or(lvt_0_1_, new VoxelShape[]{lvt_1_1_, lvt_2_1_, lvt_3_1_, lvt_4_1_});
        VoxelShape lvt_5_1_ = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 2.0D, 2.0D, 16.0D);
        VoxelShape lvt_6_1_ = Block.makeCuboidShape(14.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
        VoxelShape lvt_7_1_ = Block.makeCuboidShape(0.0D, 0.0D, 14.0D, 16.0D, 2.0D, 16.0D);
        VoxelShape lvt_8_1_ = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 2.0D);
        UNSTABLE_SHAPE = VoxelShapes.or(field_220123_f, new VoxelShape[]{STABLE_SHAPE, lvt_6_1_, lvt_5_1_, lvt_8_1_, lvt_7_1_});
    }
}
