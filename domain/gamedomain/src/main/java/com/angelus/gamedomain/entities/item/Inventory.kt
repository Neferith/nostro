package com.angelus.gamedomain.entities.item

data class Inventory(val items: Map<String, Int> = mutableMapOf())

fun Inventory.isEmpty(): Boolean {
    return this.items.isEmpty()
}

fun Inventory.isNotEmpty(): Boolean {
    return this.items.isNotEmpty()
}

fun Inventory.addItem(itemId: String, quantity: Int = 1): Inventory {
    val updated = items.toMutableMap().apply {
        this[itemId] = this.getOrDefault(itemId, 0) + quantity
    }
    return Inventory(updated)
}

fun Inventory.removeItem(itemId: String, quantity: Int = 1): Inventory {
    val current = items[itemId] ?: return this
    val newQty = current - quantity
    val updated = items.toMutableMap().apply {
        if (newQty > 0) this[itemId] = newQty else remove(itemId)
    }
    return Inventory(updated)
}
