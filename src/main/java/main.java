import com.flowpowered.math.vector.Vector3d;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.effect.particle.ParticleEffect;
import org.spongepowered.api.effect.particle.ParticleOptions;
import org.spongepowered.api.effect.particle.ParticleType;
import org.spongepowered.api.effect.particle.ParticleTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.MoveEntityEvent;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.scoreboard.Scoreboard;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.Color;
import org.spongepowered.api.util.RelativePositions;
import org.spongepowered.api.world.Location;

import java.awt.*;

@Plugin(id = "particles", name = "Particles", version = "1.0", description = "particle plugin", authors = "Brqdford")
public class main {
    @Inject
    Game game;
    @Inject
    Logger logger;
    @Inject
    private PluginContainer container;

    private static main instance;


    @Listener
    public void onPreInit(GameInitializationEvent e) {
        instance = this;
    }

    @Listener
    public void onGameInit(GameInitializationEvent e) {
        logger.info(container.getName() +
                " running (version "
                + container.getVersion().orElse("UNSTABLE")
                + ")");


        CommandSpec commandman = CommandSpec.builder()
                .arguments(
                        GenericArguments.remainingJoinedStrings(Text.of("storm:globe:vortex:fire:music"))
                )
                .executor((CommandSource src, CommandContext args) -> {
                    String particleid = String.valueOf(args.<String>getAll("storm:globe:vortex:fire:music"));
                    Player player = (Player) src;
                    if (src.hasPermission("particles.use")) {
                        if (particleid.toLowerCase().contains("help")) {
                            src.sendMessage(Text.of(TextColors.RED, "/particles <storm:globe:vortex:fire:music...>"));
                        } else if (particleid.toLowerCase().contains("storm")) {
                            Sponge.getCommandManager().process(src, "crazystorm");
                        } else if (particleid.toLowerCase().contains("globe")) {
                            Sponge.getCommandManager().process(src, "crazyglobe");
                        } else if (particleid.toLowerCase().contains("vortex")) {
                            if (particleid.toLowerCase().contains("red")){
                                Sponge.getCommandManager().process(src, "crazyredhelix");
                            }else if (particleid.toLowerCase().contains("yellow")){
                                Sponge.getCommandManager().process(src, "crazyyellowhelix");
                            }else if(particleid.toLowerCase().contains("orange")){
                                Sponge.getCommandManager().process(src, "crazyorangehelix");
                            }else if (particleid.toLowerCase().contains("blue")){
                                Sponge.getCommandManager().process(src, "crazybluehelix");
                            }else if (particleid.toLowerCase().contains("purple")){
                                Sponge.getCommandManager().process(src, "crazypurplehelix");
                            }else if (particleid.toLowerCase().contains("green")){
                                Sponge.getCommandManager().process(src, "crazygreenhelix");
                            }else if (particleid.toLowerCase().contains("white")){
                                Sponge.getCommandManager().process(src, "crazywhitehelix");
                            }else {
                                src.sendMessage(Text.of(TextColors.RED, "/particles vortex <red:yellow:orange:purple:white:green:blue...>"));
                            }
                        } else if (particleid.toLowerCase().contains("fire")) {
                            Sponge.getCommandManager().process(src, "crazyfire");
                        } else if (particleid.toLowerCase().contains("music")) {
                            Sponge.getCommandManager().process(src, "crazynote");
                        } else {
                            src.sendMessage(Text.of(TextColors.RED, "/particles <storm:globe:vortex:fire:music...>"));
                        }
                    }else {
                        src.sendMessage(Text.of(TextColors.RED, "You do not have permission to use this command."));
                    }
                    return CommandResult.success();
                })
                .build();
        Sponge.getCommandManager().register(container, commandman, "particles");
    }
}
