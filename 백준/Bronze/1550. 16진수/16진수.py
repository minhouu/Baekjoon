import sys

a=sys.stdin.readline().strip()

output = 0
for i in range(-1, -len(a)-1, -1) :
    if ord(a[i]) >= 65 :
        output += (ord(a[i]) - 55) * (16 ** (-i-1))
    else :
        output += int(a[i]) * (16 ** (-i-1))
print(output)