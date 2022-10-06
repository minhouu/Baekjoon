import sys

a = list(map(int, sys.stdin.readline().split()))

x = a[0]
y = a[1]
w = a[2]
h = a[3]

# x, y, w-x, h-y 비교해서 최솟값 출력
a[2] = w-x
a[3] = h-y

# 최솟값 찾는 함수
def find_minimum(a : list) -> int :
    min_temp = a[0]
    for i in range(len(a)) :
        if a[i] < min_temp :
            min_temp = a[i]
    return min_temp

print(find_minimum(a))