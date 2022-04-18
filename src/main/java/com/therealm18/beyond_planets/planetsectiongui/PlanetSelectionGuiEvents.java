package com.therealm18.beyond_planets.planetsectiongui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.therealm18.beyond_planets.BeyondPlanets;
import com.therealm18.beyond_planets.network.PlanetSelectionGuiNetworkHandler;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ContainerScreenEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.guis.helper.GuiHelper;
import net.mrscauthd.beyond_earth.guis.helper.ImageButtonPlacer;
import net.mrscauthd.beyond_earth.guis.screens.planetselection.PlanetSelectionGui;
import net.mrscauthd.beyond_earth.guis.screens.planetselection.PlanetSelectionGuiWindow;

import static net.mrscauthd.beyond_earth.guis.screens.planetselection.PlanetSelectionGuiWindow.*;

@Mod.EventBusSubscriber(modid = BeyondPlanets.MODID, value = Dist.CLIENT)
public class PlanetSelectionGuiEvents {

    /** TEXT */
    public static final Component sunFlowerText = tl("sunflower");
    public static final Component sunFlowerPlanet1Text = tl("sun_flower_planet_1");
    public static final Component sunFlowerPlanet1OrbitText = tl("sun_flower_planet_1_orbit");
    public static final Component sunFlowerPlanet1SpaceStationText = tl("sun_flower_planet_1_space_station");
//    public static final Component titan_The_MoonPlanet2Text = tl("titan_the_moon_planet");
//    public static final Component titan_The_MoonPlanet2OrbitText = tl("titan_the_moon_planet_orbit");
//    public static final Component titan_The_MoonPlanet2SpaceStationText = tl("titan_the_moon_planet_space_station");

    /** BUTTONS */
    private static ImageButtonPlacer sunFlowerSolarSystemButton;
    private static ImageButtonPlacer sunFlowerPlanet1MenuButton;
    private static ImageButtonPlacer sunFlowerPlanet1Button;
    private static ImageButtonPlacer sunFlowerPlanet1OrbitButton;
    private static ImageButtonPlacer sunFlowerPlanet1SpaceStationButton;
//    private static ImageButtonPlacer titan_The_MoonPlanet2MenuButton;
//    private static ImageButtonPlacer titan_The_MoonPlanet2Button;
//    private static ImageButtonPlacer titan_The_MoonPlanet2OrbitButton;
//    private static ImageButtonPlacer titan_The_MoonPlanet2SpaceStationButton;
    private static ImageButtonPlacer backButton;

    /** TEXTURES */
    public static final ResourceLocation SUN_FLOWER_PLANET_1_TEX = new ResourceLocation(BeyondPlanets.MODID, "textures/sky/gui/sunflower/sunflower/planet/1.png");
//    public static final ResourceLocation TITAN_THE_MOON_PLANET_2_TEX = new ResourceLocation(BeyondPlanets.MODID, "textures/sky/gui/sunflower/sunflower/planet/2.png");

    @SubscribeEvent
    public static void screenBackgroundRender(ContainerScreenEvent.DrawBackground event){
        if (event.getContainerScreen() instanceof PlanetSelectionGuiWindow) {
            PlanetSelectionGuiWindow screen = (PlanetSelectionGuiWindow) event.getContainerScreen();
            PoseStack ms = event.getPoseStack();

            // ENABLE RENDER SYSTEM
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();

            // SOLAR SYSTEM
            if (screen.Category >= 100 && screen.Category <= 102) {
                RenderSystem.setShaderTexture(0, solarSystemTex);
                GuiHelper.blit(ms, (screen.width - 185) / 2, (screen.height - 185) / 2, 0, 0, 185, 185, 185, 185);
            }

            // PLANETS
            if (screen.Category >= 100 && screen.Category <= 102) {
                screen.addPlanet(event.getPoseStack(), PlanetSelectionGuiEvents.SUN_FLOWER_PLANET_1_TEX, -20.5F, -20.5F, 10, 10, screen.rotationEarth);
//                screen.addPlanet(event.getPoseStack(), PlanetSelectionGuiEvents.TITAN_THE_MOON_PLANET_2_TEX, -41F, -41F, 10, 10, screen.rotationEarth);
            }

            // MENU
            if (screen.Category == 100) {
                RenderSystem.setShaderTexture(0, rocketMenuListTex);
                GuiComponent.blit(ms, 0, (screen.height / 2) - 177 / 2, 0, 0, 105, 177, 105, 177);
            } else if ((screen.Category == 101 || screen.Category == 102)) {
                RenderSystem.setShaderTexture(0, rocketMenuListTex2);
                GuiComponent.blit(ms, 0, (screen.height / 2) - 177 / 2, 0, 0, 215, 177, 215, 177);
            }

            RenderSystem.disableBlend();
        }
    }

