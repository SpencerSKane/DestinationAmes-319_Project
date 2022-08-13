const mysql = require("mysql");

//const useEffect = require('react');

const db = mysql.createConnection({
    host: process.env.DATABASE_HOST,     //can change to IP of sql server once set up
    user: process.env.DATABASE_USER,          //using XAMPP locally which uses root as default user
    password: process.env.DATABASE_PASSWORD,
    database: process.env.DATABASE 
});

exports.register = (req, res) => {
    console.log(req.body);

    const name = req.body.name;
    const email = req.body.email;
    const password = req.body.password;
    const passwordConfirm = req.body.passwordConfirm;

    db.query('SELECT email FROM users WHERE email = ?', [email], (error, results) => {
        if(error) {
            console.log(error);
        }

        if( results.length > 0 ) { //there is an email on database already
            return res.render('register', {
                message: 'That email is already in use'
            })
        } else if( password !== passwordConfirm ) {
            return res.render('register', {
                message: 'Passwords do not match'
            });
        } else if( name == "" || email == "" || password == "") {
            return res.render('register', {
                message: 'Fields were left blank'
            });
        }
        //SQL query to insert registration data into database
        db.query('INSERT INTO users SET ?', {name: name, email: email, password: password}, (error, results) => {
            if(error) {
                console.log(error);
            } else {
                console.log(results);
                return res.redirect('../login')
            }
        })
    })

}

exports.login = (req, res) => {
    const email = req.body.email;
    const password = req.body.password;
    db.query('SELECT email, name FROM users WHERE email = ? and password = ?', [email, password], (error, results) => {
        if(error) {
            console.log(error);
        }
        console.log("WOOO!", results)
        if( results.length == 1 ) { 
            
            //global.user =  JSON.stringify(results[0]);
            return res.redirect('../?user='+JSON.stringify({user:results[0]}))
        }
        else{
            return res.render('login', {
                message: 'ERROR'
            })
        }
    })
}