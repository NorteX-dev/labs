import numpy as np

# Tworzenie macierzy 2D o wymiarach 4x5
A = np.arange(20).reshape(4, 5)
print("Oryginalna macierz A:")
print(A)

# Reshape z -1 jako pierwszym parametrem
B = A.reshape(-1, 2)
print("\nMacierz B po reshape z -1 jako pierwszym parametrem:")
print(B)

# Reshape z -1 jako drugim parametrem
C = A.reshape(2, -1)
print("\nMacierz C po reshape z -1 jako drugim parametrem:")
print(C)