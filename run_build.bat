@echo off
set JAVA_HOME=C:\Program Files\Java\jdk-21
set PATH=%JAVA_HOME%\bin;%PATH%
cd /d "C:\Users\ARADHANA PANDA\Documents\GitHub\kawach-pandemic-support"
call mvnw.cmd clean install --no-transfer-progress > build_out.txt 2>&1
if %ERRORLEVEL% EQU 0 (
    echo BUILD_SUCCESS
) else (
    echo BUILD_FAILED
)
