$rm_groups = Get-AzureRmResourceGroup

$rm_groups | ForEach-Object {
    Write-Host "Removing"  $_.ResourceGroupName
    Remove-AzureRmResourceGroup -Name $_.ResourceGroupName -Force
}