<#
.SYNOPSIS
Obtiene la información de uno o más procesos en ejecución.

.DESCRIPTION
Este script utiliza get-process para consultar procesos en el sistema. Muestra el nombre del proceso,
su identificador, la memoria virtual y la memoria virtual convertida a MB redondeada.

.PARAMETER
Nombre del proceso a consultar. Este parámetro es obligatorio, también puede especificarse usando el alias 'ProcessName'

.EXAMPLE
.\practicaParcialScript.ps1 -Name explorer 
Consulta el proceso explorer y muestra su información.

.EXAMPLE
.\practicaParcialScript.ps1 -Name chrome 
Consulta el proceso chrome y muestra su información.
#>
param (
    [Parameter(mandatory=$true)]
    [Alias('ProcessName')]
    [string]$name
    )
get-process -name explorer | select name,id, vm, @{name="VM(MB)"; Expression={[math]::Round($_.VM/1MB)}}