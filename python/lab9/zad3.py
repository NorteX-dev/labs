import numpy as np

n, m = 5, 5
A = np.random.randint(0, 100, size=(n, m))

print("Macierz A:")
print(A)

max_global = np.max(A)
print("\nNajwiększy element globalnie:", max_global)

max_row = np.max(A, axis=1)
print("\nNajwiększy element w każdym wierszu:", max_row)

max_col = np.max(A, axis=0)
print("\nNajwiększy element w każdej kolumnie:", max_col)