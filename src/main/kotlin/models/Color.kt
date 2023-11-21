package models

enum class Color(val dir: String) {
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    WHITE("\u001B[37m"),
    RESET("\u001B[0m");
}