package npipes;

import buildcraft.BuildCraftCore;
import buildcraft.additionalpipes.AdditionalPipes;
import buildcraft.core.CreativeTabBuildCraft;
import buildcraft.core.utils.Localization;
import buildcraft.transport.BlockGenericPipe;
import buildcraft.transport.ItemPipe;
import buildcraft.transport.Pipe;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import npipes.blocks.Blocks;
import npipes.config.ConfigHandler;
import npipes.network.PacketHandler;
import npipes.pipes.NPhasedPipe;
import npipes.proxies.CommonProxy;
import npipes.tileentities.TileEntities;

import java.util.Properties;

@Mod(modid = "NPipes_Enn3DevPlayer", name = "NPipes", version = "1.0")
@NetworkMod(channels = {"npipes"}, clientSideRequired = true, serverSideRequired = true, packetHandler = PacketHandler.class)
public class NPipes {
    @Mod.Instance(value = "NPipes_Enn3DevPlayer")
    public static NPipes instance;
    @SidedProxy(clientSide = "npipes.proxies.ClientProxy", serverSide = "npipes.proxies.CommonProxy")
    public static CommonProxy proxy;

    public static int nPhasedPipeId = 12300;
    public NPhasedPipe nPhasedPipe;
    public ItemPipe nPhasedPipeItem;

    @Mod.PreInit
    public void preInit(FMLPreInitializationEvent event) {
        ConfigHandler.init(event.getSuggestedConfigurationFile());
        Localization.addLocalization("/npipes/lang/", "en_US");
        Properties en_US = null;
        try {
            en_US = new Properties();
            en_US.load(AdditionalPipes.class.getResourceAsStream("/npipes/lang/en_US.properties"));
            LanguageRegistry.instance().addStringLocalization(en_US);
        } catch (Exception e) {
        }
        nPhasedPipeItem = new NItemPipe(nPhasedPipeId);
        nPhasedPipeItem.setItemName("NPhasedPipe");
        proxy.initRenderers();
        BlockGenericPipe.pipes.put(nPhasedPipeItem.itemID, NPhasedPipe.class);
        nPhasedPipe = new NPhasedPipe(nPhasedPipeId);
        nPhasedPipeItem.setTextureFile(nPhasedPipe.getTextureFile());
        nPhasedPipeItem.setTextureIndex(nPhasedPipe.getTextureIndexForItem());
//        BlockGenericPipe.registerPipe(nPhasedPipeId, NPhasedPipe.class);
//        nPhasedPipeItem.setItemName("N Phased Pipe");
        Blocks.initBlocks();
        Blocks.registerBlocks();
        proxy.initSounds();
        proxy.initKeys();
        proxy.initItems();
        TileEntities.registerTileEntities();
    }

    @Mod.Init
    public void init(FMLInitializationEvent event) {
        Blocks.registerLanguages();
        GameRegistry.addRecipe(new ItemStack(nPhasedPipeItem, 1), "dpd", 'd', BuildCraftCore.diamondGearItem, 'p', AdditionalPipes.instance.pipeItemsTeleport);
    }

    @Mod.Init
    public void load(FMLInitializationEvent event) {
        proxy.loadKeys();
        proxy.loadItems();
    }

    @Mod.PostInit
    public void postInit(FMLPostInitializationEvent event) {
    }

    private static class NItemPipe extends ItemPipe {
        protected NItemPipe(int i) {
            super(i);
            this.setCreativeTab(CreativeTabBuildCraft.tabBuildCraft);
        }

        @Override
        public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int side, float par8, float par9, float par10) {
            int blockID = Blocks.nPhasedPipeBlock.blockID;
            Block block = Blocks.nPhasedPipeBlock;
            int id = world.getBlockId(i, j, k);
            if (id == Block.snow.blockID) {
                side = 1;
            } else if (id != Block.vine.blockID && id != Block.tallGrass.blockID && id != Block.deadBush.blockID && (Block.blocksList[id] == null || !Block.blocksList[id].isBlockReplaceable(world, i, j, k))) {
                if (side == 0) {
                    --j;
                }

                if (side == 1) {
                    ++j;
                }

                if (side == 2) {
                    --k;
                }

                if (side == 3) {
                    ++k;
                }

                if (side == 4) {
                    --i;
                }

                if (side == 5) {
                    ++i;
                }
            }

            if (itemstack.stackSize == 0) {
                return false;
            } else if (entityplayer.canCurrentToolHarvestBlock(i, j, k) && world.canPlaceEntityOnSide(blockID, i, j, k, false, side, entityplayer)) {
                Pipe pipe = BlockGenericPipe.createPipe(super.itemID);
                if (pipe == null) {
                    BuildCraftCore.bcLog.warning("Pipe failed to create during placement at " + i + "," + j + "," + k);
                    return true;
                } else {
                    if (BlockGenericPipe.placePipe(pipe, world, i, j, k, blockID, 0)) {
                        Block.blocksList[blockID].onBlockPlacedBy(world, i, j, k, entityplayer);
                        world.playSoundEffect((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), block.stepSound.getPlaceSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
                        --itemstack.stackSize;
                    }

                    return true;
                }
            } else {
                return false;
            }
        }
    }
}
