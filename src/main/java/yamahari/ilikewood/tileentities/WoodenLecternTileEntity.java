package yamahari.ilikewood.tileentities;

import net.minecraft.command.CommandSource;
import net.minecraft.command.ICommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IClearable;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.LecternContainer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.WrittenBookItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.IIntArray;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.ServerWorld;
import yamahari.ilikewood.blocks.WoodenLecternBlock;
import yamahari.ilikewood.util.WoodType;

import javax.annotation.Nullable;

public class WoodenLecternTileEntity extends TileEntity implements IClearable, INamedContainerProvider {
    private final IInventory field_214048_a = new IInventory() {
        public int getSizeInventory() {
            return 1;
        }

        public boolean isEmpty() {
            return WoodenLecternTileEntity.this.book.isEmpty();
        }

        public ItemStack getStackInSlot(int p_70301_1_) {
            return p_70301_1_ == 0 ? WoodenLecternTileEntity.this.book : ItemStack.EMPTY;
        }

        public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
            if (p_70298_1_ == 0) {
                ItemStack lvt_3_1_ = WoodenLecternTileEntity.this.book.split(p_70298_2_);
                if (WoodenLecternTileEntity.this.book.isEmpty()) {
                    WoodenLecternTileEntity.this.func_214042_s();
                }

                return lvt_3_1_;
            } else {
                return ItemStack.EMPTY;
            }
        }

        public ItemStack removeStackFromSlot(int p_70304_1_) {
            if (p_70304_1_ == 0) {
                ItemStack lvt_2_1_ = WoodenLecternTileEntity.this.book;
                WoodenLecternTileEntity.this.book = ItemStack.EMPTY;
                WoodenLecternTileEntity.this.func_214042_s();
                return lvt_2_1_;
            } else {
                return ItemStack.EMPTY;
            }
        }

        public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {
        }

        public int getInventoryStackLimit() {
            return 1;
        }

        public void markDirty() {
            WoodenLecternTileEntity.this.markDirty();
        }

        public boolean isUsableByPlayer(PlayerEntity p_70300_1_) {
            if (WoodenLecternTileEntity.this.world.getTileEntity(WoodenLecternTileEntity.this.pos) != WoodenLecternTileEntity.this) {
                return false;
            } else {
                return p_70300_1_.getDistanceSq((double)WoodenLecternTileEntity.this.pos.getX() + 0.5D, (double)WoodenLecternTileEntity.this.pos.getY() + 0.5D, (double)WoodenLecternTileEntity.this.pos.getZ() + 0.5D) > 64.0D ? false : WoodenLecternTileEntity.this.func_214046_f();
            }
        }

