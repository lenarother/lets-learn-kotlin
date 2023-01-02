package de.thermondo.advent2022.day02

import de.thermondo.utils.Exclude
import de.thermondo.utils.stringFromFile

/**
 * Day 2: Rock Paper Scissors
 * https://adventofcode.com/2022/day/2
 */

@Exclude
fun main() {
    val input = stringFromFile("/advent2022/InputDay02.txt")
    val score = input?.let(::calculateRockPaperScissorsScorePartOne)
    println("Tournament played according to strategy guide gives $score points.")
}

enum class Shape {ROCK, PAPER, SCISSORS}
enum class Outcome {WIN, LOSE, DRAW}

fun getShape(ch: String) =
    when(ch) {
        "A", "X" -> Shape.ROCK
        "B", "Y" -> Shape.PAPER
        "C", "Z" -> Shape.SCISSORS
        else -> throw Exception("No mapping shape for $ch")
    }

fun getOutcome(you: Shape, other: Shape) =
    when (you to other) {
        Shape.SCISSORS to Shape.PAPER,
        Shape.PAPER to Shape.ROCK,
        Shape.ROCK to Shape.SCISSORS -> Outcome.WIN

        Shape.PAPER to Shape.SCISSORS,
        Shape.ROCK to Shape.PAPER,
        Shape.SCISSORS to Shape.ROCK -> Outcome.LOSE

        else -> Outcome.DRAW
    }

fun getShapeScore(shape: Shape) =
    when(shape) {
        Shape.ROCK -> 1
        Shape.PAPER -> 2
        Shape.SCISSORS -> 3
    }

fun getOutcomeScore(outcome: Outcome) =
    when(outcome) {
        Outcome.WIN -> 6
        Outcome.LOSE -> 0
        Outcome.DRAW -> 3
    }

fun getTotalScore(you:Shape, other:Shape) =
    getOutcomeScore(getOutcome(you, other)) +
    getShapeScore(you)

fun calculateSingleGameScore(game:String):Int {
    val parts = game.split(" ")
    val other = getShape(parts.elementAt(0))
    val you = getShape(parts.elementAt(1))
    return getTotalScore(you, other)
}

fun calculateRockPaperScissorsScorePartOne(input: String): Int {
    var score = 0
    for (game in input.trimEnd().trimStart().split("\n")) {
            score += calculateSingleGameScore(game)
    }
    return score
}
