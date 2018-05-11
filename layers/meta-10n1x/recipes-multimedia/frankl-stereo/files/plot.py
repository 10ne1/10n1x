#!/bin/python3

from matplotlib import pyplot as plt
from matplotlib import style

from numpy import genfromtxt

data = genfromtxt('file.dat',delimiter=',')

plt.plot(data)

plt.title('playhrt hwbufer space/time function')
plt.ylabel('free space in buffer')
plt.xlabel('time')

plt.show()
