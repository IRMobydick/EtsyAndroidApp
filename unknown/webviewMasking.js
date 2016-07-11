var _4c = {};

(function(o) {
    var dummyCommentVar;
    dummyCommentVar = "Comments are not allowed here since they screw up javascript functionality when injected";
    dummyCommentVar = "Use this variable to add comments instead";

    var maskExclusionSelectors = [];
    var maskInclusionSelectors = [];
    var maskedElements = [];
    var getElementsMatchingSelectors = function(selectors) {
                            var result = [];
                            selectors.map(function(selector){
                                [].slice.call(document.querySelectorAll(selector)).map(function(e){result.push(e)});
                            });
                            return result;
    };
    var getElementData = function(index,element) {
                              var position = element.getBoundingClientRect();
                              return {
                                  "i": index+1,
                                  "x": position.left,
                                  "y": position.top,
                                  "w": position.width,
                                  "h": position.height
                              };
    };

    var watching = false;

    var updateMaskedElements = function() {
        var masked = getElementsMatchingSelectors(maskInclusionSelectors);
        var unmasked = getElementsMatchingSelectors(maskExclusionSelectors);
        maskedElements = masked
            .filter(function(e){return unmasked.indexOf(e) == -1;});
    };

    o.addExclusionSelector = function(selector) {
        maskExclusionSelectors.push(selector);
    };
    o.addInclusionSelector = function(selector) {
        maskInclusionSelectors.push(selector);
    };

    var stringify = function (value, replacer, space) {
        var ret;
        var backupPrototype;
        if (window["Prototype"]) {
            backupPrototype = Array.prototype.toJSON;
            delete Array.prototype.toJSON;
        }

        if (!window["JSON"] || typeof window["JSON"].stringify !== 'function') {

            var i;
            gap = '';
            indent = '';

            if (typeof space === 'number') {
                for (i = 0; i < space; i += 1)
                    indent += ' ';

            } else if (typeof space === 'string')
                indent = space;
            rep = replacer;
            if (replacer && typeof replacer !== 'function' &&
                (typeof replacer !== 'object' ||
                    typeof replacer.length !== 'number')) {
                throw new Error('_4c.stringify');
            }

            ret = stringTranslator('', {'': value});
        } else
            ret = window["JSON"].stringify(value, replacer, space);

        if (typeof(backupPrototype)!="undefined")
            Array.prototype.toJSON = backupPrototype;

        return ret;
    };

    o.getMaskedInputs = function(callback, refresh) {
        if(refresh === true)
            updateMaskedElements();
        var idx = 0;
        var result = {
            "masks": maskedElements.map(function(e){return getElementData(idx++,e);}),
            "offset" : {
                        "windowYOffset": window.pageYOffset,
                        "windowXOffset": window.pageXOffset
                    }
        };
        var returnedJSON = stringify(result);
        masking.log('Masks returned:' + returnedJSON);

        callback(stringify(result));
    };

    o.startWatching = function(interval,callback) {
        var id = setTimeout(function() {
                masking.logVerbose('checking masks on ' + document.URL);
                o.getMaskedInputs(callback,false);
                if(watching === true) {
                    setTimeout(arguments.callee,interval);
                };
            },interval);
        watching = true;
    };

    o.stopWatching = function() {
        masking.logVerbose('stopWatching on ' + document.URL);
        watching = false;
    };

    o.init = function(interval){
        maskInclusionSelectors.push("input:not([type=hidden]):not([type=button]):not([type=checkbox]):not([type=color]):not([type=radio]):not([type=range]):not([type=reset]):not([type=submit]),textarea,select");
        maskExclusionSelectors.push("input[style='visibility:hidden'],input[style='visibility:collapse'],*[class*='fs-unmasked']");
        masking.log('Initializing JS interface');
        var t = this;
        window.addEventListener("resize", function() { t.getMaskedInputs(function(arg){masking.setMaskRect(arg)},false)} ,false);
        window.addEventListener('DOMNodeInserted', function (event) {t.getMaskedInputs(function(arg){masking.setMaskRect(arg)},true) }, false);
        window.addEventListener('DOMNodeRemoved',function (event) {t.getMaskedInputs(function(arg){masking.setMaskRect(arg)},true) }, false);
        t.startWatching(interval,function(arg){masking.setMaskRect(arg)});
        masking.log('JS interface initialized');
    };

    dummyCommentVar = "The following is a pure javascript implementation of JQuery's .ready()";
    dummyCommentVar = "Taken from http://stackoverflow.com/questions/9899372/pure-javascript-equivalent-to-jquerys-ready-how-to-call-a-function-when-the";

    (function(funcName, baseObj) {
        funcName = funcName || "docReady";
        baseObj = baseObj || window;
        var readyList = [];
        var readyFired = false;
        var readyEventHandlersInstalled = false;

        function ready() {
            if (!readyFired) {
                readyFired = true;
                for (var i = 0; i < readyList.length; i++) {
                    readyList[i].fn.call(window, readyList[i].ctx);
                }
                readyList = [];
            }
        }

        function readyStateChange() {
            if ( document.readyState === "complete" ) {
                ready();
            }
        }

        baseObj[funcName] = function(callback, context) {
            if (readyFired) {
                setTimeout(function() {callback(context);}, 1);
                return;
            } else {
                readyList.push({fn: callback, ctx: context});
            }
            if (document.readyState === "complete") {
                setTimeout(ready, 1);
            } else if (!readyEventHandlersInstalled) {
                if (document.addEventListener) {
                    document.addEventListener("DOMContentLoaded", ready, false);
                    window.addEventListener("load", ready, false);
                } else {
                    document.attachEvent("onreadystatechange", readyStateChange);
                    window.attachEvent("onload", ready);
                }
                readyEventHandlersInstalled = true;
            }
        }
    })("docReady", window);

    docReady(function() {
        masking.onReady();
    });

})(_4c);

