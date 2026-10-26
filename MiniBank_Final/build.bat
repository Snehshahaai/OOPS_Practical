@echo off
if exist out rmdir /s /q out
mkdir out

javac -d out src\model\annotation\*.java src\exception\*.java src\model\*.java src\util\*.java src\service\*.java src\app\*.java

jar cfe minibank.jar app.MiniBank -C out .

echo.
echo Build complete.
echo Run: java -jar minibank.jar
