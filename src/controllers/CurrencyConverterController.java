/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;

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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author Habib
 */
public class CurrencyConverterController implements Initializable {

    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private TextField convertCurrencyField;

    @FXML
    private TextField amountField;

    @FXML
    private Label bLabel1;

    @FXML
    private Label bLabel10;

    @FXML
    private Label bLabel101;

    @FXML
    private Label bLabel11;

    @FXML
    private Label bLabel25;

    @FXML
    private Label bLabel251;

    @FXML
    private Label bLabel5;

    @FXML
    private Label bLabel50;

    @FXML
    private Label bLabel501;

    @FXML
    private Label bLabel51;

    @FXML
    private TextField baseCurrencyField;

    @FXML
    private Label baseCurrencyUnitLabel;

    @FXML
    private Label cLabel1;

    @FXML
    private Label cLabel10;

    @FXML
    private Label cLabel101;

    @FXML
    private Label cLabel11;

    @FXML
    private Label cLabel25;

    @FXML
    private Label cLabel251;

    @FXML
    private Label cLabel5;

    @FXML
    private Label cLabel50;

    @FXML
    private Label cLabel501;

    @FXML
    private Label cLabel51;

    @FXML
    private Button connvertBtn;

    @FXML
    private Label convertedCurrencyLabel;

    @FXML
    private Label invertCurrencyUnitLabel;

    @FXML
    private Button profileBtn;

    @FXML
    private ImageView swapCurrencyBtn;
    
    @FXML
    private Label warningLabel;
    
    @FXML
    private Label csConvertFromLabel;
    
    @FXML
    private Label csConvertToLabel;
    
    @FXML
    private Label inConvertFromLabel;
    
    @FXML
    private Label inConvertToLabel;
    
    @FXML
    
    
    
    //Gyi8ZFgwKYahCBOBAYSWldyUpNx5fA9C  -- 250 will renew at feb
    
    
    public static String apikey = "gE40uxZ9RZTIj8qgnikDeuMQn0y2X2wD";
    public static String convertTo = "BDT";
    public static String convertFrom = "USD";
    public static double amount = 1;
    public static double rate=106.15;
    static String convertedAmoun="106.15";
    static boolean validCurrency = false;
    
    
    static double invertRate = 0.0094;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        validCurrency = false;
        setData();
        //convertCurrency();
        
    }
    
    public void convertBtnAction(ActionEvent event){
        getData();
        warningLabel.setText("");
        convertCurrency();
        isValid();     
    }
    
    public void getData(){
        try{
            convertFrom = baseCurrencyField.getText().toUpperCase();
            convertTo = convertCurrencyField.getText().toUpperCase();
            amount = Double.valueOf(amountField.getText());
        }
        catch(Exception e)
        {
            convertFrom="";
            convertTo="";
        }
    }
    public void isValid(){
        if(validCurrency)
        {
            setData();
        }
        else
        {
            warningLabel.setText("Please Enter Valid currency and amount. Amonunt must be number.");
        }
    }
    
    
    
    public void setData(){
        convertedCurrencyLabel.setText(amount+" "+convertFrom+" = "+convertedAmoun+" "+convertTo);
        if(amount == 1)
        {
            baseCurrencyUnitLabel.setText("1 "+convertTo+" = "+getFormattedValue(invertRate)+" "+convertFrom);
            invertCurrencyUnitLabel.setText("");

        }
        else
        {
            baseCurrencyUnitLabel.setText("1 "+convertFrom+" = "+getFormattedValue(rate)+" "+convertTo);
            invertCurrencyUnitLabel.setText("1 "+convertTo+" = "+getFormattedValue(invertRate)+" "+convertFrom);
        }
        
        
        //Traveler CheatSheet
        
        //title bar
        csConvertFromLabel.setText(convertFrom);
        csConvertToLabel.setText(convertTo);
        inConvertFromLabel.setText(convertTo);
        inConvertToLabel.setText(convertFrom);
        
        //traveler cheatsheet base to foreign
        cLabel1.setText(getFormattedValue_2(rate));
        cLabel5.setText(getFormattedValue_2(rate*5));
        cLabel10.setText(getFormattedValue_2(rate*10));
        cLabel25.setText(getFormattedValue_2(rate*25));
        cLabel50.setText(getFormattedValue_2(rate*50));
        //invert rate 
        cLabel11.setText(getFormattedValue_2(invertRate));
        cLabel51.setText(getFormattedValue_2(invertRate*5));
        cLabel101.setText(getFormattedValue_2(invertRate*10));
        cLabel251.setText(getFormattedValue_2(invertRate*25));
        cLabel501.setText(getFormattedValue_2(invertRate*50));   
    }
    
    public void swapButtonAction(ActionEvent event){
        String temp=convertFrom;
        convertFrom = convertTo;
        convertTo = temp;
        warningLabel.setText("");
        baseCurrencyField.setText(convertFrom);
        convertCurrencyField.setText(convertTo);
        convertCurrency();
    }
    
    public String getFormattedValue(double rate)
    {
        return String.format("%.3f",rate);
    }
    public String getFormattedValue_2(double rate)
    {
        return String.format("%.2f",rate);
    }
    public void convertCurrency(){
        try {
            //https://api.apilayer.com/exchangerates_data/convert?to=bdt&from=usd&amount=100&apikey=Gyi8ZFgwKYahCBOBAYSWldyUpNx5fA9C
            
            //General Converter 
            //Pass in all the parameters since getCity calls getWeatherReport
            URL url = new URL("https://api.apilayer.com/exchangerates_data/convert?to="+convertTo+"&from="+convertFrom+"&amount="+amount+"&apikey="+apikey);
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
            
            //Inverted 
            URL urlIvert = new URL("https://api.apilayer.com/exchangerates_data/convert?to="+convertFrom+"&from="+convertTo+"&amount="+amount+"&apikey="+apikey);
            //Create connections to the API
            HttpURLConnection httpURLConnection1=(HttpURLConnection)urlIvert.openConnection();
            httpURLConnection1.setRequestMethod("GET");

            //Get the response JSON
            line="";
            InputStreamReader inputStreamReader1 =new InputStreamReader(httpURLConnection1.getInputStream());
            BufferedReader bufferedReader1=new BufferedReader(inputStreamReader1);
            StringBuilder response1=new StringBuilder();
            while ((line=bufferedReader1.readLine())!=null){
                response1.append(line);
            }
            bufferedReader1.close();
            JSONObject jsonObject1=new JSONObject(response1.toString());
            validCurrency = true;
            rate = jsonObject.getJSONObject("info").getDouble("rate");
            //var convertedAmmount = (double)jsonObject.getDouble("reult");
            convertedAmoun = getFormattedValue(jsonObject.getDouble("result"));
            invertRate = jsonObject1.getJSONObject("info").getDouble("rate");
            
        }
         catch (Exception e){
            System.out.println("Error in Making Get Request");
            validCurrency = false;
        }
    }
    
    
    
    public void goToHomePage(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../views/home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
    public void goToLoginPage(ActionEvent event) throws IOException{
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
