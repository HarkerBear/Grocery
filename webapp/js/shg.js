function showMenu(){
	var menuLA=document.getElementById("menuLA");
	if(menuLA.style.display=="block"){
		menuLA.style.display="none";
	}else{
		menuLA.style.display="block";
	}
}

function hideMenu(){
	var menuLA=document.getElementById("menuLA");
	menuLA.style.display="none";

}