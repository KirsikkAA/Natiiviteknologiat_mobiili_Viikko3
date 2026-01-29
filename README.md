## Viikotehtävä 2 -  ViewModel

<h>Sovelluksessa käytetään Task-data classia. Datamalliin kuuluu: 
- id
- title
- description
- priority
- dueDate
- done </h>

<h>Sovelluksessa on viisi eri funktiota: 
- addTask: Lisää uuden taskin default tekstillä
- toggleDone - Muuttaa listan ensimmäisen taskin done tilan (true/false)
- filterByDone - Näyttää ainoastaan listan taskit, joiden done-tila on true
- filterDueDate - Järjestää listan dueDaten mukaisesti
- Reset - Palauttaa koko listan alkuperäiseen tilaan </h>

<h>Sovelluksen voi ajaa:
- Android studiossa emulaattorilla tai fyysisellä laitteella
- Asentamalla debug APK Android-laitteeseen</h>

#### Compose-tilanhallinta
<h>Jetpack Compose tilanhallinta tarkoittaa tilaa(state) eli dataa, joka voi muuttua ja siten vaikuttaa UI:in. Esimerkiksi käyttäjä syöte tai datan päivittäminen muuttaa tilaa. Kun tila muuttuu, Compose päivittää UI:in automaattisesti tekemällä recompose vain niille UI-osille, jotka riippuvat kyseistä tilasta. Tämän ansiosta käyttöliittymä pysyy aina synkronissa sovelluksen tilan kanssa. </h>

#### viewModel
<h>ViewModel on parempi parempi tapa säilyttää tila konfiguraatiomuutosten yli (esimerkiksi näytön kääntäminen) kuin pelkkä remember. ViewModel erottaa toimintalogiikan käyttöliittymästä, mikä tekee sovelluksesta selkeämmän ja helpommin testattavan. Lisäksi ViewModel integroituu hyvin muiden Architecture Components -komponenttien kanssa. Remember soveltuu paremmin hetkellisiin UI-tiloihin, esimerkiksi animaatioiden tai väliaikaisten käyttöliittymätilojen hallintaan. </h>

<h>[Linkki videoon](https://youtu.be/HX6WTg3RA7k)</h>
