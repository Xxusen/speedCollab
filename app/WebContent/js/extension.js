// language extension

Object.prototype.setAttributes = function(attributes) {
	for(var attr in attributes) {
		this.setAttribute(attr, attributes[attr]);
	}
};

Element.prototype.applyCssBlock = function (declarations) {
	var cssText = "";
	for(var property in declarations){
		cssText += property+":"+declarations[property]+";"
	}
	this.style.cssText = cssText;
};

Element.prototype.addClass = function(classList) {
	classList = classList.trim().replace(/\s+/g," ");
	var classes = classList.split(" ");
	for(var i = 0; i < classes; i++){
		if(this.className.indexOf(classes[i]) != -1){
			classes.splice(i,1);
		}
	}
	classList = classes.join(" ");
	this.className += this.className.trim().length > 0 ? " "+classList : classList;
}

Element.prototype.removeClass = function(classList) {
	var classes = classList.trim().replace(/\s+/g, " ").split(" ");
	for(var i = 0; i < classes.length; i++){
		this.className = this.className.replace(classes[i], "");
	}
	this.className = this.className.trim().replace(/\s+/g," ");
}