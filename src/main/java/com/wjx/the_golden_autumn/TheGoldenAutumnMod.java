package com.wjx.the_golden_autumn;

import com.wjx.the_golden_autumn.creativetab.goldenau;
import com.wjx.the_golden_autumn.init.EntityInit;
import com.wjx.the_golden_autumn.init.PotionInit;
import com.wjx.the_golden_autumn.proxy.CommonProxy;
import com.wjx.the_golden_autumn.util.handler.RegistySound;
import com.wjx.the_golden_autumn.util.handler.RenderHandler;
import com.wjx.the_golden_autumn.util.handler.TileEntityHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = TheGoldenAutumnMod.MODID, name = TheGoldenAutumnMod.NAME, version = TheGoldenAutumnMod.VERSION)
public class TheGoldenAutumnMod
{
    public static final String MODID = "the_golden_autumn";
    public static final String NAME = "The Golden Autumn";
    public static final String VERSION = "12.0.1";

    public static final String CLIENT = "com.wjx.the_golden_autumn.proxy.ClientProxy";
    public static final String COMMON = "com.wjx.the_golden_autumn.proxy.CommonProxy";

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
        RenderHandler.registerEntityRenders();
        EntityInit.registerEntities();

    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
        // some example code
        logger.info("\u9ec4\u91d1\u4e4b\u79cbmod\u52a0\u8f7d\u5b8c\u6bd5", 0);
        if(Loader.isModLoaded("flammpfeil.slashblade")){

            logger.info("executed register!");
        }
        new RegistySound();
    }

    @SubscribeEvent
    public void registerSounds(RegistryEvent.Register<net.minecraft.util.SoundEvent> event) {
        RegistySound.registerSounds(event);
    }
}
