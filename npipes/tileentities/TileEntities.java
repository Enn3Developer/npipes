package npipes.tileentities;

import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntities {
    public static void registerTileEntities() {
        GameRegistry.registerTileEntity(NPhasePipeTE.class, "NPhasePipeTE");
    }
}
