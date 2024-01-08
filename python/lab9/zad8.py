import numpy as np

# Stworzenie tablicy 10x10
array = np.random.randint(0, 10, (10, 10))

# Zliczenie wystąpień elementów
counts = np.bincount(array.flatten())

# Wypisanie liczności wystąpień elementów
for i, count in enumerate(counts):
    print(f"Liczba {i} występuje {count} razy")