package com.angelus.gamedomain.entities.item

object ItemRegistry {
    private val items = mutableMapOf<String, Item>()

    fun register(item: Item) {
        require(!items.containsKey(item.id)) { "Un item avec cet ID existe déjà : ${item.id}" }
        items[item.id] = item
    }

    fun getItem(id: String): Item? = items[id]

    fun getAllItems(): Collection<Item> = items.values
}
