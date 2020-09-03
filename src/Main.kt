package converter

import java.util.Scanner

val scanner = Scanner(System.`in`)

enum class Units(val symbol: String, val degreeSymbol: String, val textSingular: String, val textPlural: String, val degreeNamedAfter: String) {
    DEFAULT("???", "???", "???", "???", "???"),
    GRAMS("g", "", "gram", "grams", ""),
    KILOGRAMS("kg", "", "kilogram", "kilograms", ""),
    MILLIGRAMS("mg", "", "milligram", "milligrams", ""),
    POUNDS("lb", "", "pound", "pounds", ""),
    OUNCES("oz", "", "ounce", "ounces", ""),
    METERS("m", "", "meter", "meters", ""),
    KILOMETERS("km", "", "kilometer", "kilometers", ""),
    CENTIMETERS("cm", "", "centimeter", "centimeters", ""),
    MILLIMETERS("mm", "", "millimeter", "millimeters", ""),
    MILES("mi", "", "mile", "miles", ""),
    YARDS("yd", "", "yard", "yards", ""),
    FEET("ft", "", "foot", "feet", ""),
    INCHES("in", "", "inch", "inches", ""),
    CELSIUS("c", "dc", "degree Celsius", "degrees Celsius", "celsius"),
    FAHRENHEIT("f", "df", "degree Fahrenheit", "degrees Fahrenheit", "fahrenheit"),
    KELVINS("k", "", "kelvin", "kelvins", "");

    companion object {
        fun conversion(number1: Double, inputtedUnit1: String, inputtedUnit2: String) {
            var unit1 = DEFAULT
            var unit2 = DEFAULT
            var number1InGrams = Double.NaN
            var number1InMeters = Double.NaN
            var number1InCelsius = Double.NaN
            var number2 = 0.0

            for (enum in values()) {
                when (inputtedUnit1) {
                    enum.symbol -> unit1 = enum
                    enum.degreeSymbol -> unit1 = enum
                    enum.textSingular -> unit1 = enum
                    enum.textPlural -> unit1 = enum
                    enum.degreeNamedAfter -> unit1 = enum
                }

                when (inputtedUnit2) {
                    enum.symbol -> unit2 = enum
                    enum.degreeSymbol -> unit2 = enum
                    enum.textSingular -> unit2 = enum
                    enum.textPlural -> unit2 = enum
                    enum.degreeNamedAfter -> unit2 = enum
                }
            }

            when (unit1) {
                DEFAULT -> return println("Conversion from ${unit1.textPlural} to ${unit2.textPlural} is impossible")
                GRAMS -> number1InGrams = number1
                KILOGRAMS -> number1InGrams = number1 * 1000.0
                MILLIGRAMS -> number1InGrams = number1 * 0.001
                POUNDS -> number1InGrams = number1 * 453.592
                OUNCES -> number1InGrams = number1 * 28.3495
                METERS -> number1InMeters = number1
                KILOMETERS -> number1InMeters = number1 * 1000.0
                CENTIMETERS -> number1InMeters = number1 * 0.01
                MILLIMETERS -> number1InMeters = number1 * 0.001
                MILES -> number1InMeters = number1 * 1609.35
                YARDS -> number1InMeters = number1 * 0.9144
                FEET -> number1InMeters = number1 * 0.3048
                INCHES -> number1InMeters = number1 * 0.0254
                CELSIUS -> number1InCelsius = number1
                FAHRENHEIT -> number1InCelsius = (number1 - 32.0) * (5.0/9.0)
                KELVINS -> number1InCelsius = number1 - 273.15
            }

            if (number1 < 0 && !number1InGrams.isNaN()) return println("Weight shouldn't be negative")
            if (number1 < 0 && !number1InMeters.isNaN()) return println("Length shouldn't be negative")

            when {
                !number1InGrams.isNaN() && unit2 == GRAMS -> number2 = number1InGrams
                !number1InGrams.isNaN() && unit2 == KILOGRAMS -> number2 = number1InGrams / 1000.0
                !number1InGrams.isNaN() && unit2 == MILLIGRAMS -> number2 = number1InGrams / 0.001
                !number1InGrams.isNaN() && unit2 == POUNDS -> number2 = number1InGrams / 453.592
                !number1InGrams.isNaN() && unit2 == OUNCES -> number2 = number1InGrams / 28.3495
                !number1InMeters.isNaN() && unit2 == METERS -> number2 = number1InMeters
                !number1InMeters.isNaN() && unit2 == KILOMETERS -> number2 = number1InMeters / 1000.0
                !number1InMeters.isNaN() && unit2 == CENTIMETERS -> number2 = number1InMeters / 0.01
                !number1InMeters.isNaN() && unit2 == MILLIMETERS -> number2 = number1InMeters / 0.001
                !number1InMeters.isNaN() && unit2 == MILES -> number2 = number1InMeters / 1609.35
                !number1InMeters.isNaN() && unit2 == YARDS -> number2 = number1InMeters / 0.9144
                !number1InMeters.isNaN() && unit2 == FEET -> number2 = number1InMeters / 0.3048
                !number1InMeters.isNaN() && unit2 == INCHES -> number2 = number1InMeters / 0.0254
                !number1InCelsius.isNaN() && unit2 == CELSIUS -> number2 = number1InCelsius
                !number1InCelsius.isNaN() && unit2 == FAHRENHEIT -> number2 = number1InCelsius * (9.0/5.0) + 32.00
                !number1InCelsius.isNaN() && unit2 == KELVINS -> number2 = number1InCelsius + 273.15
                else -> return println("Conversion from ${unit1.textPlural} to ${unit2.textPlural} is impossible")
            }

            print("$number1 ")
            if (number1 == 1.0) print("${unit1.textSingular} ") else print("${unit1.textPlural} ")
            print("is $number2 ")
            if (number2 == 1.0) println("${unit2.textSingular} ") else println("${unit2.textPlural} ")
        }
    }
}

fun main() {
    while (true) {
        print("Enter what you want to convert (or exit): ")
        val nextLine = scanner.nextLine().split(" ")

        if (nextLine[0] == "exit") break
        if (nextLine[0].toDoubleOrNull() == null) println("Parse error") else {
            val number1 = nextLine[0].toDouble()
            val unit1 = if (nextLine[1].toLowerCase() == "degree" || nextLine[1].toLowerCase() == "degrees") nextLine[2].toLowerCase() else nextLine[1].toLowerCase()
            val unit2 = nextLine.last().toLowerCase()

            Units.conversion(number1, unit1, unit2)
        }
    }
}
