package yamahari.ilikewood.items;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShovelItem;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodType;

public class WoodenShovelItem extends ShovelItem implements IWooden {
    private final WoodType woodType;

    public WoodenShovelItem(WoodType woodType, IItemTier tier) {
        super(tier, 1.5f, -3.f, new Item.Properties().group(ItemGroup.TOOLS));
        this.woodType = woodType;
    }

    @Override
    public WoodType getWoodType() {
        return this.woodType;
    }
}
