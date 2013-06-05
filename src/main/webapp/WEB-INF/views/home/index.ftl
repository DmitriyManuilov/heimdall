<#include "../include/header.ftl"/>
	<div class="container">

		<#if RequestParameters['authfail']??>
			<div class="row">
				<div class="span12">
					<div class="alert alert-error">
						Login failed
					</div>
				</div>
			</div>
		</#if>
		<#if RequestParameters['accessdenied']??>
			<div class="row">
				<div class="span12">
					<div class="alert alert-error">
						Access denied
					</div>
				</div>
			</div>
		</#if>
		<#if RequestParameters['login']??>
			<div class="row">
				<div class="span12">
					<div class="alert alert-warning">
						You are not signed in, please sign in first.
					</div>
				</div>
			</div>
		</#if>
		<#if RequestParameters['loggedout']??>
			<div class="row">
				<div class="span12">
					<div class="alert alert-success">
						You have been logged out.
					</div>
				</div>
			</div>
		</#if>
			
		<div class="row">
			<div class="span12">
				<fieldset>
					<legend>Hello, World!</legend>
				</fieldset>
			</div>
		</div>

		<footer>
			<p>Built with Maven profile ${buildEnv}</p>
		</footer>
	</div> <!-- /container -->
<#include "../include/footer.ftl"/>
