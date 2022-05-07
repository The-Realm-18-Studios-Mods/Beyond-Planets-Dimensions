package com.therealm18.beyond_planets.planetsectiongui;

import com.mojang.blaze3d.vertex.PoseStack;
import com.therealm18.beyond_planets.BeyondPlanets;
import com.therealm18.beyond_planets.network.PlanetSelectionGuiNetworkHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.events.forgeevents.PlanetSelectionGuiBackgroundRenderEvent;
import net.mrscauthd.beyond_earth.events.forgeevents.PlanetSelectionGuiButtonVisibilityEvent;
import net.mrscauthd.beyond_earth.events.forgeevents.PlanetSelectionGuiInitEvent;
import net.mrscauthd.beyond_earth.events.forgeevents.PlanetSelectionGuiRenderEvent;
import net.mrscauthd.beyond_earth.guis.helper.ImageButtonPlacer;
import net.mrscauthd.beyond_earth.guis.screens.planetselection.PlanetSelectionGuiWindow;
import net.mrscauthd.beyond_earth.guis.screens.planetselection.helper.CategoryHelper;
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

    private static CategoryHelper category;

    private static float rotationSunflower;

    /** TEXTURES */
    public static final ResourceLocation SUNFLOWER_PLANET_1_TEX = new ResourceLocation(BeyondPlanets.MODID, "textures/sky/gui/extended/planets/1.png");

    @SubscribeEvent
    public static void buttonVisibilityPre(PlanetSelectionGuiButtonVisibilityEvent.Pre event) {
        PlanetSelectionGuiWindow screen = (PlanetSelectionGuiWindow) event.getScreen();

        /** SET THE MAIN (BEYOND EARTH) CATEGORY TO -1 */
        if (PlanetSelectionGuiHelper.categoryRange(category.get(), 1,2)) {
            screen.category.set(-1);
        }
    }

    @SubscribeEvent
    public static void screenRender(PlanetSelectionGuiButtonVisibilityEvent.Post event) {
        PlanetSelectionGuiWindow screen = (PlanetSelectionGuiWindow) event.getScreen();

        /** BACK BUTTON */
        screen.visibleButton(backButton, PlanetSelectionGuiHelper.categoryRange(category.get(), 1, 2));

        screen.visibleButton(expandedSystemButton, screen.category.get() == 0);
        screen.visibleButton(expandedPlanet1SectionButton, category.get() == 1);
        screen.visibleButton(sunflowerSelectionButton, category.get() == 2);
        screen.visibleButton(sunflowerOrbitButton, category.get() == 2);
        screen.visibleButton(sunflowerSpaceStationButton, category.get() == 2);
    }

    @SubscribeEvent
    public static void screenBackgroundRender(PlanetSelectionGuiBackgroundRenderEvent.Post event){
        PlanetSelectionGuiWindow screen = (PlanetSelectionGuiWindow) event.getScreen();
        PoseStack ms = event.getPoseStack();
        float partialTicks = event.getPartialTicks();

        PlanetSelectionGuiHelper.enableRenderSystem();

        /** ROTATIONS FOR PLANETS */
        rotationSunflower = (rotationSunflower + partialTicks * 0.4f) % 360;


        /** SOLAR SYSTEM */
        if (PlanetSelectionGuiHelper.categoryRange(category.get(), 1, 2)) {
            PlanetSelectionGuiHelper.addCircle(screen.width / 2, screen.height / 2, 23.0, 180);
        }

        /** PLANETS */
        if (PlanetSelectionGuiHelper.categoryRange(category.get(), 1, 2)) {
            PlanetSelectionGuiHelper.addRotatedObject(screen, ms, SUNFLOWER_PLANET_1_TEX, -20.5F, -20.5F, 10, 10, rotationSunflower);
        }

        /** SMALL MENU RENDERER */
        if (PlanetSelectionGuiHelper.categoryRange(category.get(), 1, 1)) {
            PlanetSelectionGuiHelper.addTexture(ms, 0, (screen.height / 2) - 177 / 2, 105, 177, PlanetSelectionGuiWindow.SMALL_MENU_LIST);
        }

        /** LARGE MENU RENDERER */
        if (PlanetSelectionGuiHelper.categoryRange(category.get(), 2, 2)) {
            PlanetSelectionGuiHelper.addTexture(ms, 0, (screen.height / 2) - 177 / 2, 215, 177, PlanetSelectionGuiWindow.LARGE_MENU_TEXTURE);
        }

        PlanetSelectionGuiHelper.disableRenderSystem();
    }

    @SubscribeEvent
    public static void screenInit(PlanetSelectionGuiInitEvent.Post event) {
        PlanetSelectionGuiWindow screen = (PlanetSelectionGuiWindow) event.getScreen();

        category = new CategoryHelper();
        rotationSunflower = 90;

        /** MAIN CATEGORY BUTTON 1 */
        expandedSystemButton = PlanetSelectionGuiHelper.addCategoryButton(screen, category, 10, 1, 70, 20, 1, true, ImageButtonPlacer.Types.MILKY_WAY_CATEGORY, List.of(EXPANDED_SYSTEM_TEXT.getString()), screen.BLUE_BUTTON_TEXTURE, screen.BLUE_LIGHT_BUTTON_TEXTURE, EXPANDED_SYSTEM_TEXT);
        screen.visibleButton(expandedSystemButton, false);

        /** BACK BUTTON */
        backButton = PlanetSelectionGuiHelper.addBackButton(screen, 10, 1, 70, 20, PlanetSelectionGuiWindow.DARK_BLUE_BUTTON_TEXTURE, PlanetSelectionGuiWindow.DARK_BLUE_LIGHT_BUTTON_TEXTURE, PlanetSelectionGuiWindow.BACK_TEXT, (onPress) -> {
            if (category.get() == 1) {
                category.set(0);
                screen.category.set(0);
                screen.scrollIndex = 0;
                screen.updateButtonVisibility();
            } else if (PlanetSelectionGuiHelper.categoryRange(category.get(), 2, 3)) {
                category.set(1);
                screen.scrollIndex = 0;
                screen.updateButtonVisibility();
            }
        });
        screen.visibleButton(backButton, false);

        /** Expanded SOLAR SYSTEM CATEGORY */
        expandedPlanet1SectionButton = PlanetSelectionGuiHelper.addCategoryButton(screen, category, 10, 1, 70, 20, 2, screen.checkTier(4), ImageButtonPlacer.Types.SOLAR_SYSTEM_CATEGORY, List.of(SUNFLOWER_PLANET_TEXT.getString(), screen.ROCKET_TIER_4_TEXT.getString()), screen.RED_BUTTON_TEXTURE, screen.RED_LIGHT_BUTTON_TEXTURE, SUNFLOWER_PLANET_TEXT);
        screen.visibleButton(expandedPlanet1SectionButton, false);

        /** SUNFLOWER TELEPORT BUTTONS */
        sunflowerSelectionButton = PlanetSelectionGuiHelper.addHandlerButton(screen, 10, 1, 70, 20, true, BeyondPlanets.PACKET_HANDLER, getNetworkHandler(1), ImageButtonPlacer.Types.PLANET_CATEGORY, List.of(screen.PLANET_TEXT.getString(), "9.807 m/s", "a" + screen.OXYGEN_TRUE_TEXT.getString(), "a" + "20"), screen.BLUE_BUTTON_TEXTURE, screen.BLUE_LIGHT_BUTTON_TEXTURE, SUNFLOWER_PLANET_TEXT);
        screen.visibleButton(sunflowerSelectionButton, false);

        /** SUNFLOWER ORBIT TELEPORT BUTTONS */
        sunflowerOrbitButton = PlanetSelectionGuiHelper.addHandlerButton(screen, 84, 2, 37, 20, true, BeyondPlanets.PACKET_HANDLER, getNetworkHandler(2), ImageButtonPlacer.Types.PLANET_CATEGORY, List.of(screen.ORBIT_TEXT.getString(), screen.NO_GRAVITY_TEXT.getString(), "c" + screen.OXYGEN_FALSE_TEXT.getString(), "c" + "-270"), screen.SMALL_BLUE_BUTTON_TEXTURE, screen.SMALL_BLUE_LIGHT_BUTTON_TEXTURE, screen.ORBIT_TEXT);
        screen.visibleButton(sunflowerOrbitButton, false);

        /** SUNFLOWER SPACE STATION TELEPORT BUTTONS */
        sunflowerSpaceStationButton = PlanetSelectionGuiHelper.addHandlerButton(screen, 125, 3, 75, 20, screen.spaceStationItemList, BeyondPlanets.PACKET_HANDLER, getNetworkHandler(3), ImageButtonPlacer.Types.PLANET_SPACE_STATION_CATEGORY, List.of(screen.ORBIT_TEXT.getString(), screen.NO_GRAVITY_TEXT.getString(), "c" + screen.OXYGEN_FALSE_TEXT.getString(), "c" + "-270"), screen.LARGE_RED_BUTTON_TEXTURE, screen.LARGE_RED_LIGHT_BUTTON_TEXTURE, screen.SPACE_STATION_TEXT);
        screen.visibleButton(sunflowerSpaceStationButton, false);
    }

    public static PlanetSelectionGuiNetworkHandler getNetworkHandler(int handler) {
        return new PlanetSelectionGuiNetworkHandler(handler);
    }

    public static Component tl(String string) {
        return new TranslatableComponent("gui." + BeyondPlanets.MODID + ".planet_selection." + string);
    }
}