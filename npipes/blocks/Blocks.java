package npipes.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;

public class Blocks {
    public static Block nPhasedPipeBlock;

    public static void initBlocks() {
        nPhasedPipeBlock = new NPhasedPipeBlock(4006);
    }

    public static void registerBlocks() {
        GameRegistry.registerBlock(nPhasedPipeBlock, "nPhasedPipeBlock");
    }

    public static void registerLanguages() {
        LanguageRegistry.addName(nPhasedPipeBlock, "N Phased Pipe");
    }
}
