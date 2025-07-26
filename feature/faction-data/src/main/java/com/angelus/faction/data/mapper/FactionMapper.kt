package com.angelus.faction.data.mapper

import com.angelus.faction.data.dto.FactionDTO
import com.angelus.faction.domain.entities.Faction


fun Faction.convertToDTO(): FactionDTO{
    return  FactionDTO(
        id = this.id,
        name = this.name,
        description = this.description,
        relations = this.relations
    )
}

fun FactionDTO.convertFromDTO(): Faction {
return  Faction(
    id = TODO(),
    name = TODO(),
    description = TODO(),
    relations = TODO()
)
}