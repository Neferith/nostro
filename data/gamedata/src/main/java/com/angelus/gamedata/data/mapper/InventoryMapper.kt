package com.angelus.gamedata.data.mapper

import com.angelus.gamedata.data.dto.InventoryDTO
import com.angelus.gamedomain.entities.item.Inventory

fun Inventory.convertToDTO(): InventoryDTO {
    return InventoryDTO(this.items.toMap())
}

fun InventoryDTO.convertFromDTO(): Inventory {
    return Inventory(this.items.toMap())
}