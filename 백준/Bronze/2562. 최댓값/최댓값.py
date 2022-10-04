import sys

b = []
for i in range(9) :
    a = int(sys.stdin.readline())
    b.append(a)

# b = [1,2,3,4,5,6,7,8,39,40,2,3,5]

def myMax(listHelp : list) -> int :
    x = 0
    y = 0
    for i in range(len(listHelp)) :
        if listHelp[i] > x :
            x = listHelp[i]
            y = i
        else :
            pass
    return f"{x}\n{y + 1}"
    
print(myMax(b))