    @SubscribeEvent
    public static void screenRenderer(ScreenEvent.DrawScreenEvent event) {
        if (event.getScreen() instanceof PlanetSelectionGuiWindow) {
            PlanetSelectionGuiWindow screen = (PlanetSelectionGuiWindow) event.getScreen();

            // SUN FLOWER SOLAR SYSTEM BUTTOn
            if (screen.Category == -1) {
                PlanetSelectionGuiEvents.sunFlowerSolarSystemButton.visible = true;
            } else {
                PlanetSelectionGuiEvents.sunFlowerSolarSystemButton.visible = false;
            }

            // SUN FLOWER PLANET 1 MENU BUTTON
            if (screen.Category == 100) {
                PlanetSelectionGuiEvents.sunFlowerPlanet1MenuButton.visible = true;
            } else {
                PlanetSelectionGuiEvents.sunFlowerPlanet1MenuButton.visible = false;
            }

            // SUN FLOWER PLANET 1 BUTTON
            if (screen.Category == 101) {
                PlanetSelectionGuiEvents.sunFlowerPlanet1Button.visible = true;
            } else {
                PlanetSelectionGuiEvents.sunFlowerPlanet1Button.visible = false;
            }

            // SUN FLOWER PLANET 1 ORBIT BUTTON
            if (screen.Category == 101) {
                PlanetSelectionGuiEvents.sunFlowerPlanet1OrbitButton.visible = true;
            } else {
                PlanetSelectionGuiEvents.sunFlowerPlanet1OrbitButton.visible = false;
            }

            // SUN FLOWER PLANET 1 SPACE STATION BUTTON
            if (screen.Category == 101) {
                PlanetSelectionGuiEvents.sunFlowerPlanet1SpaceStationButton.visible = true;
            } else {
                PlanetSelectionGuiEvents.sunFlowerPlanet1SpaceStationButton.visible = false;
            }

//            // PLANET 2 MENU BUTTON
//            if (screen.Category == 100) {
//                PlanetSelectionGuiEvents.titan_The_MoonPlanet2MenuButton.visible = true;
//            } else {
//                PlanetSelectionGuiEvents.titan_The_MoonPlanet2MenuButton.visible = false;
//            }
//
//            // PLANET 2 BUTTON
//            if (screen.Category == 102) {
//                PlanetSelectionGuiEvents.titan_The_MoonPlanet2Button.visible = true;
//            } else {
//                PlanetSelectionGuiEvents.titan_The_MoonPlanet2Button.visible = false;
//            }
//
//            // PLANET 2 ORBIT BUTTON
//            if (screen.Category == 102) {
//                PlanetSelectionGuiEvents.titan_The_MoonPlanet2OrbitButton.visible = true;
//            } else {
//                PlanetSelectionGuiEvents.titan_The_MoonPlanet2OrbitButton.visible = false;
//            }
//
//            // PLANET 2 SPACE STATION BUTTON
//            if (screen.Category == 102) {
//                PlanetSelectionGuiEvents.titan_The_MoonPlanet2SpaceStationButton.visible = true;
//            } else {
//                PlanetSelectionGuiEvents.titan_The_MoonPlanet2SpaceStationButton.visible = false;
//            }

            // BACK BUTTON
            if (screen.Category >= 100 && screen.Category <= 102) {
                PlanetSelectionGuiEvents.backButton.visible = true;
                screen.backButton.visible = false;
            } else {
                PlanetSelectionGuiEvents.backButton.visible = false;
            }
        }
    }

