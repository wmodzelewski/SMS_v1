$resourceGroupName = "testRG1_PS"
$appServicePlanName = "testServicePlanName_PS"
$location = "North Europe"
$tier = "Premium"
$workerSize = "small"

New-AzureRmResourceGroup -Name $resourceGroupName -Location $location

New-AzureRmAppServicePlan -ResourceGroupName $resourceGroupName `
-Name $appServicePlanName -Location $location -Tier $tier -WorkerSize $workerSize