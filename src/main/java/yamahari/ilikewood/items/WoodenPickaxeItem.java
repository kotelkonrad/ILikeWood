package yamahari.ilikewood.items;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodType;

public class WoodenPickaxeItem extends PickaxeItem implements IWooden {
    private final WoodType woodType;

    public WoodenPickaxeItem(WoodType woodType, IItemTier tier) {
        super(tier, 1, -2.8f, new Item.Properties().group(ItemGroup.TOOLS));
        this.woodType = woodType;
    }

    @Override
    public WoodType getWoodType() {
        return this.woodType;
    }
}
