package dev.nautchkafe.studios.network.sdk.tabulate;

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
    }

    private void toGameProfile(final TabulatePlayer player, final TabulateSkinProfile skinProfile) {

    }

    @Override
    public TabulateSkinProfile resolveSkin(final UUID uniqueId) {
        return null;
    }

    @Override
    public void removeSkinProfile(final UUID uniqueId) {

    }
}
