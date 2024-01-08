import matplotlib.pyplot as plt
import numpy as np

oceny = np.genfromtxt('oceny.csv', delimiter='\t')
# Spłaszczamy tablicę do jednego wymiaru i zliczamy liczbę poszczególnych ocen
unique, counts = np.unique(oceny.flatten(), return_counts=True)
# Rysujemy wykres kołowy
plt.pie(counts, labels=unique, autopct='%1.1f%%')
plt.title('Rozkład ocen')
plt.savefig('filename.png')