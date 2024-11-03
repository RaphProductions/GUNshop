package fr.raphm.gunshop.core;

import java.util.ArrayList;
import java.util.List;

// TODO: this assumes that the server is on 24h/24 7j/7
public class GunShopState {
    List<GunShopItem> items;

    public GunShopState() {
        items = new ArrayList<GunShopItem>();
    }

    public void addItem(GunShopItem i) { items.add(i); }
    public List<GunShopItem> getItems() { return items; }
}
