package npipes.blocks;

import buildcraft.transport.BlockGenericPipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import npipes.tileentities.NPhasePipeTE;

public class NPhasedPipeBlock extends BlockGenericPipe {
    protected NPhasedPipeBlock(int par1) {
        super(par1);
    }

    @Override
    public TileEntity createNewTileEntity(World var1) {
        return new NPhasePipeTE();
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new NPhasePipeTE();
    }
}
