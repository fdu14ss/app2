/**
 * Created by duocai on 2016/3/11.
 */
var exec = require('cordova/exec');

exports.openMap = function() {
  exec(null, null, "IndoorMap", null, []);
};
