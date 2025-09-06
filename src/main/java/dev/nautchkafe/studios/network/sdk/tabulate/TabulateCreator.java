package dev.nautchkafe.studios.network.sdk.tabulate;

import java.util.function.BiFunction;
import java.util.function.Function;

final class TabulateCreator implements Tabulate {

    private final TabulateComposer composer;

    private Function<TabulatePlayer, String> headerComposer;
    private Function<TabulatePlayer, String> footerComposer;
    private Function<TabulatePlayer, TabulateSkinProfile> skinComposer;

    TabulateCreator() {
        this.composer = new TabulateComposer();
    }

    @Override
    public Tabulate header(final Function<TabulatePlayer, String> headerComposer) {
        this.headerComposer = headerComposer;
        return this;
    }

    @Override
    public Tabulate footer(final Function<TabulatePlayer, String> footerComposer) {
        this.footerComposer = footerComposer;
        return this;
    }

    @Override
    public Tabulate column(final int row, final int column,
                           final BiFunction<TabulatePlayer, Integer, String> columnComposer) {
        composer.implColumnComposer(new TabulatePosition(row, column),
                (player, rows, columns) -> columnComposer.apply(player, columns));

        return this;
    }

    @Override
    public Tabulate applySkin(final Function<TabulatePlayer, TabulateSkinProfile> skinComposer) {
        this.skinComposer = skinComposer;
        return this;
    }

    @Override
    public TabulateCoordinator finalizeComposition() {
        composer.headerComposer(headerComposer);
        composer.footerComposer(footerComposer);
        composer.skinComposer(player -> skinComposer.apply(player));

        return new TabulateDistributor(composer);
    }
}
