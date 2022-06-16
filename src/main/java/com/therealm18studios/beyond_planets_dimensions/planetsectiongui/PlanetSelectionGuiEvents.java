package com.therealm18studios.beyond_planets_dimensions.planetsectiongui;

import com.mojang.blaze3d.vertex.PoseStack;
import com.therealm18studios.beyond_planets_dimensions.BeyondPlanetsDimensions;
import com.therealm18studios.beyond_planets_dimensions.network.PlanetSelectionGuiNetworkHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mrscauthd.beyond_earth.events.forge.PlanetSelectionScreenBackgroundRenderEvent;
import net.mrscauthd.beyond_earth.events.forge.PlanetSelectionScreenButtonVisibilityEvent;
import net.mrscauthd.beyond_earth.events.forge.PlanetSelectionScreenInitEvent;
import net.mrscauthd.beyond_earth.guis.buttons.ImageButtonPlacer;
import net.mrscauthd.beyond_earth.guis.helper.ScreenHelper;
import net.mrscauthd.beyond_earth.guis.screens.planetselection.PlanetSelectionScreen;
import net.mrscauthd.beyond_earth.guis.screens.planetselection.helper.CategoryHelper;
import net.mrscauthd.beyond_earth.guis.screens.planetselection.helper.PlanetSelectionScreenHelper;

import java.util.List;

@Mod.EventBusSubscriber(modid = BeyondPlanetsDimensions.MODID, value = Dist.CLIENT)
public class PlanetSelectionGuiEvents {

    /** TEXT */
    public static final Component EXPANDED_SYSTEM_TEXT = tl("expanded_system");
    public static final Component PAWS_HUNTER_PLANET_1_TEXT = tl("paws_hunter_planet");

    /** BUTTONS */
    private static ImageButtonPlacer expandedSystemButton;
    private static ImageButtonPlacer expandedPlanet1SectionButton;
    private static ImageButtonPlacer backButton;
    private static ImageButtonPlacer pawsHunterSelectionButton;
    private static ImageButtonPlacer pawsHunterOrbitButton;
    private static ImageButtonPlacer pawsHunterSpaceStationButton;

    private static CategoryHelper category;

    private static float rotationPawsHunter;

    /** TEXTURES */
    public static final ResourceLocation PAWS_HUNTER_PLANET_1_TEXTURE = new ResourceLocation(BeyondPlanetsDimensions.MODID, "textures/sky/gui/extended/planets/1.png");

    @SubscribeEvent
    public static void buttonVisibilityPre(PlanetSelectionScreenButtonVisibilityEvent.Pre event) {
        PlanetSelectionScreen screen = (PlanetSelectionScreen) event.getScreen();

        /** SET THE MAIN (BEYOND EARTH) CATEGORY TO -1 */
        if (PlanetSelectionScreenHelper.categoryRange(category.get(), 1,2)) {
            screen.category.set(-1);
        }
    }

    @SubscribeEvent
    public static void screenRender(PlanetSelectionScreenButtonVisibilityEvent.Post event) {
        PlanetSelectionScreen screen = (PlanetSelectionScreen) event.getScreen();

        /** BACK BUTTON */
        screen.visibleButton(backButton, PlanetSelectionScreenHelper.categoryRange(category.get(), 1, 2));

        screen.visibleButton(expandedSystemButton, screen.category.get() == 0);
        screen.visibleButton(expandedPlanet1SectionButton, category.get() == 1);
        screen.visibleButton(pawsHunterSelectionButton, category.get() == 2);
        screen.visibleButton(pawsHunterOrbitButton, category.get() == 2);
        screen.visibleButton(pawsHunterSpaceStationButton, category.get() == 2);
    }

    @SubscribeEvent
    public static void screenBackgroundRender(PlanetSelectionScreenBackgroundRenderEvent.Post event){
        PlanetSelectionScreen screen = (PlanetSelectionScreen) event.getScreen();
        PoseStack ms = event.getPoseStack();
        float partialTicks = event.getPartialTicks();

        PlanetSelectionScreenHelper.enableRenderSystem();

        /** ROTATIONS FOR PLANETS */
        rotationPawsHunter = (rotationPawsHunter + partialTicks * 0.4f) % 360;


        /** SOLAR SYSTEM */
        if (PlanetSelectionScreenHelper.categoryRange(category.get(), 1, 3)) {
            PlanetSelectionScreenHelper.addCircle(screen.width / 2, screen.height / 2, 23.0, 180);
            PlanetSelectionScreenHelper.addCircle(screen.width / 2, screen.height / 2, 46.0, 180);
        }

        /** PLANETS */
        if (PlanetSelectionScreenHelper.categoryRange(category.get(), 1, 2)) {
            PlanetSelectionScreenHelper.addRotatedObject(screen, ms, PAWS_HUNTER_PLANET_1_TEXTURE, -20.5F, -20.5F, 10, 10, rotationPawsHunter);
        }

        /** SMALL MENU RENDERER */
        if (PlanetSelectionScreenHelper.categoryRange(category.get(), 1, 1)) {
            ScreenHelper.drawTexture(ms, 0, (screen.height / 2) - 177 / 2, 105, 177, PlanetSelectionScreen.SMALL_MENU_LIST);
        }

        /** LARGE MENU RENDERER */
        if (PlanetSelectionScreenHelper.categoryRange(category.get(), 2, 2)) {
            ScreenHelper.drawTexture(ms, 0, (screen.height / 2) - 177 / 2, 215, 177, PlanetSelectionScreen.LARGE_MENU_TEXTURE);
        }

        if (PlanetSelectionScreenHelper.categoryRange(category.get(), 3, 3)) {
            ScreenHelper.drawTexture(ms, 0, (screen.height / 2) - 177 / 2, 215, 177, PlanetSelectionScreen.LARGE_MENU_TEXTURE);
        }

        PlanetSelectionScreenHelper.disableRenderSystem();
    }

