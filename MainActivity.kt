package com.abdel.youtubesimpledownloader

import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import com.chaquo.python.Python
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            //Llamamos al módulo que gestiona el código de python
            val resultBytes = getSongData()
            //Guarda el contenido del enlace en el dispositivo (android/data/app/music/archivo)
            saveWebmFile(resultBytes)
        } catch (e: IOException) {
            Toast.makeText(this, "Error al descargar: ${e.message}", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Error desconocido: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getSongData(): ByteArray {
        //Accede al modulo script y llama a la funcion download_audio
        val py = Python.getInstance()
        val module = py.getModule("script")

        val audioContent = module.callAttr(
            "download_audio",
            "https://www.youtube.com/results?search_query=never+gonna+give+you+up" //Cambia el enlace con cualquier video que quieras
        )


        return audioContent.toJava(ByteArray::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun saveWebmFile(data: ByteArray) {
        try {
            val directory = getExternalFilesDir(Environment.DIRECTORY_MUSIC)

            if (directory != null) {
                val file = File(directory, "downloaded_file.webm")

                // Crea el directorio si no existe
                if (!file.parentFile?.exists()) {
                    file.parentFile?.mkdirs()
                }

                // Escribe los datos en el archivo usando un bloque use
                FileOutputStream(file).use { outputStream ->
                    outputStream.write(data)
                }

                println("Archivo descargado satisfactoriamente en: ${file.absolutePath}")
            } else {
                println("Error al obtener el directorio externo")
            }
        } catch (e: IOException) {
            println("Ha fallado: $e")
            // Trata la excepción de alguna manera (mostrar un mensaje al usuario, etc.)
        }
    }
}
