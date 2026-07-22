# Library Management API

Bu layihə kitabxana idarəetmə sistemləri üçün nəzərdə tutulmuş RESTful API xidmətidir. İstifadəçilərə kitabları, müəllifləri və kitabxana üzvlərini (members) idarə etmək üçün müvafiq əməliyyatları (CRUD) təqdim edir. Həmçinin, API-in bəzi resurslarında səhifələmə (pagination) və sıralama (sorting) dəstəklənir.

## 🛠 İstifadədə Olan Texnologiyalar

- **Java 21**
- **Spring Boot 3.3**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **MapStruct** (Mapper-lərin avtomatik generasiyası üçün)
- **Springdoc OpenAPI** (Swagger)

## 🚀 Quraşdırma (Installation)

Layihəni lokal mühitinizdə işə salmaq üçün aşağıdakı addımları izləyin:

### 1. PostgreSQL Bazasını Docker İlə İşə Salmaq

Əgər lokal kompüterinizdə PostgreSQL yüklü deyilsə, onu Docker vasitəsilə sürətli şəkildə qaldıra bilərsiniz:

```bash
docker run --name library-postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=library -p 5432:5432 -d postgres
```

### 2. `application.yml` Ayarları və Environment Variables (Mühit Dəyişənləri)

Layihə verilənlər bazası bağlantısı üçün `DB_USERNAME` və `DB_PASSWORD` environment variable-larından istifadə edir. Layihəni işə salmazdan əvvəl bu dəyişənləri təyin etməlisiniz (və ya `.env` faylından istifadə edə bilərsiniz). Nümunə üçün layihə kökündəki `.env.example` faylına baxa bilərsiniz.
Əgər bu dəyişənlər təyin edilməzsə, default olaraq lokal dəyərlər (`postgres` / `password`) istifadə ediləcək.

Layihənin `src/main/resources/application.yml` faylındakı konfiqurasiya aşağıdakı kimidir:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/library_db
    username: ${DB_USERNAME:postgres}
    password: ${DB_PASSWORD:password}
  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
```

### 3. Tətbiqin İşə Salınması

Layihənin qovluğuna daxil olub aşağıdakı əmri çalışdırın:

**Windows üçün:**
```cmd
.\mvnw.cmd spring-boot:run
```

**Mac/Linux üçün:**
```bash
./mvnw spring-boot:run
```

## 📖 API Endpoints

### 📚 Books
- `POST /api/v1/books` - Yeni kitab yaratmaq
- `GET /api/v1/books/{id}` - ID-yə görə kitabı gətirmək
- `GET /api/v1/books` - Bütün kitabları gətirmək (Səhifələmə dəstəkli: `?page=0&size=10&sortBy=title`)
- `PUT /api/v1/books/{id}` - Kitab məlumatlarını yeniləmək
- `DELETE /api/v1/books/{id}` - Kitabı silmək

### ✍️ Authors
- `POST /api/v1/authors` - Yeni müəllif yaratmaq
- `GET /api/v1/authors/{id}` - ID-yə görə müəllifi gətirmək
- `GET /api/v1/authors` - Bütün müəllifləri gətirmək
- `PUT /api/v1/authors/{id}` - Müəllif məlumatlarını yeniləmək
- `DELETE /api/v1/authors/{id}` - Müəllifi silmək

### 👥 Members
- `POST /api/v1/members` - Yeni üzv yaratmaq
- `GET /api/v1/members/{id}` - ID-yə görə üzvü gətirmək
- `GET /api/v1/members` - Bütün üzvləri gətirmək
- `PUT /api/v1/members/{id}` - Üzv məlumatlarını yeniləmək
- `DELETE /api/v1/members/{id}` - Üzvü silmək

## 🌐 Swagger UI Sənədləşməsi

Tətbiq işlədikdən sonra bütün API sənədlərinə interaktiv olaraq aşağıdakı linkdən baxa bilərsiniz:

👉 **[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

## 🧪 Testlərin İşə Salınması

Layihədəki Unit testləri icra etmək üçün aşağıdakı əmrdən istifadə edə bilərsiniz:

**Windows üçün:**
```cmd
.\mvnw.cmd test
```

**Mac/Linux üçün:**
```bash
./mvnw test
```
