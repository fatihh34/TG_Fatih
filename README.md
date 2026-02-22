# Java Selenium Cucumber Test Otomasyon Projesi

Bu proje, Magento e-ticaret platformu için **BDD (Behavior Driven Development)** yaklaşımı kullanılarak geliştirilmiş bir test otomasyon framework'üdür. Proje, **Page Object Model (POM)** mimarisi ile yapılandırılmış ve **Cucumber** ile BDD test senaryoları içermektedir.

Proje Özellikleri

- **Java 17** - Programlama dili
- **Selenium WebDriver 4.13.0** - Web otomasyon framework'ü
- **Cucumber 7.13.0** - BDD test framework'ü
- **Maven** - Bağımlılık yönetimi ve build aracı
- **JUnit 4** - Test runner
- **WebDriverManager 5.5.3** - Otomatik driver yönetimi
- **JavaFaker 1.0.2** - Test verisi üretimi
- **Log4j2 2.17.2** - Loglama
- **Lombok 1.18.26** - Kod kısaltma ve otomasyon

 Framework Özellikleri
- *Page Object Model (POM)** mimarisi
- *BDD (Behavior Driven Development)** yaklaşımı
- *Thread-safe** driver yönetimi
- *Paralel test çalıştırma** (3 thread)
- *Başarısız testleri tekrar çalıştırma** özelliği
- *HTML ve JSON** raporlama
- *Yapılandırma dosyası** ile merkezi ayar yönetimi
- *Gelişmiş Chrome seçenekleri** (bot tespiti bypass)
- *Hooks** ile test öncesi/sonrası işlemler

## 🎯 Test Senaryosu

Proje, Magento e-ticaret sitesinde **misafir kullanıcı checkout** akışını test eder:

1. Ana sayfaya giriş
2. Gear > Bags kategorisine navigasyon
3. Rastgele ürün seçimi
4. Ürünü sepete ekleme
5. Sepet miktarını güncelleme (2 adet)
6. Toplam fiyat doğrulama
7. Checkout işlemine geçiş
8. Misafir kullanıcı olarak checkout tamamlama (dummy verilerle)
9. Kargo yöntemi seçimi
10. Sipariş gönderimi
11. Sipariş başarı doğrulama

# Gereksinimler
- **Java JDK 17** veya üzeri
- **Maven 3.6+**
- **Chrome Browser** (veya Firefox/Safari)

# Adımlar

1. **Projeyi klonlayın veya indirin**
   ```bash
   git clone <repository-url>
   cd javaSeleniumCucumberProjectNew
   ```

2. **Yapılandırma dosyasını kontrol edin**
   
   `configuration.properties` dosyasını açın ve gerekirse düzenleyin:
   ```properties
   url=https://magento-demo.mageplaza.com
   browser=chrome
   ```

3. **Maven dependencyleri yükleyin**
   ```bash
   mvn clean install
   ```

4. **Testleri çalıştırın**
   
   Tüm testleri çalıştırmak için:
   ```bash
   mvn test
   ```
   
   Veya IDE'den `Runner.java` dosyasını çalıştırın.

5. **Raporları görüntüleyin**
   
   Test tamamlandıktan sonra raporlar şu konumlarda oluşturulur:
   - HTML Rapor: `target/cucumber-report.html`
   - Cucumber Rapor: `target/cucumber-html-reports/`
   - JSON Rapor: `target/cucumber.json`

# Yapılandırma

### Browser Seçimi
`configuration.properties` dosyasında `browser` değerini değiştirerek farklı tarayıcılar kullanabilirsiniz:
- `chrome` (varsayılan)
- `firefox`
- `safari`

# Test Etiketleri
Cucumber testlerini etiketlerle filtreleyebilirsiniz. `Runner.java` dosyasında `tags` parametresini düzenleyin:
```java
tags = "@magento"  // Sadece @magento etiketli testler çalışır
```

### Paralel Çalıştırma
`pom.xml` dosyasında paralel thread sayısını ayarlayabilirsiniz:
```xml
<threadCount>3</threadCount>
```

## 📊 Raporlama

Proje, test sonuçları için çoklu raporlama desteği sunar:

1. **HTML Rapor**: `target/cucumber-report.html`
2. **Cucumber HTML Rapor**: `target/cucumber-html-reports/`
3. **JSON Rapor**: `target/cucumber.json` (CI/CD entegrasyonu için)
4. **Pretty Reports**: `target/cucumber/`

## 🔧 Özellikler ve Özellikler

### Page Object Model
Her sayfa için ayrı bir Page sınıfı oluşturulmuştur:
- `HomePage`: Ana sayfa işlemleri
- `CategoryPage`: Kategori sayfası işlemleri
- `ProductPage`: Ürün detay sayfası işlemleri
- `CartPage`: Sepet sayfası işlemleri
- `CheckoutPage`: Checkout sayfası işlemleri

### Driver Yönetimi
- **Thread-safe** driver yönetimi (InheritableThreadLocal kullanımı)
- Otomatik driver kurulumu (WebDriverManager)
- Gelişmiş Chrome seçenekleri (bot tespiti bypass için)

### Test Verisi
- **JavaFaker** kütüphanesi ile dinamik test verisi üretimi
- Dummy verilerle checkout işlemleri

### Hooks
- Test öncesi ve sonrası işlemler için `Hooks.java` sınıfı
- Screenshot alma özelliği (başarısız testler için)

# Sorun Giderme

### Chrome Driver Sorunları
Eğer Chrome driver ile ilgili sorun yaşıyorsanız:
- WebDriverManager otomatik olarak uygun driver'ı indirir
- Manuel olarak Chrome sürümünüzü güncelleyin

### Test Başarısızlıkları
- Başarısız testler `target/rerun.txt` dosyasına kaydedilir
- `FailedTestRunner.java` ile sadece başarısız testler tekrar çalıştırılabilir

### Timeout Sorunları
- `driver.java` dosyasında implicit wait süresini ayarlayabilirsiniz
- Page sınıflarında explicit wait kullanılmaktadır

# Katkıda Bulunma

1. Fork edin
2. Feature branch oluşturun (`git checkout -b feature/amazing-feature`)
3. Değişikliklerinizi commit edin (`git commit -m 'Add some amazing feature'`)
4. Branch'inizi push edin (`git push origin feature/amazing-feature`)
5. Pull Request oluşturun

## 📄 Lisans

Bu proje eğitim ve test amaçlı geliştirilmiştir.

## 👤 Yazar

Fatih

---

## 📞 İletişim

Sorularınız veya önerileriniz için ulaşabilirsiniz.
fatihizmirbey@gmail.com
---

**Not**: Bu proje Magento demo sitesi (`https://magento-demo.mageplaza.com`) üzerinde test edilmektedir.
