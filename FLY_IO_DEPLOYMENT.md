# Fly.io Deployment Guide for Grafix Website

## 🚀 Quick Deploy to Fly.io (FREE)

### Prerequisites
- Git installed
- Fly.io account (free)
- Your code ready

---

## Step 1: Install Fly.io CLI

### Windows (PowerShell):
```powershell
iwr https://fly.io/install.ps1 -useb | iex
```

### Verify Installation:
```bash
fly version
```

---

## Step 2: Login to Fly.io

```bash
fly auth login
```

This will open your browser to authenticate.

---

## Step 3: Push Code to GitHub

```bash
cd c:\Users\admin\Downloads\spring-boot-grafana-loki-demo\grafix-website

# Create GitHub repository first, then:
git remote add origin https://github.com/YOUR_USERNAME/grafix-website.git
git add .
git commit -m "Ready for Fly.io deployment"
git branch -M main
git push -u origin main
```

---

## Step 4: Create Fly.io App

```bash
cd c:\Users\admin\Downloads\spring-boot-grafana-loki-demo\grafix-website

# Launch app (this creates the app on Fly.io)
fly launch
```

**Answer the prompts:**
- App name: `grafix-website` (or your choice)
- Region: Choose closest to you (e.g., `sin` for Singapore)
- PostgreSQL: **YES** (select Yes when asked)
- Redis: **NO**

---

## Step 5: Create PostgreSQL Database

If not created during launch:

```bash
fly postgres create --name grafix-db
```

**Attach database to your app:**
```bash
fly postgres attach grafix-db --app grafix-website
```

This automatically sets `DATABASE_URL` environment variable.

---

## Step 6: Set Environment Variables

```bash
fly secrets set SPRING_PROFILES_ACTIVE=prod
```

---

## Step 7: Deploy!

```bash
fly deploy
```

This will:
1. Build your Spring Boot app
2. Create Docker container
3. Deploy to Fly.io
4. Run Flyway migrations
5. Start your application

---

## Step 8: Access Your App

```bash
# Open in browser
fly open

# Or get the URL
fly status
```

Your app will be at: `https://grafix-website.fly.dev`

**Admin Login:**
- URL: `https://grafix-website.fly.dev/login`
- Username: `admin`
- Password: `admin123`

---

## 📊 Useful Commands

### View Logs:
```bash
fly logs
```

### Check Status:
```bash
fly status
```

### SSH into App:
```bash
fly ssh console
```

### Scale App:
```bash
fly scale count 1  # Number of instances
fly scale vm shared-cpu-1x  # VM size
```

### Database Console:
```bash
fly postgres connect -a grafix-db
```

---

## 🔧 Troubleshooting

### App Not Starting?
```bash
# Check logs
fly logs

# Restart app
fly apps restart grafix-website
```

### Database Connection Issues?
```bash
# Check database status
fly postgres db list -a grafix-db

# Verify DATABASE_URL is set
fly secrets list
```

### Need to Redeploy?
```bash
git add .
git commit -m "Update"
git push
fly deploy
```

---

## 💰 Fly.io Free Tier

**What's Included (FREE):**
- ✅ 3 shared VMs (256MB RAM each)
- ✅ 3GB persistent storage
- ✅ 160GB bandwidth/month
- ✅ PostgreSQL database
- ✅ Always running (no sleep)

**Perfect for:**
- Production apps
- Always-on services
- Low to medium traffic

---

## 🎯 Post-Deployment Checklist

- [ ] App deployed successfully
- [ ] Database connected
- [ ] Admin login works
- [ ] Change admin password
- [ ] Upload test product
- [ ] Test contact form
- [ ] Add custom domain (optional)

---

## 🌐 Custom Domain (Optional)

```bash
# Add your domain
fly certs add yourdomain.com

# Get DNS records to add
fly certs show yourdomain.com
```

Add the provided DNS records to your domain registrar.

---

## 📈 Monitoring

### View Metrics:
```bash
fly dashboard
```

### Set up Alerts:
Configure in Fly.io dashboard for:
- High CPU usage
- Memory issues
- App crashes

---

## 🔄 Updates & Redeployment

Whenever you make changes:

```bash
git add .
git commit -m "Your update message"
git push
fly deploy
```

---

## 🆘 Support

- **Fly.io Docs**: https://fly.io/docs
- **Community**: https://community.fly.io
- **Status**: https://status.flyio.net

---

**Your Grafix website is now live on Fly.io! 🎉**

Access it at: `https://grafix-website.fly.dev`
