package dev.nautchkafe.studios.network.sdk.tabulate;

import com.mojang.authlib.properties.Property;

public record TabulateSkinProfile(
        String textureValue,
        String signature
) {

    public static final TabulateSkinProfile EMPTY = new TabulateSkinProfile("", "");

    public boolean isEmpty() {
        return  (textureValue == null || textureValue.isEmpty() &&
                signature == null || signature.isEmpty());
    }

    public Property toProperty() {
        return new Property(textureValue, signature);
    }
}
