$resourceGroupName = "testRG1_PS"
$appServicePlanName = "testServicePlanName_PS"
$location = "North Europe"
$webAppName = "testrg1pswebapp"

New-AzureRmWebApp -ResourceGroupName $resourceGroupName -Location $location `
 -AppServicePlan $appServicePlanName -Name $webAppName 




