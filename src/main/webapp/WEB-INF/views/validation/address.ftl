<#include "../include/header.ftl"/>
	<div class="container">
		<div class="row">
			<div class="span12">
				<label><strong>This is a simple form validation example which makes use of the PRG Pattern at the controller level, 
				JSR-303 validation in the DTO, error messaging via properties file, and Bootstrap CSS error classes.</strong></label>
				<ul>
					<li>Controller - PRGValidatingDTOController.java
					<li>DTO - Address.java
					<li>Properties file - ValidationMessages.properties (this filename is required, convention over configuration)
				</ul>
				<hr/>
			</div>
		</div>
	
	<#if success?? >
		<strong>${success}</strong>
	</#if>

	<div class="row-fluid">
		<form method="POST" action="<@spring.url value="/validation/address.html"/>" >
		    <fieldset>
				<div id="legend">
	 				<legend class="">Address</legend>
				</div>
  		  
				<div class="control-group">
					<label class="control-label" for="name">Name</label>
					<div class="controls">
						<input type="text" id="name" name="name" placeholder="" value="${(address.name)!}" class="input-xlarge">
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="street1">Street1</label>
					<div class="controls">
						<input type="text" id="street1" name="street1" placeholder="" value="${(address.street1)!}" class="input-xlarge">
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="street2">Street2</label>
					<div class="controls">
						<input type="text" id="street2" name="street2" placeholder="" value="${(address.street2)!}" class="input-xlarge">
					</div>
				</div>

				<#assign cityError><@form.errors path="address.city" element="span" cssClass="help-inline"/></#assign>
				<div class="control-group <#if cityError?has_content>error</#if>">
					<label class="control-label" for="city"><span style="color:red">* </span>City</label>
					<input type="text" id="city" name="city" placeholder="" value="${(address.city)!}" class="input-xlarge">
					${cityError}
				</div>

				<div class="control-group">
					<label class="control-label" for="state">State</label>
					<div class="controls">
						<input type="text" id="state" name="state" placeholder="" value="${(address.state)!}" class="input-xlarge">
					</div>
				</div>

				<div class="control-group">
					<label class="control-label" for="zip">Zip</label>
					<div class="controls">
						<input type="text" id="zip" name="zip" placeholder="" value="${(address.zip)!}" class="input-xlarge">
					</div>
				</div>
				
				<div class="control-group">
					<div class="controls">
						<button class="btn">Submit</button>
					</div>
				</div>
				
			</fieldset>
		</form>
	</div>

	</div> <!-- /container -->
<#include "../include/footer.ftl"/>