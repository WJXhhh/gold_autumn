package com.wjx.the_golden_autumn.gui_container;

import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import com.wjx.the_golden_autumn.lib.ContainerSlotHelper;
import com.wjx.the_golden_autumn.lib.OutputSlot;
import com.wjx.the_golden_autumn.machine_recipe.LianqiRecipe;
import com.wjx.the_golden_autumn.tileEntity.TileEntityMachineLianQi;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.lwjgl.input.Keyboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuiMachineLianQi {
    public static class CustomContainer extends Container implements ITickable{

        private final TileEntityMachineLianQi tileEntity;
        public CustomContainer(InventoryPlayer player, TileEntityMachineLianQi tileentity) {
            this.tileEntity = tileentity;

            this.addSlotToContainer(new Slot(tileentity, 0, 27, 17));
            this.addSlotToContainer(new Slot(tileentity, 1, 27, 39));
            this.addSlotToContainer(new Slot(tileentity, 2, 27, 61));
            this.addSlotToContainer(new Slot(tileentity, 3, 51, 39));
            this.addSlotToContainer(new Slot(tileentity, 4, 73, 17));
            this.addSlotToContainer(new Slot(tileentity, 5, 73, 61));
            this.addSlotToContainer(new OutputSlot(player.player,tileentity, 6, 127, 39));

            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 9; x++) {
                    this.addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
                }
            }

            for (int x = 0; x < 9; x++) {
                this.addSlotToContainer(new Slot(player, x, 8 + x * 18, 142));
            }
        }

        @Override
        public void addListener(IContainerListener listener) {
            super.addListener(listener);
            listener.sendAllWindowProperties(this, this.tileEntity);
        }



        @Override
        public boolean canInteractWith(EntityPlayer playerIn) {
            return this.tileEntity.isUsableByPlayer(playerIn);
        }

        @Override
        public void update() {

        }

        @Override
        public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
            ItemStack stack = ItemStack.EMPTY;
            Slot slot = (Slot) this.inventorySlots.get(index);
            if (slot != null && slot.getHasStack()) {
                ItemStack stack1 = slot.getStack();
                stack = stack1.copy();
                if (index < 8) {
                    if (!this.mergeItemStack(stack1, 8, this.inventorySlots.size(), true)) {
                        return ItemStack.EMPTY;
                    }
                    slot.onSlotChange(stack1, stack);
                } else if (!this.mergeItemStack(stack1, 0, 8, false)) {
                    if (index < 8 + 27) {
                        if (!this.mergeItemStack(stack1, 8 + 27, this.inventorySlots.size(), true)) {
                            return ItemStack.EMPTY;
                        }
                    } else {
                        if (!this.mergeItemStack(stack1, 8, 8 + 27, false)) {
                            return ItemStack.EMPTY;
                        }

                    }
                    return ItemStack.EMPTY;
                }

                if (stack1.getCount() == 0) {
                    slot.putStack(ItemStack.EMPTY);
                } else {
                    slot.onSlotChanged();
                }
                if (stack1.getCount() == stack.getCount()) {
                    return ItemStack.EMPTY;
                }
                slot.onTake(playerIn, stack1);

            }
            return stack;
        }

        @Override
        protected boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection) {
            boolean flag = false;
            int i = startIndex;
            if (reverseDirection) {
                i = endIndex - 1;
            }
            if (stack.isStackable()) {
                while (!stack.isEmpty()) {
                    if (reverseDirection) {
                        if (i < startIndex) {
                            break;
                        }
                    } else if (i >= endIndex) {
                        break;
                    }
                    Slot slot = this.inventorySlots.get(i);
                    ItemStack itemstack = slot.getStack();
                    if (slot.isItemValid(itemstack) && !itemstack.isEmpty() && itemstack.getItem() == stack.getItem()
                            && (!stack.getHasSubtypes() || stack.getMetadata() == itemstack.getMetadata())
                            && ItemStack.areItemStackTagsEqual(stack, itemstack)) {
                        int j = itemstack.getCount() + stack.getCount();
                        int maxSize = Math.min(slot.getSlotStackLimit(), stack.getMaxStackSize());
                        if (j <= maxSize) {
                            stack.setCount(0);
                            itemstack.setCount(j);
                            slot.putStack(itemstack);
                            flag = true;
                        } else if (itemstack.getCount() < maxSize) {
                            stack.shrink(maxSize - itemstack.getCount());
                            itemstack.setCount(maxSize);
                            slot.putStack(itemstack);
                            flag = true;
                        }
                    }
                    if (reverseDirection) {
                        --i;
                    } else {
                        ++i;
                    }
                }
            }
            if (!stack.isEmpty()) {
                if (reverseDirection) {
                    i = endIndex - 1;
                } else {
                    i = startIndex;
                }
                while (true) {
                    if (reverseDirection) {
                        if (i < startIndex) {
                            break;
                        }
                    } else if (i >= endIndex) {
                        break;
                    }
                    Slot slot1 = this.inventorySlots.get(i);
                    ItemStack itemstack1 = slot1.getStack();
                    if (itemstack1.isEmpty() && slot1.isItemValid(stack)) {
                        if (stack.getCount() > slot1.getSlotStackLimit()) {
                            slot1.putStack(stack.splitStack(slot1.getSlotStackLimit()));
                        } else {
                            slot1.putStack(stack.splitStack(stack.getCount()));
                        }
                        slot1.onSlotChanged();
                        flag = true;
                        break;
                    }
                    if (reverseDirection) {
                        --i;
                    } else {
                        ++i;
                    }
                }
            }
            return flag;
        }
    }
    public static class CustomGui extends GuiContainer{
        private static final ResourceLocation TEXTURES = new ResourceLocation(TheGoldenAutumnMod.MODID + ":textures/gui/machine_lanqi.png");
        private final InventoryPlayer player;
        private final TileEntityMachineLianQi tileentity;
        private int x, y, z;
        private static int MSIndex1 = 0;
        private static int MSIndex2 = 2;
        private static int MSIndex3 = 4;
        private static int MSIndex4 = 5;
        private static int TuZhiIndex = 1;
        private static int QiLingIndex = 3;
        private static ArrayList<ItemStack> stacks = new ArrayList<>();



        public CustomGui(InventoryPlayer player, TileEntityMachineLianQi tileentity) {
            super(new CustomContainer(player,tileentity));
            this.player = player;
            this.tileentity = tileentity;
            x = tileentity.getPos().getX();
            y = tileentity.getPos().getY();
            z = tileentity.getPos().getZ();
        }

        @Override
        public void initGui() {
            super.initGui();
            Keyboard.enableRepeatEvents(true);
            this.buttonList.clear();
            this.buttonList.add(new GuiButton(1, this.guiLeft + 86, this.guiTop + 39, 35, 20, I18n.format("gui.lianqi.button")));
        }

        @Override
        public void drawScreen(int mouseX, int mouseY, float partialTicks) {
            this.drawDefaultBackground();
            super.drawScreen(mouseX,mouseY,partialTicks);
            this.renderHoveredToolTip(mouseX,mouseY);
        }

        @Override
        protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
            String tileName =this.tileentity.getDisplayName().getUnformattedComponentText();
            this.fontRenderer.drawString(tileName,((this.xSize/2 -this.fontRenderer.getStringWidth(tileName)/2)+3),8,4210752);
        }

        @Override
        protected void actionPerformed(GuiButton button) throws IOException {
            TheGoldenAutumnMod.PACKET_HANDLER.sendToServer(new GUIButtonPressedMessage(button.id, x,y,z));
            handleButtonAction(player.player, button.id, x, y, z);
        }

        @Override
        protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
            GlStateManager.color(1.0f,1.0f,1.0f);
            this.mc.getTextureManager().bindTexture(TEXTURES);
            this.drawTexturedModalRect(this.guiLeft,this.guiTop,0,0,this.xSize,this.ySize);
        }
        public static class GUIButtonPressedMessageHandler implements IMessageHandler<GUIButtonPressedMessage, IMessage> {
            @Override
            public IMessage onMessage(GUIButtonPressedMessage message, MessageContext context) {
                EntityPlayerMP entity = context.getServerHandler().player;
                entity.getServerWorld().addScheduledTask(() -> {
                    int buttonID = message.buttonID;
                    int x = message.x;
                    int y = message.y;
                    int z = message.z;
                    handleButtonAction(entity, buttonID, x, y, z);
                });
                return null;
            }
        }
        public static class GUIButtonPressedMessage implements IMessage {
            int buttonID, x, y, z;
            public GUIButtonPressedMessage() {
            }

            public GUIButtonPressedMessage(int buttonID, int x, int y, int z) {
                this.buttonID = buttonID;
                this.x = x;
                this.y = y;
                this.z = z;
            }

            @Override
            public void toBytes(io.netty.buffer.ByteBuf buf) {
                buf.writeInt(buttonID);
                buf.writeInt(x);
                buf.writeInt(y);
                buf.writeInt(z);
            }

            @Override
            public void fromBytes(io.netty.buffer.ByteBuf buf) {
                buttonID = buf.readInt();
                x = buf.readInt();
                y = buf.readInt();
                z = buf.readInt();
            }
        }
        private static void handleButtonAction(EntityPlayer entity, int buttonID, int x, int y, int z) {
            World world = entity.world;


            // security measure to prevent arbitrary chunk generation
            if (!world.isBlockLoaded(new BlockPos(x, y, z)))
                return;
            if (buttonID == 1) {
                    if (entity instanceof EntityPlayerMP) {
                       MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
                        //server.getPlayerList().sendMessage(new TextComponentString("is mp!"));

                        EntityPlayerMP mp = (EntityPlayerMP) entity;
                        Container _current = mp.openContainer;
                        ItemStack out = ItemStack.EMPTY;

                        List<Slot> invobj = _current.inventorySlots;
                        stacks.clear();
                        stacks.add(0,invobj.get(MSIndex1).getStack());
                        stacks.add(1,invobj.get(MSIndex2).getStack());
                        stacks.add(2,invobj.get(MSIndex3).getStack());
                        stacks.add(3,invobj.get(MSIndex4).getStack());
                        LianqiRecipe.LianqiRecipePack pack = LianqiRecipe.getRecipe(new LianqiRecipe.LianqiInputRecipeStruct(stacks,invobj.get(TuZhiIndex).getStack(),invobj.get(QiLingIndex).getStack()));
                        out = pack.out;
                        //server.getPlayerList().sendMessage(new TextComponentString(out.getItem().toString()));
                        if (!pack.isEmpty){
                          if(invobj.get(6).getStack() == ItemStack.EMPTY)
                            {
                                invobj.get(6).putStack(out);
                                ArrayList<Integer> MaterialIndexes = new ArrayList<>();
                                MaterialIndexes.add(MSIndex1);
                                MaterialIndexes.add(MSIndex2);
                                MaterialIndexes.add(MSIndex3);
                                MaterialIndexes.add(MSIndex4);
                                LianqiRecipe.OutRecipeStruct RecipeStackList = LianqiRecipe.getRecipeArray().get(pack.index);
                                for (int index : MaterialIndexes) {
                                    for (ItemStack stack : RecipeStackList.getMaterials()) {
                                        if (invobj.get(index).getStack().getItem() == stack.getItem()) {
                                            ContainerSlotHelper.shrink(invobj, stack.getCount(), index);
                                        }
                                    }
                                }
                                ContainerSlotHelper.shrink(invobj, 1, TuZhiIndex);
                                ContainerSlotHelper.shrink(invobj, 1, QiLingIndex);
                            }

                        }
                    }
            }
        }
    }

}
