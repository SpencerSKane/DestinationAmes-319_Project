const express = require('express');
const async = require('hbs/lib/async');

const router = express.Router();

const mysql = require("mysql");

//const useEffect = require('react');

const db = mysql.createConnection({
    host: process.env.DATABASE_HOST,     //can change to IP of sql server once set up
    user: process.env.DATABASE_USER,          //using XAMPP locally which uses root as default user
    password: process.env.DATABASE_PASSWORD,
    database: process.env.DATABASE 
});

//if any get request is made to / in browser, renders index from views
router.get('/', async (req, res) => {
    //Grab the data from sql database. Join reviews table so that you also get the average star. Promise because db query is a async call.
    var locations = await new Promise((resolve, reject) =>  db.query(
        'select locationName, avg(star) as rating, locations.name, address, locations.imageLocation, locations.description, locations.creator from reviews right join locations on locations.name = reviews.locationName group by locationName, name, address, imageLocation, description, creator order by  avg(star) desc', [], (error, results) => {
        locations = JSON.parse(JSON.stringify(results));
        resolve(locations);
    }));
    //pass the query data to the hsb file as the variable locationsList 
    res.render('index', {
        locationsList: locations ? locations : null,
    });
});

//if any get request is made to /register in browser, renders register from views
router.get('/register', (req, res) => {
    res.render('register');
});

//if any get request is made to /login in browser, renders login from views
router.get('/login', (req, res) => {
    res.render('login');
});

//if any get request is made to /profile in browser, renders profile from views
router.get('/profile', (req, res) => {
    res.render('profile', {
    });
});

//if any get request is made to /logout in browser, removes global user and redirects to index from views
router.get('/logout', (req, res) => {
	global.user = null;
	req.session = null;
	res.redirect('/?logout=true');
});

router.get('/locations/*', async (req, res) => {
    //Get any url that has /locations before it.

    var locationPath = decodeURI(req.path).substring(11)
    if(locationPath.indexOf("/")>-1){
        locationPath = locationPath.substring(0,locationPath.indexOf("/"))
    }
    //Convert url to get the name of the location
    
    //Grab the location data.
    var ans = await new Promise((resolve, reject) =>  db.query('SELECT * FROM locations where name = ?', [locationPath], (error, results) => {
        ans = JSON.parse(JSON.stringify(results));
        resolve(ans);
    }));
    var locationData = ans[0]

    //Grab the locations average star.
    ans = await new Promise((resolve, reject) =>  db.query('SELECT AVG(star) as rating FROM destinationAmes.reviews where  locationName = ?', [locationPath], (error, results) => {
        ans = JSON.parse(JSON.stringify(results));
        resolve(ans);
    }));
    //Set the rating to a round value.
    try{
        locationData["rating"] = Math.ceil(ans[0].rating);
    }
    catch (e){
    }



         //Grab the review data
 var ansR = await new Promise((resolve, reject) =>  db.query('SELECT * FROM reviews where locationName = ?', [locationPath], (error, results) => {
    ansR = JSON.parse(JSON.stringify(results));
    resolve(ansR);
}));

    // Pass all this data in as locationData.
    res.render('locations', {
        locationData: locationData,
		ansR: ansR,
    });


});

module.exports = router;