import numpy as np

def replace_zeros(A, x):
    A[np.where(A == 0)] = x
    return A

A = np.array([[1, 0, 3], [0, 5, 6]])
print(replace_zeros(A, 2))
print(replace_zeros(A, 3))
print(replace_zeros(A, 4))