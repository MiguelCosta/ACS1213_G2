<!DOCTYPE html />
<html>
<head>
<title>Extensive Google Maps Directions demo</title>
<link href='http://fonts.googleapis.com/css?family=Terminal+Dosis:800,600' rel='stylesheet' type='text/css'>
  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
  <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script>
var directionsDisplay;
var directionsService = new google.maps.DirectionsService();
var map;

function initialize() {
  directionsDisplay = new google.maps.DirectionsRenderer();
  var chicago = new google.maps.LatLng(41.545974,-8.434753);
  var mapOptions = {
    zoom: 8,
    mapTypeId: google.maps.MapTypeId.ROADMAP,
    center: chicago
  }
  map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
  directionsDisplay.setMap(map);
}

function calcRoute() {
  var start = document.getElementById('start').value;
  var end = document.getElementById('end').value;
  var waypts = [];
  var checkboxArray = document.getElementById('waypoints');
  for (var i = 0; i < checkboxArray.length; i++) {
    if (checkboxArray.options[i].selected == true) {
      waypts.push({
          location:checkboxArray[i].value,
          stopover:true});
    }
  }

  var request = {
      origin: start,
      destination: end,
      waypoints: waypts,
      optimizeWaypoints: true,
      travelMode: google.maps.TravelMode.DRIVING
  };
  directionsService.route(request, function(response, status) {
    if (status == google.maps.DirectionsStatus.OK) {
      directionsDisplay.setDirections(response);
      var route = response.routes[0];
      var summaryPanel = document.getElementById('directions_panel');
      summaryPanel.innerHTML = '';
      // For each route, display summary information.
      for (var i = 0; i < route.legs.length; i++) {
        var routeSegment = i + 1;
        summaryPanel.innerHTML += '<b>Route Segment: ' + routeSegment + '</b><br>';
        summaryPanel.innerHTML += route.legs[i].start_address + ' to ';
        summaryPanel.innerHTML += route.legs[i].end_address + '<br>';
        summaryPanel.innerHTML += route.legs[i].distance.text + '<br><br>';
      }
    }
  });
}

google.maps.event.addDomListener(window, 'load', initialize);

    </script>
  </head>
  <body>
    <div id="map-canvas" style="float:left;width:50%;height:70%;"></div>
    <div id="control_panel" style="float:right;width:30%;text-align:left;padding-top:20px">
    <div style="margin:20px;border-width:2px;">
    <b>Start:</b>
    <input type="text" id="start">
    <br>
    <b>Waypoints:</b> <br>
    <i>(Ctrl-Click for multiple selection)</i> <br>
    <select multiple id="waypoints">
      <option value="Braga">Braga</input>
      <option value="Aveiro">=Aveiro</input>
      <option value="Porto">Porto</input>
    </select>
    <br>
    <b>End:</b>
    <input type="text" id="end">
    <br>
      <input type="submit" onclick="calcRoute();">
    </div>
    <div id="directions_panel" style="margin:20px;background-color:#FFEE77;"></div>
    </div>
  </body>
</html>