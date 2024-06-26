var stompClient = null;

function setConnected(connected) {
  $("#connect").prop("disabled", connected);
  $("#disconnect").prop("disabled", !connected);
  if (connected) {
    $("#conversation").show();
  }
  else {
    $("#conversation").hide();
  }
  $("#greetings").html("");
}

function connect() {
  var socket = new SockJS('/ws');
  stompClient = Stomp.over(socket);
  var channelId = $("#channelId").val();

  stompClient.connect({}, function (frame) {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/' + channelId, function (message) {
      console.log('Received a message from /topic/' + channelId + ': ' + message.body);
      handleSubscribeMessage(JSON.parse(message.body));
    });
    stompClient.subscribe('/user/queue/errors', function (message) {
      console.log('Received a message from /topic/greetings: ' + message.body);
      alert('Error: ' + message.body);
    });
  });
}

function disconnect() {
  if (stompClient !== null) {
    stompClient.disconnect();
  }
  setConnected(false);
  console.log("Disconnected");
}

function sendName() {
  stompClient.send("/app/category/join", {},
      JSON.stringify({
        'channelId': $("#channelId").val(),
        'userName': $("#name").val(),
      }));
}

function viewTocket() {
  stompClient.send("/app/category/ticket/view", {},
      JSON.stringify({
        'channelId': $("#channelId").val(),
        'ticketId': $("#ticketId").val(),
        'userName': $("#name").val(),
      }));
}

function handleSubscribeMessage(message) {
  if (message.eventType === 'CATEGORY_JOIN') {
    $("#greetings").append("<tr><td>" + "user join: " + message.userName + "</td></tr>");
  } else if (message.eventType === 'VIEW_TICKET') {
    $("#greetings").append("<tr><td>" + "user : " + message.userName + ", exclude ticket: " + message.body.ticketId + "</td></tr>");
  }
}

$(function () {
  $("form").on('submit', function (e) {
    e.preventDefault();
  });
  $( "#connect" ).click(function() { connect(); });
  $( "#disconnect" ).click(function() { disconnect(); });
  $( "#send" ).click(function() { sendName(); });
  $( "#viewTicket" ).click(function() { viewTocket(); });
});