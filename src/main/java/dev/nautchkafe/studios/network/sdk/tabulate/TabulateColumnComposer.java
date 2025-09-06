package dev.nautchkafe.studios.network.sdk.tabulate;

@FunctionalInterface
interface TabulateColumnComposer {

    String compose(final TabulatePlayer player, final int row, final int column);
}
