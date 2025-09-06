package dev.nautchkafe.studios.network.sdk.tabulate;

import dev.nautchkafe.studios.network.sdk.tabulate.nms.TabulatePacketDistributor;

final class TabulateDistributor implements TabulateCoordinator {

    private final TabulateComposer composer;
    private final TabulatePacketDistributor packetDistributor;
    private final TabulateSkinCoordinator skinCoordinator;

    TabulateDistributor(final TabulateComposer composer) {
        this.composer = composer;
        this.packetDistributor = new TabulatePacketDistributor();
        this.skinCoordinator = new TabulateSkinCoordinator();
    }

    @Override
    public void distributeTo(final TabulatePlayer player) {
        final TabulateContract contract = composer.processComposition(player);
        packetDistributor.distributeTabList(player, contract);

        if (contract.skinProfile() != null && !contract.skinProfile().isEmpty()) {
            skinCoordinator.applySkinProfile(player, contract.skinProfile());
        }
    }

    @Override
    public void distributeToAll(final Iterable<TabulatePlayer> players) {
        players.forEach(this::distributeTo);
    }

    @Override
    public void revoke(final TabulatePlayer player) {
        packetDistributor.clearTabulate(player);
    }

    @Override
    public void updateSkin(final TabulatePlayer player, final TabulateSkinProfile skinProfile) {
        skinCoordinator.applySkinProfile(player, skinProfile);
        distributeTo(player);
    }
}
