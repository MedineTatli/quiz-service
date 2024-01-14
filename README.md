Proje Tanımı:
Proje akışı quiz bilgilerinin oluşturulmasıyla başlar. Bu, quiz bilgisine erişebilecek ve çözebilecek öğrenci bilgilerini, öğrencinin sınav bilgilerini ve değerlendirme sonuçlarını içerir.
Quiz tablosunda genel quiz bilgileri, question tablosunda quiz sorularının detayları, selection tablosunda ise bu quiz'e ait soruların seçenekleri tutulmaktadır. Exam tablosunda ise çözülen sorular ve öğrencinin başarı durumu işlenir.

Senaryo:
Quiz-controller servisi, quiz tablosuna insert işlemi gerçekleştiren bir servis çağırır. (Insert için örnek curl iletilmiştir.) Bu bilgiler aynı zamanda question ve selection tablolarına da işlenir.
Student-controller servisi ile öğrenci bilgileri girilir.
Yukarıdaki işlemler tamamlandıktan sonra, sınava girecek öğrenci ve sınav bilgileri exam-controller servisi ile işlenir. Öğrenci numarası ve quiz numarası bilgileri ile sorular ve cevaplar girilir. Değerlendirme sonrası öğrencinin soru sayısına göre doğru sayısı oranı hesaplanır ve başarı yüzdesi exam tablosunda saklanır. Aynı servis aynı zamanda öğrenci numarası ile öğrencinin girdiği sınavlar ve başarı oranları listelenebilir.

Ek Bilgiler:
H2 veritabanına aşina olmadığım için kolaylık sağlaması amacıyla MySQL veritabanı kullandım.

veritabanı şeması aşağıdaki gibidir.
# quiz-service
![data_structure](https://github.com/MedineTatli/quiz-service/assets/39694313/3cddee96-204c-48d8-93ff-5e9416615089)


Sample add student curl ->  

curl -X 'POST' \
  'http://127.0.0.1:8090/student' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "name": "medine",
  "surname": "tatli"
}'

Sample student exam answers with studentNo and quizNo curl->

curl -X 'POST' \
  'http://127.0.0.1:8090/exam' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "studentNo": "7e04f84c-86cc-4ca6-9470-bb8d7c55506d",
  "quizNo": 1,
  "questionAndAnswers": {
    "1": 1,
    "2": 5,
    "3": 8,
  }
}'

Sample quiz, questions and selections curl: 

curl -X 'POST' \
  'http://127.0.0.1:8090/quiz' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "quizName": "Tarih Quiz",
  "questionsList": [
    {
      "question": "Hangi yıl Amerika Bağımsızlık Bildirgesi imzalandı?",
      "selections": [
        {
          "selection": "1776",
          "true": true
        },
        {
          "selection": "1789",
          "true": false
        },
        {
          "selection": "1804",
          "true": false
        },
        {
          "selection": "1820",
          "true": false
        }
      ]
    },
    {
      "question": "I. Dünya Savaşı hangi yıllar arasında gerçekleşmiştir?",
      "selections": [
        {
          "selection": "1914-1918",
          "true": true
        },
        {
          "selection": "1939-1945",
          "true": false
        },
        {
          "selection": "1870-1871",
          "true": false
        },
        {
          "selection": "1950-1953",
          "true": false
        }
      ]
    },
    {
      "question": "Atatürk'\''ün Samsun'\''a çıkarak Türk Kurtuluş Savaşı'\''nı başlattığı tarih nedir?",
      "selections": [
        {
          "selection": "19 Mayıs 1919",
          "true": true
        },
        {
          "selection": "23 Nisan 1920",
          "true": false
        },
        {
          "selection": "30 Ağustos 1922",
          "true": false
        },
        {
          "selection": "29 Ekim 1923",
          "true": false
        }
      ]
    }
  ]
}
'