        public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
            return false;
        }

        public void clear() {
        }
    };
    private final IIntArray field_214049_b = new IIntArray() {
        public int get(int p_221476_1_) {
            return p_221476_1_ == 0 ? WoodenLecternTileEntity.this.page : 0;
        }

        public void set(int p_221477_1_, int p_221477_2_) {
            if (p_221477_1_ == 0) {
                WoodenLecternTileEntity.this.func_214035_a(p_221477_2_);
            }

        }

        public int size() {
            return 1;
        }
    };

    private final WoodType woodType;
    private ItemStack book;
    private int page;
    private int pages;

    public WoodenLecternTileEntity(TileEntityType<?> typeIn, WoodType woodType) {
        super(typeIn);
        this.woodType = woodType;
        this.book = ItemStack.EMPTY;
    }


    public ItemStack func_214033_c() {
        return this.book;
    }

    public boolean func_214046_f() {
        Item lvt_1_1_ = this.book.getItem();
        return lvt_1_1_ == Items.WRITABLE_BOOK || lvt_1_1_ == Items.WRITTEN_BOOK;
    }

    public void func_214045_a(ItemStack p_214045_1_) {
        this.func_214040_a(p_214045_1_, (PlayerEntity)null);
    }

    private void func_214042_s() {
        this.page = 0;
        this.pages = 0;
        WoodenLecternBlock.setHasBook(this.getWorld(), this.getPos(), this.getBlockState(), false);
    }

    public void func_214040_a(ItemStack p_214040_1_, @Nullable PlayerEntity p_214040_2_) {
        this.book = this.func_214047_b(p_214040_1_, p_214040_2_);
        this.page = 0;
        this.pages = WrittenBookItem.func_220049_j(this.book);
        this.markDirty();
    }

    private void func_214035_a(int p_214035_1_) {
        int lvt_2_1_ = MathHelper.clamp(p_214035_1_, 0, this.pages - 1);
        if (lvt_2_1_ != this.page) {
            this.page = lvt_2_1_;
            this.markDirty();
            WoodenLecternBlock.pulse(this.getWorld(), this.getPos(), this.getBlockState());
        }

    }

    public int func_214041_g() {
        return this.page;
    }

    public int func_214034_r() {
        float lvt_1_1_ = this.pages > 1 ? (float)this.func_214041_g() / ((float)this.pages - 1.0F) : 1.0F;
        return MathHelper.floor(lvt_1_1_ * 14.0F) + (this.func_214046_f() ? 1 : 0);
    }

    private ItemStack func_214047_b(ItemStack p_214047_1_, @Nullable PlayerEntity p_214047_2_) {
        if (this.world instanceof ServerWorld && p_214047_1_.getItem() == Items.WRITTEN_BOOK) {
            WrittenBookItem.func_220050_a(p_214047_1_, this.func_214039_a(p_214047_2_), p_214047_2_);
        }

        return p_214047_1_;
    }

    private CommandSource func_214039_a(@Nullable PlayerEntity p_214039_1_) {
        String lvt_2_2_;
        Object lvt_3_2_;
        if (p_214039_1_ == null) {
            lvt_2_2_ = "Lectern";
            lvt_3_2_ = new StringTextComponent("Lectern");
        } else {
            lvt_2_2_ = p_214039_1_.getName().getString();
            lvt_3_2_ = p_214039_1_.getDisplayName();
        }

        Vec3d lvt_4_1_ = new Vec3d((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D);
        return new CommandSource(ICommandSource.field_213139_a_, lvt_4_1_, Vec2f.ZERO, (ServerWorld)this.world, 2, lvt_2_2_, (ITextComponent)lvt_3_2_, this.world.getServer(), p_214039_1_);
    }

    public void read(CompoundNBT p_145839_1_) {
        super.read(p_145839_1_);
        if (p_145839_1_.contains("Book", 10)) {
            this.book = this.func_214047_b(ItemStack.read(p_145839_1_.getCompound("Book")), null);
        } else {
            this.book = ItemStack.EMPTY;
        }

        this.pages = WrittenBookItem.func_220049_j(this.book);
        this.page = MathHelper.clamp(p_145839_1_.getInt("Page"), 0, this.pages - 1);
    }

    public CompoundNBT write(CompoundNBT p_189515_1_) {
        super.write(p_189515_1_);
        if (!this.func_214033_c().isEmpty()) {
            p_189515_1_.put("Book", this.func_214033_c().write(new CompoundNBT()));
            p_189515_1_.putInt("Page", this.page);
        }

        return p_189515_1_;
    }

    public void clear() {
        this.func_214045_a(ItemStack.EMPTY);
    }

    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new LecternContainer(p_createMenu_1_, this.field_214048_a, this.field_214049_b);
    }

    @Override
    public ITextComponent getDisplayName() {
        switch (this.woodType) {
            case OAK:
            default:
                return new TranslationTextComponent("container.ilikewood.oak_lectern");
            case DARK_OAK:
                return new TranslationTextComponent("container.ilikewood.dark_oak_lectern");
            case SPRUCE:
                return new TranslationTextComponent("container.ilikewood.spruce_lectern");
            case BIRCH:
                return new TranslationTextComponent("container.ilikewood.birch_lectern");
            case JUNGLE:
                return new TranslationTextComponent("container.ilikewood.jungle_lectern");
            case ACACIA:
                return new TranslationTextComponent("container.ilikewood.acacia_lectern");
        }
    }
}

