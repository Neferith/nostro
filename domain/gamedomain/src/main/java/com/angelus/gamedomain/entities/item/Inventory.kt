package com.angelus.gamedomain.entities.item

data class Inventory(val items: Map<String, Int> = mutableMapOf())

fun Inventory.isEmpty(): Boolean {
    return this.items.isEmpty()
}

fun Inventory.isNotEmpty(): Boolean {
    return this.items.isNotEmpty()
}