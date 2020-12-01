#!/usr/bin/env python
# -*- coding: UTF-8 -*-

import re
import argparse, sys, os

from calendar import monthrange
from random import randint

CHECK_KEYS = "0123456789ABCDEFHJKLMNPRSTUVWXY" 
CENTURIES = {'18':'+','19':'-','20':'A'}

def create_ssn(start=1800, end=2014):

	year = randint(start, end)
	month = randint(1, 12)
	day = randint(1, monthrange(year, month)[1])

	century_sep = CENTURIES[str(year)[0:2]]

	order_num = randint(2, 889)

	check_number = "%02d%02d%s%03d" % (day, month, str(year)[0:2],order_num) 

	check_number_index = int(check_number)%31
	key = CHECK_KEYS[check_number_index]
	
	return "%02d%02d%s%s%03d%s" % (day, month, str(year)[0:2],century_sep, order_num, key)

def create_argparser():
	parser = argparse.ArgumentParser(description='Generate or validate ')
	
	parser.add_argument('-v', '--validate', dest='validate')	
	parser.add_argument('-i', '--info', dest='info')
	parser.add_argument('-g', '--generate', dest='generate', action='store_true')
	parser.add_argument('-c', '--clipboard', dest='clipboard', action='store_true')
	parser.add_argument('-s', '--startyear', type=int, default=1800, dest='start')
	parser.add_argument('-e', '--endyear', type=int, default=2099, dest='end')

	return parser

def parse_information(ssn):
	KEYS_CENTURIES = dict(zip(CENTURIES.values(),CENTURIES.keys()))

	return {
		"birth_day": ssn[0:2],
		"birth_month": ssn[2:4],
		"birth_year": KEYS_CENTURIES[ssn[6]] + ssn[4:6],
		"order_number": ssn[7:10],
		"is_man": int(ssn[7:10]) % 2 != 0
	}

def print_info(ssn):
	info = parse_information(ssn)
	print("ssn: %s" %(info.get("ssn"),) )
	print("Order number: %s" % (info.get("order_number"),))
	print("Birth time: %s.%s.%s" % (info.get("birth_day"), info.get("birth_month"), info.get("birth_year")))	
	print("Gender: %s " % ('man' if info.get("is_man") else 'female',))

def check_number(ssn):
	num = ssn[0:6] + ssn[7:10]
	return CHECK_KEYS[int(num) % 31] == ssn[-1]

def validate(ssn):
	regexp = "^\d{6}[+-A]\d{3}[a-zA-Z0-9]$"
	match = re.search(regexp, ssn)
	
	if match != None and check_number(ssn):
		print("Given ssn  [%s] is valid." % (ssn,))
	else:
		print("Given ssn [%s] is unvalid." % (ssn,))

def in_range(value, minimum, maximum):
	return value >= minimum and value <= maximum 

def main(argv=sys.argv):
    argparser = create_argparser()

    if len(argv) <= 1:
        ssn = create_ssn(1900, 2000)
	print(ssn)
	file=open('C:/KatalonStudio/KatalonProject/ssn-python/ssn.txt','w')
        file.write(ssn)
        file.write("\n")
        file.close()
	
	args = argparser.parse_args(argv[1:])

	if not in_range(args.start, 1800, 2099):
		print("Start year have to be between years 1800 - 2099, given year was %s " % args.start)
		return;

	if not in_range(args.end, 1800, 2099):
		print("End year have to be between years 1800 - 2099, given year was %s " % args.end)
		return;

	if args.info:
		print_info(args.info)

	if args.validate:
		validate(args.validate)

	if args.generate:
		ssn = create_ssn(args.start, args.end)


if __name__ == '__main__':
	main()


