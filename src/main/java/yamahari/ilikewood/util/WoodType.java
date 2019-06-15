package yamahari.ilikewood.util;

import net.minecraft.util.IStringSerializable;

public enum WoodType implements IStringSerializable {
    OAK("oak"),
    DARK_OAK("dark_oak"),
    SPRUCE("spruce"),
    BIRCH("birch"),
    ACACIA("acacia"),
    JUNGLE("jungle");

    public static final WoodType[] VALUES = values();
    private final String name;

    private WoodType(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
