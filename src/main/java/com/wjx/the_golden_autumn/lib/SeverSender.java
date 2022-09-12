package com.wjx.the_golden_autumn.lib;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class SeverSender {
    public static void sendTotalMessage(String s){
        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        server.getPlayerList().sendMessage(new TextComponentString(s));
    }
}
