Ti.WaterWaveProgress
====================

This is a Titanium module that exposes the library of [Modificator](https://github.com/Modificator/water-wave-progress).com

<img src="https://i.imgur.com/UNoOYg4.gif" width=240 />

Usage
=====

~~~
var progress = 0;
var progressView = require('ti.appwerft.waterwaveprogress').createView({
maxProgress : 100,
ringWidth:4,
waterColor: '#00ff00',
waterBgColor: '#00aa00',
ring2WaterWidth : 10.1,
fontSize : 22,
showRing : true,
showNumerical : true,
crestCount : 2.2,
amplitude : 0.4,
α : 0.7
});


var setprogressFn = function() {
if (progress<100 && progressView) {
progressView.setProgress(progress);
progress++;
setTimeout(setProgressFn,20);
}
};

setprogressFn();
~~~

More parameters methods you can find in sources.

