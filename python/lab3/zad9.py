"""
Napisać funkcję, w której zdefiniowane będą dwa zbiory z liczbami. Przetestować metody
(co jest wynikiem działania funkcji i jakiego jest typu):
a. the_set.isdisjoint(this_set)
b. the_set.issubset(this_set)
c. the_set.issuperset(this_set)
d. the_set.union(this_set)
e. the_set.difference(this_set)
f. the_set.intersection(this_set)
"""


def main():
    zbior1 = set([1, 2, 3, 4, 5])
    zbior2 = set([1, 2, 3])

    print("początkowy zbiór 1", zbior1)
    print("początkowy zbiór 2", zbior2)

    # a
    print("is disjoint (czy są rozłączne)", zbior1.isdisjoint(zbior2))
    # b
    print("is subset (czy jest podzbiorem)", zbior1.issubset(zbior2))
    # c
    print("is superset (czy jest nadzbiorem)", zbior1.issuperset(zbior2))
    # d
    print("union (suma)", zbior1.union(zbior2))
    # e
    print("difference (różnica)", zbior1.difference(zbior2))
    # f
    print("intersection (część wspólna, iloczyn)", zbior1.intersection(zbior2))


if __name__ == "__main__":
    main()
