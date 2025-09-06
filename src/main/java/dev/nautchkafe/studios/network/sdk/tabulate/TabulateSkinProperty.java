package dev.nautchkafe.studios.network.sdk.tabulate;

import java.util.UUID;

interface TabulateSkinProperty {

    void applySkinProfile(final TabulatePlayer player, final TabulateSkinProfile skinProfile);

    TabulateSkinProfile resolveSkin(final UUID uniqueId);

    void removeSkinProfile(final UUID uniqueId);
}
