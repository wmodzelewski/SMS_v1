#!/bin/bash
resourceGroupName="testRG1_CLI"
appServicePlan="testServicePlanName_CLI"
location="northeurope"
webAppName="testrg1cliwebapp"

az webapp create --plan $appServicePlan --resource-group $resourceGroupName --name $webAppName