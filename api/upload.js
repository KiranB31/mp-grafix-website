const multer = require('multer');
const { getDb } = require('./lib/db');
const { v4: uuidv4 } = require('uuid');

// Use Memory Storage to get the buffer
const storage = multer.memoryStorage();

const upload = multer({
    storage: storage,
    limits: { fileSize: 5 * 1024 * 1024 }, // 5MB limit
    fileFilter: (req, file, cb) => {
        if (file.mimetype.startsWith('image/')) {
            cb(null, true);
        } else {
            cb(new Error('Only images are allowed'));
        }
    }
}).single('file');

module.exports = async (req, res) => {
    upload(req, res, async function (err) {
        if (err) return res.status(400).json({ error: err.message });
        if (!req.file) return res.status(400).json({ error: 'No file uploaded' });

        try {
            const db = await getDb();
            const id = uuidv4();

            // Insert into DB
            await db.query(
                'INSERT INTO stored_images (id, data, mime_type, filename) VALUES ($1, $2, $3, $4)',
                [id, req.file.buffer, req.file.mimetype, req.file.originalname]
            );

            // Return URL that points to our new image serving endpoint
            const fileUrl = `/api/image?id=${id}`;

            return res.status(200).json({
                success: true,
                url: fileUrl,
                filename: req.file.originalname
            });
        } catch (dbErr) {
            console.error('Database Upload Error:', dbErr);
            return res.status(500).json({ error: 'Failed to save image to database' });
        }
    });
};
