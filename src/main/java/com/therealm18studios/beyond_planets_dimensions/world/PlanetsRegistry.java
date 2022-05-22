package com.therealm18studios.beyond_planets_dimensions.world;

import com.therealm18studios.beyond_planets_dimensions.BeyondPlanetsDimensions;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class PlanetsRegistry {
    public static final ResourceKey<Level> sunFlowerPlanet = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(BeyondPlanetsDimensions.MODID, "sunflower_planet"));
    public static final ResourceKey<Level> sunFlowerPlanetOrbit = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(BeyondPlanetsDimensions.MODID, "sunflower_planet_orbit"));
    public static final ResourceKey<Level> catTonicPlanet = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(BeyondPlanetsDimensions.MODID, "cat_tonic_planet"));
    public static final ResourceKey<Level> catTonicPlanetOrbit = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(BeyondPlanetsDimensions.MODID, "cat_tonic_orbit"));
}
