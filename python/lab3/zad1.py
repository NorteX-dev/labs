def main():
    # Utworzenie nowej pustej listy
    lista = []
    # a. Wypisanie listy
    print("Pierwotna lista", lista)
    # b. Dodanie do listy 5 elementów
    lista.append(1)
    lista.append(2)
    lista.append(3)
    lista.append(4)
    lista.append(5)
    print("Lista po dodaniu 5 elementów", lista)
    # c. Wypisanie pierwszych 2 elementów i ostatnich 2 elementów listy
    print("Pierwsze 2 el:", lista[:2])
    print("Ostatnie 2 el:", lista[-2:])
    # d. Sprawdzenie długości listy
    print("Długość listy", len(lista))
    # e. Wypisanie elementów z parzystych indeksów
    print("Elementy z parzystych indeksów", lista[::2])
    # f. Dodanie elementu numerycznego
    lista.append(6)
    print("Lista po dodaniu elementu numerycznego", lista)
    # g. Dodanie elementu napisowego
    # Tej operacji nie można wykonać ponieważ lista zawiera elementy typu int.
    # lista.append("tekst")
    # h. Posortowanie listy
    lista.sort()
    print("Lista po posortowaniu", lista)
    # i. Usunięcie ostatniego dodanego elementu
    lista.pop()
    print("Lista po usunięciu ostatniego elementu", lista)
    # j. Posortowanie listy odwrotnie
    lista.sort(reverse=True)
    print("Lista po posortowaniu odwrotnie", lista)
    # k. Dodanie elementu na miejsce o indeksie 2
    lista.insert(2, 7)
    print("Lista po dodaniu elementu na miejsce o indeksie 2", lista)
    # l. Zliczenie ile elementów o wartości 13 jest w liście
    print("Ilość elementów o wart. 13:", lista.count(13))


if __name__ == "__main__":
    main()
