

function AppClient(appObj, isAndroid) {
	this.appObj = appObj;
	this.isAndroid = isAndroid;
};

function setupWebViewJavascriptBridge(callback) {
    if (window.WebViewJavascriptBridge) { return callback(WebViewJavascriptBridge); }
    if (window.WVJBCallbacks) { return window.WVJBCallbacks.push(callback); }
    window.WVJBCallbacks = [callback];
    var WVJBIframe = document.createElement('iframe');
    WVJBIframe.style.display = 'none';
    WVJBIframe.src = 'wvjbscheme://__BRIDGE_LOADED__';
    document.documentElement.appendChild(WVJBIframe);
    setTimeout(function() { document.documentElement.removeChild(WVJBIframe) }, 0)
}

/**
 get init domain

 callback - function(domain) {}

 domain : {
	  "successful" : true,
	  "message" : "xxxxxx",
	  "networkOn" : true,

	  "appKey" : "17171",
	  "appVersion" : "1.3.0",
	  "appClient" : "android",
	  "net" : "wifi",
	  "versionCode" : 12,
	  "token" : "xxxxxx",
	  "guestId" : "xxxxx",
  }

 */
AppClient.prototype.getInitData = function (callbackName, callback) {
	var data = {
		"callback": callbackName
	};

	if (this.isAndroid) {
		this.appObj.getInitDataAction(JSON.stringify(data));
	} else {
		this.appObj.callHandler("getInitDataAction", data, callback);
	}
};

// show toast
AppClient.prototype.showToast = function (message) {
	if (this.isAndroid) {
		this.appObj.showToastAction(message);
	} else {
		this.appObj.callHandler("showToastAction", message);
	}
};

// go back
AppClient.prototype.goBack = function () {
	if (this.isAndroid) {
		this.appObj.goBackAction();
	} else {
		this.appObj.callHandler("goBackAction", {});
	}
};

// quit webview
AppClient.prototype.quit = function () {
	if (this.isAndroid) {
		this.appObj.quitAction();
	} else {
		this.appObj.callHandler("quitAction", {});
	}
};

// set title
AppClient.prototype.setTitle = function(data) {
	if (this.isAndroid) {
		this.appObj.setTitleAction(JSON.stringify(data));
	} else {
		this.appObj.callHandler("setTitleAction", data);
	}
};


/**
 screenName: "login", "bindAccount", "myCoupon", "goodCompanyList"
 */
AppClient.prototype.jumpToScreen = function (data) {
	if (this.isAndroid) {
		this.appObj.jumpToScreenAction(JSON.stringify(data));
	} else {
		this.appObj.callHandler("jumpToScreenAction", data);
	}
};



AppClient.prototype.login = function(data, callbackName) {
	data.callback = callbackName;

	if (this.isAndroid) {		
		this.appObj.loginAction(JSON.stringify(data));
	} else {
		this.appObj.callHandler("loginAction", data);
	}
};


AppClient.prototype.checkNetwork = function(callbackName) {
	var data = {
		"callback": callbackName
	};
	if (this.isAndroid) {
		this.appObj.checkNetworkAction(JSON.stringify(data));
	} else {
		this.appObj.callHandler("checkNetworkAction", data);
	}
};

/**
 share
 weixin, weixinfriend, qq, qqzone, weibo, sina, renren

 shareData : {
	   "shareFrom" : "xxx",
	   "type" : "weixin", 
		"title" : "xxx", 
		"content" : "sdfxdf", 
		"targetUrl" : "http://xxxx", 
		"callback" : "shareCallback"
   }

 callback - function(domain) {}
 domain : {
	  "successful" : true
   }

 */

AppClient.prototype.share = function (data, callbackName) {
	data.callback = callbackName;
	if (this.isAndroid) {
		this.appObj.shareAction(JSON.stringify(data));
	} else {
		this.appObj.callHandler("shareAction", data);
	}
};


AppClient.prototype.showLoading = function(){
	if (this.isAndroid) {
		this.appObj.showLoadingAction();
	} else {
		this.appObj.callHandler("showLoadingAction", {});
	}
}

AppClient.prototype.hideLoading = function(){
	if (this.isAndroid) {
		this.appObj.hideLoadingAction();
	} else {
		this.appObj.callHandler("hideLoadingAction", {});
	}
}

AppClient.prototype.paymentSuccess = function(){
	if (this.isAndroid) {
		this.appObj.paymentSuccessAction();
	} else {
		this.appObj.callHandler("paymentSuccessAction", {});
	}
}

AppClient.prototype.GPS = function(callbackName, callback){
	var data = {
		"callback": callbackName
	};

	if (this.isAndroid) {
		this.appObj.gpsAction(JSON.stringify(data));
	} else {
		this.appObj.callHandler("gpsAction", callback);
	}
}

AppClient.prototype.contactInfo = function(callbackName, callback){
	var data = {
		"callback": callbackName
	};

	if (this.isAndroid) {
		this.appObj.contactInfoAction(JSON.stringify(data));
	} else {
		this.appObj.callHandler("contactInfoAction", callback);
	}
}

AppClient.prototype.closeZmxy = function(){

	if (this.isAndroid) {
		this.appObj.closeZmxyAction();
	} else {
		this.appObj.callHandler("closeZmxyAction",{});
	}
}
