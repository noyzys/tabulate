package dev.nautchkafe.studios.network.sdk.tabulate;

record TabulateContract(
        String header,
        String footer,
        TabulateMeta meta,
        TabulateSkinProfile skinProfile
) {

    boolean hasSkin() {
        return skinProfile != null && !skinProfile.isEmpty();
    }
}
