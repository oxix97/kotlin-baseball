package baseball

import camp.nextstep.edu.missionutils.Randoms

fun main() {
    gameStartText()
    startBaseball()
}

fun startBaseball() {
    val computerNumbers = mutableListOf<Int>()
    inputComputerNumbers(computerNumbers)
    while (true) {
        val userNumbers = inputUserNumbers()
        val gameComplete = checkUserNumbers(userNumbers, computerNumbers)
        if (gameComplete) break
    }
    restartGameCheck()
}

fun checkUserNumbers(
    userNumbers: MutableList<Int>, computerNumbers: MutableList<Int>
): Boolean {
    //strike check
    val strikeCount = userNumbers.filterIndexed { idx, userNum ->
        userNum == computerNumbers[idx]
    }.size
    // ball check
    val ballCount = userNumbers.filterIndexed { idx, userNum ->
        computerNumbers.contains(userNum) && userNum != computerNumbers[idx]
    }.size
    printUserCount(strikeCount, ballCount)

    if (strikeCount == 3) return true
    return false
}

fun printUserCount(strikeCount: Int, ballCount: Int) {
    if (strikeCount == 3) {
        println("3스트라이크\n3개의 숫자를 모두 맞히셨습니다! 게임 종료")
    } else if (strikeCount > 0 && ballCount == 0) {
        println("${strikeCount}스트라이크")
    } else if (strikeCount == 0 && ballCount > 0) {
        println("${ballCount}볼")
    } else if (strikeCount == 0 && ballCount == 0) {
        println("낫싱")
    } else if (strikeCount > 0 && ballCount > 0) {
        println("${ballCount}볼 ${strikeCount}스트라이크")
    }
}

fun restartGameCheck() {
    println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.")
    val number = readLine()?.toInt()
    if (number == 1) {
        startBaseball()
    } else if (number != 2) {
        throw IllegalArgumentException()
    }
}

fun inputUserNumbers(): MutableList<Int> {
    print("숫자를 입력해주세요 : ")
    val input = readLine()!!
    //예외 사항 : 정수 인지* , 길이가 3인지 , 중복된 숫자는 없는지
    val userNumbers = input.map {
        it.digitToInt()
    }.toMutableList()

    if (userNumbers.size != userNumbers.toSet().size || userNumbers.size != 3) {
        throw IllegalArgumentException()
    }

    return userNumbers
}

fun inputComputerNumbers(numbers: MutableList<Int>) {
    while (numbers.size < 3) {
        val randomNumber = Randoms.pickNumberInRange(1, 9)
        if (!numbers.contains(randomNumber)) {
            numbers.add(randomNumber)
        }
    }
    println("computer : $numbers")
}

fun gameStartText() = println("숫자 야구 게임을 시작합니다.")
