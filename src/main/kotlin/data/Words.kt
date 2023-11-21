package data

class Words {
    companion object {
        private val spanishWords: MutableList<String> = mutableListOf()
        init {
            spanishWords.addAll(
                setOf(
                    "tieso", "mudar", "riego",
                    "plata", "pasto", "forma",
                    "farol", "calle", "cielo",
                    "dulce", "fruta", "verde",
                    "marzo", "nieve", "abril",
                    "muela", "fuego", "juego",
                    "femur", "lemur", "aireo",
                    "guiar", "indio", "huido",
                    "oxido", "bando", "banco",
                    "bañar", "barca", "queso",
                    "pañal", "papel", "papas",
                    "padre", "madre", "zorro",
                    "perro", "perla", "hacia",
                    "hielo", "enero", "labio",
                    "lejos", "libre", "obeso",
                    "flaco", "olivo", "osado",
                    "valla", "valle", "vieja",
                    "icono", "igual", "rabia",
                    "reloj", "junio", "justo",
                    "alzar", "bache", "cazar",
                    "letal", "enano", "fobia",
                    "gente", "hongo", "jugar",
                    "abajo", "abano", "abeja",
                    "abeto", "abiso", "abono",
                    "aloja", "abrir", "aguja",
                    "abuso", "acabe", "acabo",
                    "acaso", "acato", "acoso",
                    "actor", "agudo", "aldea"
                )
            )
        }

        fun pickRandomWord(): String {
            return spanishWords.random()
        }

        fun toLettersArray(word: String): List<Char> {
            return word.toCharArray().toList()
        }
    }
}