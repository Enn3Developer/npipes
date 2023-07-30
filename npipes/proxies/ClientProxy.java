package npipes.proxies;

import buildcraft.transport.TransportProxyClient;
import net.minecraftforge.client.MinecraftForgeClient;
import npipes.NPipes;

public class ClientProxy extends CommonProxy {
    @Override
    public void initSounds() {
        super.initSounds();
    }

    @Override
    public void initRenderers() {
        super.initRenderers();
        MinecraftForgeClient.preloadTexture("/npipes/sprites/textures.png");
        MinecraftForgeClient.registerItemRenderer(NPipes.instance.nPhasedPipeItem.itemID, TransportProxyClient.pipeItemRenderer);
    }

    @Override
    public void initKeys() {
        super.initKeys();
    }

    @Override
    public void initItems() {
        super.initItems();
    }

    @Override
    public void loadKeys() {
        super.loadKeys();
    }

    @Override
    public void loadItems() {
        super.loadItems();
    }
}
