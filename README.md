# TextWatcher-for-Turkish-Car-Plates

Türkiye plaka formatına uygun ayraçlar ayırmaya yarayan TextWatcher sınıfı. ilk 2 karakteri sayı olması için zorlar, 3. karakterden sonra harf girişi yapılana kadar boşluk bırakmaz. Harf girişinin ardından sayı girişi yapıldığında 2. boşluğu bırakır ve tekrar harf girişine engel olur.

Bilinen hatalar: 

- Harf bolumu 3 karakterden fazla olabiliyor.
- Son bolum 4'ten fazla olabiliyor. 
- Silmeden once hatali karakter girilirse silinmiyor. (kabul edilen karakter girilene kadar)
