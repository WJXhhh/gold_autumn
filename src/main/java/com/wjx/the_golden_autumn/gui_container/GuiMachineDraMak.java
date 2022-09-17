package com.wjx.the_golden_autumn.gui_container;

import com.wjx.the_golden_autumn.TheGoldenAutumnMod;
import com.wjx.the_golden_autumn.init.iteminit;
import com.wjx.the_golden_autumn.lib.ContainerSlotHelper;
import com.wjx.the_golden_autumn.lib.OutputSlot;
import com.wjx.the_golden_autumn.machine_recipe.DrawingTableRecipeHandler;
import com.wjx.the_golden_autumn.tileEntity.TileEntityMachineDrawingMaker;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.List;

public class GuiMachineDraMak {
    public static class CustomContainer extends Container implements ITickable{
        private final TileEntityMachineDrawingMaker tileEntity;

        public CustomContainer(InventoryPlayer player, TileEntityMachineDrawingMaker tileentity){
            this.tileEntity = tileentity;
            this.addSlotToContainer(new Slot(tileentity, 0, 51, 39));
            this.addSlotToContainer(new OutputSlot(player.player,tileentity, 1, 127, 39));

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
                if (index < 2) {
                    if (!this.mergeItemStack(stack1, 2, this.inventorySlots.size(), true)) {
                        return ItemStack.EMPTY;
                    }
                    slot.onSlotChange(stack1, stack);
                } else if (!this.mergeItemStack(stack1, 0, 2, false)) {
                    if (index < 2 + 27) {
                        if (!this.mergeItemStack(stack1, 2 + 27, this.inventorySlots.size(), true)) {
                            return ItemStack.EMPTY;
                        }
                    } else {
                        if (!this.mergeItemStack(stack1, 2, 2 + 27, false)) {
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
        private static final ResourceLocation TEXTURES = new ResourceLocation(TheGoldenAutumnMod.MODID + ":textures/gui/machine_drawingtable.png");
        private final InventoryPlayer player;
        private final TileEntityMachineDrawingMaker tileentity;
        private int x, y, z;

        public CustomGui(InventoryPlayer player, TileEntityMachineDrawingMaker tileentity) {
            super(new GuiMachineDraMak.CustomContainer(player,tileentity));
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
            this.buttonList.add(new GuiButton(0, this.guiLeft + 81, this.guiTop + 39, 35, 20, I18n.format("gui.drawing_maker.button")));
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
        protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
            GlStateManager.color(1.0f,1.0f,1.0f);
            //GL11.glColor4f(1,1,1,1);
            this.mc.getTextureManager().bindTexture(TEXTURES);

        this.drawTexturedModalRect(this.guiLeft,this.guiTop,0,0,this.xSize,this.ySize);
        }
        @Override
        protected void actionPerformed(GuiButton button) throws IOException {
            //System.out.println("sent pack to sever");
            TheGoldenAutumnMod.PACKET_HANDLER.sendToServer(new GuiMachineDraMak.CustomGui.GUIButtonPressedMessage(button.id, x,y,z));
            handleButtonAction(player.player, button.id, x, y, z);
        }
       public static class GUIButtonPressedMessageHandler implements IMessageHandler<GUIButtonPressedMessage, IMessage> {
            @Override
            public IMessage onMessage(GuiMachineDraMak.CustomGui.GUIButtonPressedMessage message, MessageContext context) {
                EntityPlayerMP entity = context.getServerHandler().player;
                entity.getServerWorld().addScheduledTask(() -> {
                    System.out.println("Dramak handler");
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

            if (buttonID == 0) {
                {

                    ItemStack input = ItemStack.EMPTY;
                    ItemStack output = ItemStack.EMPTY;
                    if (entity instanceof EntityPlayerMP){

                        EntityPlayerMP mp = (EntityPlayerMP) entity;
                        Container _current = mp.openContainer;


                        List<Slot> invobj = _current.inventorySlots;

                                input = invobj.get(0).getStack();



                            /*if (input.getItem() == iteminit.PEACE_OF_MAPLE) {
                                output = new ItemStack(iteminit.DRAWING_CUTAUTUMN);
                                if((invobj.get(1).getStack().getItem() == output.getItem()&&invobj.get(1).getStack().getCount() <64)||invobj.get(1).getStack() == ItemStack.EMPTY)
                                ContainerSlotHelper.shrink(invobj, 1, 0);
                            } else if (input.getItem() == Item.getItemFromBlock(Blocks.TALLGRASS) && input.getCount() >= 2) {
                                output = new ItemStack(iteminit.DRAWING_CLEAVER);
                                if((invobj.get(1).getStack().getItem() == output.getItem()&&invobj.get(1).getStack().getCount() <64)||invobj.get(1).getStack() == ItemStack.EMPTY)
                                ContainerSlotHelper.shrink(invobj, 2, 0);
                            } else if (input.getItem() == iteminit.ORANGE_FRUIT && input.getCount() >= 2) {
                                output = new ItemStack(iteminit.DRAWING_ORANGE);
                                if((invobj.get(1).getStack().getItem() == output.getItem()&&invobj.get(1).getStack().getCount() <64)||invobj.get(1).getStack() == ItemStack.EMPTY)
                                ContainerSlotHelper.shrink(invobj, 2, 0);
                            }
                            if (invobj.get(1).getStack() == ItemStack.EMPTY){
                                invobj.get(1).putStack(output);
                            }
                            else if(invobj.get(1).getStack().getItem() == output.getItem() && invobj.get(1).getStack().getCount()<64){
                                output.setCount(invobj.get(1).getStack().getCount() +1);
                                invobj.get(1).putStack(output);
                            }*/
                        DrawingTableRecipeHandler.DrawingTableOutStruct outl = DrawingTableRecipeHandler.getRecipe(new DrawingTableRecipeHandler.DrawingTableRecipeStruct(invobj.get(0).getStack()));
                        if(outl.outCount !=0 && invobj.get(1).getStack().getCount() < 64){
                            ItemStack outputs = new ItemStack(outl.out);
                            outputs.setCount(invobj.get(1).getStack().getCount() +outl.outCount);
                            ContainerSlotHelper.shrink(invobj, DrawingTableRecipeHandler.RecipeForNeedCount.get(input.getItem()), 0);
                            invobj.get(1).putStack(outputs);
                        }



                    }
                }
            }
        }

    }
}
