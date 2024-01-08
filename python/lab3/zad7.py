def main():
    tuple = ("apple", "banana", "cherry")
    tuple_num = (5, 4, 6)
    print(sorted(tuple_num))
    print(tuple)  # wypisanie krotki
    tuple_b = ("orange",)
    print(tuple_b)  # wypisanie krotki
    tuple += tuple_b  # dodawanie krotek
    print("tuple po dodaniu", tuple)
    multi_tuple = tuple * 2  # mnożenie krotek
    print(multi_tuple)  # wypisanie krotki
    print(len(tuple))  # długość krotki - liczba elementów
    for x in tuple:  # wypisanie wszystkich elementów krotki
        print(x)


if __name__ == "__main__":
    main()
