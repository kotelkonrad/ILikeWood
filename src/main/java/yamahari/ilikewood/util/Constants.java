package yamahari.ilikewood.util;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import yamahari.ilikewood.objectholders.ModBlocks;

import java.util.Map;

public class Constants {
    public static final String MOD_ID = "ilikewood";
    public static final ImmutableSet<Block> BARRELS =
            ImmutableSet.of(
                    ModBlocks.oak_barrel,
                    ModBlocks.dark_oak_barrel,
                    ModBlocks.spruce_barrel,
                    ModBlocks.birch_barrel,
                    ModBlocks.jungle_barrel,
                    ModBlocks.acacia_barrel
            );

    public static final ImmutableSet<Block> WALLS =
            ImmutableSet.of(
                    ModBlocks.oak_wall,
                    ModBlocks.dark_oak_wall,
                    ModBlocks.spruce_wall,
                    ModBlocks.birch_wall,
                    ModBlocks.jungle_wall,
                    ModBlocks.acacia_wall
            );

    public static final ImmutableSet<Block> CHESTS =
            ImmutableSet.of(
                    ModBlocks.oak_chest,
                    ModBlocks.dark_oak_chest,
                    ModBlocks.spruce_chest,
                    ModBlocks.birch_chest,
                    ModBlocks.jungle_chest,
                    ModBlocks.acacia_chest
            );

    public static final ImmutableSet<Block> CRAFTING_TABLES =
            ImmutableSet.of(
                    ModBlocks.oak_crafting_table,
                    ModBlocks.dark_oak_crafting_table,
                    ModBlocks.spruce_crafting_table,
                    ModBlocks.birch_crafting_table,
                    ModBlocks.jungle_crafting_table,
                    ModBlocks.acacia_crafting_table
            );

    public static final ImmutableSet<Block> POSTS =
            ImmutableSet.of(
                    ModBlocks.oak_post,
                    ModBlocks.dark_oak_post,
                    ModBlocks.spruce_post,
                    ModBlocks.birch_post,
                    ModBlocks.jungle_post,
                    ModBlocks.acacia_post
            );

    public static final ImmutableSet<Block> STRIPPED_POSTS =
            ImmutableSet.of(
                    ModBlocks.stripped_oak_post,
                    ModBlocks.stripped_dark_oak_post,
                    ModBlocks.stripped_spruce_post,
                    ModBlocks.stripped_birch_post,
                    ModBlocks.stripped_jungle_post,
                    ModBlocks.stripped_acacia_post
            );

    public static final ImmutableSet<Block> PANELS =
            ImmutableSet.of(
                    ModBlocks.oak_panels,
                    ModBlocks.dark_oak_panels,
                    ModBlocks.spruce_panels,
                    ModBlocks.birch_panels,
                    ModBlocks.jungle_panels,
                    ModBlocks.acacia_panels
            );

    public static final ImmutableSet<Block> BOOKSHELFS =
            ImmutableSet.of(
                    ModBlocks.oak_bookshelf,
                    ModBlocks.dark_oak_bookshelf,
                    ModBlocks.spruce_bookshelf,
                    ModBlocks.birch_bookshelf,
                    ModBlocks.jungle_bookshelf,
                    ModBlocks.acacia_bookshelf
            );

    public static final Map<Block, Block> POST_STRIPPING_MAP =
            (new ImmutableMap.Builder<Block, Block>())
                    .put(ModBlocks.oak_post, ModBlocks.stripped_oak_post)
                    .put(ModBlocks.dark_oak_post, ModBlocks.stripped_dark_oak_post)
                    .put(ModBlocks.spruce_post, ModBlocks.stripped_spruce_post)
                    .put(ModBlocks.birch_post, ModBlocks.stripped_birch_post)
                    .put(ModBlocks.jungle_post, ModBlocks.stripped_jungle_post)
                    .put(ModBlocks.acacia_post, ModBlocks.stripped_acacia_post).build();


}
