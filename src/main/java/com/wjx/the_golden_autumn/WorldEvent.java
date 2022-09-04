package com.wjx.the_golden_autumn;

import com.sun.media.jfxmedia.events.PlayerStateEvent;
import com.wjx.the_golden_autumn.block.QiuxiWeatherControllerClear;
import com.wjx.the_golden_autumn.entity.EntitySuda;
import com.wjx.the_golden_autumn.entity.Jaoshingan_I;
import com.wjx.the_golden_autumn.entity.Qiuxi;
import com.wjx.the_golden_autumn.init.blockinit;
import com.wjx.the_golden_autumn.init.iteminit;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.*;

@Mod.EventBusSubscriber
public class WorldEvent {

    public WorldEvent(){
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {

        MinecraftServer mcserv = FMLCommonHandler.instance().getMinecraftServerInstance();

        BlockPos pos = event.getPos();
        int x = event.getPos().getX();
        int y = event.getPos().getY();
        int z = event.getPos().getZ();
        EntityPlayer entity = event.getEntityPlayer();
        World world = event.getWorld();
        ControllerMakeHelper helper = new ControllerMakeHelper(world);
        if (event.getHand() == entity.getActiveHand()) {
            //Bottle of Autumn
            if ((entity instanceof EntityLivingBase ? entity.getHeldItemMainhand() : ItemStack.EMPTY).getItem() == (new ItemStack(Items.GLASS_BOTTLE, 1)).getItem() && world.getBlockState(new BlockPos(x, y, z)).getBlock() == blockinit.XIROSE.getDefaultState().getBlock()) {
                if (!world.isRemote) {
                    world.playSound(null, new BlockPos(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.fire.extinguish")), SoundCategory.NEUTRAL, 10.0F, 1.0F);
                } else {
                    ((WorldClient)world).playSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.fire.extinguish")), SoundCategory.NEUTRAL, 10.0F, 1.0F, false);
                }

                world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
                if (entity instanceof EntityPlayer) {
                    entity.getHeldItemMainhand().shrink(1);
                }

                if (entity instanceof EntityPlayer) {
                    ItemStack _setstack = new ItemStack(iteminit.BOTTLED_AUTU, 1);
                    _setstack.setCount(1);
                    ItemHandlerHelper.giveItemToPlayer((EntityPlayer) entity, _setstack);
                }

                if (entity instanceof EntityPlayerMP) {
                    Advancement _adv = ((EntityPlayerMP) entity).mcServer.getAdvancementManager().getAdvancement(new ResourceLocation("the_golden_autumn:warmandconf"));
                    AdvancementProgress _ap = ((EntityPlayerMP) entity).getAdvancements().getProgress(_adv);
                    if (!_ap.isDone()) {
                        Iterator _iterator = _ap.getRemaningCriteria().iterator();
                        while (_iterator.hasNext()) {
                            String _criterion = (String) _iterator.next();
                            ((EntityPlayerMP) entity).getAdvancements().grantCriterion(_adv, _criterion);
                        }
                    }
                }
            }
            //Hzkj
            if (entity.getHeldItemMainhand().getItem().equals(Items.IRON_INGOT)){
                if (world.getBlockState(pos).getBlock() == Blocks.REDSTONE_BLOCK.getDefaultState().getBlock()){
                    if (Item.getItemFromBlock(world.getBlockState(new BlockPos(x,y-1D,z)).getBlock()) == new ItemStack(Blocks.STONE,1,5).getItem()){ //Andesite
                        if (world.getBlockState(new BlockPos(x,y + 1D,z)).getBlock() == Blocks.ANVIL.getDefaultState().getBlock() && world.getBlockState(new BlockPos(x,y+2D,z)).getBlock() == Blocks.DIRT.getDefaultState().getBlock() && world.getBlockState(new BlockPos(x,y+3D,z)).getBlock() == blockinit.XIROSE){
                            entity.getHeldItemMainhand().shrink(1);
                            world.spawnEntity(new EntityLightningBolt(world,x,y,z,true));
                            BlockPos _bp5 = new BlockPos((int)x, (int)(y + 3.0D), (int)z);
                            BlockPos _bp4 = new BlockPos(x,y+2D,z);
                            BlockPos _bp3 = new BlockPos(x,y +1D,z);
                            BlockPos _bp2 = new BlockPos(x,y,z);
                            BlockPos _bp1 = new BlockPos(x,y-1D,z);
                            IBlockState _bs = Blocks.AIR.getDefaultState();
                            world.setBlockState(_bp5,_bs,3);
                            world.setBlockState(_bp4,_bs,3);
                            world.setBlockState(_bp3,_bs,3);
                            world.setBlockState(_bp2,_bs,3);
                            world.setBlockState(_bp1,blockinit.QIUXIDRILLING.getDefaultState(),3);
                            if (entity instanceof EntityPlayerMP) {
                                Advancement _adv = ((EntityPlayerMP) entity).mcServer.getAdvancementManager().getAdvancement(new ResourceLocation("the_golden_autumn:thehand"));
                                AdvancementProgress _ap = ((EntityPlayerMP) entity).getAdvancements().getProgress(_adv);
                                if (!_ap.isDone()) {
                                    for (String _criterion : _ap.getRemaningCriteria()) {
                                        ((EntityPlayerMP) entity).getAdvancements().grantCriterion(_adv, _criterion);
                                    }
                                }
                            }
                        }
                    }

                }
            }
            //Qiuxi Rose
            if(entity.getHeldItemMainhand().getItem() == Items.GOLDEN_APPLE){
                //Check XIROSE
                if(world.getBlockState(pos).getBlock() == blockinit.XIROSE){
                    if(mcserv!=null)

                    if(

                            world.getBlockState(new BlockPos(x+1,y,z)).getBlock() == blockinit.QIUROSE.getDefaultState().getBlock()
                            &&
                                    world.getBlockState(new BlockPos(x-1,y,z)).getBlock() == blockinit.QIUROSE.getDefaultState().getBlock()
                                    &&
                                    world.getBlockState(new BlockPos(x,y,z+1)).getBlock() == blockinit.QIUROSE.getDefaultState().getBlock()
                                    &&
                                    world.getBlockState(new BlockPos(x,y,z-1)).getBlock() == blockinit.QIUROSE.getDefaultState().getBlock()
                                    &&
                                    world.getBlockState(new BlockPos(x+1,y,z+1)).getBlock() == blockinit.QIUROSE.getDefaultState().getBlock()
                                    &&
                                    world.getBlockState(new BlockPos(x-1,y,z-1)).getBlock() == blockinit.QIUROSE.getDefaultState().getBlock()
                                    &&
                                    world.getBlockState(new BlockPos(x+1,y,z-1)).getBlock() == blockinit.QIUROSE.getDefaultState().getBlock()
                                    &&
                                    world.getBlockState(new BlockPos(x-1,y,z+1)).getBlock() == blockinit.QIUROSE.getDefaultState().getBlock()


                    ){

                        //SET
                        entity.getHeldItemMainhand().shrink(1);

                        setBlockStatesAir(world,new BlockPos(x+1,y,z),new BlockPos(x-1,y,z),new BlockPos(x,y,z+1),new BlockPos(x,y,z-1),new BlockPos(x+1,y,z+1),new BlockPos(x-1,y,z-1),new BlockPos(x+1,y,z-1),new BlockPos(x-1,y,z+1),pos);
                        EntityItem item = new EntityItem(world,x,y,z,new ItemStack(blockinit.QIUXIROSE));
                        world.spawnEntity(item);
                    }
                }
            }
            //Spawn Qiuxi & Suda
            if(entity.getHeldItemMainhand().getItem() == Items.CAKE){
                if(world.getBlockState(pos).getBlock() == blockinit.QIUXIROSE){
                    entity.getHeldItemMainhand().shrink(1);
                    if (world instanceof WorldServer){
                        world.spawnEntity(new EntityLightningBolt(world,x,y,z,true));
                    }
                    if (!world.isRemote){
                        Qiuxi qiuxi = new Qiuxi(world);
                        qiuxi.setPosition(x,y+1d,z);
                        EntitySuda suda = new EntitySuda(world);
                        suda.setPosition(x,y+1d,z);
                        world.spawnEntity(qiuxi);
                        world.spawnEntity(suda);
                    }
                }
            }
            //Summon Weather Controller
            if (entity.getHeldItemMainhand().getItem().equals(Items.BOOK) && world.getBlockState(pos).getBlock() == Blocks.REDSTONE_BLOCK){
                HashMap<ControllerMakeHelper.StatesIndex,IBlockState> map = helper.getBlockStates(pos);
                if (map.get(ControllerMakeHelper.StatesIndex.First).getBlock() == Blocks.MAGMA && map.get(ControllerMakeHelper.StatesIndex.Third).getBlock() == Blocks.GLOWSTONE && map.get(ControllerMakeHelper.StatesIndex.Fourth).getBlock() == Blocks.DIRT&& map.get(ControllerMakeHelper.StatesIndex.Fifth).getBlock() == blockinit.XIROSE){
                    entity.getHeldItemMainhand().shrink(1);
                    if (world instanceof WorldServer){
                        world.spawnEntity(new EntityLightningBolt(world,x,y,z,true));
                    }
                    setBlockStatesAir(world,helper.getBlockPoses(pos));
                    world.setBlockState(helper.getBlockPoses(pos)[4],blockinit.QIUXIWEATHER_CLEAR.getDefaultState(),3);
                    if (entity instanceof EntityPlayerMP) {
                        Advancement _adv = ((EntityPlayerMP) entity).mcServer.getAdvancementManager().getAdvancement(new ResourceLocation("the_golden_autumn:thesonoftheweather"));
                        AdvancementProgress _ap = ((EntityPlayerMP) entity).getAdvancements().getProgress(_adv);
                        if (!_ap.isDone()) {
                            for (String _criterion : _ap.getRemaningCriteria()) {
                                ((EntityPlayerMP) entity).getAdvancements().grantCriterion(_adv, _criterion);
                            }
                        }
                    }

                }
            }
            //Summon Timey
            if (entity.getHeldItemMainhand().getItem().equals(Items.CLOCK) && world.getBlockState(pos).getBlock() == Blocks.REDSTONE_BLOCK){
                HashMap<ControllerMakeHelper.StatesIndex,IBlockState> map = helper.getBlockStates(pos);
                if (map.get(ControllerMakeHelper.StatesIndex.First).getBlock() == Blocks.IRON_BLOCK && map.get(ControllerMakeHelper.StatesIndex.Third).getBlock() == Blocks.DIRT && map.get(ControllerMakeHelper.StatesIndex.Fourth).getBlock() == blockinit.QIUROSE){
                    entity.getHeldItemMainhand().shrink(1);
                    if (world instanceof WorldServer){
                        world.spawnEntity(new EntityLightningBolt(world,x,y,z,true));
                    }
                    setBlockStatesAir(world,helper.getBlockPoses(pos));
                    world.setBlockState(helper.getBlockPoses4(pos)[3],blockinit.QIUXITIMEY.getDefaultState(),3);
                    if (entity instanceof EntityPlayerMP) {
                        Advancement _adv = ((EntityPlayerMP) entity).mcServer.getAdvancementManager().getAdvancement(new ResourceLocation("the_golden_autumn:madeinheaven"));
                        AdvancementProgress _ap = ((EntityPlayerMP) entity).getAdvancements().getProgress(_adv);
                        if (!_ap.isDone()) {
                            for (String _criterion : _ap.getRemaningCriteria()) {
                                ((EntityPlayerMP) entity).getAdvancements().grantCriterion(_adv, _criterion);
                            }
                        }
                    }

                }
            }
            //Summon CMOON
            if (entity.getHeldItemMainhand().getItem().equals(Items.SHULKER_SHELL) && world.getBlockState(pos).getBlock() == Blocks.REDSTONE_BLOCK){
                HashMap<ControllerMakeHelper.StatesIndex,IBlockState> map = helper.getBlockStates(pos);
                if (map.get(ControllerMakeHelper.StatesIndex.First).getBlock() == Blocks.ANVIL && map.get(ControllerMakeHelper.StatesIndex.Third).getBlock() == Blocks.SLIME_BLOCK && map.get(ControllerMakeHelper.StatesIndex.Fourth).getBlock() == Blocks.DIRT&& map.get(ControllerMakeHelper.StatesIndex.Fifth).getBlock() == blockinit.XIROSE){
                    entity.getHeldItemMainhand().shrink(1);
                    if (world instanceof WorldServer){
                        world.spawnEntity(new EntityLightningBolt(world,x,y,z,true));
                    }
                    setBlockStatesAir(world,helper.getBlockPoses(pos));
                    world.setBlockState(helper.getBlockPoses(pos)[4],blockinit.QIUXIGRAVITY.getDefaultState(),3);
                    if (entity instanceof EntityPlayerMP) {
                        Advancement _adv = ((EntityPlayerMP) entity).mcServer.getAdvancementManager().getAdvancement(new ResourceLocation("the_golden_autumn:cmoon"));
                        AdvancementProgress _ap = ((EntityPlayerMP) entity).getAdvancements().getProgress(_adv);
                        if (!_ap.isDone()) {
                            for (String _criterion : _ap.getRemaningCriteria()) {
                                ((EntityPlayerMP) entity).getAdvancements().grantCriterion(_adv, _criterion);
                            }
                        }
                    }

                }
            }
            //xi Rose
            if(entity.getHeldItemMainhand().getItem() == Items.DYE){
                //MinecraftServer mcserv = world.getMinecraftServer();
                //mcserv.getPlayerList().sendMessage(new TextComponentString("is DOUBLE"));
                //Check QIUROSE
                if(world.getBlockState(pos).getBlock() == blockinit.QIUROSE){
                    if(mcserv!=null)

                        if(

                                world.getBlockState(new BlockPos(x+1,y,z)).getBlock() == blockinit.QIUROSE.getDefaultState().getBlock()
                                        &&
                                        world.getBlockState(new BlockPos(x-1,y,z)).getBlock() == blockinit.QIUROSE.getDefaultState().getBlock()
                                        &&
                                        world.getBlockState(new BlockPos(x,y,z+1)).getBlock() == blockinit.QIUROSE.getDefaultState().getBlock()
                                        &&
                                        world.getBlockState(new BlockPos(x,y,z-1)).getBlock() == blockinit.QIUROSE.getDefaultState().getBlock()
                                        &&
                                        world.getBlockState(new BlockPos(x+1,y,z+1)).getBlock() == blockinit.QIUROSE.getDefaultState().getBlock()
                                        &&
                                        world.getBlockState(new BlockPos(x-1,y,z-1)).getBlock() == blockinit.QIUROSE.getDefaultState().getBlock()
                                        &&
                                        world.getBlockState(new BlockPos(x+1,y,z-1)).getBlock() == blockinit.QIUROSE.getDefaultState().getBlock()
                                        &&
                                        world.getBlockState(new BlockPos(x-1,y,z+1)).getBlock() == blockinit.QIUROSE.getDefaultState().getBlock()


                        ){

                            //SET
                            entity.getHeldItemMainhand().shrink(1);

                            setBlockStatesAir(world,new BlockPos(x+1,y,z),new BlockPos(x-1,y,z),new BlockPos(x,y,z+1),new BlockPos(x,y,z-1),new BlockPos(x+1,y,z+1),new BlockPos(x-1,y,z-1),new BlockPos(x+1,y,z-1),new BlockPos(x-1,y,z+1),pos);
                            EntityItem item = new EntityItem(world,x,y,z,new ItemStack(blockinit.XIROSE));
                            world.spawnEntity(item);
                        }
                }
            }

                //mcserv.getPlayerList().sendMessage(new TextComponentString(Item.getItemFromBlock(Blocks.DOUBLE_PLANT).toString()));
                //mcserv.getPlayerList().sendMessage(new TextComponentString(entity.getHeldItemMainhand().toString()));

            if(world.getBlockState(pos).getBlock() == Blocks.DRAGON_EGG && entity.getHeldItemMainhand().getItem() == iteminit.BOTTLED_AUTU&& entity.isSneaking()){
                entity.getHeldItemMainhand().shrink(1);
                Jaoshingan_I spawn = new Jaoshingan_I(world);
                spawn.setPosition(pos.getX(),pos.getY(),pos.getZ());
                setBlockStatesAir(world,pos);
                world.spawnEntity(spawn);
            }
            if(entity.getHeldItemMainhand().getItem() == Items.SUGAR){
                //MinecraftServer mcserv = world.getMinecraftServer();
                //mcserv.getPlayerList().sendMessage(new TextComponentString("is DOUBLE"));
                //Check QIUROSE
                if(world.getBlockState(pos).getBlock() == Blocks.RED_FLOWER){
                    if(mcserv!=null)

                        if(

                                world.getBlockState(new BlockPos(x+1,y,z)).getBlock() == Blocks.RED_FLOWER.getDefaultState().getBlock()
                                        &&
                                        world.getBlockState(new BlockPos(x-1,y,z)).getBlock() == Blocks.RED_FLOWER.getDefaultState().getBlock()
                                        &&
                                        world.getBlockState(new BlockPos(x,y,z+1)).getBlock() == Blocks.RED_FLOWER.getDefaultState().getBlock()
                                        &&
                                        world.getBlockState(new BlockPos(x,y,z-1)).getBlock() == Blocks.RED_FLOWER.getDefaultState().getBlock()
                                        &&
                                        world.getBlockState(new BlockPos(x+1,y,z+1)).getBlock() == Blocks.RED_FLOWER.getDefaultState().getBlock()
                                        &&
                                        world.getBlockState(new BlockPos(x-1,y,z-1)).getBlock() == Blocks.RED_FLOWER.getDefaultState().getBlock()
                                        &&
                                        world.getBlockState(new BlockPos(x+1,y,z-1)).getBlock() == Blocks.RED_FLOWER.getDefaultState().getBlock()
                                        &&
                                        world.getBlockState(new BlockPos(x-1,y,z+1)).getBlock() == Blocks.RED_FLOWER.getDefaultState().getBlock()


                        ){

                            //SET
                            entity.getHeldItemMainhand().shrink(1);

                            setBlockStatesAir(world,new BlockPos(x+1,y,z),new BlockPos(x-1,y,z),new BlockPos(x,y,z+1),new BlockPos(x,y,z-1),new BlockPos(x+1,y,z+1),new BlockPos(x-1,y,z-1),new BlockPos(x+1,y,z-1),new BlockPos(x-1,y,z+1),pos);
                            EntityItem item = new EntityItem(world,x,y,z,new ItemStack(blockinit.QIUROSE));
                            world.spawnEntity(item);
                        }
                }
            }
            //Change Weather
            if(world.getBlockState(pos).getBlock() == blockinit.QIUXIWEATHER_CLEAR){
                if(world.getTileEntity(pos).getTileData() != null) {
                    NBTTagCompound tag = world.getTileEntity(pos).getTileData();
                    world.setBlockState(pos, blockinit.QIUXIWEATHER_RAIN.getDefaultState());
                    world.getTileEntity(pos).getTileData().setBoolean("tianqikongzhi",tag.getBoolean("tianqikongzhi"));
                }else{
                    world.setBlockState(pos, blockinit.QIUXIWEATHER_RAIN.getDefaultState());
                }
            }
            if(world.getBlockState(pos).getBlock() == blockinit.QIUXIWEATHER_RAIN){
                if(world.getTileEntity(pos).getTileData() != null) {
                    NBTTagCompound tag = world.getTileEntity(pos).getTileData();
                    world.setBlockState(pos,blockinit.QIUXIWEATHER_THUNDER.getDefaultState());
                    world.getTileEntity(pos).getTileData().setBoolean("tianqikongzhi",tag.getBoolean("tianqikongzhi"));
                }else{
                    world.setBlockState(pos,blockinit.QIUXIWEATHER_THUNDER.getDefaultState());
                }

            }
            if(world.getBlockState(pos).getBlock() == blockinit.QIUXIWEATHER_THUNDER){
                if(world.getTileEntity(pos).getTileData() != null) {
                    NBTTagCompound tag = world.getTileEntity(pos).getTileData();
                    world.setBlockState(pos,blockinit.QIUXIWEATHER_CLEAR.getDefaultState());
                    world.getTileEntity(pos).getTileData().setBoolean("tianqikongzhi",tag.getBoolean("tianqikongzhi"));
                }else{
                    world.setBlockState(pos,blockinit.QIUXIWEATHER_CLEAR.getDefaultState());
                }

            }
            //Summon ZLCR
            if (entity.getHeldItemMainhand().getItem().equals(Items.ROTTEN_FLESH) && world.getBlockState(pos).getBlock() == Blocks.REDSTONE_BLOCK){
                HashMap<ControllerMakeHelper.StatesIndex,IBlockState> map = helper.getBlockStates(pos);
                if (map.get(ControllerMakeHelper.StatesIndex.First).getBlock() == Blocks.SOUL_SAND && map.get(ControllerMakeHelper.StatesIndex.Third).getBlock() == Blocks.SOUL_SAND && map.get(ControllerMakeHelper.StatesIndex.Fourth).getBlock() == Blocks.DIRT&& map.get(ControllerMakeHelper.StatesIndex.Fifth).getBlock() == blockinit.QIUXIROSE){
                    entity.getHeldItemMainhand().shrink(1);
                    if (world instanceof WorldServer){
                        world.spawnEntity(new EntityLightningBolt(world,x,y,z,true));
                    }
                    setBlockStatesAir(world,helper.getBlockPoses(pos));
                    world.setBlockState(helper.getBlockPoses(pos)[4],blockinit.QIUXIBIOREDUCER.getDefaultState(),3);
                    if (entity instanceof EntityPlayerMP) {
                        Advancement _adv = ((EntityPlayerMP) entity).mcServer.getAdvancementManager().getAdvancement(new ResourceLocation("the_golden_autumn:the_grateful_dead"));
                        AdvancementProgress _ap = ((EntityPlayerMP) entity).getAdvancements().getProgress(_adv);
                        if (!_ap.isDone()) {
                            for (String _criterion : _ap.getRemaningCriteria()) {
                                ((EntityPlayerMP) entity).getAdvancements().grantCriterion(_adv, _criterion);
                            }
                        }
                    }

                }
            }

        }
    }



   int bladeupdatetick = 0;
    @SubscribeEvent
    public void bladeupdate(TickEvent.PlayerTickEvent event) {
        if (bladeupdatetick==0)
        {
            ItemStack hand = event.player.getHeldItemMainhand();
            EntityPlayer player = event.player;
            MinecraftServer mcserv = FMLCommonHandler.instance().getMinecraftServerInstance();
            World world = event.player.world;

            if (hand.getTagCompound() != null) {
                if (hand.getTagCompound().getBoolean("isUniverseBlade")) {
                    //if (mcserv != null)
                        //mcserv.getPlayerList().sendMessage(new TextComponentString("AUTUMN:FOUND!"));

                    int thes = event.player.getHeldItemMainhand().getTagCompound().getInteger("SummonedSwordColor");
                    //RED
                    if (thes == 16718929) {
                        thes = 16753664;
                    }
                    //ORANGE
                    else if (thes == 16753664) {
                        thes = 14352233;
                    }
                    //YELLOW
                    else if (thes == 14352233) {
                        thes = 7077737;
                    }
                    //GREEN
                    else if (thes == 7077737) {
                        thes = 7061184;
                    }
                    //AQUA
                    else if (thes == 7061184) {
                        thes = 7033087;
                    }
                    //BLUE
                    else if (thes == 7033087) {
                        thes = 11541698;
                    }
                    //PURPLE
                    else if (thes == 11541698) {
                        thes = 16718929;
                    }
                    event.player.getHeldItemMainhand().getTagCompound().setBoolean("Unbreakable",true);
                    event.player.getHeldItemMainhand().getTagCompound().setInteger("ProudSoul",888888888);
                    event.player.getHeldItemMainhand().getTagCompound().setInteger("SummonedSwordColor", thes);
                    event.player.getHeldItemMainhand().getTagCompound().setFloat("baseAttackModifier", 32768.0F);

                    int thes2 = event.player.getHeldItemMainhand().getTagCompound().getInteger("SummonedSwordColor");

                    //if (mcserv != null) {
                      //mcserv.getPlayerList().sendMessage(new TextComponentString("AUTUMN:CHECKED " + thes2));
                    //}

                }
                else if(hand.getTagCompound().getBoolean("isYouto")){
                    event.player.getHeldItemMainhand().getTagCompound().setFloat("baseAttackModifier", 20.0F);
                }
                else {
                    //if (mcserv != null)
                        //mcserv.getPlayerList().sendMessage(new TextComponentString("AUTUMN:NOT FOUND!Because of " + hand.getTagCompound().getBoolean("isUniverseBlade")));
                }
            } else {
                //mcserv.getPlayerList().sendMessage(new TextComponentString("AUTUMN:NULL NBT!"));
            }
            bladeupdatetick ++;
        }else if(bladeupdatetick>0){
            bladeupdatetick++;
            if(bladeupdatetick>=3){
                bladeupdatetick=0;
            }
        }
    }


    @SubscribeEvent
    public void Ondeath(LivingDeathEvent event) {
        Entity envent = event.getEntity();
        if (envent instanceof EntityPlayer) {

            EntityPlayer player = (EntityPlayer) envent;

            ItemStack hand = player.getHeldItemMainhand();


            if (hand.getTagCompound() != null) {
                if (hand.getTagCompound().getBoolean("isUniverseBlade")) {
                    player.setHealth(player.getMaxHealth());
                    player.isDead = false;
                    player.deathTime = 0;
                    player.closeScreen();
                }


            }}}


            @SubscribeEvent
            public void onClientTick (TickEvent.ClientTickEvent event){

                EntityPlayer player = Minecraft.getMinecraft().player;
                if (player != null) {
                    if (player.getHeldItemMainhand().getTagCompound() != null) {
                        if (player.getHeldItemMainhand().getTagCompound().getBoolean("isUniverseBlade")) {
                            if (player.isDead) {
                                System.out.println("AUTUMN:NODEAD");
                                player.isDead = false;
                                player.deathTime = 0;

                            }


                            if (!player.world.playerEntities.contains(player)) {
                                player.world.playerEntities.add(player);
                                player.world.onEntityAdded(player);
                                System.out.println("AUTUMN:ADDPLAYER");
                            }
                        }
                    }
                }
            }


            @SubscribeEvent
            public void onEntityJoin (EntityJoinWorldEvent event){
                Entity entity = event.getEntity();
                if (!(entity instanceof EntityPlayerMP) || !(entity.world instanceof World) || !((EntityPlayerMP) entity).getAdvancements().getProgress(((EntityPlayerMP) entity).mcServer.getAdvancementManager().getAdvancement(new ResourceLocation("the_golden_autumn:thegoldenautumn"))).isDone()) {
                    if (!entity.world.isRemote && entity.world.getMinecraftServer() != null) {
                        entity.world.getMinecraftServer().getCommandManager().executeCommand(new ICommandSender() {
                            @Override
                            public String getName() {
                                return "";
                            }

                            @Override
                            public boolean canUseCommand(int permission, String command) {
                                return true;
                            }

                            @Override
                            public World getEntityWorld() {
                                return entity.world;
                            }

                            @Override
                            public MinecraftServer getServer() {
                                return entity.world.getMinecraftServer();
                            }

                            @Override
                            public boolean sendCommandFeedback() {
                                return false;
                            }

                            @Override
                            public BlockPos getPosition() {
                                return entity.getPosition();
                            }

                            @Override
                            public Vec3d getPositionVector() {
                                return new Vec3d(entity.posX, entity.posY, entity.posZ);
                            }

                            @Override
                            public Entity getCommandSenderEntity() {
                                return entity;
                            }
                        }, "give @s patchouli:guide_book{\"patchouli:book\":\"the_golden_autumn:qiuxibook\"} 1");
                    }

                    if (entity instanceof EntityPlayerMP) {
                        Advancement _adv = ((EntityPlayerMP) entity).mcServer.getAdvancementManager().getAdvancement(new ResourceLocation("the_golden_autumn:thegoldenautumn"));
                        AdvancementProgress _ap = ((EntityPlayerMP) entity).getAdvancements().getProgress(_adv);
                        if (!_ap.isDone()) {
                            Iterator _iterator = _ap.getRemaningCriteria().iterator();

                            while (_iterator.hasNext()) {
                                String _criterion = (String) _iterator.next();
                                ((EntityPlayerMP) entity).getAdvancements().grantCriterion(_adv, _criterion);
                            }
                        }
                    }
                }
            }
            public static void setBlockStatesAir(World world, BlockPos... poses){
                IBlockState bs = Blocks.AIR.getDefaultState();
                for (BlockPos pos1 : poses){
                    world.setBlockState(pos1,bs,3);
                }
            }
            public static class ControllerMakeHelper{
        World world;
        public ControllerMakeHelper(World worldIn){
            this.world = worldIn;
        }

        public HashMap<StatesIndex,IBlockState> getBlockStates(BlockPos ClickPos){
            HashMap<StatesIndex,IBlockState> map = new HashMap<>();
            map.put(StatesIndex.First,world.getBlockState(new BlockPos(ClickPos.getX(),ClickPos.getY()-1d,ClickPos.getZ())));
            map.put(StatesIndex.Second,world.getBlockState(new BlockPos(ClickPos.getX(),ClickPos.getY(),ClickPos.getZ())));
            map.put(StatesIndex.Third,world.getBlockState(new BlockPos(ClickPos.getX(),ClickPos.getY()+1d,ClickPos.getZ())));
            map.put(StatesIndex.Fourth,world.getBlockState(new BlockPos(ClickPos.getX(),ClickPos.getY()+2d,ClickPos.getZ())));
            map.put(StatesIndex.Fifth,world.getBlockState(new BlockPos(ClickPos.getX(),ClickPos.getY()+3d,ClickPos.getZ())));
            return map;
        }

        public BlockPos[] getBlockPoses(BlockPos ClickPos){
            return new BlockPos[]{new BlockPos(ClickPos.getX(),ClickPos.getY()+3d,ClickPos.getZ()),new BlockPos(ClickPos.getX(),ClickPos.getY(),ClickPos.getZ()),new BlockPos(ClickPos.getX(),ClickPos.getY()+1d,ClickPos.getZ()),new BlockPos(ClickPos.getX(),ClickPos.getY()+2d,ClickPos.getZ()),new BlockPos(ClickPos.getX(),ClickPos.getY()-1d,ClickPos.getZ())};
        }
                public BlockPos[] getBlockPoses4(BlockPos ClickPos){
                    return new BlockPos[]{new BlockPos(ClickPos.getX(),ClickPos.getY()+2d,ClickPos.getZ()),new BlockPos(ClickPos.getX(),ClickPos.getY()+1d,ClickPos.getZ()),new BlockPos(ClickPos.getX(),ClickPos.getY(),ClickPos.getZ()),new BlockPos(ClickPos.getX(),ClickPos.getY()-1d,ClickPos.getZ())};
                }

        public enum StatesIndex{
            First,
            Second,
            Third,
            Fourth,
            Fifth
        }
            }

            @SubscribeEvent
            public void onBlockDes(BlockEvent.BreakEvent event){
        World world = event.getWorld();
        double x = event.getPos().getX();
        double y = event.getPos().getY();
        double z = event.getPos().getZ();
        BlockPos pos = event.getPos();
        IBlockState state =event.getState();

        if(state.getBlock() == Blocks.LEAVES){
            if(Math.random() <= 0.05)
            {
                EntityItem item = new EntityItem(world, x, y, z, new ItemStack(iteminit.ORANGE_FRUIT));
                world.spawnEntity(item);
            }
        }


            }
        }

