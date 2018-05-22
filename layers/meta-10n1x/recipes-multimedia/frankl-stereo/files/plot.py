#!/bin/python3

from matplotlib import pyplot as plt
from matplotlib import style
from numpy import genfromtxt
import sys
import os.path

sys.argv.pop(0)

for arg in sys.argv:
    if os.path.isfile(arg):
        data = genfromtxt(arg,delimiter=',')
        plt.plot(data)
        plt.ylabel('value')
        plt.xlabel('time')
        plt.show()
    else:
        print('ERROR {0}} does not exist', arg)
