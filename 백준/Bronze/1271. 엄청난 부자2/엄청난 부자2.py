data = list(map(int, input().split()))

def money_share(m: int, n: int) -> int :
    return m//n

def money_remainder(m: int, n: int) -> int :
    return m%n
    
print(money_share(data[0],data[1]))
print(money_remainder(data[0],data[1]))