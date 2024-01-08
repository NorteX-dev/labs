"""
Napisać funkcję, która stworzy nową listę i wypełnić ją 10 liczbami korzystając z konsoli
(użytkownik podaje liczby w pętli). Za pomocą sortowania uzyskać element największy
i najmniejszy. Obliczyć średnią liczb nieujemnych.
"""


def main():
    lista = []

    for i in range(10):
        lista.append(int(input(f"Podaj liczbę {i+1}: ")))

    print("Lista:", lista)

    # Element największy i najmniejszy
    print("Element największy:", max(lista))
    print("Element najmniejszy:", min(lista))

    # Mapujemy listę na listę liczb nieujemnych
    dodatnie = [x for x in lista if x >= 0]
    # Średnia liczb nieujemnych
    print("Średnia liczb nieujemnych:", sum(dodatnie) / len(dodatnie))


if __name__ == "__main__":
    main()
