import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.em
import com.angelus.nostro.R

// Exemple avec une police fantasy ajoutée dans ton dossier "res/font"
val MedievalFont = FontFamily(
    Font(R.font.medievalsharp_regular, FontWeight.Normal)
)

val FantasyTypography = Typography(
    displayMedium = TextStyle(
        fontFamily = MedievalFont,
        fontSize = 32.sp,
        letterSpacing = 0.05.em
    ),
    headlineSmall = TextStyle(
        fontFamily = MedievalFont,
        fontSize = 24.sp
    ),
    titleMedium = TextStyle(
        fontFamily = MedievalFont,
        fontSize = 18.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = MedievalFont,
        fontSize = 14.sp
    ),
    labelLarge = TextStyle(
        fontFamily = MedievalFont,
        fontSize = 12.sp
    )
)
