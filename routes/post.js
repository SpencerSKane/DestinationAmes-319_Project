const express = require('express');
const authController = require('../controllers/post');

const router = express.Router();

//if any post request is made to /postLocation in browser, uses authController.postLocation to send sql queries (shown in post.js in /controllers)
router.post('/postLocation', authController.postLocation);

//if any post request is made to /postReview in browser, uses authController.postReview to send sql queries (shown in post.js in /controllers)
router.post('/postReview', authController.postReview);

module.exports = router;