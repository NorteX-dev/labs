"""
Napisać funkcję, która utworzy dwie listy (min. 3 elementy) – list_A i list_B. Połączyć je na
dwa różne sposoby:
a. tak aby w listA znalazły się najpierw elementy z A, potem z B
b. tak aby w listA znalazły się najpierw elementy z B, potem z A.
"""


def main():
    list_A = [1, 2, 3]
    list_B = [4, 5, 6]

    print("Lista A:", list_A)
    print("Lista B:", list_B)

    # a. tak aby w listA znalazły się najpierw elementy z A, potem z B
    print("Lista A z B:", list_A + list_B)

    # b. tak aby w listA znalazły się najpierw elementy z B, potem z A.
    print("Lista B z A:", list_B + list_A)


if __name__ == "__main__":
    main()
