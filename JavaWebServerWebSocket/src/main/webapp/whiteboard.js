              
function defineImageBinary() {
    var byteCharacters  = atob("AwAAAAAAAAAAAAAABgAAAAZEZW5lbWU=");
    
    var byteNumbers = new Array(byteCharacters.length);
    for (var i = 0; i < byteCharacters.length; i++) {
        byteNumbers[i] = byteCharacters.charCodeAt(i);
    }
    var byteArray = new Uint8Array(byteNumbers);
     sendBinary(byteArray);
   // var image = context.getImageData(0, 0, canvas.width, canvas.height);
    //var buffer = new ArrayBuffer(image.data.length);
    //var bytes = new Uint8Array(buffer);
    //for (var i=0; i<bytes.length; i++) {
      //  bytes[i] = image.data[i];
   // }
    
}

