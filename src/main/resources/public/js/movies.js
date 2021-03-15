let searchInput = document.getElementById("search_input");
let searchButton = document.getElementById("search_btn");
let searchType = document.getElementById("search_type");
let orderType = document.getElementById("order_type");
let searchClearButton = document.getElementById("search_clear_btn");
let editBtn = document.getElementById("edit_btn");
let movieName = document.getElementById("movie_name");

orderType.value = window.location.href.substring(window.location.href.lastIndexOf("=") + 1);

searchButton.addEventListener("click", function(){
	if (searchInput.value.length > 0){
		var type = searchType.value;
		window.location.href= "/movies/list/?" + type + "=" + searchInput.value;
	}
});

orderType.addEventListener("change", event => {
	if (window.location.href.includes("orderBy")){
		var url = window.location.href.substring(0, window.location.href.lastIndexOf("orderBy") - 1);
		window.location.href = url + "&orderBy=" + event.target.value;
	}
	else if (window.location.href.includes("?")){
		window.location.href +="&orderBy=" + event.target.value;
	}
	else {
		window.location.href +="?orderBy=" + event.target.value;
	}
	
});

searchClearButton.addEventListener("click", function(){
	window.location.href = "/movies/list";
});

function deleteClicked(movie_id){
	var confirmation = confirm('Are you sure you want to delete this movie?');
	if (confirmation){
		window.location.href = "/movies/delete/" + movie_id;
	}
}
