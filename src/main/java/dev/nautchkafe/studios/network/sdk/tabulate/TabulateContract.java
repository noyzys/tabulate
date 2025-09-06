package dev.nautchkafe.studios.network.sdk.tabulate;

public record TabulateContract(
        String header,
        String footer,
        TabulateMeta meta,
        TabulateSkinProfile skinProfile
) {

    public boolean hasSkin() {
        return skinProfile != null && !skinProfile.isEmpty();
    }
}
