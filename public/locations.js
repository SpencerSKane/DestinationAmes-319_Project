//functions for opening/closing post forms
function openForm() {
    console.log("aaaa")
    document.getElementById("popupForm").style.display = "block";
  }
  
  function closeForm() {
    document.getElementById("popupForm").style.display = "none";
  }
  function delay(time){
    return new Promise(resolve => setTimeout(resolve, time));
    
  }

 