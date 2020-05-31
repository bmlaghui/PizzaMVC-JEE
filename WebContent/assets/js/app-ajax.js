  $(document).ready(function() { 
            $("#imageSelected").change(function() { 

                var fd = new FormData(); 

                var files = $('#imageSelected')[0].files[0]; 
                
                fd.append('image', files); 
       
                $.ajax({ 
                    url: 'uploadImage', 
                    type: 'post', 
                    data: fd, 
                    contentType: false, 
                    processData: false, 
                    success: function(response){ 
                        if(response != 0){ 
            		        $('#imageToChange').attr('src','assets/images/'+response);
            		        $('#monImage').val(response);
                        } 
                        else{ 
                            alert('file not uploaded'); 
                        } 
                    }, 
                }); 
                
            }); 
        }); 
