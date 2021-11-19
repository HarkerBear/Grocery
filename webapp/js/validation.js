function switchValid(onOff,input,errSelector,message){
	if(onOff==false){
		$(errSelector).text(message);
		$(input).addClass("error_input");
		$(errSelector).addClass("error_message");
	}else{
		$(errSelector).text("");
		$(input).removeClass("error_input");
		$(errSelector).removeClass("error_message");
	}
}


function checkEmpty(input,errSelector){
	var val= $(input).val();
	if($.trim(val)==""){
		switchValid(false,input,errSelector,"*required");
		return false;
	}else{
		switchValid(true,input,errSelector);
		return true;
	}
}

function checkCategory(input,errSelector){
	var val= $(input).val();
	if(val==-1){
		switchValid(false,input,errSelector,"*required");
		return false;
	}else{
		switchValid(true,input,errSelector);
		return true;
	}
}

function checkPrice(input,errSelector){
	var val=$(input).val();
	var regex=/^[1-9][0-9]*\.*[0-9]{0,2}$/
	if(!regex.test(val)){
		switchValid(false,input,errSelector,"*invalid");
		return false;
	}else{
		switchValid(true,input,errSelector);
		return true;
	}
}

function checkFile(input,errSelector){
	if(checkEmpty(input,errSelector)==false){
		return false;
	}
	err_message="*invalid file"
	var val=$(input).val().toLowerCase();
	if(val.length<4){
		switchValid(onOff,input,errSelector,err_message);
		return false;
	}
	suffix=val.substring(val.length-3);
	if(suffix=="jpg"||suffix=="png"||suffix=="gif"){
		switchValid(true,input,errSelector);
		return true;
	}else{
		switchValid(onOff,input,errSelector,err_message);
		return false;
	}
}