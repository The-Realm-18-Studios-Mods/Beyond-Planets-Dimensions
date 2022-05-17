package com.therealm18.beyond_planets.world;

import com.therealm18.beyond_planets.BeyondPlanets;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class PlanetsRegistry {
    public static final ResourceKey<Level> sunFlowerPlanet = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(BeyondPlanets.MODID, "sunflower_planet"));
    public static final ResourceKey<Level> sunFlowerPlanetOrbit = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(BeyondPlanets.MODID, "sunflower_planet_orbit"));
    public static final ResourceKey<Level> catTonicPlanet = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(BeyondPlanets.MODID, "cat_tonic_planet"));
    public static final ResourceKey<Level> catTonicPlanetOrbit = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(BeyondPlanets.MODID, "cat_tonic_orbit"));
}
