package yamahari.ilikewood.blocks;

import net.minecraft.block.BarrelBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import yamahari.ilikewood.tileentities.*;
import yamahari.ilikewood.util.WoodType;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class WoodenBarrelBlock extends BarrelBlock {
    public WoodenBarrelBlock() {
        super(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.5f).sound(SoundType.WOOD));
    }

    @Override
    public boolean onBlockActivated(BlockState p_220051_1_, World p_220051_2_, BlockPos p_220051_3_, PlayerEntity p_220051_4_, Hand p_220051_5_, BlockRayTraceResult p_220051_6_) {
        if (p_220051_2_.isRemote) {
            return true;
        } else {
            TileEntity lvt_7_1_ = p_220051_2_.getTileEntity(p_220051_3_);
            if (lvt_7_1_ instanceof WoodenBarrelTileEntity) {
                p_220051_4_.openContainer((WoodenBarrelTileEntity)lvt_7_1_);
                p_220051_4_.addStat(Stats.OPEN_BARREL);
            }

            return true;
        }
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader p_196283_1_) {
        switch (this.getWoodType()) {

            case OAK:
            default:
                return new OakBarrelTileEntity();
            case DARK_OAK:
                return new DarkOakBarrelTileEntity();
            case SPRUCE:
                return new SpruceBarrelTileEntity();
            case BIRCH:
                return new BirchBarrelTileEntity();
            case ACACIA:
                return new AcaciaBarrelTileEntity();
            case JUNGLE:
                return new JungleBarrelTileEntity();
        }
    }

    @Override
    public void tick(BlockState p_196267_1_, World p_196267_2_, BlockPos p_196267_3_, Random p_196267_4_) {
        TileEntity lvt_5_1_ = p_196267_2_.getTileEntity(p_196267_3_);
        if (lvt_5_1_ instanceof WoodenBarrelTileEntity) {
            ((WoodenBarrelTileEntity)lvt_5_1_).func_213962_h();
        }

    }

    @Override
    public void onBlockPlacedBy(World p_180633_1_, BlockPos p_180633_2_, BlockState p_180633_3_, @Nullable LivingEntity p_180633_4_, ItemStack p_180633_5_) {
        if (p_180633_5_.hasDisplayName()) {
            TileEntity lvt_6_1_ = p_180633_1_.getTileEntity(p_180633_2_);
            if (lvt_6_1_ instanceof WoodenBarrelTileEntity) {
                ((WoodenBarrelTileEntity)lvt_6_1_).setCustomName(p_180633_5_.getDisplayName());
            }
        }

    }

    public abstract WoodType getWoodType();
}
