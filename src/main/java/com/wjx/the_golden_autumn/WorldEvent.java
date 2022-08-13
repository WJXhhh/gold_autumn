package com.wjx.the_golden_autumn;

import com.wjx.the_golden_autumn.block.BlockXi_rose;
import com.wjx.the_golden_autumn.init.blockinit;
import com.wjx.the_golden_autumn.init.iteminit;
import com.wjx.the_golden_autumn.item.ItemBottledAutumn;
import com.wjx.the_golden_autumn.slashblade.blade.Items.Item_Star;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.Iterator;
import java.util.Objects;
import java.util.logging.Logger;

import static com.wjx.the_golden_autumn.slashblade.BladeLoader.STAR;

@Mod.EventBusSubscriber
public class WorldEvent {

    public WorldEvent(){
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {

        int x = event.getPos().getX();
        int y = event.getPos().getY();
        int z = event.getPos().getZ();
        EntityPlayer entity = event.getEntityPlayer();
        World world = event.getWorld();
        if (event.getHand() == entity.getActiveHand()) {
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


                    event.player.getHeldItemMainhand().getTagCompound().setInteger("SummonedSwordColor", thes);
                    event.player.getHeldItemMainhand().getTagCompound().setFloat("baseAttackModifier", 32768.0F);

                    int thes2 = event.player.getHeldItemMainhand().getTagCompound().getInteger("SummonedSwordColor");

                    //if (mcserv != null) {
                      //mcserv.getPlayerList().sendMessage(new TextComponentString("AUTUMN:CHECKED " + thes2));
                    //}

                } else {
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
        }

