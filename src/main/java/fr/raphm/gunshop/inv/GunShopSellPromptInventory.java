package fr.raphm.gunshop.inv;

import fr.raphm.gunshop.GunShopPlugin;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class GunShopSellPromptInventory implements InventoryHolder {

    private final Inventory inventory;
    private final GunShopPlugin pg;

    public GunShopSellPromptInventory(GunShopPlugin plugin) {
        // Create an Inventory with 9 slots, `this` here is our InventoryHolder.
        this.inventory = plugin.getServer().createInventory(this, 9, Component.text("Sell an item"));
        this.pg = plugin;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }


}
