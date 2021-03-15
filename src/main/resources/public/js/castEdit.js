var inputText = document.getElementById("cast_input");
	
function addToCast(name){
	
	var elements = inputText.value.split(",")
	if (elements.includes(name)){
		inputText.value = "";
		elements.splice(elements.indexOf(name), 1);
		for (var i=0; i<elements.length; i++){
			console.log(i);
			if (i == 0) { inputText.value += elements[i]; }
			else {inputText.value += "," + elements[i]; }
		}
	}
	else{
		if (inputText.value.length <= 0){
		inputText.value += name;
	}
		else{
			inputText.value += ',' + name;
		}
	}
}