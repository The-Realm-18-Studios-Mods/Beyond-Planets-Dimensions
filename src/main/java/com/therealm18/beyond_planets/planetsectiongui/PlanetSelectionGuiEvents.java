package com.therealm18.beyond_planets.planetsectiongui;

import com.mojang.blaze3d.vertex.PoseStack;
import com.therealm18.beyond_planets.BeyondPlanets;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ContainerScreenEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.guis.helper.ImageButtonPlacer;
import net.mrscauthd.beyond_earth.guis.screens.planetselection.PlanetSelectionGuiWindow;
import net.mrscauthd.beyond_earth.guis.screens.planetselection.helper.PlanetSelectionGuiHelper;

import java.util.List;

@Mod.EventBusSubscriber(modid = BeyondPlanets.MODID, value = Dist.CLIENT)
public class PlanetSelectionGuiEvents {

    /** TEXT */
    public static final Component EXPANDED_SYSTEM_TEXT = tl("expanded_system");
    public static final Component SUNFLOWER_PLANET_TEXT = tl("sunflower_planet");

    /** BUTTONS */
    private static ImageButtonPlacer expandedSystemButton;
    private static ImageButtonPlacer expandedPlanet1SectionButton;
    private static ImageButtonPlacer sunflowerSelectionButton;
    private static ImageButtonPlacer sunflowerOrbitButton;
    private static ImageButtonPlacer sunflowerSpaceStationButton;
    private static ImageButtonPlacer backButton;


    private static int category = -1;

    private static boolean buttonNotNull = false;

    private static boolean spaceStationItemList = true;

    /** TEXTURES */
    public static final ResourceLocation SUN_FLOWER_PLANET_1_TEX = new ResourceLocation(BeyondPlanets.MODID, "textures/sky/gui/extended/planets/1.png");
//    public static final ResourceLocation TITAN_THE_MOON_PLANET_2_TEX = new ResourceLocation(BeyondPlanets.MODID, "textures/sky/gui/sunflower/sunflower/planet/2.png");

    @SubscribeEvent
    public static void screenBackgroundRender(ContainerScreenEvent.DrawBackground event){
        if (event.getContainerScreen() instanceof PlanetSelectionGuiWindow) {
            PlanetSelectionGuiWindow screen = (PlanetSelectionGuiWindow) event.getContainerScreen();
            PoseStack ms = event.getPoseStack();

            PlanetSelectionGuiHelper.enableRenderSystem();

            /** SOLAR SYSTEM */
            if (PlanetSelectionGuiHelper.categoryRange(category, 1, 2)) {
                PlanetSelectionGuiHelper.addTexture(ms, (screen.width - 185) / 2, (screen.height - 185) / 2, 185, 185, PlanetSelectionGuiWindow.PROXIMA_CENTAURI_SOLAR_SYSTEM_TEXTURE);
            }

            /** PLANETS */
            if (PlanetSelectionGuiHelper.categoryRange(category, 1, 2)) {
                PlanetSelectionGuiHelper.addRotatedObject(screen, ms, SUN_FLOWER_PLANET_1_TEX, -20.5F, -20.5F, 10, 10, screen.rotationEarth);
            }

            /** SMALL MENU RENDERER */
            if (PlanetSelectionGuiHelper.categoryRange(category, 0, 1) || PlanetSelectionGuiHelper.categoryRange(category, 6, 6)) {
                PlanetSelectionGuiHelper.addTexture(ms, 0, (screen.height / 2) - 177 / 2, 105, 177, PlanetSelectionGuiWindow.SMALL_MENU_LIST);
            }

            /** LARGE MENU RENDERER */
            if (PlanetSelectionGuiHelper.categoryRange(category, 2, 5)  || PlanetSelectionGuiHelper.categoryRange(category, 7, 7)) {
                PlanetSelectionGuiHelper.addTexture(ms, 0, (screen.height / 2) - 177 / 2, 215, 177, PlanetSelectionGuiWindow.LARGE_MENU_TEXTURE);
            }

            PlanetSelectionGuiHelper.disableRenderSystem();
        }
    }

