cd C:\Users\fitim\KatalonStudio\Katalon6.1.2
katalon.exe -noSplash  -runMode=console -projectPath="C:\Users\fitim\KatalonFromGitHub\KatalonProject\KatalonProject.prj" -retry=0 -testSuiteCollectionPath="Test Suites/RegressionTestSuiteCollection" -browserType="Chrome (headless)" -apiKey=27f70c12-7093-4ea7-83d6-ed64ce7c257b -apikey=0145e236-3ede-43a2-a1bf-4c5c9e27fd23
del /q "C:\Program Files (x86)\Jenkins\workspace\TestSuiteCollection\Reports\*"
FOR /D %%p IN ("C:\Program Files (x86)\Jenkins\workspace\TestSuiteCollection\Reports\*.*") DO rmdir "%%p" /s /q
FOR /F "delims=" %%i IN ('dir "C:\Users\fitim\KatalonFromGitHub\KatalonProject\Reports\RegressionTestSuite" /b /ad-h /t:c /o-d') DO (
    SET a=%%i
    GOTO :found
)
echo No subfolder found
goto :eof
:found
echo Most recent subfolder: %a%
robocopy C:\Users\fitim\KatalonFromGitHub\KatalonProject\Reports\RegressionTestSuite\%a% "C:\Program Files (x86)\Jenkins\workspace\TestSuiteCollection\Reports" /E /COPYALL /V /NP /R:0
if ERRORLEVEL 1 set ERRORLEVEL=0