    @SubscribeEvent
    public static void screenInit(PlanetSelectionScreenInitEvent.Post event) {
        PlanetSelectionScreen screen = (PlanetSelectionScreen) event.getScreen();

        category = new CategoryHelper();
        rotationPawsHunter = 90;

        /** MAIN CATEGORY BUTTON 1 */
        expandedSystemButton = PlanetSelectionScreenHelper.addCategoryButton(screen, category, 10, 1, 70, 20, 1, true, false, ImageButtonPlacer.Types.MILKY_WAY_CATEGORY, List.of(EXPANDED_SYSTEM_TEXT.getString()), screen.BLUE_BUTTON_TEXTURE, screen.BLUE_LIGHT_BUTTON_TEXTURE, EXPANDED_SYSTEM_TEXT);

        /** BACK BUTTON */
        backButton = PlanetSelectionScreenHelper.addBackButton(screen, 10, 1, 70, 20, false, PlanetSelectionScreen.DARK_BLUE_BUTTON_TEXTURE, PlanetSelectionScreen.DARK_BLUE_LIGHT_BUTTON_TEXTURE, PlanetSelectionScreen.BACK_TEXT, (onPress) -> {
            if (category.get() == 1) {
                category.set(0);
                screen.category.set(0);
                screen.scrollIndex = 0;
                screen.updateButtonVisibility();
            } else if (PlanetSelectionScreenHelper.categoryRange(category.get(), 2, 3)) {
                category.set(1);
                screen.scrollIndex = 0;
                screen.updateButtonVisibility();
            }
        });

        /** Expanded SOLAR SYSTEM CATEGORY */
        expandedPlanet1SectionButton = PlanetSelectionScreenHelper.addCategoryButton(screen, category, 10, 1, 70, 20, 2, screen.checkTier(4), false, ImageButtonPlacer.Types.SOLAR_SYSTEM_CATEGORY, List.of(PAWS_HUNTER_PLANET_1_TEXT.getString(), screen.ROCKET_TIER_4_TEXT.getString()), screen.RED_BUTTON_TEXTURE, screen.RED_LIGHT_BUTTON_TEXTURE, PAWS_HUNTER_PLANET_1_TEXT);

        /** PAWS HUNTER TELEPORT BUTTONS */
        pawsHunterSelectionButton = PlanetSelectionScreenHelper.addHandlerButton(screen, 10, 1, 70, 20, true, false, true, BeyondPlanetsDimensions.PACKET_HANDLER, getNetworkHandler(0), ImageButtonPlacer.Types.PLANET_CATEGORY, List.of(screen.PLANET_TEXT.getString(), "9.807 m/s", "a" + screen.OXYGEN_TRUE_TEXT.getString(), "a" + "20"), screen.BLUE_BUTTON_TEXTURE, screen.BLUE_LIGHT_BUTTON_TEXTURE, PAWS_HUNTER_PLANET_1_TEXT);

        /** PAWS HUNTER ORBIT TELEPORT BUTTONS */
        pawsHunterOrbitButton = PlanetSelectionScreenHelper.addHandlerButton(screen, 84, 2, 37, 20, true, false, true, BeyondPlanetsDimensions.PACKET_HANDLER, getNetworkHandler(1), ImageButtonPlacer.Types.PLANET_CATEGORY, List.of(screen.ORBIT_TEXT.getString(), screen.NO_GRAVITY_TEXT.getString(), "c" + screen.OXYGEN_FALSE_TEXT.getString(), "c" + "-270"), screen.SMALL_BLUE_BUTTON_TEXTURE, screen.SMALL_BLUE_LIGHT_BUTTON_TEXTURE, screen.ORBIT_TEXT);

        /** PAWS HUNTER SPACE STATION TELEPORT BUTTONS */
        pawsHunterSpaceStationButton = PlanetSelectionScreenHelper.addHandlerButton(screen, 125, 3, 75, 20, screen.spaceStationItemList, false, true, BeyondPlanetsDimensions.PACKET_HANDLER, getNetworkHandler(2), ImageButtonPlacer.Types.PLANET_SPACE_STATION_CATEGORY, List.of(screen.ORBIT_TEXT.getString(), screen.NO_GRAVITY_TEXT.getString(), "c" + screen.OXYGEN_FALSE_TEXT.getString(), "c" + "-270"), screen.LARGE_RED_BUTTON_TEXTURE, screen.LARGE_RED_LIGHT_BUTTON_TEXTURE, screen.SPACE_STATION_TEXT);
    }

    public static PlanetSelectionGuiNetworkHandler getNetworkHandler(int handler) {
        return new PlanetSelectionGuiNetworkHandler(handler);
    }

    public static Component tl(String string) {
        return Component.translatable("gui." + BeyondPlanetsDimensions.MODID + ".planet_selection." + string);
    }
}