package com.therealm18.beyond_planets.world;

import com.therealm18.beyond_planets.BeyondPlanets;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class PlanetsRegistry {
    public static final ResourceKey<Level> sunFlowerPlanet1 = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(BeyondPlanets.MODID, "sun_flower_planet_1"));
    public static final ResourceKey<Level> sunFlowerPlanet1Orbit = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(BeyondPlanets.MODID, "sun_flower_planet_1_orbit"));
//    public static final ResourceKey<Level> titanTheMoonPlanet2 = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(BeyondPlanets.MODID, "titan_the_moon"));
//    public static final ResourceKey<Level> titanTheMoonPlanet2Orbit = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(BeyondPlanets.MODID, "titan_the_moon_orbit"));
}
