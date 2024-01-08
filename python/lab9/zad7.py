import numpy as np

array = np.random.rand(5, 5)

# Definicja wag
weights = np.array([1, 2, 3, 2, 1])

# Obliczenie średniej ważonej dla każdego wiersza
weighted_averages = np.sum(array * weights, axis=1) / np.sum(weights)
print(weighted_averages)