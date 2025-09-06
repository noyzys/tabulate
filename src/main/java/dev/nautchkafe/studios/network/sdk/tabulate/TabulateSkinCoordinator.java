package dev.nautchkafe.studios.network.sdk.tabulate;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

final class TabulateSkinCoordinator implements TabulateSkinProperty {

    private final Map<UUID, TabulateSkinProfile> skinProfiles;

    TabulateSkinCoordinator() {
        this.skinProfiles = new ConcurrentHashMap<>();
    }

    @Override
    public void applySkinProfile(final TabulatePlayer player, final TabulateSkinProfile skinProfile) {
        if (skinProfile.isEmpty()) {
            return;
        }

        final UUID uniqueId = player.getUniqueId();
        skinProfiles.put(uniqueId, skinProfile);
        toGameProfile(player, skinProfile);
    }

    private void toGameProfile(final TabulatePlayer player, final TabulateSkinProfile skinProfile) {
        final CraftPlayer craftPlayer = (CraftPlayer) player;
        final GameProfile gameProfile = craftPlayer.getProfile();
        final PropertyMap properties = gameProfile.getProperties();

        properties.removeAll("textures");

        final Property property = skinProfile.toProperty();
        properties.put("textures", property);
    }

    @Override
    public TabulateSkinProfile resolveSkin(final UUID uniqueId) {
        return skinProfiles.getOrDefault(uniqueId, TabulateSkinProfile.EMPTY);
    }

    @Override
    public void removeSkinProfile(final UUID uniqueId) {
        skinProfiles.remove(uniqueId);
    }
}
