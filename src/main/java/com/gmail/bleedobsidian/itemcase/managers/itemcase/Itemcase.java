/*
 * Copyright (C) 2013 Jesse Prescott <BleedObsidian@gmail.com>
 *
 * ItemCase is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/gpl.html>.
 */

package com.gmail.bleedobsidian.itemcase.managers.itemcase;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class Itemcase {
    private Item item;
    private ItemStack displayStack;
    private ItemStack itemStack;
    private Location blockLocation;
    private String player;

    private ItemcaseType type = ItemcaseType.SHOWCASE;
    private boolean canBuy;
    private boolean canSell;

    private double buyPrice = 0;
    private double sellPrice = 0;

    private boolean isInfinite;
    private Inventory inventory;

    private boolean isChunkLoaded;

    public Itemcase(ItemStack itemStack, Location blockLocation, String player) {
        this.itemStack = itemStack;

        this.displayStack = itemStack.clone();
        ItemMeta meta = displayStack.getItemMeta();
        meta.setDisplayName(UUID.randomUUID().toString()); // Stop item
                                                           // stacking.
        this.displayStack.setItemMeta(meta);

        this.blockLocation = blockLocation;
        this.player = player;

        this.setChunkLoaded(blockLocation.getWorld().isChunkLoaded(
                blockLocation.getChunk()));
    }

    public void spawnItem() {
        if (this.isChunkLoaded) {
            Location itemLocation = new Location(blockLocation.getWorld(),
                    blockLocation.getBlockX() + 0.5,
                    blockLocation.getBlockY() + 1.5,
                    blockLocation.getBlockZ() + 0.5);

            this.item = blockLocation.getWorld().dropItem(itemLocation,
                    this.displayStack);
            this.item.setVelocity(new Vector(0.0, 0.1, 0.0));
            this.item.setMetadata("ItemCase", new ItemcaseData());
        }
    }

    public void despawnItem() {
        if (!item.isDead()) {
            this.item.remove();
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public boolean isChunkLoaded() {
        return this.isChunkLoaded;
    }

    public void setChunkLoaded(boolean bool) {
        this.isChunkLoaded = bool;

        if (bool) {
            this.spawnItem();
        } else {
            this.despawnItem();
        }
    }

    public Item getItem() {
        return this.item;
    }

    public Block getBlock() {
        return this.blockLocation.getBlock();
    }

    public ItemStack getItemStack() {
        return this.itemStack;
    }

    public String getOwnerName() {
        return this.player;
    }

    public void setType(ItemcaseType type) {
        this.type = type;
    }

    public ItemcaseType getType() {
        return this.type;
    }

    public boolean canBuy() {
        return canBuy;
    }

    public void setCanBuy(boolean canBuy) {
        this.canBuy = canBuy;
    }

    public boolean canSell() {
        return canSell;
    }

    public void setCanSell(boolean canSell) {
        this.canSell = canSell;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public boolean isInfinite() {
        return isInfinite;
    }

    public void setInfinite(boolean isInfinite) {
        this.isInfinite = isInfinite;
    }
}
