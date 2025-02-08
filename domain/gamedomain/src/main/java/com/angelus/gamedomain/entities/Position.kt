package com.angelus.gamedomain.entities

enum class Direction { NORD, SUD, EST, OUEST }
enum class Rotation {LEFT, RIGHT}

data class Position(var x: Int, var y: Int, var direction: Direction)
