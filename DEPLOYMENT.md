# Production Deployment Guide

## Prerequisites

1. **Production MySQL Database**
   - MySQL 8.0+ hosted on a cloud provider (AWS RDS, DigitalOcean, etc.)
   - Create database: `CREATE DATABASE grafix;`
   - Note down: host, port, username, password

2. **Hosting Platform** (Choose one)
   - Railway.app (Recommended - Easy deployment)
   - Render.com
   - Heroku
   - AWS Elastic Beanstalk
   - DigitalOcean App Platform

## Option 1: Deploy to Railway.app (Recommended)

### Step 1: Prepare Your Database
```bash
# Connect to your production MySQL
mysql -h your-db-host -u your-username -p

# Create database
CREATE DATABASE grafix;
```

### Step 2: Push to GitHub
```bash
cd grafix-website
git init
git add .
git commit -m "Initial commit - Grafix Professional Website"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/grafix-website.git
git push -u origin main
```

### Step 3: Deploy on Railway
1. Go to [railway.app](https://railway.app)
2. Click "New Project" → "Deploy from GitHub repo"
3. Select your `grafix-website` repository
4. Railway will auto-detect Spring Boot

### Step 4: Add Environment Variables
In Railway dashboard, add these variables:
```
SPRING_PROFILES_ACTIVE=prod
DB_HOST=your-production-db-host
DB_PORT=3306
DB_NAME=grafix
DB_USERNAME=your-db-username
DB_PASSWORD=your-db-password
```

### Step 5: Deploy
- Railway will automatically build and deploy
- Access your app at the provided Railway URL

## Option 2: Deploy to Render.com

### Step 1: Create render.yaml
Already included in project root.

### Step 2: Push to GitHub (same as above)

### Step 3: Deploy on Render
1. Go to [render.com](https://render.com)
2. New → Web Service
3. Connect your GitHub repository
4. Render will detect Spring Boot automatically
5. Add environment variables (same as Railway)
6. Click "Create Web Service"

## Option 3: Docker Deployment

### Build Docker Image
```bash
docker build -t grafix-website .
```

### Run Locally with Docker
```bash
docker run -p 8080:8080 \
  -e DB_HOST=your-db-host \
  -e DB_USERNAME=your-username \
  -e DB_PASSWORD=your-password \
  -e DB_NAME=grafix \
  grafix-website
```

### Push to Docker Hub
```bash
docker tag grafix-website your-dockerhub-username/grafix-website
docker push your-dockerhub-username/grafix-website
```

## Database Migration

Your database will be automatically set up on first deployment using Flyway migrations.

### Manual Migration (if needed)
```bash
# Export from local
mysqldump -u root -p grafix > grafix_backup.sql

# Import to production
mysql -h production-host -u username -p grafix < grafix_backup.sql
```

## Post-Deployment Checklist

- [ ] Verify database connection
- [ ] Test admin login (admin/admin123)
- [ ] Change default admin password
- [ ] Upload test product with image
- [ ] Test contact form
- [ ] Verify email notifications (if configured)
- [ ] Set up custom domain
- [ ] Configure SSL certificate
- [ ] Set up backup strategy

## Environment Variables Reference

| Variable | Description | Example |
|----------|-------------|---------|
| `SPRING_PROFILES_ACTIVE` | Active profile | `prod` |
| `DB_HOST` | Database host | `db.example.com` |
| `DB_PORT` | Database port | `3306` |
| `DB_NAME` | Database name | `grafix` |
| `DB_USERNAME` | Database user | `grafix_user` |
| `DB_PASSWORD` | Database password | `secure_password` |
| `PORT` | Application port | `8080` |

## Troubleshooting

### Database Connection Issues
```bash
# Test connection
mysql -h DB_HOST -u DB_USERNAME -p DB_NAME
```

### Check Application Logs
```bash
# Railway
railway logs

# Render
Check logs in Render dashboard

# Docker
docker logs container-name
```

### Common Issues

1. **"Access denied for user"**
   - Verify DB credentials
   - Check if database user has proper permissions

2. **"Unknown database 'grafix'"**
   - Create database manually
   - Or add `?createDatabaseIfNotExist=true` to connection URL

3. **File upload not working**
   - Configure cloud storage (S3, Cloudinary)
   - Update file upload paths in controllers

## Security Recommendations

1. **Change Default Password**
   ```sql
   UPDATE admins SET password = '$2a$10$NEW_BCRYPT_HASH' WHERE username = 'admin';
   ```

2. **Use Strong Database Password**
3. **Enable HTTPS** (automatic on Railway/Render)
4. **Regular Backups**
5. **Monitor Application Logs**

## Scaling

### Horizontal Scaling
- Most platforms support auto-scaling
- Configure in platform dashboard

### Database Optimization
- Add indexes for frequently queried columns
- Use connection pooling (already configured)
- Consider read replicas for high traffic

## Support

For deployment issues:
- Check platform documentation
- Review application logs
- Contact platform support

---

**Ready to deploy!** Follow the steps above and your Grafix website will be live in production.
