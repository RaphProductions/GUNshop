package fr.raphm.gunshop.core;

import org.bukkit.entity.Item;

public class GunShopItem {
    Item item;
    int price;

    Item getItem() { return item; }
    int getPrice() { return price; }

    void setItem(Item i) { item = i; }
    void setPrice(int p) { price = p; }
}
