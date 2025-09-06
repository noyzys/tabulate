package dev.nautchkafe.studios.network.sdk.tabulate;

interface TabulateCoordinator {

    void distributeTo(final TabulatePlayer player);

    void distributeToAll(final Iterable<TabulatePlayer> players);

    void revoke(final TabulatePlayer player);

    void updateSkin(final TabulatePlayer player, final TabulateSkinProfile skinProfile);
}
