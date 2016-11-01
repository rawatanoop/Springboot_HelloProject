
  $(document).ready(function(){

    $.get("donationCamp/user" ,function(data, status){
      $.each(data, function (i, item) {

        var queue = '<div class="col-sm-4">';
        queue +='<div class="panel panel-primary">';
        queue += '<div class="panel-heading">'+ data[i].address ;
        queue += '<h4 class="panel-title">'+(i+1);
        queue += '</h4>';
        queue += '</div>';
        queue += '<div id="collapse1" class="panel-collapse collapse in">';
        queue +=  '<table id = detailsTable_'+data[i].id+' class="table table-bordered table-hover specialCollapse"> <tr><th>#</th> <th> Name </th> <th>  </th><th>  </th> </tr> </table> </div>';
        queue += '<div class="panel-body"> Some Images and Description </div>';
        queue +='<div class="panel-footer"><button value ='+ data[i].id +' id = details_'+i+'> Details</button></div>';
        queue += '</div>';
        queue += '</div>';
        queue += '</div>';


        $('#donationCamps').append(queue); 

        $.get("donationCamp/volunteer" ,{campID:data[i].id  ,requestStatus:"Request"},function(data, status){
          $.each(data, function (i, item) { 

            var row = '<tr>';
            row +='<td>'+(i+1)+'</td> <td> '+data[i].name+' </td> ';
            row +='<td> <button value ='+ data[i].id +' id = accept_'+i+'_'+data[i].campID+'> Accept</button> </td>';
            row +='<td><button value ='+ data[i].id +' id = reject_'+i+'_'+data[i].campID+'> Reject</button></td>';
            row +='</tr>';
            $('#detailsTable_'+data[i].campID).append(row); 
            $('#accept_'+i+'_'+data[i].campID).click(function(){

              var userID = data[i].id;
              var campID =data[i].campID;
              var unitDonate=data[i].unitDonate;
              $.ajax({
                url : 'volunteer/update',
                type : 'POST',
                contentType : 'application/json',
                data : JSON.stringify({
                  "userID" : userID,
                  "campID" : campID,
                  "requestStatus" : "Accept",
                  "unitDonate": unitDonate
                }),
                dataType : 'json'

              });
            });

            $('#reject'+i+'_'+data[i].campID).click(function(){
              var userID = data[i].id;
              var campID =data[i].campID;
              var unitDonate=data[i].unitDonate;
              $.ajax({
                url : 'volunteer/update',
                type : 'POST',
                contentType : 'application/json',
                data : JSON.stringify({
                  "userID" : userID,
                  "campID" : campID,
                  "requestStatus" : "Reject",
                  "unitDonate": unitDonate
                }),
                dataType : 'json'

              });
            });


          });
        });

        $('#details_'+i).click(function(){

          $('#detailsTable_'+data[i].id).toggle();
        });

      });      
      
    });

  });     