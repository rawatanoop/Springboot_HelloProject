  $(document).ready(function(){
  // for showing the acceptedRequest table contents

        var id  = 1;
        $.get("volunteer/"+id+"/acceptedRequest" ,function(data, status){
        $.each(data, function (i, item) {

       tr = $('<tr/>');
          tr.append("<td>" + (i+1)+ "</td>");
          tr.append("<td>" + data[i].address + "</td>");
          tr.append("<td>" + data[i].startDate + "</td>");
          tr.append("<td>" + data[i].endDate + "</td>");
          tr.append("<td>" + data[i].categoryName + "</td>");
          tr.append("<td>" + data[i].subCategoryName + "</td>");
          tr.append("<td>" + data[i].unit + "</td>");
          tr.append("<td>" + data[i].unitLeft + "</td>");
          tr.append("<td>" + data[i].unitDonate + "</td>");
        
          $('#acceptedTable').append(tr);  
  });     });      

 // for showing the pendingRequest table contents

  $.get("volunteer/"+id+"/pendingRequest" ,function(data, status){
        $.each(data, function (i, item) {

       tr = $('<tr/>');
          tr.append("<td>" + (i+1)+ "</td>");
          tr.append("<td>" + data[i].address + "</td>");
          tr.append("<td>" + data[i].startDate + "</td>");
          tr.append("<td>" + data[i].endDate + "</td>");
          tr.append("<td>" + data[i].categoryName + "</td>");
          tr.append("<td>" + data[i].subCategoryName + "</td>");
          tr.append("<td>" + data[i].unit + "</td>");
          tr.append("<td>" + data[i].unitLeft + "</td>");
          tr.append("<td>" + data[i].unitDonate + "</td>");
        
          $('#pendingTable').append(tr);  
  });     }); 
        
        // for showing the pendingRequest table contents

  $.get("volunteer/"+id+"/rejectedRequest" ,function(data, status){
        $.each(data, function (i, item) {

       tr = $('<tr/>');
          tr.append("<td>" + (i+1)+ "</td>");
          tr.append("<td>" + data[i].address + "</td>");
          tr.append("<td>" + data[i].startDate + "</td>");
          tr.append("<td>" + data[i].endDate + "</td>");
          tr.append("<td>" + data[i].categoryName + "</td>");
          tr.append("<td>" + data[i].subCategoryName + "</td>");
          tr.append("<td>" + data[i].unit + "</td>");
          tr.append("<td>" + data[i].unitLeft + "</td>");
          tr.append("<td>" + data[i].unitDonate + "</td>");
        
          $('#rejectedTable').append(tr);  
  });     }); 
  });    