import matplotlib.pyplot as plt

fig, ax = plt.subplots()
fruits = ['apple', 'blueberry', 'cherry', 'orange', 'pineapple']
counts = [40, 100, 30, 55, 70]
bar_labels = ['red', 'blue', '_red', 'orange', 'yellow']
bar_colors = ['red', 'blue', 'red', 'orange', 'yellow']
ax.bar(fruits, counts, label=bar_labels, color=bar_colors)
ax.set_ylabel('Ilość owoców')
ax.set_title('Różne rodzaje owoców')
ax.legend(title='Kolory')
plt.show()