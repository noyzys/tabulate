package dev.nautchkafe.studios.network.sdk.tabulate;

import java.util.function.BiFunction;
import java.util.function.Function;

interface Tabulate {

    Tabulate header(final Function<TabulatePlayer, String> headerComposer);
    Tabulate footer(final Function<TabulatePlayer, String> footerComposer);

    Tabulate column(final int row, final int column,
                    final BiFunction<TabulatePlayer, Integer, String> columnComposer);

    Tabulate applySkin(final Function<TabulatePlayer, TabulateSkinProfile> skinComposer);

    TabulateCoordinator finalizeComposition();

    static Tabulate create() {
        return new TabulateCreator();
    }
}
