"""
Napisać funkcję, w której zostanie zdefiniowany nowy słownik z kilkoma parami
klucz―wartość (można korzystać ze słownika car_dict z początku instrukcji). Wykonać
następujące działania:
a. dodać kolejną parę klucz-wartość
b. zaktualizować jeden z istniejących wpisów
c. przetestować metody pop i popitem
d. sprawdzić, czy możliwe jest użycie w jednym słowniku kluczy o dwóch
różnych typach (np. int i string)? Czy możliwe jest użycie w jednym słowniku
wartości o różnych typach?
"""


def main():
    dict = {
        "a": "b",
        "c": "d",
        # Możliwe jest użycie w jednym słowniku kluczy o dwóch różnych typach (np. int i string):
        "e": 5
    }

    print("początkowy słownik", dict)

    # a
    dict["f"] = "g"
    print("słownik po dodaniu pary", dict)

    # b
    dict["e"] = "h"
    print("słownik po aktualizacji pary", dict)

    # c
    # Usuwa po kluczu (lewej stronie pary)
    dict.pop("f")
    print("słownik po usunięciu pary", dict)
    # Usuwa ostatnią parę
    dict.popitem()
    print("słownik po usunięciu pary", dict)


if __name__ == "__main__":
    main()
