import matplotlib.pyplot as plt
import numpy as np

x = np.linspace(0, 2 * np.pi, 100)

y1 = np.sin(x)
y2 = np.cos(x)
y3 = np.tan(x)
y4 = np.arctan(x)

plt.figure(figsize=(10, 8))

plt.subplot(2, 2, 1)
plt.plot(x, y1, 'r')
plt.title('sin(x)')

plt.subplot(2, 2, 2)
plt.plot(x, y2, 'g')
plt.title('cos(x)')

plt.subplot(2, 2, 3)
plt.plot(x, y3, 'b')
plt.title('tan(x)')

plt.subplot(2, 2, 4)
plt.plot(x, y4, 'y')
plt.title('arctan(x)')

plt.show()