package com.angelus.modulea.item

import com.angelus.gamedomain.entities.item.Item
import com.angelus.gamedomain.entities.item.ItemType
import com.angelus.gamedomain.entities.item.StackRules

object NostroCross : Item {
    override val id: String = "NOSTRO_CROSS"
    override val title: String = "La croix des notres"
    override val description: String = "Une étrange croix"
    override val type: ItemType = ItemType.Quest
    override val stackRule: StackRules = StackRules()

}
