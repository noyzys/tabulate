package dev.nautchkafe.studios.network.sdk.tabulate;

import com.mojang.authlib.properties.Property;

public record TabulateSkinProfile(
        String textureValue,
        String signature
) {

    public static final TabulateSkinProfile EMPTY = new TabulateSkinProfile("", "");
    public static final TabulateSkinProfile NOYZYS_PROFILE = new TabulateSkinProfile(
            "ewogICJ0aW1lc3RhbXAiIDogMTY5MjEwOTMyODY3NSwKICAicHJvZmlsZUlkIiA6ICI3NzdlODZhZDUwMGY0MWI3YjBkOWE2ZTE0Y2FjNjY3ZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJub3l6eXMiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjAwNDMzNDM1NjliMWZmZjI3NDA5Y2I0ZTRlMTJiYmU0MTY0ODYyMTU4ZjZlNjI2YTVlMDUxMjhlNWMxNzRkIgogICAgfQogIH0KfQ==",
            "IDYFTPTS5tabLgRtWD4KgspIFmlG9Ueoxv7xpq/fUFuOMiSYBgH4m2pu7+kmpvraAf8UM/0gRL9vrzQwwbikIwTa2jZ8SH7kV2GT3egwQ39K2R+oGNsB8Bjlj381gI/g92beoF3ZKfjZDa0N2JCX2vDhCFHK5JzFk3v0U53kK7h6DvJPD0ucT5y0uSDs665mn8BquNFuH/Nfw1JR20z5PLStkrDpz8/M2uI+jQsexoHXAwphLCRD7IebgBgVJ5R72nTLvdjlBYjCi7F/L3hE3gV6rRF2feshHBo91ArKhqC0mLS5a9+98WxTHpebHC+EB9oe1su6Svz7TlWw0m5tblDquxXgpwTm/r0otjI9hp7MFoUtht+jTSL3I/sKCKRgiWRBnJvu/aDsOgeNjQkwZYyb8lphyecR+j1VzmOsMOmET+143NQowbufiv+TvGA974+6AGBVxm9kxISkjVMhezkVoEF0rdcB7gHoSgmo9jyKnVkSUt3LncWiT22ISzAZbwB7XE1IFHXQQBd7R2Ha+98MQ2y5k8IVSsgCLGwNxD1R+ZUziJWxYKePjwuyvZrdHWFk4tdObnlH9Lx0p0qMOVwKD9Bvk0R1mV/Mr3c4CXbb3F4HcUpHv3rtshiluz/m98t7CcdZu/1ZYqLz2N62wBpnfIG3XgZJL16zhmUhO0Y=");

    public boolean isEmpty() {
        return (textureValue == null || textureValue.isEmpty() &&
                signature == null || signature.isEmpty());
    }

    public Property toProperty() {
        return new Property(textureValue, signature);
    }
}
