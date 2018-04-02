$(document).ready(function () {
    $('#media').carousel({
        pause: true,
        interval: false
    });
    // console.log("c");
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(showPosition);
        } else {
            x.innerHTML = "Geolocation is not supported by this browser.";
        }
        var geocoder = new google.maps.Geocoder();
    function codeLatLng(lat, lng) {
        var latlng = new google.maps.LatLng(lat, lng);
        geocoder.geocode({'latLng': latlng}, function(results, status) {
            if(status == google.maps.GeocoderStatus.OK) {
                console.log(results)
                if(results[1]) {
                    //formatted address
                    var address = results[0].formatted_address;
                    var len = results[0].address_components.length-1;
                    $('#loc').text(results[0].address_components[len-4].long_name+results[0].address_components[len].long_name);
                    // alert("address = " + results[0].address_components[len-4].long_name);
                } else {
                    alert("No results found");
                }
            } else {
                alert("Geocoder failed due to: " + status);
            }
        });
    }
    function showPosition(position) {
       console.log(position.coords.latitude);
        console.log(position.coords.longitude);
        var lat = position.coords.latitude;
        var lng = position.coords.longitude
        codeLatLng(lat, lng);
    }
});