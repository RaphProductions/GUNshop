package fr.raphm.gunshop.inv;

import fr.raphm.gunshop.GunShopPlugin;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class GunShopMainInventory implements InventoryHolder {

    private final Inventory inventory;

    public GunShopMainInventory(GunShopPlugin plugin) {
        // Create an Inventory with 9 slots, `this` here is our InventoryHolder.
        this.inventory = plugin.getServer().createInventory(this, 9, Component.text("GUNshop - Home"));
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }

}
