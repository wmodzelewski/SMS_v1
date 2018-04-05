$resourceGroupName = "testRG1_PS"
$webAppName = "testrg1pswebapp"

$webApp = Get-AzureRmWebApp -ResourceGroupName $resourceGroupName -Name $webAppName 
$settings = $webApp.SiteConfig.AppSettings

$newSettings = New-Object Hashtable
$newSettings["setting1"]="value1"
$newSettings["setting2"]="value2"
foreach($setting in $settings) {
$newSettings.Add($setting.Name, $setting.Value)
}

Set-AzureRmWebApp -ResourceGroupName $resourceGroupName -Name $webAppName  -AppSettings $newSettings 
