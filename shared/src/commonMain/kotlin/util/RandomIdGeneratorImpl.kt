package util

import kotlin.random.Random

class RandomIdGeneratorImpl(): RandomIdGenerator {
    private val random: Random = Random

    override fun generateId(): String {
        return random.nextInt().toString()
    }
}