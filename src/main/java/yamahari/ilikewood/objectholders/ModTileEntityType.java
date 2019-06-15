package yamahari.ilikewood.objectholders;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;
import yamahari.ilikewood.tileentities.OakBarrelTileEntity;
import yamahari.ilikewood.tileentities.WoodenBarrelTileEntity;
import yamahari.ilikewood.util.Constants;

@ObjectHolder(Constants.MOD_ID)
public class ModTileEntityType {
    public static final TileEntityType<WoodenBarrelTileEntity> oak_barrel = null;
    public static final TileEntityType<WoodenBarrelTileEntity> dark_oak_barrel = null;
    public static final TileEntityType<WoodenBarrelTileEntity> spruce_barrel = null;
    public static final TileEntityType<WoodenBarrelTileEntity> birch_barrel = null;
    public static final TileEntityType<WoodenBarrelTileEntity> jungle_barrel = null;
    public static final TileEntityType<WoodenBarrelTileEntity> acacia_barrel = null;
}
