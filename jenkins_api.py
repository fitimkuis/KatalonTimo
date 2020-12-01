import os
import jenkins
import time

#parameters: jenkins url, username, token (created in Jenkins)
server = jenkins.Jenkins("http://localhost:8080/", "fitimkuis", "111d387f716ca4c77d7301927d3920e2e0")
server.build_job("TestSuiteCollection")
time.sleep(20)
os.system("C:\\Users\\fitim\\KatalonFromGitHub\\KatalonProject\\read_line.py")

