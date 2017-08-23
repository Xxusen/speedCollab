function fadeIn(elt, time) {
	var interval = 20;
	var step = 1 / time * interval;
	var opacity = 0;
	elt.style.opacity = 0;
	var intervalId = setInterval(function() {
		opacity = opacity + step;  
		elt.style.opacity = opacity;
	}, 20);
	setTimeout(function() {
		clearInterval(intervalId);
		elt.style.opacity = 1;
	}, time);
}