 function alertSave(){
    alert("Are you sure to Save or Update?")
    };
    $(function(){
    fetchCourses();
    $(".block-id").hide();
    })
    var loc = window.location;
    var baseUrl = loc.protocol + "//" + loc.hostname + (loc.port? ":"+loc.port : "") + "/";

    function saveData(){
    event.preventDefault();
    let id = $("#id_course").val();
          let dataSerialized =  $("#saveCourse").serializeArray();
            dataSerialized.push({ name : "id", value : id });
         $.post({
         url: baseUrl + "course/add",
         data:dataSerialized,
         dataType : 'json',
         success : function(response){
            data = response.body;
            alert(response.message);
            fetchCourses();
            $('#saveCourse')[0].reset()
         },
         error : function(err){
             alert("error is " + err);
    }
    })
    };

    function fetchCourses(){
//    event.preventDefault();
 $(".block-id").hide();
    let tableInstance = $("#courseTable")
        let tableHead = "";
                    tableHead += `<thead><tr>
                    <th class = 'text-center'> S.N.</th>
                    <th>Course Name</th>
                    <th> Course Credit Hour </th>
                    <th> Course Full Marks </th>
                    <th> Course Pass Marks</th>
                    <th> Edit</th>
                    <th> Delete</th>
                    </tr></thead>`;
            let tbody ="";
        $.post({
        url:  baseUrl + "course/courses",
        success : function (response){
           let  courses = response.body;

           $.each(courses, function(index, course){
                tbody += `<tr>
                <td>${index+1}</td>
                 <td>${course.courseName}</td>
                 <td>${course.courseCreditHour}</td>
                 <td>${course.courseFullMarks}</td>
                 <td>${course.coursePassMarks}</td>
                 <td><button type="button" class="btn btn-warning" data-id="${course.id}" onclick="editCourse(this)">Edit </button></td>
                 <td><button type="button" class="btn btn-warning" data-id="${course.id}"  onclick="deleteCourse(this)">Delete </button></td>
                 </tr>`
           })
           },
            error : function(err) {
            						alert("error is" + err)
            					},
           complete : function(){
           let tableBody = "";
            tableBody = `<tbody>` + tbody + `</tbody>`
             let table = tableHead + tableBody;
              tableInstance.html(table);
            }
        })
        }

        function editCourse(instance) {
        let id = $(instance).attr('data-id');
        		$.ajax({
        			type :"POST",
        			url : baseUrl + "course/edit",
        			data:{id},
        			dataType: 'json',
        			success: function(response) {
        			let course = response.body;
        			$("#id_course").val(course.id),
        				 $("#courseName").val(course.courseName),
                            $("#courseCreditHour").val(course.courseCreditHour),
        				    $("#courseFullMarks").val(course.courseFullMarks),
        				     $("#coursePassMarks").val(course.coursePassMarks)
        				      },
        				      error : function(err) {
                              				alert("error is" + err)
                              			}
                              			})
                              			}

                              			function deleteCourse(instance){
//                                            event.preventDefault();
                                           let id = $(instance).attr('data-id');
                                             $.ajax({
                                                  type:"POST",
                                                    url: baseUrl + "course/delete",
                                                    data:{id},
                                                        dataType: 'json',
                                                            success: function(response){
                                                              alert(response.message);
                                                              fetchCourses();
                                                              },
                                                                error : function(err) {
                                                                	alert("error is" + err)
                                                                		}
                                             }
                                             )
                                             }



/*

function assignCourse(){
    let studentId = $(".studentId").val();
    let courseId = $("#selectCourse :selected").val();

    $.ajax({
    type: "Post",
    url: baseUrl + "api/v1/students/assign"
    data:{
    studentId, courseId
    },
    dataType: 'json',
    success:function(response){
    location.href = 'applicants.html';
    },
    error: function(err){
    alert("error id"+ err)}
    })
}*/
