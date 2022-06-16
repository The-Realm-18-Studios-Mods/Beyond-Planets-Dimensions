package com.therealm18studios.beyond_planets_dimensions.network;

import com.therealm18studios.beyond_planets_dimensions.world.PlanetsRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.mrscauthd.beyond_earth.events.Methods;
import net.mrscauthd.beyond_earth.guis.screens.planetselection.helper.PlanetSelectionMenuNetworkHandlerHelper;

import java.util.function.Supplier;

public class PlanetSelectionGuiNetworkHandler extends PlanetSelectionMenuNetworkHandlerHelper {
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

        switch (message.getInteger()) {

            /** Teleport Planet Buttons */
            case 0:
                message.defaultOptions(player);
                Methods.teleportButton(player, PlanetsRegistry.pawsHunterPlanet, false);
                break;
            case 1:

                /** Teleport Orbit Buttons */
                message.defaultOptions(player);
                Methods.teleportButton(player, PlanetsRegistry.pawsHunterPlanetOrbit, false);
                break;

            /** Teleport Station Buttons */
            case 2:
                message.defaultOptions(player);
                message.deleteItems(player);
                Methods.teleportButton(player, PlanetsRegistry.pawsHunterPlanetOrbit, true);
                break;
        }

        context.setPacketHandled(true);
    }
}
