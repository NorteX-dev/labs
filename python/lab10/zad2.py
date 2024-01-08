import matplotlib.pyplot as plt
import numpy as np

# Definiujemy dodatkową etykietę i rozmiary
labels = 'Frogs', 'Cats', 'Dogs', 'Birds'
sizes = [25, 30, 45, 10]
explode = (0, 0.1, 0, 0)  # tylko 'Cats' będzie odseparowany

fig1, ax1 = plt.subplots()

ax1.pie(
    sizes,
    explode=explode, # określamy, które kategorie mają być odseparowane
    labels=labels, # dodajemy etykiety
    autopct='%1.1f%%', # formatter procentów
    shadow=True, # cień
    startangle=90) # początkowy kąt 90 stopni

# Dodajemy legendę
plt.legend(labels, title="Animals")

plt.show()