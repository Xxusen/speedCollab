

// creates a custom password field, inserts it where the script invoking it is.
function createReviewablePwdField(fieldAttributes, required) {
	//config
	if(fieldAttributes.eyeColor){
		iconColor = fieldAttributes.eyeColor;
		delete(fieldAttributes["eyeColor"]);
	} else {
		iconColor = "white";
	}
	var iconOpacity = "0.3";
	var iconHoverOpacity = "0.8";
	var iconLateralSpace = 5;

	//html elements declaration or creation
	var container = Array.prototype.slice.call(document.getElementsByTagName("script")).slice(-1)[0].parentElement;
	var wrapper = document.createElement("div");
	var pwdField = document.createElement("input");
	var icon = document.createElementNS("http://www.w3.org/2000/svg", "svg");

	//wrapper
	wrapper.applyCssBlock({
		"position"	: "relative",
		"display"	: "inline-block",
	});

	//setting pwdField with the attributes cared by the fieldAttributes and required arguments
	fieldAttributes["type"] = "password";
	pwdField.setAttributes(fieldAttributes);
	if(required)
		pwdField.required = true;
	//appending wrapper to document so that its input child becomes measurable
	wrapper.appendChild(pwdField);
	container.appendChild(wrapper);

	//icon (eye)
	var iconWidth = pwdField.offsetHeight/2;
	icon.setAttributes({
		"x"			: "0px",
		"y"			: "0px",
		"viewBox"	: "0 98 487.85 292",
	});
	var sclera = document.createElementNS(icon.namespaceURI, "path");
	sclera.setAttributes({
		"fill":iconColor, 
		"d":"M243.925,98.525c-93.2,0-177.7,50.9-240.1,133.8c-5.1,6.8-5.1,16.2,0,23c62.4,83,146.9,134,240.1,134s177.7-50.9,240.1-133.8c5.1-6.8,5.1-16.2,0-23C421.625,149.525,337.125,98.525,243.925,98.525z M250.625,346.325c-61.9,3.9-112.9-47.1-109-109c3.2-51,44.6-92.5,95.7-95.7c61.9-3.9,112.9,47.1,109,109C343.025,301.625,301.625,343.025,250.625,346.325z",
	});
	var iris = document.createElementNS(icon.namespaceURI, "path");
	iris.setAttributes({
		"fill":iconColor,
		"d":"M247.525,299.025c-33.3,2.1-60.8-25.4-58.7-58.7c1.7-27.5,24-49.8,51.5-51.5c33.3-2.1,60.8,25.4,58.7,58.7C297.325,275.025,275.025,297.325,247.525,299.025z",
	});
	icon.applyCssBlock({
		"display"		: "none",
		"position"		: "absolute",
		"width"			: iconWidth+"px",
		"height" 		: iconWidth/2+"px",
		"left"			: pwdField.offsetLeft + pwdField.offsetWidth - (iconWidth+iconLateralSpace) +"px",
		"top"			: (pwdField.offsetHeight - iconWidth/2)/2+"px",
		"cursor" 		: "pointer",
		"opacity"		: iconOpacity,
	});
	icon.appendChild(sclera);
	icon.appendChild(iris);
	//a little space so
	pwdField.style.paddingRight = iconWidth+iconLateralSpace*2+"px";

	//events
	icon.addEventListener("mouseover", function(e) {
		icon.style.opacity = iconHoverOpacity;
		pwdField.setAttribute("type", "text");
	});
	icon.addEventListener("mouseout", function(e) {
		icon.style.opacity = iconOpacity;
		pwdField.setAttribute("type", "password");
	});
	pwdField.addEventListener("focus", function(e) {
		icon.style.display = "inline-block";
	});
	pwdField.addEventListener("blur", function(e) {
		icon.style.display = "none";
	});
	wrapper.appendChild(icon);
}

// ajax custom module
function PostRequest(params, url, handler){
	this.xhr = new XMLHttpRequest();
	this.xhr.open("POST", url);
	this.xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	this.params = params;
	this.xhr.addEventListener("readystatechange", function(e) {
		var xhr = e.target;
		if(xhr.readyState == 4){
			if(xhr.status == 200) {
				handler(xhr.responseText);
			} else {
				alert("error while trying to reach server - "+xhr.status+" : "+xhr.statusText);
			}
		}
	});
}
PostRequest.prototype.send = function() {this.xhr.send(this.params);};