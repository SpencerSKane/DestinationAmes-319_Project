const express = require('express');
const authController = require('../controllers/auth');

const router = express.Router();

//if any post request is made to /register in browser, uses authController.register to send sql queries (shown in auth.js in /controllers)
router.post('/register', authController.register);
//if any post request is made to /login in browser, uses authController.login to send sql queries (shown in auth.js in /controllers)
router.post('/login', authController.login);

module.exports = router;