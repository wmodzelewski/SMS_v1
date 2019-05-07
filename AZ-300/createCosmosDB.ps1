#check if logged in

& "$PSScriptRoot\login.ps1"

$resourceGroupName = "rgCosmosDb"
$location = "northeurope"

$tags = @{
    "author" = "Waldemar Modzelewski";
    "script" = "createCosmosDB.ps1"
}

$cosmosAccountName = "wmcosmos1"

$cosmosLocations = @(
    @{ "locationName"="northeurope"; "failoverPriority"=0 },
    @{ "locationName"="westeurope"; "failoverPriority"=1 }
)

$consistencyPolicy = @{
    "defaultConsistencyLevel"="BoundedStaleness";
    "maxIntervalInSeconds"=300;
    "maxStalenessPrefix"=100000
}

$CosmosDBProperties = @{
    "databaseAccountOfferType"="Standard";
    "locations"=$cosmosLocations;
    "consistencyPolicy"=$consistencyPolicy;
    "enableMultipleWriteLocations"="true"
}

New-AzResourceGroup -Location $location -Name $resourceGroupName -Tags $tags

New-AzResource -ResourceType "Microsoft.DocumentDb/databaseAccounts" `
    -ApiVersion "2015-04-08" -ResourceGroupName $resourceGroupName -Location $location `
    -Name $cosmosAccountName -PropertyObject $CosmosDBProperties -Tags $tags -Force

 