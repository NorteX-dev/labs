"""
Napisać funkcję, w której zostanie zasymulowany arkusz ocen studenckich. Stworzyć
zagnieżdżony słownik, w którym kluczem są numery indeksu, a wartościami podrzędne
słowniki (zawierające klucze: „imię”, „nazwisko” i „oceny” oraz wartości: imię studenta (str),
nazwisko studenta (str), lista ocen (list). Wypisać wszystkich studentów ze słownika
(wypisać imię, nazwisko, numer indeksu oraz średnią z ocen).
"""


def main():
    dict = {
        123: {
            "imie": "Jan",
            "nazwisko": "Kowalski",
            "oceny": [3, 4, 5, 3, 4]
        },
        456: {
            "imie": "Anna",
            "nazwisko": "Nowak",
            "oceny": [5, 5, 5, 5, 5]
        }
    }

    for key, value in dict.items():
        print("Imię:", value["imie"])
        print("Nazwisko:", value["nazwisko"])
        print("Numer indeksu:", key)
        print("Średnia ocen:", sum(value["oceny"]) / len(value["oceny"]))
        print("")


if __name__ == "__main__":
    main()
