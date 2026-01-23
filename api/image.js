const { getDb } = require('./lib/db');

module.exports = async (req, res) => {
    try {
        const { id } = req.query;
        if (!id) return res.status(400).send('Image ID required');

        const db = await getDb();
        const { rows } = await db.query('SELECT data, mime_type FROM stored_images WHERE id = $1', [id]);

        if (rows.length === 0) return res.status(404).send('Image not found');

        const image = rows[0];

        // Cache for performance (1 day)
        res.setHeader('Cache-Control', 'public, max-age=86400');
        res.setHeader('Content-Type', image.mime_type);
        res.send(image.data);

    } catch (error) {
        console.error('Image Fetch Error:', error);
        res.status(500).send('Error fetching image');
    }
};
