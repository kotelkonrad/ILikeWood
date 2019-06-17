package yamahari.ilikewood.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import yamahari.ilikewood.container.WoodenWorkbenchContainer;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodType;

public class WoodenCraftingTable extends CraftingTableBlock implements IWooden {
    private static final ITextComponent OAK = new TranslationTextComponent("container.ilikewood.oak_crafting");
    private static final ITextComponent DARK_OAK = new TranslationTextComponent("container.ilikewood.dark_oak_crafting");
    private static final ITextComponent SPRUCE = new TranslationTextComponent("container.ilikewood.spruce_crafting");
    private static final ITextComponent BIRCH = new TranslationTextComponent("container.ilikewood.birch_crafting");
    private static final ITextComponent JUNGLE = new TranslationTextComponent("container.ilikewood.jungle_crafting");
    private static final ITextComponent ACACIA = new TranslationTextComponent("container.ilikewood.acacia_crafting");

    private final WoodType woodType;

    public WoodenCraftingTable(WoodType woodType) {
        super(Block.Properties.create(Material.WOOD).hardnessAndResistance(2.5f).sound(SoundType.WOOD));
        this.woodType = woodType;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public INamedContainerProvider getContainer(BlockState p_220052_1_, World p_220052_2_, BlockPos p_220052_3_) {
        ITextComponent textComponent;
        switch (this.getWoodType()) {
            case OAK:
            default:
                textComponent = this.OAK;
                break;
            case DARK_OAK:
                textComponent = this.DARK_OAK;
                break;
            case SPRUCE:
                textComponent = this.SPRUCE;
                break;
            case BIRCH:
                textComponent = this.BIRCH;
                break;
            case ACACIA:
                textComponent = this.ACACIA;
                break;
            case JUNGLE:
                textComponent = this.JUNGLE;
                break;
        }

        return new SimpleNamedContainerProvider((p_220270_2_, p_220270_3_, p_220270_4_) -> {
            return new WoodenWorkbenchContainer(p_220270_2_, p_220270_3_, IWorldPosCallable.of(p_220052_2_, p_220052_3_));
        }, textComponent);
    }

    @Override
    public WoodType getWoodType() {
        return this.woodType;
    }
}
