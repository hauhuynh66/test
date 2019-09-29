var chart1 = $("#tempChart");
var chart2 = $("#humidChart");
var tempChart = new Chart(chart1,{
    type: "line",
    data: {
        labels: [],
        datasets: [{
            label: "C",
            backgroundColor: "#ff0000",
            borderColor: "#000000",
            borderWidth: 2,
            data: []
        }]
    },
    options: {
        legend: {
            display: false
        }
    }
});

var humidChart = new Chart(chart2,{
    type: "line",
    data: {
        labels: [],
        datasets: [{
            label: "C",
            backgroundColor: "#0000ff",
            borderColor: "#000000",
            borderWidth: 2,
            data: []
        }]
    },
    options: {
        legend: {
            display: false
        }
    }
});

/*function getData() {
    $.ajax({
        type: "GET",
        url: "/sensor/getData",
        success: function (data) {
            console.log(data);
        },
        error: function (e) {
            console.log(e);
        },
        complete: function () {
            
        }
    });
}*/
var x = 0;
function getData(){
    x++
    addData(tempChart,x,Math.floor(Math.random()*30));
    if(tempChart.data.datasets[0].data.length>=10){
        tempChart.data.labels.shift();
        tempChart.data.datasets[0].data.shift();
    }
}

setInterval(getData,2000);

function addData(chart, label, data) {
    chart.data.labels.push(label);
    chart.data.datasets.forEach(function (dataset) {
        dataset.data.push(data);
    });
    chart.update();
}

function generateColor(){
    var c = "#";
    var str = "0123456789ABCDEF";
    for(i=0;i<6;i++){
        c += str.charAt(Math.floor(Math.random()*str.length));
    }
    return c;
}