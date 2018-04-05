$resourceGroupName = "testRG1_PS"
$webAppName = "testrg1pswebapp"
$staginSlotName = "Staging"
$productionSlotName = "Production"
$testingSlotName = "Testing"
$appServicePlanName = "testServicePlanName_PS"

Swap-AzureRmWebAppSlot -ResourceGroupName $resourceGroupName -Name $webAppName -SourceSlotName $testingSlotName -DestinationSlotName $productionSlotName -SwapWithPreviewAction ResetSlotSwap