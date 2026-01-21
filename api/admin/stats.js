const { getDb } = require('../lib/db');

module.exports = async (req, res) => {
    try {
        const db = await getDb();

        const [[{ count: productCount }]] = await db.execute('SELECT COUNT(*) as count FROM products');
        const [[{ count: messageCount }]] = await db.execute('SELECT COUNT(*) as count FROM contact_messages');
        const [[{ count: visitorCount }]] = await db.execute('SELECT COUNT(*) as count FROM visitor_logs');

        // Note: For reviews, posts, subscribers - you would add tables for these as you scale
        // For now returning current MySQL counts
        res.status(200).json({
            productCount,
            messageCount,
            visitorCount,
            postCount: 0, // Placeholder
            subscriberCount: 0 // Placeholder
        });
    } catch (error) {
        console.error(error);
        res.status(500).json({ error: error.message });
    }
};
