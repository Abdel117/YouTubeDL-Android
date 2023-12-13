# YouTubeDL-Android
YouTube Simple Downloader
Una pequeña demo para probar la librería Chaquopy para trabajar con Python y Kotlin desde Android Studio

- Instala Python 3.8 si aun no lo has hecho.
- Para probar el proyecto instala Android Studio Giraffe y crea un proyecto en Kotlin con Gradle Groovy (Mi recomendación) y seguidamente copia los archivos del repositorio.

IMPORTANTE :
  Debes seguir los pasos indicados en https://chaquo.com/chaquopy/doc/current/android.html para añadir el plugin de Chaquopy

- Modifica la ruta a tu python.exe y a tu directorio de scripts python tal y como lo indica la documentación proporcionada. Por lo general para el directorio de scripts se recomienda src/main/python.
- Añade las dependencias necesarias de python (pytube y Python-IO) como se indica anteriormente.
- Dentro del código encontrarás las anotaciones necesarias para su correcta ejecución.
- RECUERDA AÑADIR CORRECTAMENTE LOS PERMISOS DE ESCRITURA Y ACCESO A INTERNET.
- Establece la url de tu vídeo como se especifica.
- Por defecto solo se muestra un Toast con la información de la ejecución.
  
Info: 
* Para aprender como se usa chaquopy en profundidad revisa su documentación y ejemplos
* La demo ha sido debuggeada en un móvil físico
* La UI queda a tu creatividad.

Errores comunes: 
* Los errores más comunes tienen que ver con la conexión a internet. Si estás usando un emulador: revisa los permisos de android studio y la configuración de internet. Si el problema persiste prueba con un móvil android físico.
