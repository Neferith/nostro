package com.angelus.nostro.page.inventory

import androidx.compose.runtime.Composable

interface InventoryNavigator

@Composable
fun InventoryScreen(navigator: InventoryNavigator, viewModel: InventoryViewModel ) {
}