    @SubscribeEvent
    public static void screenRenderer(ScreenEvent.DrawScreenEvent event) {
        if (event.getScreen() instanceof PlanetSelectionGuiWindow) {
            PlanetSelectionGuiWindow screen = (PlanetSelectionGuiWindow) event.getScreen();

            /** SET THE MOD CATEGORY TO -1 */
            if (PlanetSelectionGuiHelper.categoryRange(category, 1,2)){
                screen.category = -1;
            }

            if (!buttonNotNull) {
                return;
            }

            screen.visibleButton(expandedSystemButton, screen.category == 0);
            screen.visibleButton(expandedPlanet1SectionButton, category == 1);
            screen.visibleButton(sunflowerSelectionButton, category == 2);

            // BACK BUTTON
            if (category >= 1 && category <=2) {
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

            expandedSystemButton = PlanetSelectionGuiHelper.addCategoryButton(screen,10, -24, 70, 20, 1, true, ImageButtonPlacer.Types.MILKY_WAY_CATEGORY, List.of(EXPANDED_SYSTEM_TEXT.getString()), PlanetSelectionGuiWindow.BLUE_BUTTON_TEXTURE, PlanetSelectionGuiWindow.BLUE_LIGHT_BUTTON_TEXTURE, EXPANDED_SYSTEM_TEXT);
            expandedSystemButton.visible = true;

            expandedPlanet1SectionButton = PlanetSelectionGuiHelper.addCategoryButton(screen, 10, -24, 70, 20, 2, screen.checkTier(4), ImageButtonPlacer.Types.SOLAR_SYSTEM_CATEGORY, List.of(EXPANDED_SYSTEM_TEXT.getString(), PlanetSelectionGuiWindow.ROCKET_TIER_4_TEXT.getString()), PlanetSelectionGuiWindow.RED_BUTTON_TEXTURE, PlanetSelectionGuiWindow.RED_LIGHT_BUTTON_TEXTURE, SUNFLOWER_PLANET_TEXT);
            expandedPlanet1SectionButton.visible = false;

            sunflowerSelectionButton = PlanetSelectionGuiHelper.addHandlerButton(screen, 10, -24, 70, 20, true, BeyondPlanets.PACKET_HANDLER, PlanetSelectionGuiHelper.getNetworkHandler(1), ImageButtonPlacer.Types.PLANET_CATEGORY, List.of(PlanetSelectionGuiWindow.PLANET_TEXT.getString(), "9.807 m/s", "a" + PlanetSelectionGuiWindow.OXYGEN_TRUE_TEXT.getString(), "a" + "14"), PlanetSelectionGuiWindow.BLUE_BUTTON_TEXTURE, PlanetSelectionGuiWindow.BLUE_LIGHT_BUTTON_TEXTURE, SUNFLOWER_PLANET_TEXT);
            sunflowerSelectionButton.visible = false;

            sunflowerOrbitButton = PlanetSelectionGuiHelper.addHandlerButton(screen, 84, -24, 37, 20, true, BeyondPlanets.PACKET_HANDLER, PlanetSelectionGuiHelper.getNetworkHandler(2), ImageButtonPlacer.Types.PLANET_CATEGORY, List.of(PlanetSelectionGuiWindow.ORBIT_TEXT.getString(), PlanetSelectionGuiWindow.NO_GRAVITY_TEXT.getString(), "c" + PlanetSelectionGuiWindow.OXYGEN_FALSE_TEXT.getString(), "c" + "-270"), PlanetSelectionGuiWindow.SMALL_BLUE_BUTTON_TEXTURE, PlanetSelectionGuiWindow.SMALL_BLUE_LIGHT_BUTTON_TEXTURE, PlanetSelectionGuiWindow.ORBIT_TEXT);
            sunflowerOrbitButton.visible = false;

            sunflowerSpaceStationButton = PlanetSelectionGuiHelper.addHandlerButton(screen, 125, -24, 75, 20, spaceStationItemList, BeyondPlanets.PACKET_HANDLER, PlanetSelectionGuiHelper.getNetworkHandler(3), ImageButtonPlacer.Types.PLANET_SPACE_STATION_CATEGORY, List.of(PlanetSelectionGuiWindow.ORBIT_TEXT.getString(), PlanetSelectionGuiWindow.NO_GRAVITY_TEXT.getString(), "c" + PlanetSelectionGuiWindow.OXYGEN_FALSE_TEXT.getString(), "c" + "-270"), PlanetSelectionGuiWindow.LARGE_RED_BUTTON_TEXTURE, PlanetSelectionGuiWindow.LARGE_RED_LIGHT_BUTTON_TEXTURE, PlanetSelectionGuiWindow.SPACE_STATION_TEXT);
            sunflowerSpaceStationButton.visible = false;

            backButton = PlanetSelectionGuiHelper.addBackButton(screen, 10, -68, 70, 20, PlanetSelectionGuiWindow.DARK_BLUE_BUTTON_TEXTURE, PlanetSelectionGuiWindow.DARK_BLUE_LIGHT_BUTTON_TEXTURE, PlanetSelectionGuiWindow.BACK_TEXT, (onPress) -> {
                if (category == 1) {
                    category = 0;
                }
                else if (PlanetSelectionGuiHelper.categoryRange(category, 2, 3)) {
                    category = 1;
                }
            });
            backButton.visible = false;

            buttonNotNull = true;
        }
    }

    public static Component tl(String string) {
        return new TranslatableComponent("gui." + BeyondPlanets.MODID + ".planet_selection." + string);
    }
}