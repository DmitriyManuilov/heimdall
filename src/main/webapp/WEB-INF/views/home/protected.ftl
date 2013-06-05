<#include "../include/header.ftl"/>
	<div class="container">

		<div class="row">
			<div class="span12">
				<fieldset>
					<legend>This page is protected, only users can access it.</legend>
				</fieldset>
				<p>Logged in username: ${username}
			</div>
		</div>

		<footer>
			<p>Built with Maven profile ${buildEnv}</p>
		</footer>
	</div> <!-- /container -->
<#include "../include/footer.ftl"/>
