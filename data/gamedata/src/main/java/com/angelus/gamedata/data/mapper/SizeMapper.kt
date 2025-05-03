package com.angelus.gamedata.data.mapper

import com.angelus.gamedata.data.dto.SizeDTO
import com.angelus.gamedomain.entities.Size

fun Size.convertToDTO(): SizeDTO {
    return SizeDTO(
        width = this.width,
        height = this.height
    )
}

fun SizeDTO.convertFromDTO(): Size {
    return Size(
        width = this.width,
        height = this.height
    )
}