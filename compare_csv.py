import pandas as pd
import os
from sys import exit

#os.remove("update.csv")

t = os.path.isfile("update.csv")
print t
if t == True:
    os.remove("update.csv")
A=set(pd.read_csv("file1.csv", index_col=False, header=None)[0]) #reads the csv, takes only the first column and creates a set out of it.
B=set(pd.read_csv("file2.csv", index_col=False, header=None)[0]) #same here
print("missing rows from file2 %s "%(A-B)) #set A - set B gives back everything thats only in A.
messageA = A-B
messageB = B-A
#print messageA
a = list(messageA)
b = list(messageB)

print a
print b
#print ("missing rows from file1 %s "%(B-A))

with open('update.csv', 'w') as outFile:
    if a == b:
        outFile.write(" files are identical\n")
        exit()

 
with open('update.csv', 'w') as outFile:
    outFile.write(" added in file1 but missing from file2\n")
    for line in a:
        outFile.write(line)
        outFile.write("\n")

with open('update.csv', 'a') as outFile:
    outFile.write("\n added in file2 but missing from file1\n")
    for line in b:
        outFile.write(line)
        outFile.write("\n")
