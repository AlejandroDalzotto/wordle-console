package models

class OutputMessage(english: Boolean) {

    val onInvalidInputSpecialChars =
        if (english) "Please enter a word without special characters or numbers."
        else "Por favor, ingresa una palabra sin caracteres especiales o números."

    val onInvalidInputNumbers =
        if (english) "Please enter a word containing only letters, no numbers."
        else "Por favor, ingresa una palabra que solo contenga letras, sin números."

    val onInvalidInputOutOfRange =
        if (english) "Invalid input, please enter a five-letter word."
        else "Entrada invalida, por favor ingresa una palabra de cinco letras."

    val onUserInputEvent =
        if (english) "Please enter a five-letter word: "
        else "Por favor, ingresa una palabra de cinco letras: "

    val onPlayerLose =
        if (english) "🟥🟥🟥 You lose, thanks for playing 🟥🟥🟥"
        else "🟥🟥🟥 Has perdido, gracias por jugar 🟥🟥🟥"

    val onPlayerWin =
        if (english) "🟩🟩🟩 Congratulations, you win!! 🟩🟩🟩"
        else "🟩🟩🟩 Felicidades, has ganado!! 🟩🟩🟩"
}