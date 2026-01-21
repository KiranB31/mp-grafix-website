# Vercel + Spring Boot Deployment Guide

## ⚠️ Important: Vercel Limitation

**Vercel does NOT support Java/Spring Boot applications.**

Vercel supports:
- Node.js
- Python
- Go
- Ruby
- Static sites

## 🎯 Recommended Solution: Hybrid Architecture

### Architecture Overview
```
┌─────────────────┐
│  Vercel         │  → Static Frontend (HTML/CSS/JS)
│  (Frontend)     │
└────────┬────────┘
         │
         │ API Calls
         ↓
┌─────────────────┐
│  Railway/Render │  → Spring Boot Backend
│  (Backend)      │
└────────┬────────┘
         │
         ↓
┌─────────────────┐
│  MySQL Database │  → Vercel Postgres or External
└─────────────────┘
```

## Option 1: Deploy Backend to Railway (Easiest)

### Step 1: Deploy Spring Boot to Railway

```bash
# Push your code to GitHub first
git remote add origin https://github.com/YOUR_USERNAME/grafix-website.git
git push -u origin main
```

1. Go to https://railway.app
2. New Project → Deploy from GitHub
3. Select `grafix-website`
4. Add environment variables:
   ```
   SPRING_PROFILES_ACTIVE=prod
   DB_HOST=your-db-host
   DB_PORT=3306
   DB_NAME=grafix
   DB_USERNAME=your-username
   DB_PASSWORD=your-password
   ```
5. Railway will give you a URL like: `https://grafix-website-production.up.railway.app`

### Step 2: Use Vercel for Static Assets (Optional)

If you want to use Vercel for serving static files:
1. Create a separate `public` folder with your HTML/CSS/JS
2. Deploy that to Vercel
3. Update API calls to point to Railway backend

## Option 2: All-in-One Railway Deployment (Recommended)

**Skip Vercel entirely** and use Railway for everything:

### Advantages:
- ✅ Single platform for backend + database
- ✅ Automatic HTTPS
- ✅ Built-in MySQL support
- ✅ Free tier available
- ✅ Easy environment variables
- ✅ Automatic deployments from GitHub

### Quick Deploy to Railway:

```bash
# 1. Push to GitHub
git remote add origin https://github.com/YOUR_USERNAME/grafix-website.git
git push -u origin main

# 2. Go to railway.app
# 3. New Project → Deploy from GitHub
# 4. Select repository
# 5. Add MySQL database (Railway provides this)
# 6. Set environment variables automatically
# 7. Done!
```

Your site will be live at: `https://your-app.up.railway.app`

## Option 3: Vercel Postgres + External Spring Boot

If you MUST use Vercel:

### Use Vercel for:
- Database (Vercel Postgres)
- Static file hosting

### Use Railway/Render for:
- Spring Boot backend

### Steps:

1. **Set up Vercel Postgres**:
   ```bash
   # In Vercel dashboard
   # Storage → Create Database → Postgres
   # Get connection details
   ```

2. **Deploy Spring Boot to Railway**:
   - Use Vercel Postgres connection string
   - Point your Spring Boot to Vercel's database

3. **Configure CORS** in Spring Boot to allow Vercel domain

## 🚀 Recommended: Complete Railway Deployment

Since Vercel can't run Spring Boot, here's the **simplest solution**:

### 1. Create Railway Account
Go to https://railway.app and sign up with GitHub

### 2. Create New Project
- Click "New Project"
- Select "Deploy from GitHub repo"
- Choose `grafix-website`

### 3. Add MySQL Database
- In Railway project dashboard
- Click "New" → "Database" → "Add MySQL"
- Railway auto-configures connection

### 4. Environment Variables (Auto-set by Railway)
Railway automatically sets:
- `DATABASE_URL`
- `MYSQL_URL`

You just need to add:
```
SPRING_PROFILES_ACTIVE=prod
```

### 5. Deploy
- Railway auto-detects Spring Boot
- Builds with Gradle
- Deploys automatically
- Gives you a public URL

### 6. Custom Domain (Optional)
- Railway Settings → Domains
- Add your custom domain
- Update DNS records

## 📊 Cost Comparison

| Platform | Free Tier | Spring Boot Support | Database Included |
|----------|-----------|---------------------|-------------------|
| **Railway** | $5 credit/month | ✅ Yes | ✅ MySQL included |
| **Render** | 750 hours/month | ✅ Yes | ✅ PostgreSQL free |
| **Vercel** | Unlimited | ❌ No | ✅ Postgres only |
| **Heroku** | No free tier | ✅ Yes | ❌ Paid only |

## ⚡ Quick Deploy Command (Railway)

```bash
# Install Railway CLI
npm i -g @railway/cli

# Login
railway login

# Initialize
railway init

# Deploy
railway up
```

## 🔄 Migration from Vercel

If you have existing Vercel deployment:

1. **Export your Vercel Postgres data** (if using)
2. **Import to Railway MySQL**
3. **Update DNS** to point to Railway
4. **Keep Vercel** for other projects

## 💡 Why Not Vercel for Spring Boot?

Vercel is optimized for:
- Serverless functions (Node.js, Python, Go)
- Static sites (Next.js, React, Vue)
- Edge computing

Spring Boot needs:
- Long-running JVM process
- Persistent server
- More memory/CPU
- Not serverless-friendly

## ✅ Final Recommendation

**Use Railway.app for your complete Spring Boot application:**

1. Push code to GitHub ✅
2. Deploy to Railway (5 minutes) ✅
3. Add Railway MySQL database ✅
4. Get public URL ✅
5. Add custom domain (optional) ✅

**Your Grafix website will be fully functional with:**
- Admin portal
- Database
- File uploads
- Authentication
- All features working

---

**Ready to deploy to Railway?** Let me know and I'll guide you through the exact steps!
