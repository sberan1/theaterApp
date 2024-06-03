# Návod na použití rezervačního systému
Autory aplikace jsou Štěpán Beran (bers06@vse.cz) a Alexandr Sekera (seka01@vse.cz). Rozdělení práce bylo rozděleno na (ne)uživatelské prostředí a administrátorské. Administrátorskému řešení se věnoval Štěpán Beran, (ne)uživatelskému Alexandr Sekera.
<br> Aplikace slouží jako rezervační systém pro kina. Uživatel má možnost si rezervovat lístky na filmy.
<br> Aplikace používá PostgreSQL databázi.
<br> Problémy aplikace:
<br> Uživatel musí po loginu občas refreshnout stránku, aby se propsal horní panel přihlášeného uživatele.
<br> Aplikace momentálně nijak nevyužívá kredit přidělený uživatelům (možnost rozšíření do budoucna, například platba za rezervaci).
<br> Dalších potíží si autoři nejsou vědomí.
<br>
<br> Odchylek oproti zadání si vývojáři nejsou vědomí.
<br> Následuje popis spuštění a použití aplikace.
1. V souboru ddl.sql je kód pro vytvoření databáze. Je nutné, aby uživatel tento kód před spuštěním projektu spustil (databáze obsahuje dummy data, není třeba je vytvářet). Následně je třeba spustit server pomocí příkazu java -jar backend/target/theater-0.0.1-SNAPSHOT.jar (pro spuštění je potřeba Node SDK a Java SDK).
2. Je potřeba spustit stránku pomocí příkazu node frontend/build/index.js
3. Na hlavní stránce má uživatel možnost si zobrazit všechny projekce podle filtru, který si nastaví. Buďto si může zobrazit všechny projekce podle konkrétního filmu, nebo podle zvoleného kina.
4. Nepřihlášený uživatel nemůže nic jiného než zobrazovat tyto projekce. Pokud chce dělat více, musí se buďto přihlásit, nebo zaregistrovat.
5. Pokud zvolí tlačítko zaregistrovat, bude vybídnut k zadání svého emailu, username, telefonního čísla, hesla a potvrzení hesla. Neprobíhá žádná externí ochrana, ale email, username a tel. číslo musí být unikátní.
6. Pokud se chce uživatel přihlásit, použije k tomu své uživatelské jméno a heslo.
7. Aplikace každých 5 sekund kontroluje, zda je uživatel přihlášený a zda existuje spojení mezi ním a serverem. Pokud se horní tlačítka nezmění okamžitě, vyčkejte prosím tuto dobu (pokud se i tak tlačítka nezobrazí, refreshněte stránku).
8. Uživatel má nyní možnost si vlézt do portálu se svým účtem a přidat si kredit.
9. V mém účtu vidí informace o sobě, tj. uživatelské jméno, email, telefon a zůstatek. Heslo se z důvodu bezpečnosti nezobrazuje a nepřenáší napříč komunikací se serverem, přenáší se pouze JWT token.
10. Uživatel má možnost si upravit jakékoliv informace o sobě včetně hesla.
11. Uživatel má možnost si zobrazit seznam svých rezervací a zároveň je i vytvořit.
12. Pokud chce rezervaci vytvořit, může filtrovat stejně jako na hlavní stránce. Následně si zvolí konečnou projekci, zvolí, jestli rovnou zaplatil, uplatní slevu, vybere si sedadlo a vytvoří rezervaci. Vytváření rezervace je chráněno pomocí OptimisticLock, není tedy možné, aby si dva uživatelé zarezervovali stejná místa.
13. V seznamu mých rezervací má uživatel možnost svojí rezervaci upravit či smazat.
14. Na záložce přidat kredit má uživatel možnost si přidat kredit (zatím bez použití).
15. Uživatel má možnost se odhlásit.
16. Pokud je uživatel administrátor, má možnost přidávat pobočky, sály, filmy, ceny, projekce a sedačky. Zároveň má možnost si vše přes filtry zobrazovat a upravovat. Funguje zde systém pagination, kdy se zobrazuje maximální počet projekcí na 1 stránku. Pokud jich je více, administrátor musí překlikávat pomocí previous a next page.