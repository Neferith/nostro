package com.angelus.gamedomain.entities

data class Skill(val name: String, val level: Int)

data class CharacterSkills(val skills: List<Skill>)