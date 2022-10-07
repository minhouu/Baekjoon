import sys
size_of_board = list(map(int, sys.stdin.readline().split()))

# 2차원 행렬로 input 값 재구성
board = []
for i in range(size_of_board[0]) :
    board.append(list(sys.stdin.readline()))
    
# 1. 전체 행렬 중 8*8 행렬 부분집합을 모두 검사한다(O(n^2))

min_for_all = 32 # 전체 반복에서 다시 칠하는 횟수의 최솟값 구하기 위해서 미리 선언(이론상 최대값)
for i in range(len(board) - 7) : # board[0] ~ [m - 8], board[i][0] ~ [n - 8] 의 경우를 검사해줘야 한다
    for j in range(len(board[i]) - 7) :
        # 2. 이후 각 경우마다 8 * 8 행렬이 올바른 조건이 되기위한 수정횟수를 gross를 통해 체크
        # 검사하는 행렬의 [0][0]이 B인 경우와 W인 경우를 각각 계산하여 
        # 둘 중 작은 수를 사용한다. 
        
        # 2 - 1. B로 시작하는 경우
        min_b = 32 # 이론상 최댓값
        gross = 0
        for p in range(8) :
            for q in range(8) :
                # 짝수번째 행일때 B로 시작
                if p%2 == 0 : # 짝수번째 행일 때
                    if q%2 == 0 : # 짝수번째 열이면 
                        if board[i + p][j + q] == 'B' : # B여야 한다
                            continue
                        else : 
                            gross +=1
                    else : # 홀수번째 열이면
                        if board[i + p][j + q] == 'W' : # W여야 한다
                            continue
                        else :
                            gross +=1
                # 홀수번째 행일때 W로 시작
                else :
                    if q%2 == 0 :
                        if board[i + p][j + q] == 'W' :
                            continue
                        else :
                            gross +=1
                    else :
                        if board[i + p][j + q] == 'B' :
                            continue
                        else :
                            gross +=1
        if gross < min_b : # 8*8 for문이 다 돌아간 후 gross와 min_b를 비교하여 업데이트해준다
            min_b = gross
            
        # 2 - 2. W로 시작하는 경우
        min_w = 32 # 이론상 최댓값
        gross = 0    
        for p in range(8) :
            for q in range(8) :
                # 짝수번째 행일때 W로 시작
                if p%2 == 0 : # 짝수번째 행일 때
                    if q%2 == 0 : # 짝수번째 열이면
                        if board[i + p][j + q] == 'W' : # W여야 한다
                            continue
                        else :
                            gross +=1
                    else : # 홀수번째 열이면
                        if board[i + p][j + q] == 'B' :# B여야 한다
                            continue
                        else :
                            gross +=1
                # 홀수번째 행일때 B로 시작
                else : # 홀수번째 행일때
                    if q%2 == 0 : # 짝수번째 열이면
                        if board[i + p][j + q] == 'B' : # B여야 한다
                            continue
                        else :
                            gross +=1
                    else : #홀수번째 열이면
                        if board[i + p][j + q] == 'W' : # W여야 한다
                            continue
                        else :
                            gross +=1
        if gross < min_w : #min_w 와 gross 비교해서 업데이트
            min_w = gross
        
        # 3. min_w 와 min_b 비교 후 min_for_all(n^2 반복동안의 최소횟수) 업데이트
        if min_w > min_b :
            if min_b < min_for_all :
                min_for_all = min_b
        else :
            if min_w < min_for_all :
                min_for_all = min_w
                
# 4. print min_for_all              
print(min_for_all)