data = []
whilestop = 1
while whilestop != 0 :
    data.append(list(map(int, input().split())))
    whilestop=data[-1][0]
del data[-1]

def bigger(L : list) -> str :
    if L[0] > L[1] :
        return "Yes"
    else :
        return "No"

    
        
for i in range(len(data)) :
    print(bigger(data[i]))