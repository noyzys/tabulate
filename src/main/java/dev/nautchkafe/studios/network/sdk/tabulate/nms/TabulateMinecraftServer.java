package dev.nautchkafe.studios.network.sdk.tabulate.nms;

import dev.nautchkafe.studios.network.sdk.tabulate.TabulateContract;
import dev.nautchkafe.studios.network.sdk.tabulate.TabulatePlayer;

interface TabulateMinecraftServer {

    void distributeTabList(final TabulatePlayer player, final TabulateContract contract);

    void clearTabulate(final TabulatePlayer player);
}
