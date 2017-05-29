
#include <Adafruit_NeoPixel.h>

#define LEDPIN 4

//Wysyłamy dane w formie x,y,rcolor,bcolor,gcolor czyli w 11 znakowej liczbie. 
//Mamy ">" jako znak początku danych i "<" jako znak końca danych;

byte rcolor;
byte bcolor;
byte gcolor;
byte x;
byte y;
byte input;

bool read_instructions()
{
  input = Serial.read();
  if(input != '<') {
    Serial.flush();
    return 0;}
  delay(1);
byte xtemp = Serial.read();
byte ytemp = Serial.read();
byte rcolortemp = Serial.read();
byte bcolortemp = Serial.read();
byte gcolortemp = Serial.read();
input = Serial.read();
if(input != '>'){
  
  Serial.flush();
  return 0;
}
x = xtemp;
y = ytemp;
rcolor = rcolortemp;
bcolor = bcolortemp;
gcolor = gcolortemp;
return 1;
}

int way[8][8] =
{0,1,2,3,4,5,6,7,
15,14,13,12,11,10,9,8,
16,17,18,19,20,21,22,23,
31,30,29,28,27,26,25,24,
32,33,34,35,36,37,38,39,
47,46,45,44,43,42,41,40,
48,49,50,51,52,53,54,55,
63,62,61,60,59,58,57,56
  };

Adafruit_NeoPixel main_strip = Adafruit_NeoPixel(64,LEDPIN,NEO_GRB + NEO_KHZ800); 
void setup() {
  // put your setup code here, to run once:
main_strip.begin();
Serial.begin(115200);
}

void loop() {
  // put your main code here, to run repeatedly:
  if(Serial.available()>0)
  if(read_instructions())
  {
main_strip.setPixelColor(way[x][y],main_strip.Color(rcolor,bcolor,gcolor));
  main_strip.show();
  }
}
