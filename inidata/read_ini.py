#if sys.version_info[0] == 2:
#    import ConfigParser as configparser
#else:
#    import configparser
import configparser

def writeConfigFile():
    parser = configparser.ConfigParser()
    parser.add_section('Manager')
    parser.set('Manager', 'Name', 'Test Name')
    parser.set('Manager', 'email', 'test.name@gmail.com')
    parser.set('Manager', 'password', 'secret')
    fp=open('test.ini','w')
    parser.write(fp)
    fp.close()

def getParsedIni():
    b = []
    parser = configparser.ConfigParser()
    parser.read('sampleconfig.ini')
    for sect in parser.sections():
        print('Section:', sect)
        b.append('Section: %s' %(sect))
        for k,v in parser.items(sect):
            b.append('{} = {}'.format(k,v))
            print(' {} = {}'.format(k,v))
        print()
    return b

a = getParsedIni()
print a
value = 'runstatus = 1'
if value in a:
    print "list contains", value


#writeConfigFile()
