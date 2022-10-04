import sys

n, m = map(int, sys.stdin.readline().split())

def split_chocolate(n : int, m : int) -> int :
    if n == 1 or m == 1 :
        return abs(n - m)
    else :
        if n >= m :
            return 1 + split_chocolate(n//2, m) + split_chocolate(n - n//2, m)
        else :
            return 1 + split_chocolate(n, m//2) + split_chocolate(n, m - m//2)
        
print(split_chocolate(n, m))