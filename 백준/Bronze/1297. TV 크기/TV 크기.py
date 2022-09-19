import sys

a = list(map(int, sys.stdin.readline().split()))
diagonal = a[0]
side1ratio = a[1]
side2ratio = a[2]

import math

xSquare = diagonal**2 / (side1ratio**2 + side2ratio**2)
x = math.sqrt(xSquare)

side1 = int(side1ratio * x)
side2 = int(side2ratio * x)
print(side1, side2)