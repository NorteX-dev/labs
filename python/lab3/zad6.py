def main():
    lista = []

    while True:
        liczba = int(input("Podaj liczbę: "))

        if liczba == 0:
            break

        lista.append(liczba)

    print("Lista:", lista)

    # Elementy unikatowe
    print("Elementy unikatowe:", list(set(lista)))


if __name__ == "__main__":
    main()
