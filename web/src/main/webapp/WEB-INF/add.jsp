<jsp:useBean id="user" scope="session" type="fr.efrei.se.emapp.common.model.CredentialTranscript"/>
<jsp:useBean id="employeeChecked" scope="session" type="fr.efrei.se.emapp.common.model.EmployeeTranscript"/>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link type="text/css" rel="stylesheet" href="css/cssWelcome.css">
        <link type="text/css" rel="stylesheet" href="css/darkTheme.css">
        <script type="text/javascript" src="js/darkTheme.js"></script>
    </head>
    <body onload="loadPage2();">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <nav class="navbar navbar-expand-sm bg-primary navbar-dark">
                <div id = "hello">
                    <h5>Hello ${user.login}! Your session is active.</h5>
                </div>
                <div id="listOfEmployee">
                    <h2> EMAPP - Details of employee: ${employeeChecked.firstName} ${employeeChecked.lastName}</h2>
                </div>
                <br/>
                <br/>
        </nav>
        
        <nav class="navbar navbar-fixed-left navbar-minimal animate" role="navigation">
		<div class="navbar-toggler animate">
			<span class="menu-icon"></span>
		</div>
		<ul class="navbar-menu animate">
			<li>
                             <a href="#blog" class="animate" onclick="setLightMode()">
                                 <span class="desc animate"> Light </span>
				<span class="glyphicon glyphicon-flash"></span>
                             </a>
			</li>
			<li>
                            <a href="#blog" class="animate" onclick="setDarkMode()">
				<span class="desc animate"> Dark </span>
				<span class="glyphicon glyphicon-certificate"></span>
                            </a>
			</li>
                        	<li>
                            <a href="#blog" class="animate" data-toggle="modal" data-target=".bs-example-modal-sm">
				<span class="desc animate"> Log out </span>
				<span class="glyphicon glyphicon-off"></span>
                            </a>
			</li>
		</ul>
	</nav>
        
        <br/>
        <form method="post" action="${pageContext.request.contextPath}/app">
            <div class="modal bs-example-modal-sm" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog modal-sm">
                    <div class="modal-content">
                        <div class="modal-header"><h4>Logout <i class="fa fa-lock"></i></h4></div>
                        <div class="modal-body"><i class="fa fa-question-circle"></i> Do you really want to log out?</div>
                        <div class="modal-footer">
                            <input type='submit' class="btn btn-primary btn-block" name="action" value="Logout"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <label for="inputLastName" class="col-sm-2 col-form-label">Last name</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="inputLastName" name='inputLastName' placeholder="Doe" value='${employeeChecked.lastName}'>
                </div>
            </div>
            <div class="form-group row">
                <label for="inputFirstName" class="col-sm-2 col-form-label">First name</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="inputFirstName" name="inputFirstName" placeholder="John" value="${employeeChecked.firstName}">
                </div>
            </div>
            <div class="form-group row">
                <label for="inputHomePhone" class="col-sm-2 col-form-label">Home phone</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="inputHomePhone" name="inputHomePhone" placeholder="+33 6 00 00 00 00" value="${employeeChecked.homePhone}">
                </div>
            </div>
            <div class="form-group row">
                <label for="inputMobilePhone" class="col-sm-2 col-form-label">Mobile phone</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="inputMobilePhone" name="inputMobilePhone" placeholder="+33 6 00 00 00 00" value="${employeeChecked.mobilePhone}">
                </div>
            </div>
            <div class="form-group row">
                <label for="inputWorkPhone" class="col-sm-2 col-form-label">Work phone</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="inputWorkPhone" name="inputWorkPhone" placeholder="+33 6 00 00 00 00" value="${employeeChecked.workPhone}">
                </div>
            </div>
            <div class="form-group row">
                <label for="inputAddress" class="col-sm-2 col-form-label">Address</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="inputAddress" name="inputAddress" placeholder="-1, ArrayOutOfBound Street" value="${employeeChecked.address}">
                </div>
            </div>
            <div class="form-group row">
                <label for="inputPostalCode" class="col-sm-2 col-form-label">Postal code</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" id="inputPostalCode" name="inputPostalCode" placeholder="00000" value="${employeeChecked.postalCode}">
                </div>
            </div>
            <div class="form-group row">
                <label for="inputCity" class="col-sm-2 col-form-label">City</label>
                <div class="col-sm-9">
                   <input type="text" class="form-control" id="inputCity" name="inputCity" placeholder="Exception city" value="${employeeChecked.city}">
                </div>
            </div>
            <div class="form-group row">
               <label for="inputEmail" class="col-sm-2 col-form-label">Email address</label>
                <div class="col-sm-9">
                   <input type="text" class="form-control" id="inputEmail" name="inputEmail" placeholder="Email address" value="${employeeChecked.email}">
                </div>
            </div>
            <div class="form-group row">
                <div class="form-group col-sm-10"></div>
                <div class="form-group col-sm-2">
                    <input type='submit' class="btn btn-primary" name='action' value='Commit'>
                    <input type='submit' class="btn btn-light" name='action' value='Cancel'>
                </div>
            </div>
        </form>
                
            <script type="text/javascript">
                    $(function () {
    
    $('.navbar-toggler').on('click', function(event) {
		event.preventDefault();
		$(this).closest('.navbar-minimal').toggleClass('open');
	});
});</script>
    </body>
</html>
