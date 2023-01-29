
package controllers;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONObject;

/**
 *
 * @author Habib
 */
public class CheckWeatherController implements Initializable{
    private Parent root;
    private Stage stage;
    private Scene scene;
    
    @FXML
    private Button aboutBtn;

    @FXML
    private Label airPressureLabel;

    @FXML
    private Button bookBtn;

    @FXML
    private Button checkWBtn;

    @FXML
    private Label dateLabel;
    
    @FXML 
    private Label dayLabel;
             
    @FXML
    private Label dateNtimeLabel;

    @FXML
    private Button emergencyBtn;

    @FXML
    private Button exchangeBtn;

    @FXML
    private Label feelsLikeLabel;

    @FXML
    private Button homeBtn;

    @FXML
    private Label humidityLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private Button planBtn;

    @FXML
    private Button profileBtn;

    @FXML
    private Button settingsBtn;

    @FXML
    private Label sunRiseLabel;

    @FXML
    private Label sunSetLabel;

    @FXML
    private Button supportBtn;

    @FXML
    private Label tempLabel;

    @FXML
    private Button travelWUsBtn;

    @FXML
    private Label weatherConditionLabel;

    @FXML
    private Label windSpeedLabel;

    @FXML
    private TextField searchCityBar;

    @FXML 
    private Label warningLabel;
    
    //API Key 
    static String APPID = "b702a4286daab5d7af2a7409d0a68275";
    //City Name
    static String city="dhaka";
    boolean validCity=false;
    
    //All the data get from API Call;
    static String cityName="";
    static String countryName ="";
    static String time="";
    static String weatherCondition="";
    static String tempInC="";
    static String tempFeels="";
    static double pressure=0;
    static int humidity=0;
    static String sunRise="";
    static String sunSet="";
    static double windSpeed =0;
    static String date = "";
    static String day ="";
    
    /**
     * Initializes the controller class.
     */
        public void initialize(URL url, ResourceBundle rb) {
        getWeatherReport();
        setData(); 
    }
    
    public void searchCity(){
        city = searchCityBar.getText().toLowerCase(); 
        try {
                URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+APPID);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();

                //If the city doesn't exist, this line will trigger an exception.
                InputStreamReader inputStreamReader=new InputStreamReader(httpURLConnection.getInputStream());
                     validCity =true;
            }	 
        catch (Exception e) {
                validCity = false;
        }
        
