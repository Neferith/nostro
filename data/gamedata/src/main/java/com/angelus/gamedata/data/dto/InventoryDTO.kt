package com.angelus.gamedata.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class InventoryDTO(val items: Map<String, Int> = mutableMapOf())