from pytube import YouTube
from io import BytesIO


def download_audio(url):
    try:
        # Obtén el objeto YouTube
        yt = YouTube(url)

        # Obtiene la transmisión de audio de la más alta resolución disponible
        audio_stream = yt.streams.filter(only_audio=True, file_extension="webm").first()

        # Descarga la transmisión de audio en un objeto BytesIO
        audio_bytesio = BytesIO()
        audio_stream.stream_to_buffer(audio_bytesio)

        # Vuelve al inicio del objeto BytesIO antes de leer
        audio_bytesio.seek(0)

        # Lee el contenido del objeto BytesIO (archivo de audio en formato webm)
        audio_content = audio_bytesio.read()
        return bytearray(audio_content) #devuelve el contenido en formato de bytearray (para evitar problemas)
    except Exception as e:
        return f"Error: {e}"
