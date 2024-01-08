"""
Napisać funkcję, która utworzy listę z 10 elementami (int). Wypisać z niej jedynie elementy
nieparzyste. Podać najmniejszy z nich.
"""


def main():
    lista = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

    print("Lista:", lista)

    # Definicja listy z elementami które spełaniają warunek x % 2 != 0 (nieparzyste)
    nieparzyste = [x for x in lista if x % 2 != 0]

    # Elementy nieparzyste
    print("Elementy nieparzyste:", nieparzyste)

    # Najmniejszy element nieparzysty
    print("Najmniejszy element nieparzysty:", min(nieparzyste))


if __name__ == "__main__":
    main()
