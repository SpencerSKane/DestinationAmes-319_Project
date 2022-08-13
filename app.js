const WebSocketServer = require("ws").Server;
const express = require("express");
const http = require("http");
const path = require('path');
const mysql = require("mysql");
const dotenv = require('dotenv');
var imageFunction = require("./public/image");
const async = require("hbs/lib/async");
var hbs = require('hbs');

dotenv.config({ path: './.env'});

const app = express();
const server = http.createServer(app);

//sql database connection
//use env variables
const db = mysql.createConnection({
    host: process.env.DATABASE_HOST,     //can change to IP of sql server once set up
    user: process.env.DATABASE_USER,          //using XAMPP locally which uses root as default user
    password: process.env.DATABASE_PASSWORD,
    database: process.env.DATABASE 
});

const publicDirectory = path.join(__dirname, './public'); //define dir to use css and javascript
app.use(express.static(publicDirectory)); //grab static files of css or javascript from dir

// Parse URL-encoded bodies (as sent by HTML forms)
app.use(express.urlencoded({ extended: false }));
// Parse JSON bodies (as sent by API clients)
app.use(express.json());

app.set('view engine', 'hbs'); //handlebars library for webpage templates

hbs.registerHelper('times', function(n, block) {
  var accum = '';

  for(var i = 0; i < n; ++i)
      n = Math.ceil(n);
      accum += block.fn(i);
  return accum;
});
hbs.registerHelper('for', function(from, to, incr, block) {
  var accum = '';
  from = Math.ceil(from);
  to = Math.ceil(to);
  
  for(var i = from; i < to; i += incr)
      accum += block.fn(i);
  return accum;
});


db.connect( (error) => {
    if(error) {
        console.log(error)
    } else {
        console.log("MYSQL connected...")
    }
})

//Define Routes
app.use('/', require('./routes/pages')); //whenever accessing forward slash, use defined routes in pages.js
app.use('/auth', require('./routes/auth')); 
app.use('/post', require('./routes/post')); 

server.listen(8080, () => {
    console.log("Server started on port 8080");
})

//WebSocket server implementation
const wss = new WebSocketServer({server: server});
wss.broadcast = function(data, sender) {
  wss.clients.forEach(function(client) {
    if (client !== sender) {
      client.send(data)
    }
  })
}
wss.on('connection', function connection(ws) {
    console.log("New websocket connection")
    ws.on('message', function(message) { //server receives message
      console.log("Message received from client")
      wss.broadcast(message,ws); //broadcast the message
    })
  })

