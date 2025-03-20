import com.angelus.gamedata.data.CharacterDTO
import com.angelus.gamedata.data.EntityPositionDTO
import com.angelus.gamedata.data.PositionDTO
import com.angelus.gamedata.data.convertCharacterFromDTO
import com.angelus.gamedata.data.convertCharacterToDTO
import com.angelus.gamedomain.entities.Character
import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.Orientation
import com.angelus.gamedomain.entities.Position
import com.angelus.playerdomain.entities.Player
import com.angelus.playerdomain.entities.PlayerBand
import kotlinx.serialization.Serializable

@Serializable
data class PlayerDTO(val id: String,
                     val entityPosition: EntityPositionDTO,
                     val  playerBand: PlayerBandDTO
)

@Serializable
data class PlayerBandDTO(val characters:List<CharacterDTO>)

fun PlayerBand.convertToDTO(): PlayerBandDTO {

    return PlayerBandDTO(
        characters = this.characters.map { it.convertCharacterToDTO() }
    )
}

fun PlayerBandDTO.convertPlayerFromDTO(): PlayerBand {
    return PlayerBand(
        characters = this.characters.map { it.convertCharacterFromDTO() }
    )
}

fun Player.convertPlayerToDTO(): PlayerDTO {
    val positionDTO = PositionDTO(this.entityPosition.x, this.entityPosition.y)

    val entityPositionDTO = EntityPositionDTO(
        this.entityPosition.mapId,
        positionDTO,
        this.entityPosition.orientation.toString()
        )
    return PlayerDTO(this.id, entityPositionDTO, this.band.convertToDTO())
}

fun PlayerDTO.convertPlayerFromDTO(): Player {
    val position = Position(this.entityPosition.position.x, this.entityPosition.position.y)

    val entityPosition = EntityPosition(
        this.entityPosition.mapId,
        position,
        this.entityPosition.orientation.toOrientation()
    )
    return Player(this.id,
        entityPosition,
        this.playerBand.convertPlayerFromDTO()
    )
}

fun String.toOrientation(): Orientation {
    return try {
        Orientation.valueOf(this.uppercase())
    } catch (e: IllegalArgumentException) {
        return Orientation.NORTH// Retourne null si la valeur n'est pas valide
    }
}