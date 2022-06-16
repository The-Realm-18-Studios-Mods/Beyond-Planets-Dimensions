package com.therealm18studios.beyond_planets_dimensions.world;

import com.therealm18studios.beyond_planets_dimensions.BeyondPlanetsDimensions;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class PlanetsRegistry {
    public static final ResourceKey<Level> pawsHunterPlanet = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(BeyondPlanetsDimensions.MODID, "paws_hunter_planet"));
    public static final ResourceKey<Level> pawsHunterPlanetOrbit = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(BeyondPlanetsDimensions.MODID, "paws_hunter_planet_orbit"));
}
