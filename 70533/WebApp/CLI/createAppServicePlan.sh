#!/bin/bash
resourceGroupName="testRG1_CLI"
appServicePlan="testServicePlanName_CLI"
location="northeurope"
sku="P1"

az group create --location $location --name $resourceGroupName

az appservice plan create --resource-group $resourceGroupName \
--name $appServicePlan --location $location --sku $sku