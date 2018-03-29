$resourceGroupName = "testRG1_PS"
$webAppName = "testrg1pswebapp"
$staginSlotName = "Staging"
$productionSlotName = "Production"
$testingSlotName = "Testing"
$appServicePlanName = "testServicePlanName_PS"

New-AzureRmWebAppSlot -ResourceGroupName $resourceGroupName -Name $webAppName -Slot $staginSlotName


$productionSite = Get-AzureRmWebAppSlot -ResourceGroupName $resourceGroupName -Name $webAppName -Slot $productionSlotName

New-AzureRmWebAppSlot -ResourceGroupName $resourceGroupName -Name $webAppName -Slot $testingSlotName -AppServicePlan $appServicePlanName -SourceWebApp $productionSite