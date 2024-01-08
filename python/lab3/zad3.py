"""
Napisać funkcję, która utworzy dwie listy (int) i wypełni je wartościami (min. 5 elementów
w każdej liście). Wypisać elementy unikatowe listy 1, czyli elementy występujące w liście 1,
ale nie znajdujące się w liście 2.
"""


def main():
    lista1 = [1, 2, 3, 4, 5]
    lista2 = [4, 5, 6, 7, 8]

    print("Lista 1:", lista1)
    print("Lista 2:", lista2)

    # Elementy unikatowe listy 1
    print("Elementy unikatowe listy 1:", [
          x for x in lista1 if x not in lista2])


if __name__ == "__main__":
    main()
