package models

class Cell(val letter: Char, var color: Color = Color.WHITE) {
    companion object {
        fun stringToCellArray(value: String): Array<Cell> {
            val result: ArrayList<Cell> = arrayListOf()
            for (it in value) {
                result.add(Cell(it))
            }
            return result.toTypedArray()
        }
    }
}