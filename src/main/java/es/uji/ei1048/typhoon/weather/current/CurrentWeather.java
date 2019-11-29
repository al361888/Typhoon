package es.uji.ei1048.typhoon.weather.current;

import es.uji.ei1048.typhoon.core.City;
import es.uji.ei1048.typhoon.core.Coordinates;
import es.uji.ei1048.typhoon.core.InvalidCoordinatesException;
import es.uji.ei1048.typhoon.core.NoCityFoundException;
import es.uji.ei1048.typhoon.weather.WeatherStatus;
import org.json.JSONObject;



import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class CurrentWeather {

    private String apikey = "af04e9aa5c54a3a096f2178fc79f10c2";
    private String apiBase = "http://api.openweathermap.org/data/2.5/weather?q=";
    private String apiCoord = "http://api.openweathermap.org/data/2.5/weather?";
    //private String apiForecast = "http://api.openweathermap.org/data/2.5/forecast?q=";
    private String units = "metric"; // metric
    private String lang = "en";

    //private String openWeatherUrl = "http://api.openweathermap.org/data/2.5/forecast?id=524901&APPID=af04e9aa5c54a3a096f2178fc79f10c2";

    public CurrentWeather() {
    }

    public WeatherStatus getCurrentWeatherAtCity(City city) throws UnsupportedEncodingException, MalformedURLException, NoCityFoundException {//Incompleto
        //Llamada al server
        String apiUrl = apiBase + URLEncoder.encode(city.getName(), "utf-8") + "&appid=" + apikey + "&mode=json&units=" + units + "&lang="+lang;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(apiUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream response = urlConnection.getInputStream();
            System.out.println(response.toString());

            //Llamada a la funcion que gestiona el inputStream para sacar los datos
            return fetchJsonData(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new NoCityFoundException();
    }

    public WeatherStatus getCurrentWeatherAtCoordinates(Coordinates coord) throws InvalidCoordinatesException {//Incompleto
        //Llamada al server
        String apiUrl = apiCoord + "lat="+coord.getX()+ "&lon="+ coord.getY()+ "&appid=" + apikey + "&mode=json&units=" + units + "&lang="+lang;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(apiUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream response = urlConnection.getInputStream();
            System.out.println(response.toString());

            //Llamada a la funcion que gestiona el inputStream para sacar los datos
            return fetchJsonData(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Llamada a la funcion que gestiona el JSON
        throw new InvalidCoordinatesException();
    }

    private WeatherStatus fetchJsonData(InputStream inputStream) throws IOException {
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();

        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);

        JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());

        JSONObject main = jsonObject.getJSONObject("main");

        WeatherStatus status = new WeatherStatus(main.getDouble("temp"), main.getDouble("pressure"), main.getDouble("humidity"),
                main.getDouble("temp_min"), main.getDouble("temp_max"));
        return status;
    }
}
