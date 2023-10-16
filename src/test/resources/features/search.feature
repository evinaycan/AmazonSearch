Feature: Search

  @search
  Scenario: The user should be able to search for items and add to basket on Amazon
    When kullanıcı Amazon anasayfasına gider
    And Arama kutucuğuna "glasses" kelimesi girilir.
    Then Arama yapılır.
    And Arama kutucuğuna girilen "glasses" kelimesi silinir.
    And Arama kutucuğuna "sun glasses" kelimesi girilir.
    And Klavye üzerinden "enter" tuşuna bastırılır.
    And Sonuca göre sergilenen ürünlerden rastgele bir ürün seçilir.
    And Eğer fiyatı 1$ dan fazlaysa, arama ekranına geri dönülür, "$50 to $100" fiyat aralığı seçilir ve tekrar aranır. İlk seçenek seçilir.
    And Ürün arama sayfasında listelenen ürün sayısı (listedProductCount), seçilen ürünün ürün bilgisi(selectedProduct) ve tutar bilgisi(currentPrice) txt dosyasına yazılır.
    And Seçilen ürün sepete eklenir.
    Then Ürün sayfasındaki fiyat(currentPrice) ile sepette yer alan ürün fiyatının (basketPrice) doğruluğu karşılaştırılır.
    And Adet arttırılarak ürün adedinin 2 olduğu doğrulanır.
