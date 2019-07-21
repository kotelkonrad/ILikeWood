package yamahari.ilikewood.items;

import net.minecraft.item.*;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodType;

public class WoodenHoeItem extends HoeItem implements IWooden {
    private final WoodType woodType;

    protected WoodenHoeItem(WoodType woodType, IItemTier itemTier, float speed) {
        super(itemTier, speed, new Item.Properties().group(ItemGroup.TOOLS));
        this.woodType = woodType;
    }


    public static WoodenHoeItem builder(WoodType woodType, IItemTier itemTier) {
        if(itemTier instanceof ItemTier) {
            ItemTier tier = (ItemTier)itemTier;
            switch (tier) {
                case WOOD:
                case GOLD:
                default:
                    return new WoodenHoeItem(woodType, itemTier, -3.f);
                case STONE:
                    return new WoodenHoeItem(woodType, itemTier, -2.f);
                case IRON:
                    return new WoodenHoeItem(woodType, itemTier, -1.f);
                case DIAMOND:
                    return new WoodenHoeItem(woodType, itemTier, 0.f);
            }
        }
        else {
            return null;
        }
    }

    @Override
    public WoodType getWoodType() {
        return this.woodType;
    }
}
