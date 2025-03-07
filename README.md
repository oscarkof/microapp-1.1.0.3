Pasos para trabajar en modo de desarrollo:

1. Descargar netbeans (se hizo este proceso de decompilación con NB 25)
2. descargar e instalar el JDK 1.8 y asociarlo al netbeans.
3. abrir el proyecto desde netbeans y el archivo de nb settings deberia dejar el proyecto configurado.
4. click derecho en el icono de aplicación web y luego dar en clean and build
5. deberiamos obtener una salida como la siguiente:

´´
------------------< com.fiduciaria.microapp:microapp >------------------
Building BBVA - MICRO APLICACION 1.1.0.3
  from pom.xml
--------------------------------[ war ]---------------------------------

--- clean:3.2.0:clean (default-clean) @ microapp ---
Deleting D:\projects\microapp-1.1.0.3\target

--- resources:3.3.1:resources (default-resources) @ microapp ---
Copying 0 resource from src\main\resources to target\classes
Copying 126 resources from src\main\java to target\classes

--- compiler:3.11.0:compile (default-compile) @ microapp ---
Changes detected - recompiling the module! :source
Compiling 252 source files with javac [debug deprecation target 1.8] to target\classes
/D:/projects/microapp-1.1.0.3/src/main/java/com/fiduciaria/microapp/base/BasePanel.java: Some input files use unchecked or unsafe operations.
/D:/projects/microapp-1.1.0.3/src/main/java/com/fiduciaria/microapp/base/BasePanel.java: Recompile with -Xlint:unchecked for details.

--- resources:3.3.1:testResources (default-testResources) @ microapp ---
skip non existing resourceDirectory D:\projects\microapp-1.1.0.3\src\test\resources
Copying 0 resource from src\test\java to target\test-classes

--- compiler:3.11.0:testCompile (default-testCompile) @ microapp ---
Changes detected - recompiling the module! :dependency

--- surefire:3.2.5:test (default-test) @ microapp ---

--- war:3.4.0:war (default-war) @ microapp ---
Packaging webapp
Assembling webapp [microapp] in [D:\projects\microapp-1.1.0.3\target\microapp-1.1.0.3]
Processing war project
Copying webapp resources [D:\projects\microapp-1.1.0.3\src\main\webapp]
Building war: D:\projects\microapp-1.1.0.3\target\microapp-1.1.0.3.war

--- install:3.1.2:install (default-install) @ microapp ---
Installing D:\projects\microapp-1.1.0.3\pom.xml to C:\Users\osmar\.m2\repository\com\fiduciaria\microapp\microapp\1.1.0.3\microapp-1.1.0.3.pom
Installing D:\projects\microapp-1.1.0.3\target\microapp-1.1.0.3.war to C:\Users\osmar\.m2\repository\com\fiduciaria\microapp\microapp\1.1.0.3\microapp-1.1.0.3.war
------------------------------------------------------------------------
BUILD SUCCESS
------------------------------------------------------------------------
Total time:  23.954 s
Finished at: 2025-03-07T14:29:50-05:00
------------------------------------------------------------------------

´´
