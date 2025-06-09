package com.angelus.dungeonengine

data class DungeonSquare(
    val leftBack: Float,
    val topBack: Float,
    val rightBack: Float,
    val bottomBack: Float,

    val leftForward: Float,
    val topForward: Float,
    val rightFoward: Float,
    val bottomForward: Float
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DungeonSquare

        if (leftBack != other.leftBack) return false
        if (topBack != other.topBack) return false
        if (rightBack != other.rightBack) return false
        if (bottomBack != other.bottomBack) return false
        if (leftForward != other.leftForward) return false
        if (topForward != other.topForward) return false
        if (rightFoward != other.rightFoward) return false
        if (bottomForward != other.bottomForward) return false

        return true
    }

    override fun hashCode(): Int {
        var result = leftBack.hashCode()
        result = 31 * result + topBack.hashCode()
        result = 31 * result + rightBack.hashCode()
        result = 31 * result + bottomBack.hashCode()
        result = 31 * result + leftForward.hashCode()
        result = 31 * result + topForward.hashCode()
        result = 31 * result + rightFoward.hashCode()
        result = 31 * result + bottomForward.hashCode()
        return result
    }
}