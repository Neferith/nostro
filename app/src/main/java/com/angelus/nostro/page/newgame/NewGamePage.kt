package com.angelus.nostro.page.newgame

import FantasyTypography
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.angelus.gamedomain.entities.CharacterGender
import com.angelus.nostro.R
import com.angelus.nostro.page.newgame.composables.GenderSelectionContent
import com.angelus.nostro.page.newgame.composables.GenderSelectionView
import com.angelus.nostro.page.newgame.composables.SexSelection
import com.angelus.nostro.ui.theme.FantasyColors

interface NewGameNavigator {

}

@Composable
fun NewGamePage(
    newGameViewModel: NewGameViewModel,
    newGameNavigator: NewGameNavigator,
    modifier: Modifier = Modifier
) {

/*    var step by remember { mutableStateOf(0) }

    // Les données que l'utilisateur va remplir
    var sex by remember { mutableStateOf<String?>(null) }
   // var height by remember { mutableStateOf<String?>(null) }
    //var weight by remember { mutableStateOf<String?>(null) }
    //var origin by remember { mutableStateOf<String?>(null) }
    //var skills by remember { mutableStateOf<List<String>>(emptyList()) }

    // Fond
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Texture de mur
        Image(
            painter = painterResource(id = R.drawable.stone_wall_texture),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        // Overlay
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.7f))
        )

        // Formulaire progressif
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Création de personnage",
                style = FantasyTypography.displaySmall,
                color = FantasyColors.Accent
            )
            Spacer(modifier = Modifier.height(32.dp))

            // Étapes progressives
            when (step) {
                0 -> GenderSelectionView(null, onGenderSelected = { /*sex = it; step++*/ })
               // 1 -> HeightSelection(onSelected = { height = it; step++ })
                //2 -> WeightSelection(onSelected = { weight = it; step++ })
                //3 -> OriginSelection(onSelected = { origin = it; step++ })
                //4 -> SkillSelection(
                  //  selectedSkills = skills,
                   // onSelected = { skills = it }
               // )
                /*5 -> Button(
                    onClick = {
                        onCharacterCreated(
                            CharacterData(sex!!, height!!, weight!!, origin!!, skills)
                        )
                    },
                    modifier = Modifier.fillMaxWidth(0.7f)
                ) {
                    Text("Finaliser le personnage")
                }*/
            }
        }
    }*/
    // État local ou ViewModel selon ton architecture
    var selectedGender by remember { mutableStateOf<CharacterGender?>(null) }

    // Exemple de contenu global
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(FantasyColors.BackgroundSecondary)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

         //   Spacer(modifier = Modifier.height(24.dp))

            // ✅ Intégration de ta vue de sélection de genre
            GenderSelectionContent(
                selectedGender = selectedGender,
                onGenderSelected = { gender ->
                    selectedGender = gender
                   // onGenderSelected(gender) // Si besoin, pour remonter au ViewModel ou parent
                }
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Exemple : Bouton "Suivant" si un genre est sélectionné
            Button(
                onClick = { /* Passer à l'étape suivante */ },
                enabled = selectedGender != null,
                colors = ButtonDefaults.buttonColors(
                    containerColor = FantasyColors.Primary
                )
            ) {
                Text("Suivant", color = FantasyColors.onPrimary)
            }
        }
    }
}