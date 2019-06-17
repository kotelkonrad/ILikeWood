package yamahari.ilikewood.objectholders;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;
import yamahari.ilikewood.tileentities.WoodenBarrelTileEntity;
import yamahari.ilikewood.tileentities.WoodenChestTileEntity;
import yamahari.ilikewood.util.Constants;

@ObjectHolder(Constants.MOD_ID)
public class ModTileEntityType {
    public static final TileEntityType<WoodenBarrelTileEntity> oak_barrel = null;
    public static final TileEntityType<WoodenBarrelTileEntity> dark_oak_barrel = null;
    public static final TileEntityType<WoodenBarrelTileEntity> spruce_barrel = null;
    public static final TileEntityType<WoodenBarrelTileEntity> birch_barrel = null;
    public static final TileEntityType<WoodenBarrelTileEntity> jungle_barrel = null;
    public static final TileEntityType<WoodenBarrelTileEntity> acacia_barrel = null;

    public static final TileEntityType<WoodenChestTileEntity> oak_chest = null;
    public static final TileEntityType<WoodenChestTileEntity> dark_oak_chest = null;
    public static final TileEntityType<WoodenChestTileEntity> spruce_chest = null;
    public static final TileEntityType<WoodenChestTileEntity> birch_chest = null;
    public static final TileEntityType<WoodenChestTileEntity> jungle_chest = null;
    public static final TileEntityType<WoodenChestTileEntity> acacia_chest = null;
}
