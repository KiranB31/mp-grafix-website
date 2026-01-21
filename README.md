# Grafix Professional Website

A modern, full-stack creative studio website built with Spring Boot and MySQL.

## Features

- 🎨 **Admin Dashboard**: Complete content management system
- 📦 **Product Management**: Add/edit/delete digital assets with images
- 📝 **Blog System**: Create and publish articles
- ⭐ **Review Management**: Moderate customer feedback
- 👥 **Team Management**: Showcase your creative professionals
- ⚙️ **Business Settings**: Update studio information and contact details
- 📧 **Contact Form**: Customer inquiry management
- 📰 **Newsletter**: Subscriber management system

## Tech Stack

- **Backend**: Spring Boot 3.4.1, Java 17
- **Database**: MySQL 8.0
- **Security**: Spring Security with BCrypt
- **Migration**: Flyway
- **Template Engine**: Thymeleaf
- **Build Tool**: Gradle

## Local Development

### Prerequisites

- Java 17 or higher
- MySQL 8.0
- Gradle 8.x

### Setup

1. Clone the repository:
```bash
git clone <your-repo-url>
cd grafix-website
```

2. Create MySQL database:
```sql
CREATE DATABASE grafix;
```

3. Update `application.properties` with your MySQL credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/grafix
spring.datasource.username=root
spring.datasource.password=your_password
```

4. Run the application:
```bash
./gradlew bootRun
```

5. Access the application:
- Website: http://localhost:8080
- Admin Login: http://localhost:8080/login
  - Username: `admin`
  - Password: `admin123`

## Production Deployment

### Environment Variables

Set these environment variables in your production environment:

```bash
DB_HOST=your-production-db-host
DB_PORT=3306
DB_NAME=grafix
DB_USERNAME=your-db-username
DB_PASSWORD=your-db-password
PORT=8080
SPRING_PROFILES_ACTIVE=prod
```

### Build for Production

```bash
./gradlew clean build
java -jar build/libs/grafix-website-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

### Docker Deployment (Optional)

```bash
docker build -t grafix-website .
docker run -p 8080:8080 \
  -e DB_HOST=your-db-host \
  -e DB_USERNAME=your-username \
  -e DB_PASSWORD=your-password \
  grafix-website
```

## Database Schema

The application uses Flyway for database migrations. All schema changes are version-controlled in `src/main/resources/db/migration/`.

### Initial Setup

On first run, Flyway will automatically:
1. Create all required tables
2. Insert default admin user
3. Add sample staff members
4. Set up business information

## Security

- Passwords are encrypted using BCrypt
- Admin routes are protected with Spring Security
- CSRF protection enabled for forms
- Role-based access control (ADMIN, STAFF)

## File Uploads

Uploaded images are stored in the `uploads/` directory. For production:
- Consider using cloud storage (AWS S3, Cloudinary)
- Update file upload paths in controllers
- Configure CDN for better performance

## API Endpoints

### Public Routes
- `GET /` - Home page
- `GET /products` - Product catalog
- `GET /blog` - Blog listing
- `GET /about` - About page
- `GET /contact` - Contact form
- `POST /reviews/add` - Submit review
- `POST /subscribe` - Newsletter subscription

### Admin Routes (Authentication Required)
- `GET /admin/dashboard` - Admin dashboard
- `GET /admin/products` - Manage products
- `GET /admin/blog` - Manage blog posts
- `GET /admin/staff` - Manage team
- `GET /admin/settings` - Business settings
- `GET /admin/reviews` - Moderate reviews
- `GET /admin/messages` - View contact messages
- `GET /admin/subscribers` - Newsletter subscribers

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is proprietary software. All rights reserved.

## Support

For support, email hello@grafix.com or open an issue in the repository.
