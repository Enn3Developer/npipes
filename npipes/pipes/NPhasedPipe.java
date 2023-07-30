package npipes.pipes;

import buildcraft.additionalpipes.pipes.PipeItemsTeleport;

public class NPhasedPipe extends PipeItemsTeleport {
    public NPhasedPipe(int itemID) {
        super(itemID);
    }

    @Override
    public String getTextureFile() {
        return "/npipes/sprites/textures.png";
    }
}
