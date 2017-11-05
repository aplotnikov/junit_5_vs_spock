package io.github.aplotnikov.junit5_vs_spock.entities

enum DateUnit {
    DAY(1),
    MONTH(30),
    YEAR(365)

    final int factor

    DateUnit(int factor) {
        this.factor = factor
    }

    int toDays(int number) {
        return number * factor
    }
}