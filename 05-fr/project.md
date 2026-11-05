# System aukcyjny

## Wprowadzenie

Specyfikacja wymagań funkcjonalnych w ramach informatyzacji procesu sprzedaży produktów w oparciu o mechanizm aukcyjny. 

## Procesy biznesowe

---
<a id="bc1"></a>
### BC1: Sprzedaż aukcyjna 

**Aktorzy:** [Sprzedający](#ac1), [Kupujący](#ac2)

**Opis:** Proces biznesowy opisujący sprzedaż za pomocą mechanizmu aukcyjnego.

**Scenariusz główny:**
1. [Sprzedający](#ac1) wystawia produkt na aukcję. ([UC1](#uc1))
2. [Kupujący](#ac2) oferuje kwotę za produkt wyższą od aktualnie najwyższej oferty. ([UC2](#uc2), [BR1](#br1))
3. [Kupujący](#ac2) wygrywa aukcję ([UC3](#uc3), [BR2](#br2))
4. [Kupujący](#ac2) przekazuje należność Sprzedającemu. ([UC4](#uc4))
5. [Sprzedający](#ac1) przekazuje produkt Kupującemu. ([UC5](#uc5), [UC6](#uc6))

**Scenariusze alternatywne:** 

2.A. Oferta Kupującego została przebita, a [Kupujący](#ac2) pragnie przebić aktualnie najwyższą ofertę.
* 2.A.1. Przejdź do kroku 2.

3.A. Czas aukcji upłynął i [Kupujący](#ac2) przegrał aukcję. ([UC3](#uc3), [BR2](#br2))
* 3.A.1. Koniec przypadku użycia.

---

## Aktorzy

<a id="ac1"></a>
### AC1: Sprzedający

Osoba oferująca towar na aukcji.

<a id="ac2"></a>
### AC2: Kupujący

Osoba chcąca zakupić produkt na aukcji.

<a id="ac3"></a>
### AC3: Czas

Aktor czasowy. Reprezentuje upływ czasu trwania aukcji, który inicjuje w systemie działania niewymagające udziału człowieka.


## Przypadki użycia poziomu użytkownika

### Aktorzy i ich cele

[Sprzedający](#ac1):
* [UC1](#uc1): Wystawienie produktu na aukcję
* [UC3](#uc3): Rozstrzygnięcie aukcji (otrzymanie informacji o wyniku)
* [UC5](#uc5): Potwierdzenie przekazania produktu Kupującemu
* UC7: Wyszukiwanie aukcji
* UC8: Przeglądanie szczegółów aukcji
* UC10: Przeglądanie własnych aukcji
* UC11: Przeglądanie historii transakcji
* UC12: Edycja aukcji
* UC13: Anulowanie aukcji
* UC14: Rejestracja konta
* UC15: Logowanie
* UC16: Edycja danych konta
* UC17: Usunięcie konta

[Kupujący](#ac2):
* [UC2](#uc2): Złożenie oferty w aukcji
* [UC3](#uc3): Rozstrzygnięcie aukcji (otrzymanie informacji o wyniku)
* [UC4](#uc4): Opłacenie wygranej aukcji
* [UC6](#uc6): Potwierdzenie otrzymania produktu
* UC7: Wyszukiwanie aukcji
* UC8: Przeglądanie szczegółów aukcji
* UC9: Przeglądanie aukcji, w których uczestniczę
* UC11: Przeglądanie historii transakcji
* UC14: Rejestracja konta
* UC15: Logowanie
* UC16: Edycja danych konta
* UC17: Usunięcie konta

[Czas](#ac3):
* [UC3](#uc3): Rozstrzygnięcie aukcji (inicjowanie po upływie czasu aukcji)

*Przypadki UC1–UC6 wynikają z procesu biznesowego [BC1](#bc1) i mają opisane scenariusze. Przypadki UC7–UC17 zostały zidentyfikowane na podstawie analizy macierzy CRUDL (patrz niżej) i nie mają jeszcze opisanych scenariuszy. Przypadki UC7–UC13 oraz UC16–UC17 zakładają, że użytkownik jest zalogowany ([UC15](#uc15)).*

---
<a id="uc1"></a>
### UC1: Wystawienie produktu na aukcję

**Aktorzy:** [Sprzedający](#ac1)

**Scenariusz główny:**
1. [Sprzedający](#ac1) zgłasza do systemu chęć wystawienia produktu na aukcję.
2. System prosi o podanie danych produktu i ceny wywoławczej.
3. [Sprzedający](#ac1) podaje dane produktu oraz cenę wywoławczą.
4. System weryfikuje poprawność danych.
5. System informuje o pomyślnym wystawieniu produktu na aukcję.

**Scenariusze alternatywne:** 

4.A. Podano niepoprawne lub niekompletne dane produktu.
* 4.A.1. System informuje o błędnie podanych danych.
* 4.A.2. Przejdź do kroku 2.

---

<a id="uc2"></a>
### UC2: Złożenie oferty w aukcji

**Aktorzy:** [Kupujący](#ac2)

**Scenariusz główny:**
1. [Kupujący](#ac2) zgłasza do systemu chęć złożenia oferty w wybranej aukcji.
2. System przedstawia aktualnie najwyższą ofertę (lub cenę wywoławczą, jeśli nie złożono jeszcze żadnej oferty) oraz prosi o podanie kwoty oferty.
3. [Kupujący](#ac2) podaje kwotę oferty.
4. System weryfikuje, czy kwota spełnia warunki złożenia oferty. ([BR1](#br1))
5. System rejestruje ofertę jako aktualnie najwyższą.
6. System informuje [Kupującego](#ac2) o pomyślnym złożeniu oferty.

**Scenariusze alternatywne:** 

1.A. Czas aukcji już upłynął.
* 1.A.1. System informuje [Kupującego](#ac2), że aukcja została zakończona i nie przyjmuje już ofert.
* 1.A.2. Koniec przypadku użycia.

4.A. Podana kwota nie spełnia warunków złożenia oferty. ([BR1](#br1))
* 4.A.1. System informuje [Kupującego](#ac2) o niepoprawnej kwocie oraz o minimalnej dopuszczalnej kwocie oferty.
* 4.A.2. Przejdź do kroku 2.

4.B. Czas aukcji upłynął w trakcie składania oferty.
* 4.B.1. System informuje [Kupującego](#ac2), że aukcja została zakończona i oferta nie została przyjęta.
* 4.B.2. Koniec przypadku użycia.

---

<a id="uc3"></a>
### UC3: Rozstrzygnięcie aukcji

**Aktorzy:** [Czas](#ac3) (inicjujący), [Kupujący](#ac2) (informowany o wyniku), [Sprzedający](#ac1) (informowany o wyniku)

**Scenariusz główny:**
1. [Czas](#ac3) zgłasza do systemu upłynięcie czasu trwania aukcji.
2. System odszukuje najwyższą ofertę złożoną w aukcji.
3. System wyznacza zwycięzcę aukcji i oznacza aukcję jako zakończoną. ([BR2](#br2))
4. System informuje [Kupującego](#ac2), który wygrał aukcję, o wygranej oraz o należności do zapłaty. (patrz [UC4](#uc4))
5. System informuje pozostałych [Kupujących](#ac2), którzy złożyli oferty, że przegrali aukcję.
6. System informuje [Sprzedającego](#ac1) o wyniku aukcji i o zwycięzcy.

**Scenariusze alternatywne:** 

2.A. W aukcji nie złożono żadnej oferty.
* 2.A.1. System oznacza aukcję jako zakończoną bez rozstrzygnięcia.
* 2.A.2. System informuje [Sprzedającego](#ac1), że aukcja zakończyła się bez żadnej oferty.
* 2.A.3. Koniec przypadku użycia.

---

<a id="uc4"></a>
### UC4: Opłacenie wygranej aukcji

**Aktorzy:** [Kupujący](#ac2)

**Scenariusz główny:**
1. [Kupujący](#ac2) zgłasza do systemu chęć opłacenia wygranej aukcji.
2. System weryfikuje, że [Kupujący](#ac2) jest zwycięzcą aukcji ([BR2](#br2)) i że należność nie została jeszcze uregulowana.
3. System przedstawia należność do zapłaty (kwotę zwycięskiej oferty) oraz dostępne sposoby płatności.
4. [Kupujący](#ac2) wybiera sposób płatności i autoryzuje płatność.
5. System weryfikuje poprawność i skuteczność płatności.
6. System rejestruje płatność.
7. System informuje [Kupującego](#ac2) o przyjęciu płatności oraz informuje [Sprzedającego](#ac1) o otrzymaniu należności.

**Scenariusze alternatywne:** 

2.A. [Kupujący](#ac2) nie wygrał aukcji lub należność została już uregulowana.
* 2.A.1. System informuje [Kupującego](#ac2), że opłacenie aukcji nie jest możliwe.
* 2.A.2. Koniec przypadku użycia.

4.A. [Kupujący](#ac2) rezygnuje z płatności.
* 4.A.1. Koniec przypadku użycia.

5.A. Płatność nie powiodła się.
* 5.A.1. System informuje [Kupującego](#ac2) o nieudanej płatności.
* 5.A.2. Przejdź do kroku 3.

---

<a id="uc5"></a>
### UC5: Potwierdzenie przekazania produktu Kupującemu

**Aktorzy:** [Sprzedający](#ac1)

**Scenariusz główny:**
1. [Sprzedający](#ac1) zgłasza do systemu chęć potwierdzenia przekazania produktu zwycięzcy wskazanej aukcji.
2. System weryfikuje, czy należność za aukcję została przekazana. ([UC4](#uc4))
3. System prosi o podanie informacji o przekazaniu produktu (np. sposób przekazania, numer przesyłki).
4. [Sprzedający](#ac1) podaje informacje o przekazaniu produktu.
5. System weryfikuje poprawność podanych informacji.
6. System rejestruje przekazanie produktu.
7. System informuje [Sprzedającego](#ac1) o pomyślnym zarejestrowaniu przekazania oraz informuje [Kupującego](#ac2) o przekazaniu produktu.

**Scenariusze alternatywne:** 

2.A. Należność za aukcję nie została jeszcze przekazana.
* 2.A.1. System informuje [Sprzedającego](#ac1), że przekazanie produktu można potwierdzić dopiero po otrzymaniu należności.
* 2.A.2. Koniec przypadku użycia.

5.A. Podano niepoprawne lub niekompletne informacje o przekazaniu produktu.
* 5.A.1. System informuje o błędnie podanych danych.
* 5.A.2. Przejdź do kroku 3.

---

<a id="uc6"></a>
### UC6: Potwierdzenie otrzymania produktu

**Aktorzy:** [Kupujący](#ac2)

**Scenariusz główny:**
1. [Kupujący](#ac2) zgłasza do systemu chęć potwierdzenia otrzymania produktu z wygranej aukcji.
2. System przedstawia informacje o przekazaniu produktu zarejestrowane przez [Sprzedającego](#ac1) ([UC5](#uc5)) i prosi o potwierdzenie otrzymania produktu.
3. [Kupujący](#ac2) potwierdza otrzymanie produktu.
4. System rejestruje potwierdzenie otrzymania produktu.
5. System informuje [Sprzedającego](#ac1) o potwierdzeniu odbioru i zakończeniu transakcji.

**Scenariusze alternatywne:** 

2.A. [Sprzedający](#ac1) nie zarejestrował jeszcze przekazania produktu.
* 2.A.1. System informuje [Kupującego](#ac2), że produkt nie został jeszcze przekazany.
* 2.A.2. Koniec przypadku użycia.

3.A. [Kupujący](#ac2) rezygnuje z potwierdzenia (np. produkt nie dotarł).
* 3.A.1. Koniec przypadku użycia.

---

## Obiekty biznesowe (inaczej obiekty dziedzinowe lub informatyczne)

### BO1: Aukcja

Aukcja jest formą zawierania transakcji kupna-sprzedaży, w której Sprzedający określa cenę wywoławczą produktu, natomiast Kupujący mogą oferować własną ofertę zakupu każdorazowo proponując kwotę wyższą od aktualnie oferowanej kwoty. Aukcja kończy się po upływie określonego czasu. Jeśli złożona została co najmniej jedna oferta zakupu, produkt nabywa ten Kupujący, który zaproponował najwyższą kwotę. 

### BO2: Produkt

Fizyczny lub cyfrowy obiekt, który ma zostać sprzedany w ramach aukcji.

### BO3: Oferta

Propozycja zakupu produktu w ramach aukcji, złożona przez Kupującego i zawierająca proponowaną kwotę. Złożenie oferty podlega regule [BR1](#br1). Oferta jest wiążąca – po zarejestrowaniu nie można jej zmienić ani wycofać.

### BO4: Płatność

Przekazanie przez Kupującego należności za wygraną aukcję w wysokości kwoty zwycięskiej oferty.

### BO5: Przekazanie produktu

Informacja o przekazaniu produktu zwycięzcy aukcji przez Sprzedającego (np. wysyłka przesyłki lub udostępnienie produktu cyfrowego) wraz z potwierdzeniem otrzymania produktu przez Kupującego.

### BO6: Konto użytkownika

Dane identyfikujące osobę korzystającą z systemu w roli Sprzedającego lub Kupującego.

## Reguły biznesowe

<a id="br1"></a>
### BR1: Złożenie oferty

Złożenie oferty wymaga zaproponowania kwoty wyższej niż aktualnie oferowana o minimum 1,00 PLN.


<a id="br2"></a>
### BR2: Rozstrzygnięcie aukcji

Aukcję wygrywa ten z [Kupujących](#ac2), który w momencie jej zakończenia (upłynięcia czasu) złożył najwyższą ofertę.

## Macierz CRUDL

Oznaczenia: **C** – tworzenie (create), **R** – odczyt (read), **U** – modyfikacja (update), **D** – usuwanie (delete), **L** – wyświetlanie listy / wyszukiwanie (list), **—** – brak operacji.

| Przypadek użycia                                  | Aukcja | Produkt | Oferta | Płatność | Przekazanie produktu | Konto użytkownika |
| ------------------------------------------------- | ------ | ------- | ------ | -------- | -------------------- | ----------------- |
| UC1: Wystawienie produktu na aukcję               |   C    |    C    |   —    |    —     |          —           |         —         |
| UC2: Złożenie oferty w aukcji                     |   R    |    —    |  C,R   |    —     |          —           |         —         |
| UC3: Rozstrzygnięcie aukcji                       |  R,U   |    —    |   R    |    —     |          —           |         —         |
| UC4: Opłacenie wygranej aukcji                    |   R    |    —    |   —    |    C     |          —           |         —         |
| UC5: Potwierdzenie przekazania produktu Kupującemu|   R    |    —    |   —    |    R     |          C           |         —         |
| UC6: Potwierdzenie otrzymania produktu            |   —    |    —    |   —    |    —     |         R,U          |         —         |
| UC7: Wyszukiwanie aukcji                          |   L    |    L    |   —    |    —     |          —           |         —         |
| UC8: Przeglądanie szczegółów aukcji               |   R    |    R    |   R    |    —     |          —           |         —         |
| UC9: Przeglądanie aukcji, w których uczestniczę   |   L    |    —    |   R    |    —     |          —           |         —         |
| UC10: Przeglądanie własnych aukcji                |   L    |    —    |   —    |    —     |          —           |         —         |
| UC11: Przeglądanie historii transakcji            |   R    |    —    |   —    |    L     |          R           |         —         |
| UC12: Edycja aukcji                               |   U    |    U    |   —    |    —     |          —           |         —         |
| UC13: Anulowanie aukcji                           |   D    |    D    |   —    |    —     |          —           |         —         |
| UC14: Rejestracja konta                           |   —    |    —    |   —    |    —     |          —           |         C         |
| UC15: Logowanie                                   |   —    |    —    |   —    |    —     |          —           |         R         |
| UC16: Edycja danych konta                         |   —    |    —    |   —    |    —     |          —           |         U         |
| UC17: Usunięcie konta                             |   —    |    —    |   —    |    —     |          —           |         D         |

### Analiza kompletności

Macierz została najpierw wypełniona dla UC1–UC6 (wynikających z procesu [BC1](#bc1)). Poniższa tabela pokazuje, jakich operacji brakowało dla poszczególnych obiektów biznesowych i które przypadki użycia (UC7–UC17) je uzupełniają.

| Obiekt biznesowy     | Czego brakowało w UC1–UC6                                           | Uzupełnienie                                                                          |
| -------------------- | ------------------------------------------------------------------- | ------------------------------------------------------------------------------------- |
| Aukcja               | Przeglądanie (poza odczytem pomocniczym), lista, modyfikacja przez Sprzedającego, usunięcie | UC7, UC10 (L), UC8 (R), UC12 (U), UC13 (D)                                  |
| Produkt              | Odczyt, lista, modyfikacja, usunięcie                               | UC7 (L), UC8 (R), UC12 (U), UC13 (D)                                                  |
| Oferta               | Lista ofert                                                         | UC9 (L/R), UC8 (R). Brak U i D jest zamierzony – oferta jest wiążąca ([BO3](#bo3-oferta)) |
| Płatność             | Lista płatności                                                     | UC11 (L). Brak U i D jest zamierzony – zapis płatności jest niezmienny                |
| Przekazanie produktu | Lista / podgląd historii                                            | UC11 (R). Brak D jest zamierzony – historia przekazań jest zachowywana                |
| Konto użytkownika    | Brak jakiegokolwiek przypadku użycia                                | UC14 (C), UC15 (R), UC16 (U), UC17 (D)                                                |

Uwaga: UC12 (Edycja aukcji) i UC13 (Anulowanie aukcji) powinny być dostępne dla Sprzedającego tylko do momentu złożenia pierwszej oferty. Warunek ten należy zapisać jako regułę biznesową przy opisywaniu scenariuszy tych przypadków.
