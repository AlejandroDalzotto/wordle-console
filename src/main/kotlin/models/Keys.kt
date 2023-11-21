package models

enum class KeyState {
    CORRECT,
    INCORRECT,
    NULL;

    companion object {
        fun getColorByState(state: KeyState): Color {
            return when(state) {
                CORRECT -> Color.GREEN
                INCORRECT -> Color.YELLOW
                NULL -> Color.RESET
            }
        }
    }
}

data class Key(val value: Char, var isDiscovered: Boolean, var color: Color = Color.WHITE)

class Keys {
    private val values = "qwertyuiopasdfghjklzxcvbnm".toSortedSet()

    val keys = mutableListOf<Key>()

    private fun printKey(key: Key) {
        print("${key.color.dir}[${key.value}]${Color.WHITE.dir}")
    }

    fun printKeys() {
        keys.forEach {
            printKey(it)
        }
    }

    init {
        values.forEach { keys.add(Key(it, false)) }
    }

    fun discoverKey(k: Char, keyState: KeyState) {
        val key = keys.find { it.value == k }
        key!!.color = KeyState.getColorByState(keyState)
    }
}