  $(document).ready(function(){
  // for showing the acceptedRequest table contents

        var id  = 1;
        $.get("volunteer/acceptedRequest" ,function(data, status){
        $.each(data, function (i, item) {

       tr = $('<tr/>');
          tr.append("<td>" + (i+1)+ "</td>");
          tr.append("<td>" + data[i].address + "</td>");
          myDate = new Date( data[i].startDate );
          tr.append("<td>" + myDate.toDateString()+ "</td>");
          myDate = new Date( data[i].endDate );
          tr.append("<td>" + myDate.toDateString() + "</td>");
          tr.append("<td>" + data[i].categoryName + "</td>");
          tr.append("<td>" + data[i].subCategoryName + "</td>");
          tr.append("<td>" + data[i].unit + "</td>");
          tr.append("<td>" + data[i].unitLeft + "</td>");
          tr.append("<td>" + data[i].unitDonate + "</td>");
        
          $('#acceptedTable').append(tr);  
  });     });      

 // for showing the pendingRequest table contents

  $.get("volunteer/pendingRequest" ,function(data, status){
        $.each(data, function (i, item) {

       tr = $('<tr/>');
          tr.append("<td>" + (i+1)+ "</td>");
          tr.append("<td>" + data[i].address + "</td>");
          myDate = new Date( data[i].startDate );
          tr.append("<td>" + myDate.toDateString()+ "</td>");
          myDate = new Date( data[i].endDate );
          tr.append("<td>" + myDate.toDateString() + "</td>");
          tr.append("<td>" + data[i].categoryName + "</td>");
          tr.append("<td>" + data[i].subCategoryName + "</td>");
          tr.append("<td>" + data[i].unit + "</td>");
          tr.append("<td>" + data[i].unitLeft + "</td>");
          tr.append("<td>" + data[i].unitDonate + "</td>");
        
          $('#pendingTable').append(tr);  
  });     }); 
        
        // for showing the pendingRequest table contents

  $.get("volunteer/rejectedRequest" ,function(data, status){
        $.each(data, function (i, item) {

       tr = $('<tr/>');
          tr.append("<td>" + (i+1)+ "</td>");
          tr.append("<td>" + data[i].address + "</td>");
          myDate = new Date( data[i].startDate );
          tr.append("<td>" + myDate.toDateString()+ "</td>");
          myDate = new Date( data[i].endDate );
          tr.append("<td>" + myDate.toDateString() + "</td>");
          tr.append("<td>" + data[i].categoryName + "</td>");
          tr.append("<td>" + data[i].subCategoryName + "</td>");
          tr.append("<td>" + data[i].unit + "</td>");
          tr.append("<td>" + data[i].unitLeft + "</td>");
          tr.append("<td>" + data[i].unitDonate + "</td>");
        
          $('#rejectedTable').append(tr);  
  });     }); 
  });    