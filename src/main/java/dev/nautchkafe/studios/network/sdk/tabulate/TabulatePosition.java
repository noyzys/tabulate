package dev.nautchkafe.studios.network.sdk.tabulate;

record TabulatePosition(
        int row,
        int column
) {

    boolean validate() {
        return row >= 0 && column >= 0;
    }

    int toScore() {
        return row * 20 + column;
    }
}
