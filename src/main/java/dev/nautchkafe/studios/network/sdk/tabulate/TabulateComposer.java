package dev.nautchkafe.studios.network.sdk.tabulate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

final class TabulateComposer implements TabulateProcessor {

    private final Map<TabulatePosition, TabulateColumnComposer> columnComposers;

    private Function<TabulatePlayer, String> headerComposer;
    private Function<TabulatePlayer, String> footerComposer;
    private TabulateSkinComposer skinComposer;

    TabulateComposer() {
        this.columnComposers = new ConcurrentHashMap<>();
        skinComposer = TabulateSkinComposer.empty();
    }

    void headerComposer(final Function<TabulatePlayer, String> headerComposer) {
        this.headerComposer = headerComposer;
    }

    void footerComposer(final Function<TabulatePlayer, String> footerComspoer) {
        this.footerComposer = footerComspoer;
    }

    void skinComposer(final TabulateSkinComposer skinComposer) {
        this.skinComposer = skinComposer != null
                ? skinComposer : TabulateSkinComposer.empty();
    }

    void implColumnComposer(final TabulatePosition position,
                            final TabulateColumnComposer composer) {
        if (position.validate()) {
            extracted(position, composer);
        }
    }

    private void extracted(final TabulatePosition position,
                           final TabulateColumnComposer composer) {
        columnComposers.put(position, composer);
    }

    private String composeHeader(final TabulatePlayer player) {
        return headerComposer != null
                ? headerComposer.apply(player) : "";
    }

    private String composeFooter(final TabulatePlayer player) {
        return footerComposer != null
                ? footerComposer.apply(player) : "";
    }

    private TabulateSkinProfile composeSkin(final TabulatePlayer player) {
        return skinComposer.apply(player);
    }

    private TabulateMeta composeColumns(final TabulatePlayer player) {
        final Map<TabulatePosition, String> columns = new ConcurrentHashMap<>();

        columnComposers.forEach((position, composer) -> {
            final String value = composer.compose(
                    player,
                    position.row(),
                    position.column());
        });

        return new TabulateMeta(columns);
    }

    @Override
    public TabulateContract processComposition(final TabulatePlayer player) {
        return new TabulateContract(
                composeHeader(player),
                composeFooter(player),
                composeColumns(player),
                composeSkin(player));
    }
}
