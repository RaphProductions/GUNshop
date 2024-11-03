package fr.raphm.gunshop;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import fr.raphm.gunshop.core.GunShopState;
import fr.raphm.gunshop.inv.GunShopMainInventory;
import fr.raphm.gunshop.inv.GunShopSellPromptInventory;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import io.papermc.paper.registry.RegistryKey;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class GunShopPlugin extends JavaPlugin implements Listener {

    GunShopState state;

    public GunShopState getState() { return state; }

    public int gunShopCommandExecuted(CommandContext<CommandSourceStack> ctx) {
        CommandSourceStack source = ctx.getSource();

        // Check if the sender is a player
        if (source.getExecutor() instanceof Player player) {
            GunShopMainInventory myInventory = new GunShopMainInventory(this);
            player.openInventory(myInventory.getInventory());
            return Command.SINGLE_SUCCESS;
        } else {
            source.getSender().sendMessage("This command can only be executed by a player.");
            return 0; // Indicating command did not execute successfully
        }
    }

    public int sellCommandExecuted(CommandContext<CommandSourceStack> ctx) {
        CommandSourceStack source = ctx.getSource();

        // Check if the sender is a player
        if (source.getExecutor() instanceof Player player) {
            GunShopMainInventory myInventory = new GunShopMainInventory(this);
            player.openInventory(myInventory.getInventory());
            return Command.SINGLE_SUCCESS;
        } else {
            source.getSender().sendMessage("This command can only be executed by a player.");
            return 0; // Indicating command did not execute successfully
        }
    }

    public void brigadierInit() {
        LifecycleEventManager<Plugin> manager = this.getLifecycleManager();
        manager.registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            final Commands commands = event.registrar();
            commands.register(
                    Commands.literal("gunshop")
                            .executes(this::gunShopCommandExecuted)
                            .build(),
                    "some bukkit help description string",
                    List.of("shop", "guns", "gs", "gshop")
            );

            commands.register(
                    Commands.literal("sell")
                            .executes(this::sellCommandExecuted)
                            .build(),
                    "some bukkit help description string",
                    List.of("shop", "guns", "gs", "gshop")
            );

            commands.register(
                    Commands.literal("sell_cheat")
                            .then(
                                    Commands.argument("item", ArgumentTypes.resource(RegistryKey.ITEM))
                                            .then(
                                                    Commands.argument("count", IntegerArgumentType.integer())
                                                            .then(
                                                                    Commands.argument("price", IntegerArgumentType.integer())
                                                                            .executes(ctx -> {
                                                                                ctx.getSource().getSender().sendPlainMessage(
                                                                                        ctx.getArgument("item", ItemType.class).getKey().toString()
                                                                                );
                                                                                return Command.SINGLE_SUCCESS;
                                                                            })
                                                            )
                                            )
                            ).build()
            );
        });
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);

        getLogger().info("GUNshop v0.1 (BETA)");
        getLogger().info("Made by Raph. for the Open OSDev Minecraft server.");

        getLogger().info("Initializing Brigadier commands.");
        brigadierInit();

        getLogger().info("Initializing the state.");
        state = new GunShopState();

        getLogger().info("The GUNshop has been successfully initialized.");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage(Component.text("Hello, " + event.getPlayer().getName() + "!\nTo open the GUNshop, issue /gunshop"));
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent ev) {
        if (!(ev.getInventory().getHolder(false) instanceof GunShopSellPromptInventory sp)) {
            // It's not our inventory, ignore it.
            return;
        }

        this.getLogger().info("InventoryDrag");
    }
}