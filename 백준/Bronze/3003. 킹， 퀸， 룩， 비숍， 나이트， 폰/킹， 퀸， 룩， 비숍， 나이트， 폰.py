chess = list(map(int, input().split()))
real_chess = [1,1,2,2,2,8]

def chess_num(L : list) -> list :
    for i in range(len(L)) :
        chess[i]=real_chess[i] - chess[i]
    return chess

print(*chess_num(chess))