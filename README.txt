RACHITEANU ROBERT-ALEXANDRU
322CBa

Clase importante:
-Main: 
     In clasa main se citeste cu un buffer fiecare linie data la input.
Cu ajutorul unui switch, in functie de comanda data (primul cuvant din linie)
se determina metoda care o sa fie apelata. Metodele apelate sunt in interiorul
unui Facade, acesta reprezentand primul design pattern implementat. 

-AuctionHouse:
     Aceasta clasa este de forma Singleton (al 2-lea design pattern), 
avand o singura instanta. In aceasta sunt retinute toate listele folosite, dar
si cateva metode: initializeBrokers(), care genereaza un numar de la 2 la 4 de 
brokeri si ii adauga in lista; calculateMaxBid() primeste o lista de bid-uri 
dintr-o anumita runda a unei licitatii pe care o parcurge si calculeaza pretul
maxim oferit pe care il si returneaza.

-Product, Painting, Jewelry, Furniture:
    Product este clasa principala, aceasta avand toate variabilele comune a 
restului de 3 clase. Celelalte 3 sunt implementate cu design pattern-ul 
Builder(cel de-al 3-lea), intrucat au foarte multe variabile si ajuta la 
lizibilitatea codului. In afara de Product, restul claselor au suprascrisa
metoda toString() pentru o afisare mai "curata" a output-ului.

-Bid:
     Aceasca clasa este facuta pentru obiectul licitatie, care are specifice
licitatia la care se intampla, pretul curent care se ofera, pretul maxim pe 
care o persoana il ofera si id-ul clientului.

-Client, PrivatePerson, LegalEntity, ClientFactory:
    Clasa Client are implementate variabilele principale, cu precizarea ca are
setteri si getteri pentru campurile de capital social, data nasterii, numele de
familie si tipul companiei scrise "de forma". Acestea sunt suprascrise in 
clasele PrivatePerson, respectiv LegalEntity pentru o implementare mai usoara.
     Metoda generateBidPrice() calculeaza pretul pe care l-ar oferi o persoana
la un anumit pas in functie de pretul maxim precedent oferit la licitatie. In 
cazul in care clientul nu poate sa plateasca pretul anterior maxim, atunci 
metoda intoarce -1.
     De mentionat, in functie de tipul specificat in comanda data la input,
cu ajutorul design pattern-ului Factory se creaza tipul de client specificat
(persoana privata sau juridica).

-Auction - Runnable:
     Implementeaza pe langa variabilele cerute si un ArrayList<Bid> (Generic),
care retine preturile pentru pasul curent din licitatia actuala. Tot aici se 
regaseste si comanda run() care genereaza un thread pentru licitatia curenta 
in cazul in care numarul maxim de participanti (generat la intamplare) este 
atins. Astfel, se apeleaza toti broker-ii, se cauta in fiecare in clientii
acestora persoane care au intrat in licitatia curenta si se genereaza un 
pret la intamplare intre pretul maxim curent pe runda si maximul posibil oferit
de client. In final, daca pretul maxim oferit de clienti nu a fost mai mare 
decat pretul minim pe produs, licitatia se anuleaza si produsul reintra in 
lista de produse stocate. In caz contrar se anunta castigatorul si acestuia
i se mareste numarul de licitatii castigate.


-Facade:
     Este o clasa care mediaza interactiunea dintre utilizatori si codul in 
sine. Aici se afla metodele apelate in main: 
*de adaugare (addPainting, addJewelry, addFurniture si addClient), care primesc
inputul si creeaza obiectele respective cu valorile specificate, pe care le 
adauga la listele din AuctionHouse; 
*solicitBid - genereaza si atribuie broker-ii, verifica daca produsul 
specificat este in lista de produse si mareste numarul de participanti
pentru licitatia produsului (pornind un thread nou daca incepe) sau creeaza o 
licitatie noua daca nu exista pentru produs; 
*listCommand, printList - metode generice pentru a afisa tipul de obiect cerut. 


				---Mentiuni---

- licitatiile sunt implementate ca thread-uri;
- s-au folosit Design Pattern-urile: Builder, Singleton, Facade, Factory;
- s-au folosit arraylist-uri generice, dar au fost create si metode generice;
- Casa de licitatii interactioneaza cu clientul exclusiv prin intermediul
 listelor de clienti ale broker-ilor.

























     