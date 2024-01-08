import matplotlib.pyplot as plt
import numpy as np

x = np.linspace(-np.pi, np.pi, 100)

y1 = np.sin(x)
y2 = 2 * np.cos(x)

plt.plot(x, y1, label="sin(x)")
plt.plot(x, y2, label="2cos(x)")

plt.title("Wykresy funkcji sin(x) i 2cos(x)")
plt.xlabel("x")
plt.ylabel("y")

plt.legend()

plt.show()
plt.savefig("filename.png")