fun main() {
    var totalChanges = 6
    var myMutableListInt: MutableList<Int> = mutableListOf()

    fun howManyChanges(num: Int): Int {
        if ((num + 1) % 3 == 0) return 1
        if ((num + 2) % 3 == 0) return 2
        else return 3
    }

    println("Write a number:")
    // input for three numbers
    for (i in 1..3) {
        val input = readLine()
        myMutableListInt.add(input!!.toInt())
    }
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
    println(myMutableListInt)
    println("Changes left: $totalChanges")
}