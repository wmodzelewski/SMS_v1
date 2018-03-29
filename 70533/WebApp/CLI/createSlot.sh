#!/bin/bash
resourceGroupName="testRG1_CLI"
webAppName="testrg1cliwebapp"
stagingSlotName = "Staging"
productionSlotName = "Production"

az webapp deployment slot create --resource-group $resourceGroupName --name $webAppName --slot $stagingSlotName
