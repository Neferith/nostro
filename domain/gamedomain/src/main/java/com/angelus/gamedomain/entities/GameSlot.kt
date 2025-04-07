package com.angelus.gamedomain.entities

sealed class GameSlot(slot:Int) {
    class newGame(slot:Int): GameSlot(slot)
    class loadGame(slot:Int): GameSlot(slot)
}