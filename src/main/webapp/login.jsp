
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Login &amp; Signup forms in panel - Bootsnipp.com</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="C:\Users\Bhagyashree\Desktop\bootstrap\css\bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <style type="text/css">
        </style>



</head>
<body>
	    <div class="container"> 
 <div class="well">       
        <div id="loginbox" style="margin-top:160px">
	<div style= "margin-bottom:100px">                    
            <div class="panel panel-info" >
                    <div class="panel-heading">
			<div class="panel-title">Drop here</div>
                        <div class="panel-title">Sign In</div>
                        <div style="float:middle; font-size: 100%; position: relative; top:180px"><a href="#">Forgot password?</a></div>
                    </div>     

                    <div style="padding-top:30px" class="panel-body" >

                        <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                            
                        <form id="loginform" class="form-horizontal" role="form">
                                    
                            <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                        <input id="login-username" type="text" class="form-control" name="username" value="" placeholder="username or email">                                        
                                    </div>
                                
                            <div style="margin-bottom: 25px" class="input-group">
                                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                        <input id="login-password" type="password" class="form-control" name="password" placeholder="password">
                                    </div>
                                    

                                
                            <div class="input-group">
                                      <div class="checkbox">
                                        <label>
                                          <input id="login-remember" type="checkbox" name="remember" value="1"> Remember me
                                        </label>
                                      </div>
                                    </div>


                                
                                    <!-- Button -->

                                    <div class="col-sm-12 controls">
                                      <a id="btn-login" href="#" class="btn btn-info">Login  </a>
                                     
                                    </div>
                                </div>


                                <div class="form-group">
                                    <div class="col-md-12 control">
                                         <div style="border-top: 5px solid#888; padding-top:15px; font-size:85%" >
                                            Don't have an account!  
                                        <a href="#" onClick="$('#loginbox').hide(); $('#signupbox').show()">
                                            Sign Up Here
                                        </a>
                                        </div>
                                    </div>
                                </div>    
                            </form>     



                        </div>                     
                    </div>  
        </div>
        
    	<script type="text/javascript">
		</script>
</body>
</html>



