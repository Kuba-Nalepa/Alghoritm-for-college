fun main() {
    println("Enter three different numbers.\n That algorithm has to change those numbers to fit the following requirements")
    println("1) Each number should be divisible by three")
    println("2) Sum of all three numbers should be as large as possible")
    println("3) Digit 9 cannot be changed")
    println("4) You can perform up to six changes total for all three numbers.")
    println("Changing a number means increasing (decreasing is not allowed) any of its digits. Increasing a digit by one means one change, that is increasing a digit by a five would mean five changes.")
    var totalChanges = 6
    var myMutableListInt: MutableList<Int> = mutableListOf()

    fun howManyChanges(num: Int): Int {
        if ((num + 1) % 3 == 0) return 1
        if ((num + 2) % 3 == 0) return 2
        else return 3
    }

    for (i in 1..3) {
        println("Write $i. number:")
        val input = readLine()
        myMutableListInt.add(input!!.toInt())
    }
    println("INPUT:")
    println(myMutableListInt)

    var i = 0
    while (totalChanges > 0) {

        myMutableListInt = myMutableListInt.map {
            if(i == 0 && it % 3 == 0) {     // (doing it first time, and only first time)
                return@map it               //  it checks if in our list there is no number dividable by 3
            }
            
            var changesToMakeNumDividable = howManyChanges(it)
                                 // splitting each number to digit
            val listOfChars = it.toString().toList().map {
                var digit = it.toString().toInt()

                if (changesToMakeNumDividable > totalChanges) {
                    return@map digit
                }
                while (changesToMakeNumDividable > 0 && digit < 9) {
                    changesToMakeNumDividable--
                    totalChanges--
                    digit++
                }
                 return@map digit
            }
            // bind each digit to string
            return@map listOfChars.joinToString("").toInt()
        }.toMutableList()
        // assurance if there are any changes left, to not take the infinite loop
        if (totalChanges < 3 && myMutableListInt.all { it % 3 == 0 }) break

        i++     // this incrementation is for 23 line
    }
    println("OUTPUT:")
    println(myMutableListInt)
    println("Changes left: $totalChanges")
}