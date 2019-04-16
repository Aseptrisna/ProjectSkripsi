#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
#include <dummy.h>
#include <ESP8266WiFi.h>
#include <ArduinoJson.h>
float Volt1,volt ;
String  kondisi;
const char* ssid     = "ARDUINO NUSANTARA";  
const char* password = "02091995";
String request_string;
const char* host     = "monitoringaki.000webhostapp.com"; // Your domain  
String path          = "/sim900/light.json";  
const int pin        = D5;
const int pinn        = D4;
HTTPClient http;

void setup() {  
  pinMode(pin, OUTPUT); 
  pinMode(pin, HIGH);
  Serial.begin(115200);

  delay(10);
  Serial.print("Connecting to ");
  Serial.println(ssid);

  WiFi.begin(ssid, password);
  int wifi_ctr = 0;
  while (WiFi.status() != WL_CONNECTED) {
    delay(2000);
    Serial.print(".");
  }

  Serial.println("WiFi connected");  
  Serial.println("IP address: " + WiFi.localIP());
}

void loop() {  

  Serial.print("connecting to ");
  Serial.println(host);
  WiFiClient client;
  const int httpPort = 80;
  if (!client.connect(host, httpPort)) {
    Serial.println("connection failed");
    return;
  }

  client.print(String("GET ") + path + " HTTP/1.1\r\n" +
               "Host: " + host + "\r\n" + 
               "Connection: keep-alive\r\n\r\n");

  delay(500); // wait for server to respond

  // read response
  String section="header";
  while(client.available()){
    String line = client.readStringUntil('\r');
    // Serial.print(line);
    // we’ll parse the HTML body here
    if (section=="header") { // headers..
      Serial.print(".");
      if (line=="\n") { // skips the empty space at the beginning 
        section="json";
      }
    }
    else if (section=="json") {  // print the good stuff
      section="ignore";
      String result = line.substring(1);

      // Parse JSON
      int size = result.length() + 1;
      char json[size];
      result.toCharArray(json, size);
      StaticJsonBuffer<200> jsonBuffer;
      JsonObject& json_parsed = jsonBuffer.parseObject(json);
      if (!json_parsed.success())
      {
        Serial.println("parseObject() failed");
        return;
      }

      // Make the decision to lock and unlock
      if (strcmp(json_parsed["light"], "on") == 0) {
        digitalWrite(pin, LOW); 
        //digitalWrite(pinn, HIGH); 
        Serial.println("LED OFF");
      }
      else {
        digitalWrite(pin, HIGH);
       // digitalWrite(pinn, LOW); 
        Serial.println("LED ON");
      }


      // Make the decision to lock and unlock
      if (strcmp(json_parsed["light"], "on") == 0) {
        digitalWrite(pinn, HIGH); 
       // digitalWrite(pinn, HIGH); 
        Serial.println("LED ON1");
      }
      else {
        digitalWrite(pinn, LOW);
       // digitalWrite(pinn, LOW); 
        Serial.println("LED OFF1");
      }
    }
  }
  Serial.print("closing connection. ");
 Volt1=analogRead (A0) ;
  volt=(Volt1* 0.00305) *5.0;
      Serial.print(volt);
      Serial.println("V"); 
   delay(1000);
//   Serial.println(link);


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
//    request_string +=link;
   //request_string += "&data3=";
   // request_string += kondisi;
    Serial.print("requesting URL: ");
    Serial.println(request_string);
   client.print(String("GET ") + request_string + " HTTP/1.1\r\n" + "Host: " + host + "\r\n" + "Connection: close\r\n\r\n");
  
 
  }
   
  
