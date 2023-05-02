package com.wjx.the_golden_autumn;

import com.wjx.the_golden_autumn.api.API;
import com.wjx.the_golden_autumn.creativetab.goldenau;
import com.wjx.the_golden_autumn.gui_container.GuiMachineDraMak;
import com.wjx.the_golden_autumn.gui_container.GuiMachineLianQi;
import com.wjx.the_golden_autumn.init.EntityInit;
import com.wjx.the_golden_autumn.init.PotionInit;
import com.wjx.the_golden_autumn.machine_recipe.DrawingTableRecipeHandler;
import com.wjx.the_golden_autumn.machine_recipe.LianqiRecipe;
import com.wjx.the_golden_autumn.proxy.CommonProxy;
import com.wjx.the_golden_autumn.util.handler.GuiHandler;
import com.wjx.the_golden_autumn.util.handler.RecipeHandler.DrawingHandler;
import com.wjx.the_golden_autumn.util.handler.RecipeHandler.LianQiHandler;
import com.wjx.the_golden_autumn.util.handler.RegistySound;
import com.wjx.the_golden_autumn.util.handler.RenderHandler;
import com.wjx.the_golden_autumn.util.handler.TileEntityHandler;
import com.wjx.the_golden_autumn.world.gen.Oregen_1;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.network.play.client.CPacketCreativeInventoryAction;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.ArrayList;

@Mod(modid = TheGoldenAutumnMod.MODID, name = TheGoldenAutumnMod.NAME, version = TheGoldenAutumnMod.VERSION)
public class TheGoldenAutumnMod
{
    public static final String MODID = "the_golden_autumn";
    public static final String NAME = "The Golden Autumn";
    public static final String VERSION = "12.0.3.2";

    public static final String CLIENT = "com.wjx.the_golden_autumn.proxy.ClientProxy";
    public static final String COMMON = "com.wjx.the_golden_autumn.proxy.CommonProxy";

    public static ArrayList<API.APIStructs.ApiDrawingTableRecipe> APIRecipeDrawingTable = new ArrayList<>();
    public static ArrayList<API.APIStructs.ApiLianQiRecipe> APIRecipeLianQi = new ArrayList<>();

    public static final SimpleNetworkWrapper PACKET_HANDLER = NetworkRegistry.INSTANCE.newSimpleChannel("the_golden_autumn");

    @Mod.Instance
    public static TheGoldenAutumnMod instance;

    public static final CreativeTabs TabGold = new goldenau("goldenau");

    @SidedProxy(clientSide = CLIENT,serverSide = COMMON)
    public static CommonProxy proxy;
    public static Logger logger;
    public TheGoldenAutumnMod() {

        MinecraftForge.EVENT_BUS.register(this);
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {



        proxy.preInit(event);
        logger = event.getModLog();
        TileEntityHandler.registerTileEntity();
        PotionInit.registerPotion();

        EntityInit.registerEntities();
        GameRegistry.registerWorldGenerator(new Oregen_1(),5);
        this.registerMessages();
        API.HandlerRegister.RegisterApiDrawingTableHandler(new DrawingHandler());
        API.HandlerRegister.RegisterApiLianQiHandler(new LianQiHandler());
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
        NetworkRegistry.INSTANCE.registerGuiHandler(TheGoldenAutumnMod.instance,new GuiHandler());
        // some example code
        logger.info("\u9ec4\u91d1\u4e4b\u79cbmod\u52a0\u8f7d\u5b8c\u6bd5", 0);
        if(Loader.isModLoaded("flammpfeil.slashblade")){

            logger.info("executed register!");
        }
        new RegistySound();
        DrawingTableRecipeHandler.addRecipe();
        LianqiRecipe.addRecipe();
    }

    @SubscribeEvent
    public void registerSounds(RegistryEvent.Register<net.minecraft.util.SoundEvent> event) {
        RegistySound.registerSounds(event);
    }

    private void registerMessages(){
       PACKET_HANDLER.registerMessage(GuiMachineLianQi.CustomGui.GUIButtonPressedMessageHandler.class, GuiMachineLianQi.CustomGui.GUIButtonPressedMessage.class, 0,Side.SERVER);
       PACKET_HANDLER.registerMessage(GuiMachineDraMak.CustomGui.GUIButtonPressedMessageHandler.class,GuiMachineDraMak.CustomGui.GUIButtonPressedMessage.class,1,Side.SERVER); }
}
