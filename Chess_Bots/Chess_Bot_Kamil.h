using namespace std;
#include <string>
vector<vector<string> > board ={
        {"r","k","b","q","a","b","k","r"},
        {"p","p","p","p","p","p","p","p"},
        {" "," "," "," "," "," "," "," "},
        {" "," "," "," "," "," "," "," "},
        {" "," "," "," "," "," "," "," "},
        {" "," "," "," "," "," "," "," "},
        {"P","P","P","P","P","P","P","P"},
        {"R","K","B","Q","A","B","K","R"}};
     int pawnBoard[8][8]={
        { 0,  0,  0,  0,  0,  0,  0,  0},
        {50, 50, 50, 50, 50, 50, 50, 50},
        {10, 10, 20, 30, 30, 20, 0, 10},
        { 5,  5, 10, 25, 25, 10,  5,  5},
        { 0,  0,  0, 20, 20,  0,  0,  0},
        { 5, -5,-10,  0,  0,-10, -5,  5},
        { 5, 10, 10,-20,-20, 10, 10,  5},
        { 0,  0,  0,  0,  0,  0,  0,  0}};
     int rookBoard[8][8]={
        { 0,  0,  0,  0,  0,  0,  0,  0},
        { 5, 10, 10, 10, 10, 10, 10,  5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        { 0,  0,  0,  5,  5,  0,  0,  0}};
     int knightBoard[8][8]={
        {-50,-40,-30,-30,-30,-30,-40,-50},
        {-40,-20,  0,  0,  0,  0,-20,-40},
        {-30,  0, 10, 15, 15, 10,  0,-30},
        {-30,  5, 15, 20, 20, 15,  5,-30},
        {-30,  0, 15, 20, 20, 15,  0,-30},
        {-30,  5, 10, 15, 15, 10,  5,-30},
        {-40,-20,  0,  5,  5,  0,-20,-40},
        {-50,-40,-30,-30,-30,-30,-40,-50}};
     int bishopBoard[8][8]={
        {-20,-10,-10,-10,-10,-10,-10,-20},
        {-10,  0,  0,  0,  0,  0,  0,-10},
        {-10,  0,  5, 10, 10,  5,  0,-10},
        {-10,  5,  5, 10, 10,  5,  5,-10},
        {-10,  0, 10, 10, 10, 10,  0,-10},
        {-10, 10, 10, 10, 10, 10, 10,-10},
        {-10,  5,  0,  0,  0,  0,  5,-10},
        {-20,-10,-10,-10,-10,-10,-10,-20}};
     int queenBoard[8][8]={
        {-20,-10,-10, -5, -5,-10,-10,-20},
        {-10,  0,  0,  0,  0,  0,  0,-10},
        {-10,  0,  5,  5,  5,  5,  0,-10},
        { -5,  0,  5,  5,  5,  5,  0, -5},
        {  0,  0,  5,  5,  5,  5,  0, -5},
        {-10,  5,  5,  5,  5,  5,  0,-10},
        {-10,  0,  5,  0,  0,  0,  0,-10},
        {-20,-10,-10, -5, -5,-10,-10,-20}};
     int kingMidBoard[8][8]={
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-20,-30,-30,-40,-40,-30,-30,-20},
        {-10,-20,-20,-20,-20,-20,-20,-10},
        { 20, 20,  0,  0,  0,  0, 20, 20},
        { 20, 30, 10,  0,  0, 10, 30, 20}};
     int kingEndBoard[8][8]={
        {-50,-40,-30,-20,-20,-30,-40,-50},
        {-30,-20,-10,  0,  0,-10,-20,-30},
        {-30,-10, 20, 30, 30, 20,-10,-30},
        {-30,-10, 30, 40, 40, 30,-10,-30},
        {-30,-10, 30, 40, 40, 30,-10,-30},
        {-30,-10, 20, 30, 30, 20,-10,-30},
        {-30,-30,  0,  0,  0,  0,-30,-30},
        {-50,-30,-30,-30,-30,-30,-30,-50}};
