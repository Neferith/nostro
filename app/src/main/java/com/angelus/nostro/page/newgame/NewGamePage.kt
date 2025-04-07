package com.angelus.nostro.page.newgame

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.angelus.gamedomain.entities.character.genderToSizeMap
import com.angelus.gamedomain.entities.character.sizeToWeigth
import com.angelus.gamedomain.entities.character.weightToSensitivity
import com.angelus.nostro.R
import com.angelus.nostro.page.newgame.composables.BackgroundSelectionView
import com.angelus.nostro.page.newgame.composables.CharacterStepContainer
import com.angelus.nostro.page.newgame.composables.GenderSelector
import com.angelus.nostro.page.newgame.composables.NameSelectionView
import com.angelus.nostro.page.newgame.composables.SensitivitySelector
import com.angelus.nostro.page.newgame.composables.SizeSelector
import com.angelus.nostro.page.newgame.composables.WeightSelector
import com.angelus.nostro.ui.component.AttributesPreview
import com.angelus.nostro.ui.theme.FantasyColors
import com.angelus.nostro.ui.theme.FantasyTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

interface NewGameNavigator {

    fun goToGame()
}

@Composable
fun NewGamePage(
    viewModel: NewGameViewModel,
    newGameNavigator: NewGameNavigator,
    modifier: Modifier = Modifier
) {

    val systemUiController = rememberSystemUiController()
    val statusBarColor = FantasyColors.Primary

    systemUiController.setSystemBarsColor(color = statusBarColor)

    val selectedName by viewModel.nameState
    val selectedGender by viewModel.genderState
    val selectedSize by viewModel.sizeState
    val selectedWeight by viewModel.currentWeightState
    val selectedSensitivity by viewModel.currentSensitivity
    val selectedBackground by viewModel.currentBackground
    val currentStep by viewModel.currentStepState
    val currentAttributes by viewModel.currentAttributes
    val playerCreated by viewModel.playerResult

    LaunchedEffect(playerCreated) {
        if (playerCreated?.isSuccess == true) {
            newGameNavigator.goToGame()
            // Naviguer vers une autre vue
           // navController.navigate("destination_screen")
        }
    }
    FantasyTheme {
        Image(
            painter = painterResource(id = R.drawable.stone_wall_texture),
            contentDescription = "Texture murale sombre et antique",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // Pour remplir l'écran sans déformation
        )

        // Exemple de contenu global
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.6f))
                .systemBarsPadding() // Prend en compte les safe areas (status bar, navigation bar)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(24.dp))


                CharacterStepContainer(
                    selectorContent = {

                        when(currentStep) {
                            NewGameViewModel.STEP.NAME -> {
                                NameSelectionView(
                                    firstname = selectedName?.firstname?:"",
                                    lastname = selectedName?.lastname?:"",
                                    onFirstNameChange = {viewModel.updateFirstname(it)},
                                    onLastNameChange = {viewModel.updateLastname(it)}
                                )


                            }
                            NewGameViewModel.STEP.GENDER -> {
                                GenderSelector (
                                    selectedGender = selectedGender,
                                    onGenderSelected = {
                                        viewModel.updateGender(it)
                                    }
                                )
                            }
                            NewGameViewModel.STEP.SIZE -> {
                                SizeSelector (
                                    values = selectedGender?.genderToSizeMap()?: emptyList(),
                                    selectedSize = selectedSize,
                                    onSizeSelected = {
                                        viewModel.updateSize(it)
                                    }
                                )
                            }
                            NewGameViewModel.STEP.WEIGHT -> {
                                WeightSelector (
                                    values = selectedSize?.sizeToWeigth()?: emptyList(),
                                    selectedWeight = selectedWeight,
                                    onWeightSelected = {
                                        viewModel.updateWeight(it)
                                    }
                                )
                            }
                            NewGameViewModel.STEP.SENSITIVITY -> {
                                SensitivitySelector (
                                    values = selectedWeight?.weightToSensitivity()?: emptyList(),
                                    selectedSensitivity = selectedSensitivity,
                                    onSensitivitySelected = {
                                        viewModel.updateSensitivity(it)
                                    }
                                )
                            }

                            NewGameViewModel.STEP.BACKGROUND -> {
                                viewModel.currentBackgroundList.value?.let {
                                    BackgroundSelectionView(
                                        backgrounds = it,
                                        selectedBackground = selectedBackground,
                                        onBackgroundSelected = { viewModel.updateBackground(background = it) }
                                    )
                                }

                            }
                        }
                    },
                    attributesContent = {
                        AttributesPreview(currentAttributes = currentAttributes)
                    },
                    modifier = modifier
                )


                Spacer(modifier = Modifier.height(32.dp))

                if(!viewModel.checkIsLastStep()) {
                    // Exemple : Bouton "Suivant" si un genre est sélectionné
                    Button(
                        onClick = { viewModel.nextStep() },
                        enabled = viewModel.checkCurrentStep(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = FantasyColors.Primary
                        )
                    ) {
                        Text("Suivant", color = FantasyColors.onPrimary)
                    }
                } else {
                    Button(
                        onClick = { viewModel.submitCharacter() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = FantasyColors.Primary
                        )
                    ) {
                        Text("Valider", color = FantasyColors.onPrimary)
                    }
                }
            }
        }
    }
}