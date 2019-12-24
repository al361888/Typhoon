package es.uji.ei1048.typhoon.weather.current;

import es.uji.ei1048.typhoon.core.City;
import es.uji.ei1048.typhoon.core.Coordinates;
import es.uji.ei1048.typhoon.core.InvalidCoordinatesException;
import es.uji.ei1048.typhoon.core.NoCityFoundException;
import es.uji.ei1048.typhoon.weather.WeatherStatus;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class ForecastWeather {
    private String apikey = "af04e9aa5c54a3a096f2178fc79f10c2";
    private String apiCoord = "http://api.openweathermap.org/data/2.5/forecast?";
    private String apiForecast = "http://api.openweathermap.org/data/2.5/forecast?q=";
    private String units = "metric"; // metric
    private String lang = "en";

    public ForecastWeather() {
    }

    /**
     *
     * @param city
     * @return WeatherStatus: Devuelve el estado actual del tiempo dada el nombre de una ciudad
     * @throws UnsupportedEncodingException
     * @throws MalformedURLException
     * @throws NoCityFoundException
     *
     */
    public List<WeatherStatus> getForecastWeatherAtCity(City city) throws IOException, NoCityFoundException {
        //Llamada al server
        String apiUrl = apiForecast + URLEncoder.encode(city.getName(), "utf-8") + "&appid=" + apikey + "&mode=json&units=" + units + "&lang="+lang;
        InputStream response = connection(apiUrl);
        if(response!=null) return fetchJsonData(response);
        else throw new NoCityFoundException();
    }

    /**
     *
     * @param coord
     * @return WeatherStatus: Devuelve el estado actual del tiempo dadas unas coordenadas
     * @throws InvalidCoordinatesException
     */
    public List<WeatherStatus> getForecastWeatherAtCoordinates(Coordinates coord) throws InvalidCoordinatesException, IOException {
        //Llamada al server
        String apiUrl = apiCoord + "lat=" + coord.getX() + "&lon=" + coord.getY() + "&appid=" + apikey + "&mode=json&units=" + units + "&lang="+ lang;
        InputStream response = connection(apiUrl);
        if(response!=null) return fetchJsonData(response);
        else throw new InvalidCoordinatesException();
    }

    /**
     *
     * @param apiUrl
     * @return InputStream: Devuelve los datos de la conexión con el servidor en un InputStream
     * @throws MalformedURLException
     */
    public InputStream connection(String apiUrl) throws MalformedURLException {
        URL url = new URL(apiUrl);
        HttpURLConnection urlConnection = null;
        InputStream response = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            response = urlConnection.getInputStream();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     *
     * @param inputStream
     * @return WeatherStatus: Devuelve los datos del InputStream convertidos en un objeto WeatherStatus
     * @throws IOException
     */
    private List<WeatherStatus> fetchJsonData(InputStream inputStream) throws IOException {
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();

        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);

        JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
        List<WeatherStatus> res = new ArrayList<WeatherStatus>();
        JSONArray list = jsonObject.getJSONArray("list");
        for(int i=0; i<=3; i++){
            JSONObject main = list.getJSONObject(i).getJSONObject("main");
            JSONObject weather = list.getJSONObject(i).getJSONArray("weather").getJSONObject(0);
            JSONObject wind = list.getJSONObject(i).getJSONObject("wind");
            WeatherStatus status = new WeatherStatus(weather.getString("main"), main.getDouble("temp"), main.getDouble("pressure"), main.getDouble("humidity"),
                    main.getDouble("temp_min"), main.getDouble("temp_max"), wind.getDouble("speed"));
            res.add(status);

        }

        return res;
    }
}
