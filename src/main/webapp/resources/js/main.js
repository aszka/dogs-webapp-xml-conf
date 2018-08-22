$(document).ready(function() {
	$('table#dog-list tbody > tr').click(function() {
		window.location.href = "/dogs/" + $(this).attr('id');
	});

	$('#delete-dog').click(function() {
		var result = confirm("Do you want to delete it?");
		if (result) {
			window.location.href = "/dogs/" + $("input#dog-id").val() + "/delete";
		}
	});
});