<!DOCTYPE html>

<html lang="en">

<body>
	<h1>PostgreSQL</h1>
	<div>
		variables: 
		<ul>
		<#list variables as item>
			<li>${item.name} = ${item.setting} : ${item.description}</li>
		</#list>
		</ul>
	</div> 
</body>

</html>