# Algorytm
## Klasa `Writer` i  `Reader`
Obie klasy dziedziczą po klasie `Thread`, nadpisując metodę run nieskończoną pętlą.
Proszą o dostęp do zasobów biblioteki poprzez wspólny `Semafor` `libraryAccessPermits`, przy czym pisarz prosi o 
`MAX_READER_NUMBER` permitów.
Spełnia to warunek posiadania czytelni na wyłączność przez jednego pisarza.

Obie klasy zawierają cztery wartości stałe, przekazywane im poprzez `WriterFactory` lub `ReaderFactory`.
Są to:
- `MIN_ACTIVE_TIME`: minimalny czas przebywania w bibliotece, w milisekundach
- `MAX_ACTIVE_TIME`: maksymalny czas przebywania w bibliotece, w milisekundach
- `MIN_IDLE_TIME`: minimalny czas do kolejnej próby wejścia do biblioteki, w milisekundach
- `MAX_IDLE_TIME`: maksymalny czas do kolejnej próby wejścia do biblioteki, w milisekundach

Obie klasy w nieskończoność proszą o dostęp do biblioteki i z niej korzystają.

## Kwestia listy osób w kolejce i listy osób czytających
Aby uniknąć race conditions, te dwa `ArrayList`y są objęte drugim `Semafor`em, co umożliwia ładne i prawidłowe dodawanie
i usuwanie obiektów z listy osób przebywających w bibiliotece i listy osób oczekujących na wejście. Pisarze nigdy nie jest
dodawany do listy czytelników, ponieważ ma bibliotekę na wyłączność.

## Klasa `Library`
Ta klasa zawiera metody umożliwiające start, zaprzestanie i zgłoszenie chęci korzystania z biblioteki. Wszystkie metody,
które chcą mieć dostęp do zasobów 
- `ArrayList<Thread> awaiting`: listy oczekujących pisarzy i czytelników
- `ArrayList<Thread> readers`: listy czytających czytelników
muszą zdobyć permit z `Semafor`a `queueAccessPermits`

# Odpalanie
Paczka wykonywalna znajduje sie w module main. 
Należy ją odpalić używając `java -jar nazwa-paczki.jar liczbaCzytelników liczbaPisarzy`
`liczbaCzytelników` - argument określający, ilu czytelników ma wytworzyć `ReaderFactory`
`liczbaPisarzy` - argument określający, ilu czytelników ma wytworzyć `WriterFactory`