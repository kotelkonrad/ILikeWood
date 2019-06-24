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
    private static final VoxelShape field_220121_d;
    private static final VoxelShape field_220122_e;
    private static final VoxelShape field_220123_f = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
    private static final VoxelShape field_220124_g = VoxelShapes.fullCube().withOffset(0.0D, -1.0D, 0.0D);
    public static final IntegerProperty field_220118_a;
    public static final BooleanProperty field_220119_b;
    public static final BooleanProperty field_220120_c;
    private final WoodType woodType;

    public WoodenScaffoldingBlock(WoodType woodType) {
        super(Block.Properties.create(Material.WOOD).sound(SoundType.SCAFFOLDING).doesNotBlockMovement().variableOpacity());
        this.setDefaultState(this.stateContainer.getBaseState().with(field_220118_a, 7).with(field_220119_b, false).with(field_220120_c, false));
        this.woodType = woodType;
    }

    @Override
    public WoodType getWoodType() {
        return this.woodType;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> p_206840_1_) {
        p_206840_1_.add(field_220118_a, field_220119_b, field_220120_c);
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
        boolean flag = false;
        for(Block block : Constants.SCAFFOLDINGS) {
            if(p_220053_4_.hasItem(Item.getItemFromBlock(block))) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            return p_220053_1_.get(field_220120_c) ? field_220122_e : field_220121_d;
        } else {
            return VoxelShapes.fullCube();
        }
    }

    @Override
    public VoxelShape getRaytraceShape(BlockState p_199600_1_, IBlockReader p_199600_2_, BlockPos p_199600_3_) {
        return VoxelShapes.fullCube();
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isReplaceable(BlockState p_196253_1_, BlockItemUseContext p_196253_2_) {
        return p_196253_2_.getItem().getItem() == this.asItem();
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {
        BlockPos lvt_2_1_ = p_196258_1_.getPos();
        World lvt_3_1_ = p_196258_1_.getWorld();
        int lvt_4_1_ = func_220117_a(lvt_3_1_, lvt_2_1_);
        return this.getDefaultState().with(field_220119_b, lvt_3_1_.getFluidState(lvt_2_1_).getFluid() == Fluids.WATER).with(field_220118_a, lvt_4_1_).with(field_220120_c, this.func_220116_a(lvt_3_1_, lvt_2_1_, lvt_4_1_));
    }

    @Override
    public void onBlockAdded(BlockState p_220082_1_, World p_220082_2_, BlockPos p_220082_3_, BlockState p_220082_4_, boolean p_220082_5_) {
        if (!p_220082_2_.isRemote) {
            p_220082_2_.getPendingBlockTicks().scheduleTick(p_220082_3_, this, 1);
        }

    }

    @Override
    public BlockState updatePostPlacement(BlockState p_196271_1_, Direction p_196271_2_, BlockState p_196271_3_, IWorld p_196271_4_, BlockPos p_196271_5_, BlockPos p_196271_6_) {
        if (p_196271_1_.get(field_220119_b)) {
            p_196271_4_.getPendingFluidTicks().scheduleTick(p_196271_5_, Fluids.WATER, Fluids.WATER.getTickRate(p_196271_4_));
        }

        if (!p_196271_4_.isRemote()) {
            p_196271_4_.getPendingBlockTicks().scheduleTick(p_196271_5_, this, 1);
        }

        return p_196271_1_;
    }

    @Override
    public void tick(BlockState p_196267_1_, World p_196267_2_, BlockPos p_196267_3_, Random p_196267_4_) {
        int lvt_5_1_ = func_220117_a(p_196267_2_, p_196267_3_);
        BlockState lvt_6_1_ = p_196267_1_.with(field_220118_a, lvt_5_1_).with(field_220120_c, this.func_220116_a(p_196267_2_, p_196267_3_, lvt_5_1_));
        if (lvt_6_1_.get(field_220118_a) == 7) {
            if (p_196267_1_.get(field_220118_a) == 7) {
                p_196267_2_.addEntity(new FallingBlockEntity(p_196267_2_, (double) p_196267_3_.getX() + 0.5D, (double) p_196267_3_.getY(), (double) p_196267_3_.getZ() + 0.5D, (BlockState) lvt_6_1_.with(field_220119_b, false)));
            } else {
                p_196267_2_.destroyBlock(p_196267_3_, true);
            }
        } else if (p_196267_1_ != lvt_6_1_) {
            p_196267_2_.setBlockState(p_196267_3_, lvt_6_1_, 3);
        }

    }

    @Override
    public boolean isValidPosition(BlockState p_196260_1_, IWorldReader p_196260_2_, BlockPos p_196260_3_) {
        return func_220117_a(p_196260_2_, p_196260_3_) < 7;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState p_220071_1_, IBlockReader p_220071_2_, BlockPos p_220071_3_, ISelectionContext p_220071_4_) {
        if (p_220071_4_.func_216378_a(VoxelShapes.fullCube(), p_220071_3_, true) && !p_220071_4_.isSneaking()) {
            return field_220121_d;
        } else {
            return p_220071_1_.get(field_220118_a) != 0 && p_220071_1_.get(field_220120_c) && p_220071_4_.func_216378_a(field_220124_g, p_220071_3_, true) ? field_220123_f : VoxelShapes.empty();
        }
    }

    @Override
    public IFluidState getFluidState(BlockState p_204507_1_) {
        return p_204507_1_.get(field_220119_b) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(p_204507_1_);
    }

    private boolean func_220116_a(IBlockReader p_220116_1_, BlockPos p_220116_2_, int p_220116_3_) {
        return p_220116_3_ > 0 && !(p_220116_1_.getBlockState(p_220116_2_.down()).getBlock() instanceof WoodenScaffoldingBlock);
    }

    public static int func_220117_a(IBlockReader p_220117_0_, BlockPos p_220117_1_) {
        BlockPos.MutableBlockPos lvt_2_1_ = (new BlockPos.MutableBlockPos(p_220117_1_)).move(Direction.DOWN);
        BlockState lvt_3_1_ = p_220117_0_.getBlockState(lvt_2_1_);
        int lvt_4_1_ = 7;
        if (lvt_3_1_.getBlock() instanceof WoodenScaffoldingBlock) {
            lvt_4_1_ = lvt_3_1_.get(field_220118_a);
        } else if (Block.hasSolidSide(lvt_3_1_, p_220117_0_, lvt_2_1_, Direction.UP)) {
            return 0;
        }

        Iterator var5 = Direction.Plane.HORIZONTAL.iterator();

        while (var5.hasNext()) {
            Direction lvt_6_1_ = (Direction) var5.next();
            BlockState lvt_7_1_ = p_220117_0_.getBlockState(lvt_2_1_.setPos(p_220117_1_).move(lvt_6_1_));
            if (lvt_7_1_.getBlock() instanceof WoodenScaffoldingBlock) {
                lvt_4_1_ = Math.min(lvt_4_1_, lvt_7_1_.get(field_220118_a) + 1);
                if (lvt_4_1_ == 1) {
                    break;
                }
            }
        }

        return lvt_4_1_;
    }

    static {
        field_220118_a = BlockStateProperties.DISTANCE_0_7;
        field_220119_b = BlockStateProperties.WATERLOGGED;
        field_220120_c = BlockStateProperties.BOTTOM;
        VoxelShape lvt_0_1_ = Block.makeCuboidShape(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        VoxelShape lvt_1_1_ = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 2.0D, 16.0D, 2.0D);
        VoxelShape lvt_2_1_ = Block.makeCuboidShape(14.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.0D);
        VoxelShape lvt_3_1_ = Block.makeCuboidShape(0.0D, 0.0D, 14.0D, 2.0D, 16.0D, 16.0D);
        VoxelShape lvt_4_1_ = Block.makeCuboidShape(14.0D, 0.0D, 14.0D, 16.0D, 16.0D, 16.0D);
        field_220121_d = VoxelShapes.or(lvt_0_1_, new VoxelShape[]{lvt_1_1_, lvt_2_1_, lvt_3_1_, lvt_4_1_});
        VoxelShape lvt_5_1_ = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 2.0D, 2.0D, 16.0D);
        VoxelShape lvt_6_1_ = Block.makeCuboidShape(14.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
        VoxelShape lvt_7_1_ = Block.makeCuboidShape(0.0D, 0.0D, 14.0D, 16.0D, 2.0D, 16.0D);
        VoxelShape lvt_8_1_ = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 2.0D);
        field_220122_e = VoxelShapes.or(field_220123_f, new VoxelShape[]{field_220121_d, lvt_6_1_, lvt_5_1_, lvt_8_1_, lvt_7_1_});
    }
}
