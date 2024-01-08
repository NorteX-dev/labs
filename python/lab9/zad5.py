import numpy as np

# Wczytanie pliku
data = np.genfromtxt('oceny.csv', delimiter='\t', skip_header=1)

# Najniższa ocena z laboratoriów dla każdego studenta
print(np.min(np.take(data, [0, 1, 2, 3, 4], axis=1), axis=1))

# Średnia ocena z egzaminu
print(np.mean(np.take(data, [5], axis=1)))

# Liczba 2 ze egzaminu
print(np.count_nonzero(np.take(data, [5], axis=1) == 2))

# Czy jest student, który miał same 5 z laboratoriów
print(np.any(np.all(np.take(data, [0, 1, 2, 3, 4], axis=1) == 5, axis=1)))

# Czy jest student, który miał 2 z LAB2 i LAB3
print(np.any((np.take(data, [1], axis=1) == 2) & (np.take(data, [2], axis=1) == 2)))

# Zlicz ilu studentów dostało wyższą ocenę z egzaminu niż ich średnia ocen z laboratoriów
print(np.sum(np.take(data, [5], axis=1) > np.mean(np.take(data, [0, 1, 2, 3, 4], axis=1), axis=1)))

# Liczba piątek, którą uzyskał student mający najwięcej 5 w całej grupie
max_fives = np.max(np.count_nonzero(np.take(data, [0, 1, 2, 3, 4], axis=1) == 5, axis=1))
print(max_fives)