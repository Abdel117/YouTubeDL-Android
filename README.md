# YouTubeDL-Android
Una pequeña demo para probar la librería Chaquopy para trabajar con Python y Kotlin desde Android Studio

Para ejecutar el proyecto instala Android Studio Giraffe e importa el proyecto. 
Instala Python 3.11 si aun no lo has hecho.
IMPORTANTE :
  En buil.gradle debes modificar la ruta de python: 
  Debería quedar algo como: 
  
  android{
       defaultConfig{
       python {
            version "3.11"
            buildPython "ruta/a/tu/directorio/python/python.exe"
            pip {
                // A requirement specifier, with or without a version number:
                install "pytube"
                install "Python-IO"
            }
        }
      }
    }
    
Dentro de este encontrarás las anotaciones necesarias para su correcta ejecución.
El emulador que he probado ha sido un Nexus 5X version 11.0+
Por defecto en el archivo script.py se obtienen los datos del audio, te invito a modificar esto tal y como quieras.

