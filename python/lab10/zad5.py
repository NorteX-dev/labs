import matplotlib.pyplot as plt
import numpy as np

# Generujemy tablicę 100 liczb z zakresu <0, 9>
numbers = np.random.randint(0, 10, 100)

counts = np.bincount(numbers)

plt.subplot(1, 2, 1)  # 1 wiersz, 2 kolumny, pierwszy wykres
plt.bar(range(10), counts, color='blue')
plt.title('Wykres słupkowy')
plt.xlabel('Liczby')
plt.ylabel('Ilość powtórzeń')

plt.subplot(1, 2, 2)  # 1 wiersz, 2 kolumny, drugi wykres
plt.hist(numbers, bins=range(11), edgecolor='black', align='left')
plt.title('Histogram')
plt.xlabel('Liczby')
plt.ylabel('Ilość powtórzeń')

plt.show()