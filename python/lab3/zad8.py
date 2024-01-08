"""
Wrócić do części teoretycznej 2.4. Stworzyć w nowej funkcji definicję zbioru (5 elementów) i
przetestować działania:
a. usuwanie metodą remove i discard (sprawdzić działanie dla nieistniejącego
elementu)
b. zdejmowanie elementów metodą pop – Czy zawsze zdejmuje się ten sam
element? Dlaczego tak się dzieje?
"""


def main():
    zbior = set([1, 2, 3, 4, 5])
    print("zbiór", zbior)

    # a
    zbior.remove(1)  # usuwa element o wartości 1
    print("zbiór po usunięciu 1", zbior)
    zbior.discard(2)  # usuwa element o wartości 2
    print("zbiór po usunięciu 2", zbior)
    # zbior.remove(), jeżeli nie znajdzie elementu, to wyrzuci błąd
    # zbior.discard(), jeżeli nie znajdzie elementu, to nie wyrzuci błędu (nic nie zrobi)
    # Poniższy kod nie wyrzuci błędu:
    zbior.discard(2)
    print("zbiór po usunięciu 2", zbior)

    # b
    zbior.pop()  # usuwa ostatni element ze zbioru
    print("zbiór po zdejściu elementu", zbior)
    # Zawsze zdejmuje się ten sam element, ponieważ zbiór jest nieuporządkowany
    # i nie ma znaczenia, który element zostanie zdejmowany.


if __name__ == "__main__":
    main()
