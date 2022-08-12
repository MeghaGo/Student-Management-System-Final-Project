 function alertSave(){
    alert("Are you sure to Save or Update?")
    };
    $(function(){
    fetchSchools();
    $(".block-id").hide();
    })
    var loc = window.location;
    var baseUrl = loc.protocol + "//" + loc.hostname + (loc.port? ":"+loc.port : "") + "/";

    function saveData(){
    event.preventDefault();
    let id = $("#id_school").val();
          let dataSerialized =  $("#saveSchool").serializeArray();
            dataSerialized.push({ name : "id", value : id });
         $.post({
         url: baseUrl + "school/add",
         data:dataSerialized,
         dataType : 'json',
         success : function(response){
            data = response.body;
            alert(response.message);
            fetchSchools();
            $('#saveSchool')[0].reset()
         },
         error : function(err){
             alert("error is " + err);
    }
    })
    };

    function fetchSchools(){
//    event.preventDefault();
 $(".block-id").hide();
    let tableInstance = $("#schoolTable")
        let tableHead = "";
                    tableHead += `<thead><tr>
                    <th class = 'text-center'> S.N.</th>
                    <th> School Id</th>
                    <th>  Name of School</th>
                    <th> School Level </th>
                    <th> Address </th>
                    <th> City</th>
                    <th>Contact No</th>
                    <th> Edit</th>
                    <th> Delete</th>
                    </tr></thead>`;
            let tbody ="";
        $.post({
        url:  baseUrl + "school/schools",
        success : function (response){
           let  schools = response.body;

           $.each(schools, function(index, school){
                tbody += `<tr>
                <td>${index+1}</td>
                <td>${school.schoolId}</td>
                 <td>${school.schoolName}</td>
                 <td>${school.schoolLevel}</td>
                 <td>${school.address}</td>
                 <td>${school.city}</td>
                 <td>${school.phone}</td>
                 <td><button type="button" class="btn btn-warning" data-id="${school.id}" onclick="editSchool(this)">Edit </button></td>
                 <td><button type="button" class="btn btn-warning" data-id="${school.id}"  onclick="deleteSchool(this)">Delete </button></td>
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

        function editSchool(instance) {
        let id = $(instance).attr('data-id');
        		$.ajax({
        			type :"POST",
        			url : baseUrl + "school/edit",
        			data:{id},
        			dataType: 'json',
        			success: function(response) {
        			let school = response.body;
        			$("#id_school").val(school.id),
        				$("#schoolId").val(school.schoolId),
        				 $("#schoolName").val(school.schoolName),
                            $("#schoolLevel").val(school.schoolLevel),
        				    $("#address").val(school.address),
        				     $("#city").val(school.city),
        				      $("#phone").val(school.phone)
        				      },
        				      error : function(err) {
                              				alert("error is" + err)
                              			}
                              			})
                              			}

                              			function deleteSchool(instance){
//                                            event.preventDefault();
                                           let id = $(instance).attr('data-id');
                              			console.log(id, "id");
                                             $.ajax({
                                                  type:"POST",
                                                    url: baseUrl + "school/delete",
                                                    data:{id},
                                                        dataType: 'json',
                                                            success: function(response){
                                                              alert(response.message);
                                                              fetchSchools();
                                                              },
                                                                error : function(err) {
                                                                	alert("error is" + err)
                                                                		}
                                             }
                                             )
                                             }



