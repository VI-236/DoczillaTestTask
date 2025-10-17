@echo off
title Application Launcher

echo Starting Java Console Application and React Development Server...
echo.

set "SCRIPT_DIR=%~dp0"

if not exist "%SCRIPT_DIR%web-service-0.0.1-SNAPSHOT.jar" (
    echo ERROR: ConsoleApp-1.0-SNAPSHOT-jar-with-dependencies.jar not found!
    echo Please make sure the JAR file is in the same directory as this script.
    echo.
    pause
    exit /b 1
)

if not exist "%SCRIPT_DIR%frontend\" (
    echo ERROR: frontend folder not found!
    echo Please make sure the frontend folder exists in the same directory as this script.
    echo.
    pause
    exit /b 1
)

if not exist "%SCRIPT_DIR%frontend\package.json" (
    echo WARNING: package.json not found in frontend folder!
    echo npm start might fail if run from wrong directory.
    echo.
)

echo Launching Java Console Application...
start "Java Console App" cmd /k "cd /d "%SCRIPT_DIR%" && java -jar web-service-0.0.1-SNAPSHOT.jar"

timeout /t 3 /nobreak >nul

echo Launching React Development Server...
start "React App" cmd /k "cd /d "%SCRIPT_DIR%frontend" && npm install react-scripts --save-dev && npm start"

echo.
echo Both applications have been launched in separate windows.
echo - Java Console App: first window
echo - React Development Server: second window (running from frontend folder)
echo.
echo You can close this launcher now.
timeout /t 5 >noul
