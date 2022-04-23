$.extend($.fn.validatebox.defaults.rules, {
myvalidateGuest: {
validator: function(value,param){
	//if (isBlank(param)) return false;
	return false;
	//if (isBlank($("#ff").find("#occuDate").val()))	return false;
	//else return true;
},
message: 'Field do not match.'
}
});