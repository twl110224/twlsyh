
function Btn(id, url) {
	var item = {
		"id" : id,
		"url" : url
	}
	$("#cc #right").load(contextPath + "/" + url, item);
	
}