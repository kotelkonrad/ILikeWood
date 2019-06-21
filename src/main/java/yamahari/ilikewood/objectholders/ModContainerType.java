package yamahari.ilikewood.objectholders;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.registries.ObjectHolder;
import yamahari.ilikewood.container.WoodenLecternContainer;
import yamahari.ilikewood.container.WoodenWorkbenchContainer;
import yamahari.ilikewood.util.Constants;

@ObjectHolder(Constants.MOD_ID)
public class ModContainerType {
    public static final ContainerType<WoodenWorkbenchContainer> wooden_workbench_container = null;
    public static final ContainerType<WoodenLecternContainer> wooden_lectern_container = null;
}
