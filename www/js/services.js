angular.module('starter.services', [])

.factory('Chats', function() {
  // Might use a resource here that returns a JSON array

  // Some fake testing data
  var chats = [{
    id: 0,
    name: 'Origo',
    lastText: 'Origo',
    face: 'img/Origo.png'
  }, {
    id: 1,
    name: 'Origo',
    lastText: 'Origo',
    face: 'img/Origo.png'
  }, {
    id: 2,
    name: 'Origo',
    lastText: 'Origo',
    face: 'img/Origo.png'
  }];

  return {
    all: function() {
      return chats;
    },
    remove: function(chat) {
      chats.splice(chats.indexOf(chat), 1);
    },
    get: function(chatId) {
      for (var i = 0; i < chats.length; i++) {
        if (chats[i].id === parseInt(chatId)) {
          return chats[i];
        }
      }
      return null;
    }
  };
});
