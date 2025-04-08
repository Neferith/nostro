import com.angelus.gamedata.data.dto.CharacterDTO
import com.angelus.gamedata.data.dto.EntityPositionDTO
import kotlinx.serialization.Serializable

@Serializable
data class PlayerDTO(val id: String,
                     val entityPosition: EntityPositionDTO,
                     val  playerBand: PlayerBandDTO
)

@Serializable
data class PlayerBandDTO(val characters:List<CharacterDTO>)