        if(validCity)
        {
            getWeatherReport();
            setData();
            
        }
        else
        {
            warningLabel.setText("Please enter a valid city.");
        }
        

    }
    
    
    public void searchBtnClick(ActionEvent event){
       warningLabel.setText("");
       searchCity();
    }
    public static String capitalize(String message) {
    	
    	//Find the spaces, capitalize what's after.
        char[] charArray = message.toCharArray();
        boolean foundSpace = true;
        for(int i = 0; i < charArray.length; i++) {
          if(Character.isLetter(charArray[i])) {
            if(foundSpace) {
              charArray[i] = Character.toUpperCase(charArray[i]);
              foundSpace = false;
            }
          }
          else 
            foundSpace = true;
        }
        //String.valueOf converts to a string.
       return String.valueOf(charArray);
      }
    public static String KtoF(double temp) {
    	
    	//Convert to string to be able to concat with print statements
        return String.format("%.2f",(temp - 273.15) * 9/5 + 32);
    }
    public static String KtoC(double temp) {
    	
    	//Convert to string to be able to concat with print statements
        return String.format("%.1f",(temp - 273.15));
    }
    public static String KtoCLow(double temp) {
    	
    	//Convert to string to be able to concat with print statements
        return String.format("%.2f",(temp - 273.15-2.3));
    }
    public static String KtoCHigh(double temp) {
    	
    	//Convert to string to be able to concat with print statements
        return String.format("%.2f",(temp - 273.15+2.5));
    }
    public static String getDateTime(long time, String f) throws InterruptedException {
    	
    		//Takes in milliseconds
            Date date = new Date(time);
            //The "f" parameter is to specify what we want from the time stamp.
            DateFormat format = new SimpleDateFormat(f);
            format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
            return format.format(date);
        }  
    public static double getWindSpeed(double windSpeed)
    {
        if(windSpeed == 0)
        {
            windSpeed = 1.9;
        }
        return windSpeed;
          
    }
    public  void getWeatherReport(){
        try {
            //https://api.openweathermap.org/data/2.5/weather?q=khulna&appid=b702a4286daab5d7af2a7409d0a68275
            //Pass in all the parameters since getCity calls getWeatherReport
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+APPID);
            //Create connections to the API
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            //Get the response JSON
            String line="";
            InputStreamReader inputStreamReader=new InputStreamReader(httpURLConnection.getInputStream());
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            StringBuilder response=new StringBuilder();
            while ((line=bufferedReader.readLine())!=null){
                response.append(line);
            }
            bufferedReader.close();
            
            //Parse through the JSON 
            //JSONObject = {}, JSONArray = []
            JSONObject jsonObject=new JSONObject(response.toString());
                //var cityName =jsonObject.getString("name");
                cityName = capitalize(city);
                countryName = jsonObject.getJSONObject("sys").getString("country");
                time = getDateTime(System.currentTimeMillis() + (jsonObject.getInt("timezone")*1000), "hh:mm a z");
                date = getDateTime(System.currentTimeMillis() + (jsonObject.getInt("timezone")*1000), "dd MMMM yyyy");
                day = getDateTime(System.currentTimeMillis() + (jsonObject.getInt("timezone")*1000), "E ");
                weatherCondition = capitalize(jsonObject.getJSONArray("weather").getJSONObject(0).getString("description"));
                //var tempInF = KtoF(jsonObject.getJSONObject("main").getDouble("temp"));
                tempInC = KtoC(jsonObject.getJSONObject("main").getDouble("temp"));
                tempFeels = KtoC(jsonObject.getJSONObject("main").getDouble("feels_like"));
                //var tempLow = KtoCLow(jsonObject.getJSONObject("main").getDouble("temp"));
                //var tempHigh = KtoCHigh(jsonObject.getJSONObject("main").getDouble("temp"));
                pressure = (jsonObject.getJSONObject("main").getDouble("pressure"));
                humidity = jsonObject.getJSONObject("main").getInt("humidity");
                
                sunRise = getDateTime((jsonObject.getJSONObject("sys").getLong("sunrise")+jsonObject.getLong("timezone"))*1000, "hh:mm a");
                sunSet = getDateTime((jsonObject.getJSONObject("sys").getLong("sunset")+jsonObject.getLong("timezone"))*1000, "hh:mm a");
                windSpeed = getWindSpeed((jsonObject.getJSONObject("wind").getDouble("speed")));
            //Set it back to show the rest
            
        }
        catch (Exception e){
            System.out.println("Error in Making Get Request");
            warningLabel.setText("Error in Making Get Request. Please try again.");
        }
        
        //Boolean in parameter gives out different print statements weather or not the city they input was a real city.
        
      }
    public void setData()
    {
        tempLabel.setText(tempInC);
        locationLabel.setText(cityName+", "+countryName);
        weatherConditionLabel.setText(weatherCondition);
        feelsLikeLabel.setText(tempFeels+" °C");
        humidityLabel.setText(Integer.toString(humidity)+"%");
        airPressureLabel.setText(Double.toString(pressure)+"");
        dateNtimeLabel.setText(time);
        windSpeedLabel.setText(Double.toString(windSpeed)+" kmh");
        sunRiseLabel.setText(sunRise);
        sunSetLabel.setText(sunSet);
        dateLabel.setText(date);
        dayLabel.setText(day);
    }
    public void goToHomePage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../views/home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    public void goToLoginPage(ActionEvent event) throws IOException{
        System.out.println("Hello");
        root = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goToRegisterPage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../views/signup.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }   
    public void goToPlanATourPage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../views/planatour.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();  
    }
    public void goToCheckWeatherPage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../views/checkweather.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    public void goToExchangeRatePage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../views/convertcurrency.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    public void goToEmergencySOSPage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../views/sos.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    public void goToBookAnythingPage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../views/bookanything.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    public void goToTravelWithTravsyPage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../views/travelwithtravsy.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    public void goToSettingsPage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../views/setting.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    public void goToSupportPage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../views/support.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    public void goToAboutPage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../views/about.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    public void profileIconClick(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../views/profile.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();   
    }
    
    
 
}
