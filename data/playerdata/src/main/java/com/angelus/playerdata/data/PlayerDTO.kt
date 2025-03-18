import com.angelus.gamedomain.entities.EntityPosition
import com.angelus.gamedomain.entities.Orientation
import com.angelus.gamedomain.entities.Position
import com.angelus.playerdomain.entities.Player
import com.angelus.playerdomain.entities.PlayerBand
import kotlinx.serialization.Serializable

@Serializable
data class PlayerDTO(val id: String,
                  var entityPosition: EntityPositionDTO
)

@Serializable
data class PositionDTO(
    val x: Int,
    val y: Int
)

@Serializable
data class EntityPositionDTO(
    val mapId: String,
    val position: PositionDTO,
    val orientation: String
)

fun Player.convertPlayerToDTO(): PlayerDTO {
    val positionDTO = PositionDTO(this.entityPosition.x, this.entityPosition.y)

    val entityPositionDTO = EntityPositionDTO(
        this.entityPosition.mapId,
        positionDTO,
        this.entityPosition.orientation.toString()
        )
    return PlayerDTO(this.id, entityPositionDTO)
}

fun PlayerDTO.convertPlayerFromDTO(): Player {
    val position = Position(this.entityPosition.position.x, this.entityPosition.position.y)

    val entityPosition = EntityPosition(
        this.entityPosition.mapId,
        position,
        this.entityPosition.orientation.toOrientation()
    )
    return Player(this.id, entityPosition, PlayerBand(emptyList()))
}

fun String.toOrientation(): Orientation {
    return try {
        Orientation.valueOf(this.uppercase())
    } catch (e: IllegalArgumentException) {
        return Orientation.NORTH// Retourne null si la valeur n'est pas valide
    }
}