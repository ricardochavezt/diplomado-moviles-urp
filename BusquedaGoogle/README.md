Aplicación de búsqueda en Google
--------------------------------

Esta aplicación de ejemplo se conecta a una API de Google para obtener
los resultados de búsqueda para un término en particular.

**Nota**: estamos utilizando un API que ya está deprecated por Google por 
facilidad, ya que esta no requiere de una API Key para funcionar. Google 
recomienda utilizar ahora el Custom Search API 
(<https://developers.google.com/custom-search/v1/overview>).

Los puntos importantes son:

- AndroidManifest.xml: los permisos requeridos por la aplicación, en
  especial `android.permission.INTERNET`
- ResultadosActivity.java: aquí está todo el código importante:
  - realizarBusquedaGoogle se encarga de crear en AsyncHttpClient para
    la conexión a la API de Google y de procesar las respuestas de éxito
    o fallo
  - parsearJSON se encarga de convertir la respuesta JSON en un listado
    de objetos que podamos utilizar con el SimpleAdapter
  - cargarResultados utiliza el SimpleAdapter y el listado generado por
    parsearJSON para cargar la información al ListView
- resultado\_list\_item.xml: layout de cada uno de los elementos del
  listado.
