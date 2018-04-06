$resourceGroupName = "testrg1psvmus"
$location = "East US"
$VMName = "vmOne"
$VNetName = "vmOnetVNet"
$SubNetName = "vmOneSubnet"
$publicAddres = "vmOnePublicAddress"
$SecurityGroupName = "vmOneNGS"
$Size = "Standard_A0"


New-AzureRmResourceGroup -Name $resourceGroupName -Location $location 

New-AzureRmVM -ResourceGroupName $resourceGroupName -Name $VMName -Location $location -VirtualNetworkName $VNetName -SubnetName $SubNetName -SecurityGroupName $SecurityGroupName `
-PublicIpAddressName $publicAddres -OpenPorts 80,3389 -Size $Size -Verbose 

Set-AzureRmVMExtension -ResourceGroupName $resourceGroupName `
    -ExtensionName "IIS" `
    -VMName $VMName `
    -Location $location  `
    -Publisher Microsoft.Compute `
    -ExtensionType CustomScriptExtension `
    -TypeHandlerVersion 1.8 `
    -SettingString '{"commandToExecute":"powershell Add-WindowsFeature Web-Server; powershell Add-Content -Path \"C:\\inetpub\\wwwroot\\Default.htm\" -Value $($env:computername)"}'

#Get-AzureRmVMSize -Location $location