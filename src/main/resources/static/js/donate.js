

$(document).ready(function(){    
  $.get("category/all" ,function(data, status){
    tr = "<option value="+0+"> All </option>";
    $('#categorySelector').append(tr);
    $.each(data, function (i, item) {
      tr = "<option value="+data[i].id+">"+ data[i].category+" , "+data[i].subCategory+ " </option>";

      $('#categorySelector').append(tr);
    }); 
  });

  $('#searchButton').click(function(){
    $.get( "search/filter", { address: $('#addressSelector').val(), category: $('#categorySelector').val() } )
    .done(function( data ) {
      $("#donationCampTable tr").remove();
      $.each(data, function (i, item) {

    tr = $('<tr/>');
    tr.append("<td>" + (i+1)+ "</td>");
    tr.append("<td>" + data[i].address + "</td>");
    var myDate = new Date( data[i].startDate *1000);
    tr.append("<td>" + myDate.toLocaleString() + "</td>"); 
    myDate = new Date( data[i].endDate *1000);
    tr.append("<td>" + myDate.toLocaleString() + "</td>");
    tr.append("<td>" + data[i].categoryName + "</td>");
    tr.append("<td>" + data[i].subCategoryName + "</td>");
    tr.append("<td>" + data[i].unit + "</td>");
    tr.append("<td>" + data[i].unitLeft + "</td>");
    tr.append("<td> <input id="+ "unit_"+data[i].id+ " type=\"number\"> </td>");

    tr.append($('<button/>', {
      text: 'Send Request', 
      id: "sendRequest_"+data[i].id ,
      val: data[i].id ,
      click: function() { 

        $.ajax({
          url : 'volunteer/save',
          type : 'POST',
          contentType : 'application/json',
          data : JSON.stringify({
            "userID" : 1,
            "campID" : $(this).val(),
            "requestStatus" : "Request",
            "unitDonate": $("#unit_"+$(this).val()).val()
          }),
          dataType : 'json'

        });
        $("#unit_"+$(this).val()).prop("disabled", true);
        $(this).prop("disabled", true);
        $(this).prop("text", "Request Sent");
      }
    }));

    $('#donationCampTable').append(tr);


  });      
    });
  });

});      

