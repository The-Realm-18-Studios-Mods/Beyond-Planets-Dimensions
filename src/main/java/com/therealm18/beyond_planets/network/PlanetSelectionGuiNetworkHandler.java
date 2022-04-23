package com.therealm18.beyond_planets.network;

import com.therealm18.beyond_planets.world.PlanetsRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.mrscauthd.beyond_earth.events.Methods;
import net.mrscauthd.beyond_earth.guis.screens.planetselection.PlanetSelectionGui;

import java.util.function.Supplier;

public class PlanetSelectionGuiNetworkHandler extends PlanetSelectionGui.AbstractNetworkHandler {
    private int integer = 0;

    public PlanetSelectionGuiNetworkHandler(int integer) {
        this.setInteger(integer);
    }

    public int getInteger() {
        return this.integer;
    }

    public void setInteger(int integer) {
        this.integer = integer;
    }

    public PlanetSelectionGuiNetworkHandler(FriendlyByteBuf buffer) {
        this.setInteger(buffer.readInt());
    }

    public static PlanetSelectionGuiNetworkHandler decode(FriendlyByteBuf buffer) {
        return new PlanetSelectionGuiNetworkHandler(buffer);
    }

    public static void encode(PlanetSelectionGuiNetworkHandler message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.getInteger());
    }

    public static void handle(PlanetSelectionGuiNetworkHandler message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        ServerPlayer player = context.getSender();

        /** Teleport Planet Buttons */
        if (message.getInteger() == 1) {
            PlanetSelectionGui.defaultOptions(player);
            Methods.teleportButton(player, PlanetsRegistry.sunFlowerPlanet1, false);
        }

        /** Teleport Orbit Buttons */
        if (message.getInteger() == 2) {
            PlanetSelectionGui.defaultOptions(player);
            Methods.teleportButton(player, PlanetsRegistry.sunFlowerPlanet1Orbit, false);
        }

        /** Teleport Station Buttons */
        if (message.getInteger() == 3) {
            PlanetSelectionGui.defaultOptions(player);
            PlanetSelectionGui.deleteItems(player);
            Methods.teleportButton(player, PlanetsRegistry.sunFlowerPlanet1Orbit, true);
        }
//        /** Teleport Planet Buttons */
//        if (message.getInteger() == 104) {
//            PlanetSelectionGui.defaultOptions(player);
//            Methods.teleportButton(player, PlanetsRegistry.titanTheMoonPlanet2, false);
//        }
//
//        /** Teleport Orbit Buttons */
//        if (message.getInteger() == 105) {
//            PlanetSelectionGui.defaultOptions(player);
//            Methods.teleportButton(player, PlanetsRegistry.titanTheMoonPlanet2Orbit, false);
//        }
//
//        /** Teleport Station Buttons */
//        if (message.getInteger() == 106) {
//            PlanetSelectionGui.defaultOptions(player);
//            PlanetSelectionGui.deleteItems(player);
//            Methods.teleportButton(player, PlanetsRegistry.titanTheMoonPlanet2Orbit, true);
//        }

        context.setPacketHandled(true);
    }
}
