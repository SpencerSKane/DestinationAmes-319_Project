//code derived from --> https://github.com/PracticalCodeAcademy/responsive-chat-popup-window
//javascript file for chatbox functionality

const popup = document.querySelector('.chat-popup');
const chatBtn = document.querySelector('.chat-btn');
const submitBtn = document.querySelector('.submit');
const chatArea = document.querySelector('.chat-area');
const inputElm = document.querySelector('input[name="chat_input"]'); //make sure name is specified for distinction between other input types
const emojiBtn = document.querySelector('#emoji-btn');
const picker = new EmojiButton();

const emojiBtnPost = document.querySelector('#emoji-btn-post');
const inputElmPost = document.querySelector('input[name="thoughts"]');
const pickerPost = new EmojiButton();



// Emoji selection  
window.addEventListener('DOMContentLoaded', () => {

    picker.on('emoji', emoji => {
      inputElm.value += emoji; //again make sure name is specified for input
    });
  
    emojiBtn.addEventListener('click', () => {
      picker.togglePicker(emojiBtn);
    });
  });     
  

// chat button toggler 
chatBtn.addEventListener('click', ()=>{
    popup.classList.toggle('show');
})


//event listener for hitting enter to submit text in chat input
inputElm.addEventListener("keyup", function(event) {
  // Number 13 is the "Enter" key on the keyboard
  if (event.keyCode === 13) {
    // Cancel the default action, if needed
    event.preventDefault();
    // Trigger the button element with a click
    submitBtn.click();
  }
});

//functions for opening/closing post forms
function openForm() {
  document.getElementById("popupForm").style.display = "block";
}

function closeForm() {
  document.getElementById("popupForm").style.display = "none";
}


// Emoji selection for posts
window.addEventListener('DOMContentLoaded', () => {

  pickerPost.on('emoji', emoji => {
    inputElmPost.value += emoji; //again make sure name is specified for input
  });

  emojiBtnPost.addEventListener('click', () => {
    pickerPost.togglePicker(emojiBtn);
  });
}); 


////////////////////////////////////////////////////
//      WebSocket client side implementation      //
////////////////////////////////////////////////////

//function to display what client sent in chat window
function showMessage(message) {
  let temp = `<span class="avatarMe">You</span>
  <div class="out-msg">
  <span class="my-msg">${message}</span>
  </div>`;

  chatArea.insertAdjacentHTML("beforeend", temp);
  inputElm.value = '';
}

//function to display what client receives in chat window
function showReceived(message) {
  const data = JSON.parse(message);
  let temp = `<span class="avatarYou">${data.username}</span>
  <div class="income-msg">
  <span class="msg">${data.message}</span>
  </div>`;

  chatArea.insertAdjacentHTML("beforeend", temp);
}

//message to display websocket status
function showStatus(message) {
  let temp = `<span class="socket-status">${message}</span>`;

  chatArea.insertAdjacentHTML("beforeend", temp);
  inputElm.value = '';
}

let ws;

function socketInit() {
  if (ws) {
    ws.onerror = ws.onopen = ws.onclose = null;
    ws.close();
  }

  ws = new WebSocket('ws://localhost:8080');
        ws.onopen = () => { //when socket is opened by client
          console.log("Websocket connection opened!");
          showStatus("Websocket connection opened!");
        }
        ws.onmessage = ({ data }) => { //when client receives a message
          var reader = new FileReader(); //use a reader to read the "blob" data and send it to showReceived()
          reader.onload = function() {
            showReceived(reader.result);
          }
          reader.readAsText(data);
        }
        ws.onclose = function() {
          ws = null;
        }
  }
socketInit();
  
// submitBtn.onclick = function() {
//       if (!ws) {
//         showStatus("No WebSocket connection");
//         return ;
//       }
//       ws.send(inputElm.value);
//       showMessage(inputElm.value);
// }

submitBtn.onclick = function() {
  if (!ws) {
    showStatus("No WebSocket connection");
    return ;
  }
  ws.send(JSON.stringify({
    username: user.name,
    message: inputElm.value
  }));
  showMessage(inputElm.value);
}