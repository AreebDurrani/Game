@echo off
SETLOCAL ENABLEEXTENSIONS

:# Open RSC: A replica RSC private server framework
:#
:# Multi-purpose script for Open RSC

set GREEN=[92m
set RED=[91m
set NC=[0m

:<------------Begin Verify Administrator------------>
REM  --> Check for permissions
>nul 2>&1 "%SYSTEMROOT%\system32\cacls.exe" "%SYSTEMROOT%\system32\config\system"

REM --> If error flag set, we do not have admin.
if '%errorlevel%' NEQ '0' (
    echo Requesting administrative privileges...
    goto UACPrompt
) else ( goto gotAdmin )

:UACPrompt
    echo Set UAC = CreateObject^("Shell.Application"^) > "%temp%\getadmin.vbs"
    set params = %*:"="
    echo UAC.ShellExecute "cmd.exe", "/c %~s0 %params%", "", "runas", 1 >> "%temp%\getadmin.vbs"

    "%temp%\getadmin.vbs"
    del "%temp%\getadmin.vbs"
    exit /B

:gotAdmin
    pushd "%CD%"
    CD /D "%~dp0"
:<------------End Verify Administrator------------>


:<------------Begin Start------------>
:start
cls
echo %RED%Open RSC:%NC%
echo An easy to use RSC private server framework.
echo:
echo What would you like to do?
echo:
echo Choices:
echo   %RED%1%NC% - Install Open RSC
echo   %RED%2%NC% - Update Open RSC
echo   %RED%3%NC% - Run Open RSC
echo   %RED%4%NC% - Manage Players
echo   %RED%5%NC% - Perform a Hard Reset
echo   %RED%6%NC% - Exit
echo:
SET /P action=Please enter a number choice from above:
echo:

if /i "%action%"=="1" goto install
if /i "%action%"=="2" goto update
if /i "%action%"=="3" goto run
if /i "%action%"=="4" goto manage
if /i "%action%"=="5" goto reset
if /i "%action%"=="6" exit

echo Error! %action% is not a valid option. Press enter to try again.
echo:
SET /P action=""
goto start
:<------------End Start------------>


:<------------Begin Install------------>
:install
echo:
@"%SystemRoot%\System32\WindowsPowerShell\v1.0\powershell.exe" -NoProfile -InputFormat None -ExecutionPolicy Bypass -Command "iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))" && SET "PATH=%PATH%;%ALLUSERSPROFILE%\chocolatey\bin"
echo:
echo:
echo Installing basic software needed to run the rest of this script.
echo:
choco install -y 7zip make
echo:
echo:
echo Do you have Oracle Java JDK 8 and Apache Ant installed already? It is required for this.
echo:
echo Choices:
echo   %RED%1%NC% - Install for me!
echo   %RED%2%NC% - I'm all set, continue!
echo:

SET /P java=Please enter a number choice from above:
echo:
IF /i "%java%"=="1" goto installjava
IF /i "%java%"=="2" goto askdocker

echo Error! %java% is not a valid option. Press enter to try again.
echo:
SET /P java=""
goto install
:<------------End Install------------>


:<------------Begin Java------------>
:installjava
echo:
echo Installing Oracle Java JDK 8 and Apache Ant.
echo:
choco install -y jdk8 ant
echo:
goto askdocker
:<------------End Java------------>


:<------------Begin Docker------------>
:askdocker
echo:
echo Do you have Docker installed already? It is required for this.
echo:
echo Choices:
echo   %RED%1%NC% - Install for me!
echo   %RED%2%NC% - I'm all set, continue!
echo:

SET /P docker=Please enter a number choice from above:
echo:
IF /i "%docker%"=="1" goto installdocker
IF /i "%docker%"=="2" goto askgit

echo Error! %docker% is not a valid option. Press enter to try again.
echo:
SET /P docker=""
goto askdocker


:installdocker
echo:
echo Installing Docker for Windows.
echo:
choco install -y docker-for-windows
echo:
echo:
echo Launching Docker for Windows. The Docker whale icon is by the system clock.
echo Allow it a short bit of time to start running.
echo:
call START "C:\Program Files\Docker\Docker\Docker for Windows.exe"
echo:
echo Once started, right click on it and click "Settings..."
echo Then click the "Shared Drives" tab on the left.
echo Check the box beside the "C:" drive so that the Docker containers can work.
echo Click "Apply" and then press enter here so the script can continue.
echo:
echo:
SET /P install="Press enter when the above steps have been completed."
echo:
goto askgit
:<------------End Docker------------>


:<------------Begin Git------------>
:askgit
echo:
echo Do you have Git installed already? It is required for this.
echo:
echo Choices:
echo   %RED%1%NC% - Install for me!
echo   %RED%2%NC% - I'm all set, continue!
echo:
SET /P git=Please enter a number choice from above:
echo:
IF /i "%git%"=="1" goto installgit
IF /i "%git%"=="2" goto askide

echo Error! %git% is not a valid option. Press enter to try again.
echo:
SET /P git=""
goto askgit


:installgit
echo:
echo Installing Git.
echo:
choco install -y git
echo:
echo:
goto edition
:<------------End Git------------>


:<------------Begin Edition------------>
:edition
echo:
echo Which edition of Open RSC would you like?
echo:
echo Choices:
echo  %RED%1%NC% - Simple single player mode
echo  %RED%2%NC% - Developer mode (includes everything)
echo:
SET /P edition=Please enter a number choice from above:
echo:

IF /i "%edition%"=="1" goto simple
IF /i "%edition%"=="2" goto developer

echo Error! %edition% is not a valid option. Press enter to try again.
echo:
SET /P edition=""
goto edition
:<------------End Edition------------>


:<------------Begin Simple------------>
:simple
echo:
echo Starting Docker containers and downloading what is needed. This may take a while the first time.
echo:
make start-single-player
echo:
echo:
echo Downloading a copy of the Game repository
echo:
make clone-windows-game
echo:
echo:
echo Importing the game databases.
echo:
make pull-game-windows
echo:
make import-windows-game
echo:
echo:
goto start
:<------------End Simple------------>


:<------------Start Developer------------>
:developer
echo:
echo Would you like to install Git Kraken? It simplifies git usage!
echo:
echo Choices:
echo   %RED%1%NC% - Install for me!
echo   %RED%2%NC% - I'm all set, continue!
echo:
SET /P gitkraken=Please enter a number choice from above:
echo:
IF /i "%gitkraken%"=="1" goto installgitkraken
IF /i "%gitkraken%"=="2" goto askide

echo Error! %gitkraken% is not a valid option. Press enter to try again.
echo:
SET /P gitkraken=""
goto developer


:installgitkraken
echo:
echo Installing Git Kraken.
echo:
choco install -y gitkraken
echo:
goto askide


:askide
echo:
echo Would you like a programming IDE installed for editing code?
echo:
echo Choices:
echo   %RED%1%NC% - Install NetBeans (recommended)
echo   %RED%2%NC% - Install IntelliJ IDEA Community Edition
echo   %RED%3%NC% - Install Eclipse
echo   %RED%4%NC% - I'm all set, continue!
echo:
SET /P git=Please enter a number choice from above:
echo:
IF /i "%git%"=="1" goto installnetbeans
IF /i "%git%"=="2" goto installintellij
IF /i "%git%"=="3" goto installeclipse
IF /i "%git%"=="4" goto developerstart

echo Error! %git% is not a valid option. Press enter to try again.
echo:
SET /P git=""
goto askide


:installnetbeans
echo:
choco install -y netbeans
echo:
goto askide


:installintellij
echo:
choco install -y intellijidea-community
echo:
goto askide


:installeclipse
echo:
choco install -y eclipse
echo:
goto askide


:developerstart
echo:
echo Starting Docker containers and downloading what is needed. This may take a while the first time.
echo:
make stop
make start
echo:
echo:
echo Downloading a copy of the Website repository
echo:
make clone-windows-website
make pull-website-windows
echo:
echo:
echo Downloading a copy of the Game repository
echo:
make clone-windows-game
make pull-game-windows
echo:
echo:
make pull-game-windows
echo:
echo Importing the game databases.
echo:
make import-windows-game
make import-windows-ghost
echo:
echo:
goto start
:<------------End Developer------------>


:<------------Begin Update------------>
:update
echo:
echo Checking for updates to the Docker-Home repository.
echo:
git pull
echo:
goto start
:<------------End Update------------>


:<------------Start Run------------>
:run
echo:
echo Starting Open RSC
echo:
echo:
echo Extracting client cache
echo:
md "%HOMEPATH%/OpenRSC"
7z x "Game/client/cache.zip" -o"%HOMEPATH%/OpenRSC"
echo:
echo:
SET /P singleplayer=Ready to launch "Windows_Single_Player.cmd" - Press enter when ready.
echo:
echo:
Windows_Single_Player.cmd
goto start
:<------------End Run------------>


:<------------Start Manage------------>
:manage
echo:
echo Manage Players
echo:
goto start
:<------------End Manage------------>


:<------------Start Reset------------>
:reset
echo:
echo Perform a Hard Reset
echo:
goto start
:<------------End Reset------------>
