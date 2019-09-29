function getData(){
    $.ajax({
        type: "GET",
        url: "/dht/lastData",
        success: function (data) {
            console.log(data);
        },
        error: function (e) {
            console.log(e);
        }
    });
};

$(document).ready(function () {
    setInterval(getData,2000);
});