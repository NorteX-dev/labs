import numpy as np

def medianize(A):
    median_value = np.mean(A)
    A = A - median_value
    return A

A = np.array([[1, 0, 3], [0, 5, 6]])
print(medianize(A))