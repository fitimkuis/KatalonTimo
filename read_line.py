import os
import sys
from datetime import timedelta
from datetime import datetime
import time

def timeout():
    # This will set t_end at 5 minutes
    t_end = time.time() * (60 * 5)

    while time.time() < t_end:
       if (condition):
           break
       else:
           pass

def loop_until():
    delete_TO = 1
    wait_until = datetime.now() + timedelta(hours=delete_TO)
    break_loop = False
    while not break_loop:
        #do-your loop-stuff
        if wait_until < datetime.now():#or somecondition:
            break_loop = True

def read_log(path):
    path = path + "\\log"
    #print "DEBUG %s" % (path)

    important = []
    keep_phrases = ["End sending to Katalon Analytics","Report has been sent to Katalon Analytics"]
    #keep_phrases = ["python"]

    #with open('filename', mode='rb') as f:

    with open(path,mode='r') as f:
        f = f.readlines()

    t = 0
    #t_end = time.time() * (10 * 1)
    t1 = datetime.now()
    #while time.time() < t_end:
    #while (datetime.now()-t1).seconds <= 900:
    for line in f:
        for phrase in keep_phrases:
            if phrase in line:
                important.append(line)
                #print important
                break
    return important



def show_cwd_contents( path="." ): 
    # A function that calls 2 functions to separately  
    # listing out directories and files. 
    # It takes a default argument as cwd(.). We can  
    # pass other paths too. 
    import os 
  
    f_list = [] 
    d_list = list() 
  
    try: 
        for f in os.listdir(path): 
            if os.path.isfile(os.path.join(path, f)): 
                f_list.append(f) 
            else: 
                if os.path.isdir(os.path.join(path, f)): 
                    d_list.append(f) 
    except: 
        print "\nError, once check the path"
        return
  
    show_files(f_list, path) 
  
    show_directories(d_list, path)

def show_files(file_list, path): 
    """ A function that lists the files """
  
    import os 
    s = "%s%d%s"%("\n", len(file_list), " files of " + os.path.abspath(path)) 
    l = len(s) 
    print s 
    print "="*l 
    for index, file in enumerate(file_list): 
        print str(index+1) + ") ", file

def show_directories(dir_list, path): 
    """ A function that lists the directories """
    import os 
    s = "%s%d%s"%("\n", len(dir_list), " directories of " + os.path.abspath(path)) 
    l = len(s) 
    print s 
    print "="*l 
    for index, dir in enumerate(dir_list): 
        print str(index+1) + ") ", dir

def get_newest(path):
    files = os.listdir(path)
    latest_file = files[0]
    for key in files:
        if os.path.getctime(path+key) > os.path.getctime(path + latest_file):
            latest = key
    print latest

def newest(path):
    files = os.listdir(path)
    paths = [os.path.join(path, basename) for basename in files]
    return max(paths, key=os.path.getctime)

path = "C:\\Program Files (x86)\\Jenkins\\jobs\\TestSuiteCollection\\builds\\"

#show_cwd_contents(path) 

path = newest(path)
#print latest

#keep_phrases = ["End sending to Katalon Analytics\n","Report has been sent to Katalon Analytics\n"]
search = "Report has been sent to Katalon Analytics\n"
#search = "kissa"
t1 = datetime.now()
t = 0
log = []
count = 0
start = time.time()
time.clock()
elapsed = 0
print "waiting report to been sent Katalon Analytics"
while (datetime.now()-t1).seconds <= 1400:
    elapsed = time.time() - start
    #elapsed = time.time() - start
    log = read_log(path)
    if search in log:
        print "Report has been sent to Katalon Analytics time elapsed: %02d \n" % (elapsed)
        t = 0
        time.sleep(5)
        break
    elif search not in log:
        #print "waiting report to been sent Katalon Analytics..."
        if count < 10: 
            sys.stdout.write(".")
            sys.stdout.flush()
            time.sleep(3)
        if count > 10:
            print "\n"
            print "waiting report to been sent Katalon Analytics, time elapsed: %02d \n" % (elapsed)
            count = 0
        count = count + 1
        t = 1
if (t == 1):
    print "No report to been sent Katalon Analytics."
        


#get_newest(path)
