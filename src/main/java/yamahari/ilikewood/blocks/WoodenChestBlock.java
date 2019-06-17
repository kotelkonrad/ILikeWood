package yamahari.ilikewood.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.DoubleSidedInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import yamahari.ilikewood.objectholders.ModTileEntityType;
import yamahari.ilikewood.tileentities.WoodenChestTileEntity;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodType;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

public class WoodenChestBlock extends ChestBlock implements IWooden {
    private static final WoodenChestBlock.InventoryFactory<IInventory> field_220109_i;
    private static final WoodenChestBlock.InventoryFactory<INamedContainerProvider> field_220110_j;
    private final WoodType woodType;

    public WoodenChestBlock(WoodType woodType) {
        super(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.5f).sound(SoundType.WOOD));
        this.woodType = woodType;
    }


    @Override
    public WoodType getWoodType() {
        return this.woodType;
    }

    private static boolean isBlocked(IWorld p_220108_0_, BlockPos p_220108_1_) {
        return isBelowSolidBlock(p_220108_0_, p_220108_1_) || isCatSittingOn(p_220108_0_, p_220108_1_);
    }

    private static boolean isBelowSolidBlock(IBlockReader p_176456_0_, BlockPos p_176456_1_) {
        BlockPos lvt_2_1_ = p_176456_1_.up();
        return p_176456_0_.getBlockState(lvt_2_1_).isNormalCube(p_176456_0_, lvt_2_1_);
    }

    private static boolean isCatSittingOn(IWorld p_220107_0_, BlockPos p_220107_1_) {
        List<CatEntity> lvt_2_1_ = p_220107_0_.getEntitiesWithinAABB(CatEntity.class, new AxisAlignedBB((double)p_220107_1_.getX(), (double)(p_220107_1_.getY() + 1), (double)p_220107_1_.getZ(), (double)(p_220107_1_.getX() + 1), (double)(p_220107_1_.getY() + 2), (double)(p_220107_1_.getZ() + 1)));
        if (!lvt_2_1_.isEmpty()) {
            Iterator var3 = lvt_2_1_.iterator();

            while(var3.hasNext()) {
                CatEntity lvt_4_1_ = (CatEntity)var3.next();
                if (lvt_4_1_.isSitting()) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    @Nullable
    public INamedContainerProvider getContainer(BlockState p_220052_1_, World p_220052_2_, BlockPos p_220052_3_) {
        return (INamedContainerProvider)func_220106_a(p_220052_1_, p_220052_2_, p_220052_3_, false, field_220110_j);
    }


    @Override
    public void onBlockPlacedBy(World p_180633_1_, BlockPos p_180633_2_, BlockState p_180633_3_, LivingEntity p_180633_4_, ItemStack p_180633_5_) {
        if (p_180633_5_.hasDisplayName()) {
            TileEntity lvt_6_1_ = p_180633_1_.getTileEntity(p_180633_2_);
            if (lvt_6_1_ instanceof WoodenChestTileEntity) {
                ((WoodenChestTileEntity)lvt_6_1_).setCustomName(p_180633_5_.getDisplayName());
            }
        }

    }

    @Nullable
    public static <T> T func_220106_a(BlockState p_220106_0_, IWorld p_220106_1_, BlockPos p_220106_2_, boolean p_220106_3_, WoodenChestBlock.InventoryFactory<T> p_220106_4_) {
        TileEntity lvt_5_1_ = p_220106_1_.getTileEntity(p_220106_2_);
        if (!(lvt_5_1_ instanceof WoodenChestTileEntity)) {
            return null;
        } else if (!p_220106_3_ && isBlocked(p_220106_1_, p_220106_2_)) {
            return null;
        } else {
            WoodenChestTileEntity lvt_6_1_ = (WoodenChestTileEntity)lvt_5_1_;
            ChestType lvt_7_1_ = (ChestType)p_220106_0_.get(TYPE);
            if (lvt_7_1_ == ChestType.SINGLE) {
                return p_220106_4_.forSingle(lvt_6_1_);
            } else {
                BlockPos lvt_8_1_ = p_220106_2_.offset(getDirectionToAttached(p_220106_0_));
                BlockState lvt_9_1_ = p_220106_1_.getBlockState(lvt_8_1_);
                if (lvt_9_1_.getBlock() == p_220106_0_.getBlock()) {
                    ChestType lvt_10_1_ = (ChestType)lvt_9_1_.get(TYPE);
                    if (lvt_10_1_ != ChestType.SINGLE && lvt_7_1_ != lvt_10_1_ && lvt_9_1_.get(FACING) == p_220106_0_.get(FACING)) {
                        if (!p_220106_3_ && isBlocked(p_220106_1_, lvt_8_1_)) {
                            return null;
                        }

                        TileEntity lvt_11_1_ = p_220106_1_.getTileEntity(lvt_8_1_);
                        if (lvt_11_1_ instanceof WoodenChestTileEntity) {
                            WoodenChestTileEntity lvt_12_1_ = lvt_7_1_ == ChestType.RIGHT ? lvt_6_1_ : (WoodenChestTileEntity)lvt_11_1_;
                            WoodenChestTileEntity lvt_13_1_ = lvt_7_1_ == ChestType.RIGHT ? (WoodenChestTileEntity)lvt_11_1_ : lvt_6_1_;
                            return p_220106_4_.forDouble(lvt_12_1_, lvt_13_1_);
                        }
                    }
                }

                return p_220106_4_.forSingle(lvt_6_1_);
            }
        }
    }

    @Nullable
    public static IInventory getInventory(BlockState p_220105_0_, World p_220105_1_, BlockPos p_220105_2_, boolean p_220105_3_) {
        return (IInventory)func_220106_a(p_220105_0_, p_220105_1_, p_220105_2_, p_220105_3_, field_220109_i);
    }

    @Override
    public int getComparatorInputOverride(BlockState p_180641_1_, World p_180641_2_, BlockPos p_180641_3_) {
        return Container.calcRedstoneFromInventory(getInventory(p_180641_1_, p_180641_2_, p_180641_3_, false));
    }

    public TileEntityType<WoodenChestTileEntity> getTileEntityType() {
        switch (this.getWoodType()) {
            case OAK:
            default:
                return ModTileEntityType.oak_chest;
            case DARK_OAK:
                return ModTileEntityType.dark_oak_chest;
            case SPRUCE:
                return ModTileEntityType.spruce_chest;
            case BIRCH:
                return ModTileEntityType.birch_chest;
            case ACACIA:
                return ModTileEntityType.acacia_chest;
            case JUNGLE:
                return ModTileEntityType.jungle_chest;
        }
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader p_196283_1_) {
        return new WoodenChestTileEntity(this.getTileEntityType(), this.getWoodType());
    }

    static {
        field_220109_i = new WoodenChestBlock.InventoryFactory<IInventory>() {
            public IInventory forDouble(WoodenChestTileEntity p_212855_1_, WoodenChestTileEntity p_212855_2_) {
                return new DoubleSidedInventory(p_212855_1_, p_212855_2_);
            }

            public IInventory forSingle(WoodenChestTileEntity p_212856_1_) {
                return p_212856_1_;
            }
        };
        field_220110_j = new WoodenChestBlock.InventoryFactory<INamedContainerProvider>() {
            public INamedContainerProvider forDouble(final WoodenChestTileEntity p_212855_1_, final WoodenChestTileEntity p_212855_2_) {
                final IInventory lvt_3_1_ = new DoubleSidedInventory(p_212855_1_, p_212855_2_);
                return new INamedContainerProvider() {
                    @Nullable
                    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
                        if (p_212855_1_.canOpen(p_createMenu_3_) && p_212855_2_.canOpen(p_createMenu_3_)) {
                            p_212855_1_.fillWithLoot(p_createMenu_2_.player);
                            p_212855_2_.fillWithLoot(p_createMenu_2_.player);
                            return ChestContainer.createGeneric9X6(p_createMenu_1_, p_createMenu_2_, lvt_3_1_);
                        } else {
                            return null;
                        }
                    }

                    public ITextComponent getDisplayName() {
                        return new TranslationTextComponent("container.chestDouble", new Object[0]);
                    }
                };
            }

            public INamedContainerProvider forSingle(WoodenChestTileEntity p_212856_1_) {
                return p_212856_1_;
            }
        };
    }

    interface InventoryFactory<T> {
        T forDouble(WoodenChestTileEntity var1, WoodenChestTileEntity var2);

        T forSingle(WoodenChestTileEntity var1);
    }
}
