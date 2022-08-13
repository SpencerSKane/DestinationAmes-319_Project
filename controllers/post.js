const mysql = require("mysql");

const db = mysql.createConnection({
    host: process.env.DATABASE_HOST,     //can change to IP of sql server once set up
    user: process.env.DATABASE_USER,          //using XAMPP locally which uses root as default user
    password: process.env.DATABASE_PASSWORD,
    database: process.env.DATABASE 
});

exports.postLocation = (req, res) => {
    //Recives the req data from the fetch call made in index.hbs
    console.log(req.body);

    //Inserts data into database
    db.query('INSERT INTO locations SET ?', {name: req.body.name, address: req.body.address, imageLocation: req.body.url, description: req.body.description, creator: req.body.user}, (error, results) => {
        if(error) {
            console.log(error);
        } else {
            console.log(results);
            return res.redirect('./')
        }
    })
}

exports.postReview = (req, res) => {
    console.log(req.body);
    db.query('INSERT INTO reviews SET ?', {star: req.body.star, locationName: req.body.locationName,title: req.body.title, image: req.body.image, author: req.body.author, description: req.body.description, date: req.body.date}, (error, results) => {
        if(error) {
            console.log(error);
        } else {
            console.log(results);
            return res.redirect('./')
        }
    })
}

