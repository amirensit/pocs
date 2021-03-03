var courbes = [];
var chart = new SmoothieChart();
chart.streamTo(document.getElementById("chart"), 400);
var colors = [
    {
        strokeStyle: 'rgba(255, 0, 0, 1)', // rouge
        fillStyle: 'rgba(255, 0, 0, 0.2)', // rouge
        lineWidth: 2
    },
    {
        strokeStyle: 'rgba(0, 255, 0, 1)', // vert
        fillStyle: 'rgba(0, 255, 0, 0.2)', // vert
        lineWidth: 2
    },
    {
        strokeStyle: 'rgba(0, 0, 255, 1)', // rouge
        fillStyle: 'rgba(0, 0, 255, 0.2)', // rouge
        lineWidth: 2
    },
]
let index = -1;
function onConnect(id) {
    if (!courbes[id]) {
        courbes[id] = new TimeSeries();
        var color = randomColor();
        chart.addTimeSeries(courbes[id], color);
        var connection = new EventSource("/transactions");
        connection.onmessage = function(resp) {
        var price = JSON.parse(resp.data).price;
        courbes[id].append(new Date().getTime(), price);
    }
   }
}

function randomColor() {
    ++index;
    if (index >= colors.length) index = 0;
    return colors[index];
}