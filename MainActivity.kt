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

        //Esta versión solo funciona con videos 
        //Los videos que no se pueden descargar están especificados en la documentacion de pytube
        val url = "https://www.youtube.com/watch?v=ID_VIDEO"

        val runnable = DownloadPytubeRunnable(url)
        Thread(runnable).start()
    }

    //Clase para ejecutar la descarga en otro hilo
    inner class DownloadPytubeRunnable(val url : String) : Runnable {
        @RequiresApi(Build.VERSION_CODES.R)
        override fun run() {
            saveFilePytube(url)
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    fun saveFilePytube(url: String) {
        try {
            val sslContext = SSLContext.getInstance("TLSv1.2")
            sslContext.init(null, null, null)
            SSLContext.setDefault(sslContext)
            val py = Python.getInstance()
            val module = py.getModule("script")

            // Obtén el directorio principal de la aplicación
            val directory = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)

            // Nombre del archivo basado en el título del video
            val video = module.callAttr("YouTube", url)
            val fileName = video["title"].toString() + ".mp4"

            // Ruta completa del archivo
            val filePath = File(directory, fileName).absolutePath

            // Descarga el archivo en el directorio principal de la aplicación ./android/com.tu.proyecto/Download/fileName/
            video["streams"]?.callAttr("get_highest_resolution")?.callAttr("download", filePath)

            showToast("Archivo descargado satisfactoriamente en: $filePath")
            Log.d("Descarga Exitosa", "Archivo descargado satisfactoriamente en: $filePath")

        } catch (e: Exception) {
            // Manejar excepciones
            Log.d("Descarga Fallida", e.toString())
            showToast("Archivo no descargado")
        }
    }

    
    private fun showToast(message: String) {
        runOnUiThread {
            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun runOnUiThread(action: () -> Unit) {
        Handler(Looper.getMainLooper()).post(action)
    }
}