    @SubscribeEvent
    public static void screenInit(ScreenEvent.InitScreenEvent event) {
        if (event.getScreen() instanceof PlanetSelectionGuiWindow) {
            PlanetSelectionGuiWindow screen = (PlanetSelectionGuiWindow) event.getScreen();
            PlanetSelectionGui.GuiContainer menu = screen.getMenu();

            PlanetSelectionGuiEvents.sunFlowerSolarSystemButton = screen.addImageButtonSetCategory(10, (screen.height / 2) + 109 / 2, 70, 20, PlanetSelectionGuiWindow.bbButtonTex, 100, menu.getRocket(), 0, sunFlowerText);
            PlanetSelectionGuiEvents.sunFlowerSolarSystemButton.visible = true;

            PlanetSelectionGuiEvents.sunFlowerPlanet1MenuButton = screen.addImageButtonSetCategory(10, (screen.height / 2) - 24 / 2, 70, 20, PlanetSelectionGuiWindow.bgbButtonTex, 101, menu.getRocket(), 4, sunFlowerText);
            PlanetSelectionGuiEvents.sunFlowerPlanet1MenuButton.visible = false;

            PlanetSelectionGuiEvents.sunFlowerPlanet1Button = screen.addImageButton(10, (screen.height / 2) - 24 / 2, 70, 20, PlanetSelectionGuiWindow.bbButtonTex, BeyondPlanets.PACKET_HANDLER, getNetworkHandler(101), sunFlowerPlanet1Text);
            PlanetSelectionGuiEvents.sunFlowerPlanet1Button.visible = false;

            PlanetSelectionGuiEvents.sunFlowerPlanet1OrbitButton = screen.addImageButton(84, (screen.height / 2) - 24 / 2, 37, 20,PlanetSelectionGuiWindow.bbButtonTex, BeyondPlanets.PACKET_HANDLER, getNetworkHandler(102), sunFlowerPlanet1OrbitText);
            PlanetSelectionGuiEvents.sunFlowerPlanet1OrbitButton.visible = false;

            PlanetSelectionGuiEvents.sunFlowerPlanet1SpaceStationButton = screen.addImageButton(125, (screen.height / 2) - 24 / 2, 75, 20, bgbButtonTex, BeyondPlanets.PACKET_HANDLER, getNetworkHandler(103), sunFlowerPlanet1SpaceStationText);
            PlanetSelectionGuiEvents.sunFlowerPlanet1SpaceStationButton.visible = false;

//            PlanetSelectionGuiEvents.titan_The_MoonPlanet2MenuButton = screen.addImageButtonSetCategory(10, (screen.height / 2) + 21 / 2, 70, 20, PlanetSelectionGuiWindow.bgbButtonTex, 102, menu.getRocket(), 4, titan_The_MoonPlanet2Text);
//            PlanetSelectionGuiEvents.titan_The_MoonPlanet2MenuButton.visible = false;
//
//            PlanetSelectionGuiEvents.titan_The_MoonPlanet2Button = screen.addImageButton(10, (screen.height / 2) - 24 / 2, 70, 20, PlanetSelectionGuiWindow.bbButtonTex, BeyondPlanets.PACKET_HANDLER, getNetworkHandler(104), titan_The_MoonPlanet2Text);
//            PlanetSelectionGuiEvents.titan_The_MoonPlanet2Button.visible = false;
//
//            PlanetSelectionGuiEvents.titan_The_MoonPlanet2OrbitButton = screen.addImageButton(84, (screen.height / 2) - 24 / 2, 37, 20,PlanetSelectionGuiWindow.bbButtonTex, BeyondPlanets.PACKET_HANDLER, getNetworkHandler(105), titan_The_MoonPlanet2OrbitText);
//            PlanetSelectionGuiEvents.titan_The_MoonPlanet2OrbitButton.visible = false;
//
//            PlanetSelectionGuiEvents.titan_The_MoonPlanet2SpaceStationButton = screen.addImageButton(125, (screen.height / 2) - 24 / 2, 75, 20, bgbButtonTex, BeyondPlanets.PACKET_HANDLER, getNetworkHandler(106), titan_The_MoonPlanet2SpaceStationText);
//            PlanetSelectionGuiEvents.titan_The_MoonPlanet2SpaceStationButton.visible = false;

            PlanetSelectionGuiEvents.backButton = screen.addAddonButton(10, (screen.height / 2) - 68 / 2, 70, 20, dbbButtonTex, backTEXT, (p_2130901) -> {
                if(screen.Category == 100){
                    screen.Category = -1;
                }
                if(screen.Category == 101){
                    screen.Category = 100;
                }
                if(screen.Category == 102){
                    screen.Category = 100;
                }
            });
            PlanetSelectionGuiEvents.backButton.visible = false;
        }
    }

    public static PlanetSelectionGuiNetworkHandler getNetworkHandler(int handler) {
        return new PlanetSelectionGuiNetworkHandler(handler);
    }

    public static Component tl(String string) {
        return new TranslatableComponent("gui." + BeyondPlanets.MODID + ".planet_selection." + string);
    }
}