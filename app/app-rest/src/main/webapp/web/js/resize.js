(function (doc, win) {
	var docEl = doc.documentElement,
    resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
    recalc = function () {
        var clientWidth = docEl.clientWidth;
        if (!clientWidth) return;
        if(clientWidth>=640){
            clientWidth=640;
        }else if(clientWidth<=320){
            clientWidth=320;
        }else{}
        docEl.style.fontSize = 10 * (clientWidth / 320) + 'px';
    };
    if (!doc.addEventListener) return;
    win.addEventListener(resizeEvt, recalc, false);
    doc.addEventListener('DOMContentLoaded', recalc, false);
})(document, window);