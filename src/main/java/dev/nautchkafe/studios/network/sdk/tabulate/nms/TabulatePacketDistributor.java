package dev.nautchkafe.studios.network.sdk.tabulate.nms;

import dev.nautchkafe.studios.network.sdk.tabulate.TabulateContract;
import dev.nautchkafe.studios.network.sdk.tabulate.TabulatePlayer;
import dev.nautchkafe.studios.network.sdk.tabulate.TabulatePosition;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetDisplayObjectivePacket;
import net.minecraft.network.protocol.game.ClientboundSetObjectivePacket;
import net.minecraft.network.protocol.game.ClientboundSetScorePacket;
import net.minecraft.network.protocol.game.ClientboundTabListPacket;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.scores.DisplaySlot;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.ScoreHolder;
import net.minecraft.world.scores.Scoreboard;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;

import java.util.Map;

public final class TabulatePacketDistributor implements TabulateMinecraftServer {

    @Override
    public void distributeTabList(final TabulatePlayer player, final TabulateContract contract) {
        final CraftPlayer craftPlayer = (CraftPlayer) player;

        distributeHeaderFooter(craftPlayer, contract.header(), contract.footer());
        distributeColumns(craftPlayer, contract.meta().columns());
    }

    @Override
    public void clearTabulate(final TabulatePlayer player) {
        final CraftPlayer craftPlayer = (CraftPlayer) player;
        distributeHeaderFooter(craftPlayer, "", "");

        final Scoreboard scoreboard = new Scoreboard();
        final Objective clearObjective = new Objective(
                scoreboard,
                "tablist_display",
                ObjectiveCriteria.DUMMY,
                createTextComponent(""),
                ObjectiveCriteria.RenderType.INTEGER,
                false,
                null);

        final ClientboundSetObjectivePacket removePacket = new ClientboundSetObjectivePacket(clearObjective, ClientboundSetObjectivePacket.METHOD_REMOVE);
        peekConnection(craftPlayer).send(removePacket);
    }

    private void distributeHeaderFooter(final CraftPlayer player,
                                        final String header,
                                        final String footer) {
        final Component headerComponent = createTextComponent(header);
        final Component footerComponent = createTextComponent(footer);

        final ClientboundTabListPacket tabListPacket = new ClientboundTabListPacket(headerComponent, footerComponent);
        peekConnection(player).send(tabListPacket);
    }

    private void distributeColumns(final CraftPlayer player, final Map<TabulatePosition, String> columns) {
        final Scoreboard scoreboard = new Scoreboard();
        final Objective objective = new Objective(
                scoreboard,
                "tablist_display",
                ObjectiveCriteria.DUMMY,
                Component.literal("Tabulate"),
                ObjectiveCriteria.RenderType.INTEGER,
                false,
                null);

        final ClientboundSetObjectivePacket objectivePacket = new ClientboundSetObjectivePacket(objective, ClientboundSetObjectivePacket.METHOD_ADD);
        peekConnection(player).send(objectivePacket);

        final ClientboundSetDisplayObjectivePacket displayPacket = new ClientboundSetDisplayObjectivePacket(DisplaySlot.LIST, objective);
        peekConnection(player).send(displayPacket);

        columns.forEach((position, content) -> {
            final String entry = createColumnEntry(position, content);
            final ScoreHolder scoreHolder = ScoreHolder.forNameOnly(entry);

            final ClientboundSetScorePacket scorePacket = new ClientboundSetScorePacket(
                    scoreHolder.getScoreboardName(),
                    objective.getName(),
                    position.toScore(),
                    null,
                    null);

            peekConnection(player).send(scorePacket);
        });
    }

    private ServerGamePacketListenerImpl peekConnection(final CraftPlayer player) {
        return player.getHandle().connection;
    }

    private String createColumnEntry(final TabulatePosition position, final String content) {
        return String.format("§%x§%x%s", position.row(), position.column(), content);
    }

    private Component createTextComponent(final String content) {
        return Component.literal(content);
    }
}
