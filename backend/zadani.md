# Rezervacni system pro kino

Mel by to byt system pro rezervaci mist v kine, zaroven by mela byt moznost pridavat promitani, filmy, kinosaly a pobocky.

**Databázové schéma:**

1. **Uživatel**
    - ID (Primary Key)
    - jméno
    - email
    - telefon
    - konto
    - role (enum: admin, user, etc.)

2. **Film**
    - ID (Primary Key)
    - název
    - délka
    - rating
    - typ (enum: 2D, 3D, Imax, Dolby Atmos)

3. **Kinosál**
    - ID (Primary Key)
    - číslo sálu
    - typ (enum: standard, deluxe, etc.)
    - pobočka_id (Foreign Key)

4. **Sedačka**
    - ID (Primary Key)
    - kinosál_id (Foreign Key)
    - řada
    - číslo v řadě

5. **Pobočka**
    - ID (Primary Key)
    - název
    - adresa

6. **Promítání**
    - ID (Primary Key)
    - timestamp
    - cena
    - film_id (Foreign Key)
    - kinosál_id (Foreign Key)

7. **Rezervace**
    - ID (Primary Key)
    - uživatel_id (Foreign Key)
    - promítání_id (Foreign Key)
    - zaplaceno (boolean)
    - typ úhrady (enum: karta, hotovost, etc.)

8. **Rezervace_Sedačka**
    - rezervace_id (Foreign Key)
    - sedačka_id (Foreign Key)

**ER Diagram:**

```
Uživatel (ID, jméno, email, telefon, konto, role)
   |
   +--< Rezervace (ID, uživatel_id, promítání_id, zaplaceno, typ úhrady)
       |
       +--< Rezervace_Sedačka (rezervace_id, sedačka_id)
       
Film (ID, název, délka, rating, typ)
   |
   +--< Promítání (ID, timestamp, cena, film_id, kinosál_id)
       
Kinosál (ID, číslo sálu, typ, pobočka_id)
   |
   +--< Sedačka (ID, kinosál_id, řada, číslo v řadě)

Pobočka (ID, název, adresa)
```
