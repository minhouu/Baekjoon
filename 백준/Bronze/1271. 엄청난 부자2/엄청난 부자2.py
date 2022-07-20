data = list(map(int, input().split()))
m = data[0]
n = data[1]

def money_share(m: int, n: int) -> int :
    return m//n

def money_remainder(m: int, n: int) -> int :
    return m%n
    
print(money_share(m,n))
print(money_remainder(m,n))