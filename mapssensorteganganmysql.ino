#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <TinyGPS++.h>
#include <SoftwareSerial.h>
SoftwareSerial serial_gps(D1, D2); 
TinyGPSPlus gps;

double latitude,longitude;
double lati,longi;
float Volt1,volt ;
String  kondisi;
WiFiClient client;
String request_string;
const char* host ="monitoringaki.000webhostapp.com"; // ip computer agan, bisa di lihat ipv4 di CMD dengan perintah ipconfig
HTTPClient http;
void setup()
{
    WiFi.disconnect();
   WiFi.begin("ARDUINO NUSANTARA","02091995"); // nama wifi ssid dan Passwordnya
    while ((!(WiFi.status() == WL_CONNECTED))){
      delay(300);
    }
    Serial.begin(9600);
    serial_gps.begin(9600);
  Serial.println("GPS Mulai");
}

void loop(){
 while(serial_gps.available()) {
    gps.encode(serial_gps.read());
  }
  if(gps.location.isUpdated()) {
    latitude = gps.location.lat();
    longitude = gps.location.lng();
    String link = "www.google.com/maps/place/" + String(latitude,10) + "," + String(longitude,10) ;
    Serial.println(link);
    String lati= String(latitude,10);
    String longi= String(longitude,10);
    Serial.println(lati);
    Serial.println(longi);
    delay(5000);
  //}
    String sensor1 = String(latitude,10);
    String sensor2 = String(longitude,10);
 
    if (!client.connect(host,80)) {
      Serial.println("Gagal Konek");
      return;
    }
    request_string = "/sim900/dua.php?data1=";
    request_string += sensor1;
    request_string += "&data2=";
    request_string += sensor2;
    Serial.print("requesting URL: ");
    Serial.println(request_string);
   client.print(String("GET ") + request_string + " HTTP/1.1\r\n" + "Host: " + host + "\r\n" + "Connection: close\r\n\r\n");
  
 
    unsigned long timeout = millis();
  while (client.available() == 0) {
    if (millis() - timeout > 5000) {
      Serial.println(">>> Client Timeout !");
      client.stop();
      return;
  }
       
    }
    Volt1=analogRead (A0) ;
  volt=(Volt1* 0.00305) *5.0;
      Serial.print(volt);
      Serial.println("V"); 
   delay(1000);
   Serial.println(link);


if (volt < 12) {
  kondisi="Baterai low";
}

if (volt > 14) {
  kondisi="pengisian baterai over";
}

if (volt ==12) {
  kondisi="normal mesin off";
}
if (volt == 13) {
  kondisi="normal mesin on";
}
Serial.println(kondisi);

   
   if (!client.connect(host,80)) {
      Serial.println("Gagal Konek");
      return;
    }
    request_string = "/sim900/input.php?data1=";
    request_string += volt;
    request_string += "&data2=";
    request_string +=link;
   //request_string += "&data3=";
   // request_string += kondisi;
    Serial.print("requesting URL: ");
    Serial.println(request_string);
   client.print(String("GET ") + request_string + " HTTP/1.1\r\n" + "Host: " + host + "\r\n" + "Connection: close\r\n\r\n");
  
 
  }
   
  }
    

