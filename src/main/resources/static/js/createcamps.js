
$(document).ready(function(){
  $('#createCamp').click(function(){
    $.ajax({
      url : 'donationCamp/save',
      type : 'POST',
      contentType : 'application/json',
      data : JSON.stringify({
        "unit" : $("#unit").val(),
        "startDate" :$("#startDate").val(),
        "endDate" : $("#endDate").val(),
        "campCategoryID":$("#categorySelector").val(),
        "address" :$("#addressSelector").val()
      }),

    });

  });});

$(document).ready(function(){

  $.get("category/all" ,function(data, status){
    tr = "<option value="+0+"> All </option>";
    $.each(data, function (i, item) {
      tr = "<option value="+data[i].id+">"+ data[i].category+" , "+data[i].subCategory+ " </option>";

      $('#categorySelector').append(tr);

    }); 

  });});
