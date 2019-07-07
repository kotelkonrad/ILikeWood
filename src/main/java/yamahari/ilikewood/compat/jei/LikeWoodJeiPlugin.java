package yamahari.ilikewood.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import yamahari.ilikewood.container.WoodenWorkbenchContainer;
import yamahari.ilikewood.gui.screen.WoodenCraftingScreen;
import yamahari.ilikewood.util.Constants;

import javax.annotation.Nonnull;

@JeiPlugin
public class LikeWoodJeiPlugin implements IModPlugin {
    private static final ResourceLocation PLUGIN_UID = new ResourceLocation(Constants.MOD_ID, "plugin");

    @Nonnull
    @Override
    public ResourceLocation getPluginUid() {
        return PLUGIN_UID;
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        // Could add crafting tables as recipe catalysts, but probably better not to. It might make
        // it more difficult for players to learn they can change them into vanilla crafting tables.
//        Constants.CRAFTING_TABLES.forEach(block ->
//                registration.addRecipeCatalyst(new ItemStack(block), VanillaRecipeCategoryUid.CRAFTING));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration reg) {
        // Allows player to click the arrow to view recipes
        reg.addRecipeClickArea(WoodenCraftingScreen.class, 88, 32, 28, 23, VanillaRecipeCategoryUid.CRAFTING);
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration reg) {
        // Allows JEI to transfer items into the crafting grid
        reg.addRecipeTransferHandler(WoodenWorkbenchContainer.class, VanillaRecipeCategoryUid.CRAFTING, 1, 9, 10, 36);
    }
}
