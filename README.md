Proje Tanımı:
Proje, akış quiz bilgilerinin oluşturulmasıyla başlar. Bu, quiz bilgisine erişebilecek ve çözebilecek öğrenci bilgilerini, öğrencinin sınav bilgilerini ve değerlendirme sonuçlarını içerir.
Quiz tablosunda genel quiz bilgileri, question tablosunda quiz sorularının detayları, selection tablosunda ise bu quiz'e ait soruların seçenekleri tutulmaktadır. Exam tablosunda ise çözülen sorular ve öğrencinin başarı durumu işlenir.

Senaryo:
Quiz-controller servisi, quiz tablosuna insert işlemi gerçekleştiren bir servis çağırır. (Insert için örnek curl iletilmiştir.) Bu bilgiler aynı zamanda question ve selection tablolarına da işlenir.
Student-controller servisi ile öğrenci bilgileri girilir. (Örnek öğrenci insert bilgileri iletilmiştir.)
Yukarıdaki işlemler tamamlandıktan sonra, sınava girecek öğrenci ve sınav bilgileri exam-controller servisi ile işlenir. Öğrenci numarası ve quiz numarası bilgileri ile sorular ve cevaplar girilir. Değerlendirme sonrası öğrencinin soru sayısına göre doğru sayısı oranı hesaplanır ve başarı yüzdesi exam tablosunda saklanır. Aynı servis aynı zamanda öğrenci numarası ile öğrencinin girdiği sınavlar ve başarı oranları listelenebilir.

Ek Bilgiler:
H2 veritabanına aşina olmadığım için kolaylık sağlaması amacıyla MySQL veritabanı kullandım.


# quiz-service

![data_structure](https://github.com/MedineTatli/quiz-service/assets/39694313/08e30e0a-11b1-429f-b5ff-5db53faf5228)

Örnek quiz ekleme curl -> 

curl -X 'POST' \
  'http://127.0.0.1:8090/quiz' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "quizName": "Tarih Quiz",
  "questionsList": [
    {
      "question": "Hangi yıl Türkiye Cumhuriyeti kuruldu?",
      "selections": [
        {"selection": "a. 1920", "true": false},
        {"selection": "b. 1921", "true": false},
        {"selection": "c. 1922", "true": false},
        {"selection": "d. 1923", "true": true}
      ]
    },
    {
      "question": "I. Dünya Savaşı hangi yıllar arasında gerçekleşmiştir?",
      "selections": [
        {"selection": "a. 1912-1916", "true": false},
        {"selection": "b. 1914-1918", "true": true},
        {"selection": "c. 1916-1920", "true": false},
        {"selection": "d. 1918-1922", "true": false}
      ]
    },
    {
      "question": "Hangi yıl Türk milleti tarafından Büyük Taarruz zaferle sonuçlandı?",
      "selections": [
        {"selection": "a. 1919", "true": false},
        {"selection": "b. 1920", "true": false},
        {"selection": "c. 1921", "true": false},
        {"selection": "d. 1922", "true": true}
      ]
    },
    {
      "question": "Osmanlı İmparatorluğu'\''nun kuruluş yılı nedir?",
      "selections": [
        {"selection": "a. 1258", "true": false},
        {"selection": "b. 1299", "true": true},
        {"selection": "c. 1389", "true": false},
        {"selection": "d. 1453", "true": false}
      ]
    },
    {
      "question": "Hangi yıl Türk Dil Kurumu kuruldu?",
      "selections": [
        {"selection": "a. 1928", "true": true},
        {"selection": "b. 1932", "true": false},
        {"selection": "c. 1935", "true": false},
        {"selection": "d. 1940", "true": false}
      ]
    },
    {
      "question": "Türkiye'\''nin ilk cumhurbaşkanı kimdir?",
      "selections": [
        {"selection": "a. İsmet İnönü", "true": false},
        {"selection": "b. Mustafa Kemal Atatürk", "true": true},
        {"selection": "c. Celal Bayar", "true": false},
        {"selection": "d. Adnan Menderes", "true": false}
      ]
    },
    {
      "question": "Kurtuluş Savaşı'\''nın başlangıç tarihi nedir?",
      "selections": [
        {"selection": "a. 1918", "true": false},
        {"selection": "b. 1919", "true": true},
        {"selection": "c. 1920", "true": false},
        {"selection": "d. 1921", "true": false}
      ]
    },
    {
      "question": "Hangi yıl Türk kadınlarına seçme ve seçilme hakkı tanındı?",
      "selections": [
        {"selection": "a. 1923", "true": false},
        {"selection": "b. 1930", "true": false},
        {"selection": "c. 1934", "true": true},
        {"selection": "d. 1940", "true": false}
      ]
    },
    {
      "question": "Türkiye'\''nin ilk zaferi hangi savaşla elde edilmiştir?",
      "selections": [
        {"selection": "a. Malazgirt Meydan Muharebesi", "true": false},
        {"selection": "b. Çanakkale Savaşı", "true": true},
        {"selection": "c. Sakarya Meydan Muharebesi", "true": false},
        {"selection": "d. Dumlupınar Meydan Muharebesi", "true": false}
      ]
    },
    {
      "question": "Hangi yıl Türkiye'\''de çok partili siyasi hayata geçildi?",
      "selections": [
        {"selection": "a. 1945", "true": false},
        {"selection": "b. 1950", "true": true},
        {"selection": "c. 1960", "true": false},
        {"selection": "d. 1971", "true": false}
      ]
    }
  ]
}
'
