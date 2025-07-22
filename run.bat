@echo off
cls
javac -d build\classes -cp build\classes src\*.java  src\BSTree\*.java src\Entities\*.java src\LinkedList\*.java
java -classpath build\classes --module-path build\classes Program
cls