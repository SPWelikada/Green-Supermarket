$(document).ready(function () {

    function loadFeedbacks() {
        $.ajax({
            url: "${pageContext.request.contextPath}/feedback?action=list",
            type: "GET",
            dataType: "json",
            success: function (data) {
                $("#feedbackTableBody").empty();
                $.each(data, function (index, feedback) {
                    var row = "<tr>" +
                        "<td>" + feedback.id + "</td>" +
                        "<td>" + feedback.name + "</td>" +
                        "<td>" + feedback.email + "</td>" +
                        "<td>" + feedback.message + "</td>" +
                        "<td><button class='deleteBtn' data-id='" + feedback.id + "'>Delete</button></td>" +
                        "</tr>";
                    $("#feedbackTableBody").append(row);
                });
            },
            error: function (error) {
                console.log("Error loading feedbacks: " + JSON.stringify(error));
            }
        });
    }

    function addFeedback() {
        var formData = {
            customerId: $("#customerId").val(),
            message: $("#message").val(),
            rating: $("#rating").val()
        };
        console.log("formdata is : "+formData)

        $.ajax({
            url: "${pageContext.request.contextPath}/feedback?action=add",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(formData),
            success: function () {
                loadFeedbacks();
                $("#customerId").val("");
                $("#message").val("");
                $("#rating").val("");
                alert("Feedback added successfully!");
            },
            error: function (error) {
                console.log("Error adding feedback: " + JSON.stringify(error));
                // Provide feedback to the user (you can customize this part)
                alert("Error adding feedback. Please try again.");
            }
        });
    }


    $(document).on("click", ".deleteBtn", function () {
        var feedbackId = $(this).data("id");
        $.ajax({
            url: "${pageContext.request.contextPath}/feedback?id=" + feedbackId,
            type: "DELETE",
            success: function () {
                loadFeedbacks();
                // Provide feedback to the user (you can customize this part)
                alert("Feedback deleted successfully!");
            },
            error: function (error) {
                console.log("Error deleting feedback: " + JSON.stringify(error));
                // Provide feedback to the user (you can customize this part)
                alert("Error deleting feedback. Please try again.");
            }
        });
    });

    $("#loadFeedbacksBtn").click(function () {
        loadFeedbacks();
    });

    $("#addFeedbackForm").submit(function (event) {
        event.preventDefault();
        addFeedback();
    });

    loadFeedbacks();
});
