package yamahari.ilikewood.items;

import net.minecraft.item.*;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodType;

public class WoodenAxeItem extends AxeItem implements IWooden {
    private final WoodType woodType;

    protected WoodenAxeItem(WoodType woodType, IItemTier tier, float attackDamageIn, float attackSpeedIn) {
        super(tier, attackDamageIn, attackSpeedIn, new Item.Properties().group(ItemGroup.TOOLS));
        this.woodType = woodType;
    }

    public static WoodenAxeItem builder(WoodType woodType, IItemTier tier) {
        if(tier instanceof ItemTier) {
            ItemTier itemTier = (ItemTier)tier;
            switch (itemTier) {
                case WOOD:
                default:
                    return new WoodenAxeItem(woodType, tier, 6.f, -3.2f);
                case STONE:
                    return new WoodenAxeItem(woodType, tier, 7.f, -3.2f);
                case IRON:
                    return new WoodenAxeItem(woodType, tier, 6.f, -3.1f);
                case DIAMOND:
                    return new WoodenAxeItem(woodType, tier, 5.f, -3.f);
                case GOLD:
                    return new WoodenAxeItem(woodType, tier, 6.f, -3.f);
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
