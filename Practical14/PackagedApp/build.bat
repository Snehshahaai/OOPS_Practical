@echo off
javac -d out src\model\Model.java src\service\Service.java src\app\Main.java
jar cfe app.jar app.Main -C out .
echo Run using: java -jar app.jar
