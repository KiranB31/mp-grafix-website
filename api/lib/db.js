try { require('dotenv').config(); } catch (e) {}

const { Pool } = require('pg');

// Use prod first, fallback to local
const connectionString =
  process.env.DATABASE_URL || process.env.POSTGRES_URL;

if (!connectionString) {
  throw new Error("No database connection string found");
}

const isLocal = connectionString.includes("localhost");

const pool = new Pool({
  connectionString,
  ssl: isLocal ? false : { rejectUnauthorized: false }
});

module.exports = pool;
