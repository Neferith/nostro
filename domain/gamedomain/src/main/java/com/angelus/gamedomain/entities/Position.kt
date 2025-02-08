package com.angelus.gamedomain.entities

enum class Direction { NORD, SUD, EST, OUEST }

data class Position(var x: Int, var y: Int, var direction: Direction)
