var wsUri = "ws://" + document.location.host + document.location.pathname + "javawsendpoint";
var websocket = new WebSocket(wsUri);
websocket.binaryType = "arraybuffer";
websocket.onerror = function(evt) {  
     alert("received: " + evt.data); };
   // writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data); };
websocket.onmessage = function(evt) {  
    
    alert("received: " + evt.data); };
function onError(evt) {
   
}

function sendBinary(bytes) {
    console.log("sending binary: " + Object.prototype.toString.call(bytes));
    websocket.send(bytes);
}



function sendText(json) {
    console.log("sending text: " + json);
    websocket.send(json);
}
                
function onMessage(evt) {
   alert("received: " + evt.data);
   
}