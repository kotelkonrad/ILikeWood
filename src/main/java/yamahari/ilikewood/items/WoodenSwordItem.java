package yamahari.ilikewood.items;

import net.minecraft.item.*;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodType;

public class WoodenSwordItem extends SwordItem implements IWooden {
    private final WoodType woodType;

    public WoodenSwordItem(WoodType woodType, IItemTier itemTier) {
        super(itemTier, 3, -2.4f, new Item.Properties().group(ItemGroup.COMBAT));
        this.woodType = woodType;
    }

    @Override
    public WoodType getWoodType() {
        return this.woodType;
    }
}
