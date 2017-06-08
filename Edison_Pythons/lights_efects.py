

def fluon(x,y,r,b,g,czas):
    n=max(r,g,b)
	for num in range(n,1):
		u.writeStr("<"+chr(x+48)+chr(y+48)+chr(r//(n-num))+chr(b//((n-num))+chr(g//(n-num))+">")
		time.sleep(czas/n)

def fluoff(x,y,r,b,g,czas):
    n=max(r,g,b)
	for num in range(1,n):
		u.writeStr("<"+chr(x+48)+chr(y+48)+chr(r//num)+chr(b//num)+chr(g//num)+">")
		time.sleep(czas/n)

def RNG():
	t = 2.0
	n = 64
	visited = [False] * 64
	while (n > 0):
		x=randint(0,7)
		y=randint(0,7)
		if visited[x + y*8] == True:
			continue
		else:
			visited[x + y*8] = True
		r=randint(0,255)
		b=randint(0,255)
		g=randint(0,255)
		u.writeStr("<"+chr(x+48)+chr(y+48)+chr(r)+chr(b)+chr(g)+">")
		time.sleep(t)
		if(t > 0.05): 
			t = t / 1.2
		n = n - 1
		
def light(kolejka):
	tempKolejka = []
	tempKolejka.extend(kolejka)
	while len(tempKolejka) > 0:
		a = tempKolejka.pop()
		x = a % 8
		y = (a - x) / 8
		r = 40
		g = 40
		b = 200
		print(("x: ", x ," ,y: ", y)) 
		#u.writeStr("<"+chr(x+48)+chr(y+48)+chr(r)+chr(b)+chr(g)+">")
	print("next")
	print("")
	return
			
		
def isXInRange(x):
	if x >= 0 and x <= 63:
		return True
	else:
		return False
	
def getSunsiedzi(kolejka, odwiedzone):
	kolejka1 = []
	kolejka1.extend(kolejka)
	kolejka3 = []
	while len(kolejka1) > 0:
		x = kolejka1.pop()
		
		if isXInRange(x+1) and odwiedzone[x+1] == False:
			kolejka3.append(x+1)
			odwiedzone[x+1] = True
		if isXInRange(x-1) and odwiedzone[x-1] == False:
			kolejka3.append(x-1)
			odwiedzone[x-1] = True
		if isXInRange(x+8) and odwiedzone[x+8] == False:
			kolejka3.append(x+8)
			odwiedzone[x+8] = True
		if isXInRange(x-8) and odwiedzone[x-8] == False:
			kolejka3.append(x-8)
			odwiedzone[x-8] = True
			
	return kolejka3 	
	
def doCircles(start):
	if isXInRange(start) == False:
		 return
	
	kol = 0
	odwiedzone = [False] * 64
	kolejka = []
	kolejka2 = []

	kolejka.append(start)
	odwiedzone[start] = True

	while (len(kolejka) > 0 or len(kolejka2) > 0):
		if kol == 0:
			kolejka2 = []
			kolejka2.extend(getSunsiedzi(kolejka, odwiedzone))
			kolejka = []
			kol = 1
			light(kolejka2)
		else:
			kolejka = []
			kolejka.extend(getSunsiedzi(kolejka2, odwiedzone))
			kolejka2 = []
			kol = 0
			light(kolejka)
			
		print(len(kolejka))
		print(len(kolejka2))	
		time.sleep(0.3)