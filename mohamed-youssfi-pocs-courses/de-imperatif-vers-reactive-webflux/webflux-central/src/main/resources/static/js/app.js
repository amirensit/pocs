function onConnect(id) {
    var connection = new EventSource("/transactions");
    connection.onmessage = function(resp) {
    console.log(JSON.parse(resp.data).price);
    }
}