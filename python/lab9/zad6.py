import numpy as np

# Wylosowanie 10 liczb do tablicy Numpy
array = np.random.rand(10)

# Sortowanie tablicy rosnąco
sorted_array_asc = np.sort(array)
print(sorted_array_asc)

# Sortowanie tablicy malejąco
sorted_array_desc = np.sort(array)[::-1]
print(sorted_array_desc)