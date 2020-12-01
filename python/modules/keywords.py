import selenium
import modules.utils as utils
import logging
import time
from natsort import natsorted
import cv2

def hello_world(allArgs, *numbers):
  print("Hello, World from print")
  logging.warning("Hello, World from logging.info")
  print("Sum of", numbers)
  s = sum(numbers)
  print("Sum is", s)
  return s
  
def read_image(allArgs, filepath):
  try:
      from PIL import Image
  except ImportError:
      import Image
  from pytesseract import image_to_string, pytesseract

  pytesseract.tesseract_cmd = r'C:\\Program Files\\Tesseract-OCR\\tesseract.exe'
  img = cv2.imread(filepath)
  img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
  custom_config = r'--oem 3 --psm 6'
  text = pytesseract.image_to_string(img, config=custom_config)
  
  #text = pytesseract.image_to_string(Image.open(filepath))
  #image = Image.open(filepath)
  #text = image_to_string(image)
  print("**********DEBUG**********"+text)
  return text
  
def get_image_text(allArgs, filename, tempname):
  print(filename)
  print(tempname)
  try:
      from PIL import Image
  except ImportError:
      import Image
  from pytesseract import image_to_string, pytesseract

  #pytesseract.tesseract_cmd = r'C:\\Program Files\\Tesseract-OCR\\tesseract.exe'
  #text = pytesseract.image_to_string(Image.open(filename))
  #print("**********DEBUG**********"+text)
  
  image = cv2.imread(filename)
  image = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
  #custom_config = r'--oem 3 --psm 6'
  #pytesseract.image_to_string(image, config=custom_config)
  cv2.imwrite(tempname, image)
  #image = Image.open(tempname)
  #text = image_to_string(image)
  #print(text)
  return tempname
  
def diff(AllArgs, list1, list2):
  #res1 = list1.strip('][').split(', ')
  #res2 = list1.strip('][').split(', ')
  c = set(list1).union(set(list2))  # or c = set(list1) | set(list2)
  d = set(list1).intersection(set(list2))  # or d = set(list1) & set(list2)
  print (list(c - d))
  return list(c - d)
  
def sort_string(AllArgs, names):
  res = names.strip('][').split(', ')
  res = natsorted(res, key=lambda y: y.lower())
  print(res)
  return res

def goto_google(allArgs):
  driver = utils.get_driver(allArgs)
  driver.get("https://google.com")
  input_element = driver.find_element_by_name("q")
  input_element.send_keys("Search and close after 5 seconds")
  input_element.submit()
  time.sleep(5)


def fibonacci(allArgs, a):  
  nterms = a

  # first two terms
  n1, n2 = 0, 1
  count = 0

  # check if the number of terms is valid
  if nterms <= 0:
     print("Please enter a positive integer")
  elif nterms == 1:
    print("Fibonacci sequence upto",nterms,":")
    print(n1)
  else:
    print("Fibonacci sequence:")
    while count < nterms:
      print(n1)
      nth = n1 + n2
      # update values
      n1 = n2
      n2 = nth
      count += 1
  
def compare_csv(allArgs, csv1, csv2):
  print("COMPARE CSV")
  #deff file3 = System.getProperty("user.dir")+"\\python\\modules\\update.csv";
  #print (file3)
  #t = os.path.isfile(file3)
  A=set(pd.read_csv(csv1, index_col=False, header=None)[0]) #reads the csv, takes only the first column and creates a set out of it.
  B=set(pd.read_csv(csv2, index_col=False, header=None)[0]) #same here
  print("missing rows from file2 %s "%(A-B)) #set A - set B gives back everything thats only in A.
  messageA = A-B
  messageB = B-A
  #print messageA
  a = list(messageA)
  b = list(messageB)
  print (a)
  print (b)