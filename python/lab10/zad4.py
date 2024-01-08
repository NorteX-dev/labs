import matplotlib.pyplot as plt
import numpy as np

y1 = np.random.randint(0, 10, 10)
y2 = np.random.randint(0, 10, 10)
y3 = np.random.randint(0, 10, 10)

x = np.arange(10)

plt.plot(x, y1, 'g-d', label='zielone diamenty') # g-d oznacza green-diamond
plt.plot(x, y2, 'y-*', label='żółte gwiazdki') # y-* oznacza yellow-asterisk
plt.plot(x, y3, 'm-p', label='fioletowe pięciokąty') # m-p oznacza magenta-pentagon

plt.legend()
plt.show()