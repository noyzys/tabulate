package dev.nautchkafe.studios.network.sdk.tabulate;

import java.util.function.Function;

@FunctionalInterface
interface TabulateSkinComposer extends Function<TabulatePlayer, TabulateSkinProfile> {

    static TabulateSkinComposer empty() {
        return player -> TabulateSkinProfile.EMPTY;
    }
}
