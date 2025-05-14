package com.angelus.gamedomain.entities.item


sealed class ItemType {
    object Other : ItemType() // Cas générique

    sealed class Equipment() : ItemType() {
        /*obec Weapon() : Equipment()
        data class Armour(val resistance: Int) : Equipment()
        data class Helmet(val resistance: Int) : Equipment()
        data class Shield(val block: Int) : Equipment()*/
    }

  //  data class Consumable(val effect: String) : ItemType()
    object Quest : ItemType()
   // data class Material(val rarity: Int) : ItemType()
}
data class StackRules(val stackable: Boolean = false, val max: Int = 1) {
    init {
        require(stackable || max == 1) { "Un objet non stackable doit avoir max = 1" }
    }
}

interface Item {
    val id:  String
    val title: String
    val description: String
    val type: ItemType
    val stackRule: StackRules
}