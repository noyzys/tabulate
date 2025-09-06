package dev.nautchkafe.studios.network.sdk.tabulate;

public record TabulatePosition(
        int row,
        int column
) {

    public boolean validate() {
        return row >= 0 && column >= 0;
    }

    public int toScore() {
        return row * 20 + column;
    }
}
