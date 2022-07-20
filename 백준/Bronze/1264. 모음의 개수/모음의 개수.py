a=0
data = []
while a != "#" :
    a = input()
    data.append(a)
del data[-1]
data

def find_vowel(s : str) -> int :
    vowel_num = 0
    vowels = ["a", "e", "i", "o", "u", "A", "E", "I", "O", "U"]
    for i in s :
        if i in vowels :
            vowel_num = vowel_num + 1
    return vowel_num

for i in data :
    print(find_vowel(i))