string sub(int a,int b,string c)
{
    string res="";
    for (int i=a;i<b;i++)
    {
        res+=c[i];
    }
    return res;
}
int getKingPosUpper()
{
    int temp=0;
    while(board[temp/8][temp%8]!="A"){temp++;}
    return temp;
}
int getKingPosLower()
{
    int temp=0;
    while(board[temp/8][temp%8]!="a"){temp++;}
    return temp;
}
int kingPosUpper = getKingPosUpper();
int kingPosLower = getKingPosLower();
int gDepth=3;
string best="";
bool side = true;
int counter=0;
int bscore=-9999999;
bool kingSafe()
{
kingPosUpper=getKingPosUpper();
kingPosLower=getKingPosLower();
    // b/q
    int temp=1;
    for (int i=-1; i<=1; i+=2) {
            for (int j=-1; j<=1; j+=2) {
                try{
                    while (board.at(kingPosUpper/8+temp*i).at(kingPosUpper%8+temp*j)==" "){temp++;}
                    if(board.at(kingPosUpper/8+temp*i).at(kingPosUpper%8+temp*j)=="b" || board.at(kingPosUpper/8+temp*i).at(kingPosUpper%8+temp*j)=="q")
                        return false;
                } catch (...) {}
                temp=1;
            }
    }
    // r/q
    temp=1;
    for (int i=-1; i<=1; i+=2) {
            try {
                while (board.at(kingPosUpper/8).at(kingPosUpper%8+temp*i)==" ") {temp++;}
                if (board.at(kingPosUpper/8).at(kingPosUpper%8+temp*i)=="r" || board.at(kingPosUpper/8).at(kingPosUpper%8+temp*i)=="q")
                    return false;
            } catch (...) {}
            temp=1;
            try {
                while (board.at(kingPosUpper/8+temp*i).at(kingPosUpper%8)==" ") {temp++;}
                if (board.at(kingPosUpper/8+temp*i).at(kingPosUpper%8)=="r" || board.at(kingPosUpper/8+temp*i).at(kingPosUpper%8)=="q")
                    return false;
            } catch (...) {}
            temp=1;
    }
    // k
    for (int i=-1; i<=1; i+=2) {
            for (int j=-1; j<=1; j+=2) {
                    try{
                        if (board.at(kingPosUpper/8+i).at(kingPosUpper+j*2)=="k")
                            return false;
                    } catch (...) {}
                    try{
                        if (board.at(kingPosUpper/8+i*2).at(kingPosUpper+j)=="k")
                            return false;
                    } catch (...) {}
            }
    }
    // p
    if (kingPosUpper>=16)
    {
        try{
        if (board.at(kingPosUpper/8-1).at(kingPosUpper%8-1)=="p")
            return false;
            } catch (...) {}
        try{
        if (board.at(kingPosUpper/8-1).at(kingPosUpper%8+1)=="p")
            return false;
            } catch (...) {}
            // a
            for (int i=-1; i<=1; i++) {
                for (int j=-1; j<=1; j++) {
                    if (i!=0 || j!=0) {
                            try{
                                if(board.at(kingPosUpper/8+i).at(kingPosUpper%8+j)=="a")
                                    return false;
                            } catch (...) {}
                    }
                }
            }
    }
    return true;
}
string to_string(int a)
{
stringstream ss;
ss << a;
return ss.str();
}
string tolow(string a)
{
    string res="";
    for (int i=0;i!=a.size();i++)
        res+=tolower(a[i]);
    return res;
}
string toupp(string a)
{
    string res="";
    for (int i=0;i!=a.size();i++)
        res+=toupper(a[i]);
    return res;
}
int to_int(string a)
{
stringstream ss;
ss << a;
int res=0;
ss >> res;
return res;
}
int to_int(char a)
{
return a-'0';
}
string pA (int coord)
{
string result="",tempchar="";
int temppos;
int x=coord/8;
int y=coord%8;
for (int i=0;i<9;i++) {
            if (i!=4) {
                try {
                        if (islower(board.at((x-1)+i/3).at((y-1)+i%3).at(0)) || board.at((x-1)+i/3).at((y-1)+i%3)==" ")
                        {
                            tempchar=board[(x-1)+i/3][(y-1)+i%3];
                            board[x][y]=" ";
                            board[(x-1)+i/3][(y-1)+i%3]="A";
                            temppos=kingPosUpper;
                            kingPosUpper=coord+(i/3)*8+i%3-9;
                            if (kingSafe())
                            {
                                result+=to_string(x)+to_string(y)+to_string((x-1)+i/3)+to_string((y-1)+i%3)+tempchar;
                            }
                            board[x][y]="A";
                            board[(x-1)+i/3][(y-1)+i%3]=tempchar;
                            kingPosUpper=temppos;
                    }
                } catch (...) {}
            }
        }
    return result;
}
string pQ (int coord)
{
    string result="",tempchar="";
    int temp=1;
    int x=coord/8;
    int y=coord%8;
    for (int i=-1; i<=1; i++)
            for (int j=-1; j<=1; j++) {
                if (i!=0 || j!=0)
                {
                    try{
                        while (board.at(x+temp*i).at(y+temp*j)==" ")
                        {
                            tempchar=board.at(x+temp*i).at(y+temp*j);
                            board.at(x).at(y)=" ";
                            board.at(x+temp*i).at(y+temp*j)="Q";
                            if (kingSafe())
                            {
                                result+=to_string(x)+to_string(y)+to_string(x+temp*i)+to_string(y+temp*j)+tempchar;
                            }
                            board.at(x).at(y)="Q";
                            board.at(x+temp*i).at(y+temp*j)=tempchar;
                            temp++;
                        }
                        if (islower(board.at(x+temp*i).at(y+temp*j).at(0)))
                        {
                            tempchar=board.at(x+temp*i).at(y+temp*j);
                            board.at(x).at(y)=" ";
                            board.at(x+temp*i).at(y+temp*j)="Q";
                            if (kingSafe())
                            {
                                result+=to_string(x)+to_string(y)+to_string(x+temp*i)+to_string(y+temp*j)+tempchar;
                            }
                            board.at(x).at(y)="Q";
                            board.at(x+temp*i).at(y+temp*j)=tempchar;
                        }
                    } catch (...) {}
                    temp=1;
                }

                }
    return result;

}
string pR (int coord)
{
     string result="",tempchar="";
    int temp=1;
    int x=coord/8;
    int y=coord%8;
    for (int i=-1; i<=1; i+=2) {
            try {
                while (board.at(x).at(y+temp*i)==" ")
                {

                            tempchar=board.at(x).at(y+temp*i);
                            board.at(x).at(y)=" ";
                            board.at(x).at(y+temp*i)="R";
                            if (kingSafe())
                            {
                                result+=to_string(x)+to_string(y)+to_string(x)+to_string(y+temp*i)+tempchar;
                            }
                            board.at(x).at(y)="R";
                            board.at(x).at(y+temp*i)=tempchar;
                            temp++;
                }
                if (islower(board.at(x).at(y+temp*i).at(0)))
                {
                            tempchar=board.at(x).at(y+temp*i);
                            board.at(x).at(y)=" ";
                            board.at(x).at(y+temp*i)="R";
                            if (kingSafe())
                            {
                                result+=to_string(x)+to_string(y)+to_string(x)+to_string(y+temp*i)+tempchar;
                            }
                            board.at(x).at(y)="R";
                            board.at(x).at(y+temp*i)=tempchar;
                }
                } catch (...) {}
                temp=1;
                try {
                while (board.at(x+temp*i).at(y)==" ")
                {

                            tempchar=board.at(x+temp*i).at(y);
                            board.at(x).at(y)=" ";
                            board.at(x+temp*i).at(y)="R";
                            if (kingSafe())
                            {
                                result+=to_string(x)+to_string(y)+to_string(x+temp*i)+to_string(y)+tempchar;
                            }
                            board.at(x).at(y)="R";
                            board.at(x+temp*i).at(y)=tempchar;
                            temp++;
                }
                if (islower(board.at(x+temp*i).at(y).at(0)))
                {
                            tempchar=board.at(x+temp*i).at(y);
                            board.at(x).at(y)=" ";
                            board.at(x+temp*i).at(y)="R";
                            if (kingSafe())
                            {
                                result+=to_string(x)+to_string(y)+to_string(x+temp*i)+to_string(y)+tempchar;
                            }
                            board.at(x).at(y)="R";
                            board.at(x+temp*i).at(y)=tempchar;
                }
                } catch (...) {}
                temp=1;
    }
    return result;
}
string pB (int coord)
{
    string result="",tempchar="";
    int temp=1;
    int x=coord/8;
    int y=coord%8;
    for (int i=-1; i<=1; i+=2) {
            for (int j=-1; j<=1; j+=2) {
                    try{
                        while (board.at(x+temp*i).at(y+temp*j)==" ")
                        {
                            tempchar=board.at(x+temp*i).at(y+temp*j);
                            board.at(x).at(y)=" ";
                            board.at(x+temp*i).at(y+temp*j)="B";
                            if (kingSafe())
                            {
                                result+=to_string(x)+to_string(y)+to_string(x+temp*i)+to_string(y+temp*j)+tempchar;
                            }
                            board.at(x).at(y)="B";
                            board.at(x+temp*i).at(y+temp*j)=tempchar;
                            temp++;
                        }
                        if (islower(board.at(x+temp*i).at(y+temp*j).at(0)))
                        {
                            tempchar=board.at(x+temp*i).at(y+temp*j);
                            board.at(x).at(y)=" ";
                            board.at(x+temp*i).at(y+temp*j)="B";
                            if (kingSafe())
                            {
                                result+=to_string(x)+to_string(y)+to_string(x+temp*i)+to_string(y+temp*j)+tempchar;
                            }
                            board.at(x).at(y)="B";
                            board.at(x+temp*i).at(y+temp*j)=tempchar;
                        }
                    } catch (...) {}
                    temp=1;
            }
    }
    return result;
}
string pK (int coord)
{
    string result="",tempchar="";
    int x=coord/8;
    int y=coord%8;
    for (int i=-1; i<=1; i+=2) {
            for (int j=-1; j<=1; j+=2) {
                    try{
                        if (islower(board.at(x+i).at(y+j*2).at(0)) || board.at(x+i).at(y+j*2)==" ")
                        {
                            tempchar=board.at(x+i).at(y+j*2);
                            board.at(x).at(y)=" ";
                            board.at(x+i).at(y+j*2)="K";
                            if (kingSafe())
                            {
                                result+=to_string(x)+to_string(y)+to_string(x+i)+to_string(y+j*2)+tempchar;
                            }
                            board.at(x).at(y)="K";
                            board.at(x+i).at(y+j*2)=tempchar;
                        }
                    }catch (...) {}
                    try{
                        if (islower(board.at(x+i*2).at(y+j).at(0)) || board.at(x+i*2).at(y+j)==" ")
                        {
                            tempchar=board.at(x+i*2).at(y+j);
                            board.at(x).at(y)=" ";
                            board.at(x+i*2).at(y+j)="K";
                            if (kingSafe())
                            {
                                result+=to_string(x)+to_string(y)+to_string(x+i*2)+to_string(y+j)+tempchar;
                            }
                            board.at(x).at(y)="K";
                            board.at(x+i*2).at(y+j)=tempchar;
                        }
                    }catch (...) {}
            }
    }
    return result;
}
string pP (int coord)
{
    string result="",tempchar="";
    int x=coord/8;
    int y=coord%8;
    for (int i=-1; i<=1; i+=2) {
        try{ //cap
            if (islower(board.at(x-1).at(y+i).at(0)) && coord>=16)
            {
                tempchar=board.at(x-1).at(y+i);
                board.at(x).at(y)=" ";
                board.at(x-1).at(y+i)="P";
                if (kingSafe())
                {
                result+=to_string(x)+to_string(y)+to_string(x-1)+to_string(y+i)+tempchar;
                }
                board.at(x).at(y)="P";
                board.at(x-1).at(y+i)=tempchar;
            }
        } catch (...) {}
        try{ //cap & prom
            if (islower(board.at(x-1).at(y+i).at(0)) && coord<16)
            {
                string temp[4]={"Q","R","B","K"};
                for (int j=0;j<4;j++)
                {
                tempchar=board.at(x-1).at(y+i);
                board.at(x).at(y)=" ";
                board.at(x-1).at(y+i)=temp[j];
                if (kingSafe())
                {
                result+=to_string(y)+to_string(y+i)+tempchar+temp[j]+"P";
                }
                board.at(x).at(y)="P";
                board.at(x-1).at(y+i)=tempchar;
                }
            }
        } catch (...) {}
    }
        try{ //one up
            if (board.at(x-1).at(y)==" " && coord>=16)
            {
                tempchar=board.at(x-1).at(y);
                board.at(x).at(y)=" ";
                board.at(x-1).at(y)="P";
                if (kingSafe())
                {
                result+=to_string(x)+to_string(y)+to_string(x-1)+to_string(y)+tempchar;
                }
                board.at(x).at(y)="P";
                board.at(x-1).at(y)=tempchar;
            }
        } catch (...) {}
        try{ //prom
            if (board.at(x-1).at(y)==" " && coord<16)
            {
                string temp[4]={"Q","R","B","K"};
                for (int j=0;j<4;j++)
                {
                tempchar=board.at(x-1).at(y);
                board.at(x).at(y)=" ";
                board.at(x-1).at(y)=temp[j];
                if (kingSafe())
                {
                result+=to_string(y)+to_string(y)+tempchar+temp[j]+"P";
                }
                board.at(x).at(y)="P";
                board.at(x-1).at(y)=tempchar;
                }
            }
        } catch (...) {}
        try{ //two up
            if (board.at(x-1).at(y)==" " && board.at(x-2).at(y)==" " && coord>=48)
            {
                tempchar=board.at(x-2).at(y);
                board.at(x).at(y)=" ";
                board.at(x-2).at(y)="P";
                if (kingSafe())
                {
                result+=to_string(x)+to_string(y)+to_string(x-2)+to_string(y)+tempchar;
                }
                board.at(x).at(y)="P";
                board.at(x-2).at(y)=tempchar;
            }
        } catch (...) {}
        return result;
}
void flipBoard ()
{
kingPosLower=getKingPosLower();
kingPosUpper=getKingPosUpper();
string temp;
for (int i=0;i<32;i++)
{
    int x=i/8, y=i%8;
    if (isupper(board[x][y][0]))
    {
        temp=tolow(board[x][y]);
    }
    else temp=toupp(board[x][y]);

    if (isupper(board[7-x][7-y][0]))
    {
        board[x][y]=tolow(board[7-x][7-y]);
    }
    else board[x][y]=toupp(board[7-x][7-y]);
    board[7-x][7-y]=temp;
}
int kingTemp=kingPosUpper;
kingPosUpper=63-kingPosLower;
kingPosLower=63-kingTemp;
}
string pMoves()
{
    string result="";
    for (int i=0;i!=64;i++)
    {
        if (board[i/8][i%8]=="P") result+=pP(i);
        if (board[i/8][i%8]=="R") result+=pR(i);
        if (board[i/8][i%8]=="K") result+=pK(i);
        if (board[i/8][i%8]=="B") result+=pB(i);
        if (board[i/8][i%8]=="Q") result+=pQ(i);
        if (board[i/8][i%8]=="A") result+=pA(i);
    }
    return result;
}
void makeMove (string line)
{
    if (line[4]!='P') // no prom
    {
        board[line[2]-'0'][line[3]-'0']=board[line[0]-'0'][line[1]-'0'];
        board[line[0]-'0'][line[1]-'0']=" ";
        if (board[line[2]-'0'][line[3]-'0']=="A")
        {
            kingPosUpper=8*line[2]-'0' + line[3]-'0';
        }
    }
    else // prom
    {
        board[1][line[0]-'0']=" ";
        board[0][line[1]-'0']=line[3];
    }
}
void undoMove (string line)
{
    if (line[4]!='P') // no prom
    {
        board[line[0]-'0'][line[1]-'0']=board[line[2]-'0'][line[3]-'0'];
        board[line[2]-'0'][line[3]-'0']=line[4];
        if (board[line[0]-'0'][line[1]-'0']=="A")
        {
            kingPosUpper=8*line[0]-'0' + line[1]-'0';
        }
    }
    else // prom
    {
        board[1][line[0]-'0']="P";
        board[0][line[1]-'0']=line[2];
    }
}
int rateMaterial()
{
        int counter=0, bishopCounter=0, bishopOppCounter=0;
        for (int i=0;i<64;i++) {
            if (board[i/8][i%8]=="P") {counter+=100;}
            if (board[i/8][i%8]=="R") {counter+=500;}
            if (board[i/8][i%8]=="K") {counter+=300;}
            if (board[i/8][i%8]=="B") {bishopCounter+=1;}
            if (board[i/8][i%8]=="Q") {counter+=900;}
            if (board[i/8][i%8]=="p") {counter-=100;}
            if (board[i/8][i%8]=="r") {counter-=500;}
            if (board[i/8][i%8]=="k") {counter-=300;}
            if (board[i/8][i%8]=="b") {bishopOppCounter+=1;}
            if (board[i/8][i%8]=="q") {counter-=900;}
        }
        if (bishopCounter>=2) {
            counter+=300*bishopCounter;
        } else {
            if (bishopCounter==1) {counter+=250;}
        }
        if (bishopOppCounter>=2) {
            counter-=300*bishopOppCounter;
        } else {
            if (bishopOppCounter==1) {counter-=250;}
        }
        return counter;
}
int ratePositional(int material)
{
    kingPosLower=getKingPosLower();
    kingPosUpper=getKingPosUpper();
    int counter=0;
        for (int i=0;i<64;i++) {
            if (board[i/8][i%8]=="P") {counter+=pawnBoard[i/8][i%8];}
            if (board[i/8][i%8]=="R") {counter+=rookBoard[i/8][i%8];}
            if (board[i/8][i%8]=="K") {counter+=knightBoard[i/8][i%8];}
            if (board[i/8][i%8]=="B") {counter+=bishopBoard[i/8][i%8];}
            if (board[i/8][i%8]=="Q") {counter+=queenBoard[i/8][i%8];}
            if (board[i/8][i%8]=="A"){counter+=kingMidBoard[i/8][i%8];}

            if (board[i/8][i%8]=="p") {counter-=pawnBoard[i/8][i%8];}
            if (board[i/8][i%8]=="r") {counter-=rookBoard[i/8][i%8];}
            if (board[i/8][i%8]=="k") {counter-=knightBoard[i/8][i%8];}
            if (board[i/8][i%8]=="b") {counter-=bishopBoard[i/8][i%8];}
            if (board[i/8][i%8]=="q") {counter-=queenBoard[i/8][i%8];}
            if (board[i/8][i%8]=="a") {counter-=kingMidBoard[i/8][i%8];}

            }
        return counter;
}
int rating() {
        int counter=0, material=rateMaterial();
        counter+=material;
        counter+=ratePositional(material);
        return counter;
}
////////////////////////////////////////////////////////////////////

int alphaBetaMin(int alpha,int beta, int depthleft);
int alphaBetaMax( int alpha, int beta, int depthleft ) {

   if ( depthleft == 0 ) return rating();
   string moves=pMoves();
   for (int i=0;i<moves.length();i+=5) {
        string temp=sub(i,i+5,moves);
        makeMove(temp);
        flipBoard();
      int score = alphaBetaMin( alpha, beta, depthleft - 1 );
      if (depthleft==gDepth && score > bscore){bscore=score;best=temp;}
      flipBoard();
      undoMove(temp);
      if( score >= beta )
         return beta;
      if( score > alpha )
         alpha = score;
   }
   return alpha;
}
int alphaBetaMin( int alpha, int beta, int depthleft ) {

   if ( depthleft == 0 ) {return -rating();}
   string moves=pMoves();
   for (int i=0;i<moves.length();i+=5) {
      string temp=sub(i,i+5,moves);
      makeMove(temp);
      flipBoard();
      int score = alphaBetaMax( alpha, beta, depthleft - 1 );
      flipBoard();
      undoMove(temp);
      if( score <= alpha )
         return alpha;
      if( score < beta )
         beta = score;
   }
   return beta;
}
