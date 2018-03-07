$admin_user = "wmodzelewski"
$password = "Test1234Test"
$vmName ="testVM_1"
$location = "North Europe"
$imageFamily = "Widows Server 2016 R2 Datacenter"
$resourceGroupName = "testRG_PS"
$vmSize = "Standard_A1"

## Storage
$StorageName = "testwm1storage"
$StorageType = "Standard_GRS"
    
## Network
$InterfaceName = "testwm1ifname"
$Subnet1Name = "testwm1Subnet"
$VNetName = "testwm1VNet0"
$VNetAddressPrefix = "10.0.0.0/16"
$VNetSubnetAddressPrefix = "10.0.0.0/24"
    
## Compute
$ComputerName = "testwm1Server"
$OSDiskName = $vMName + "OSDisk"
    
# Resource Group
New-AzureRmResourceGroup -Name $resourceGroupName -Location $location
    
# Storage
$StorageAccount = New-AzureRmStorageAccount -ResourceGroupName $resourceGroupName -Name $StorageName -Type $StorageType -Location $location
    
# Network
$PIp = New-AzureRmPublicIpAddress -Name $InterfaceName -ResourceGroupName $resourceGroupName -Location $location -AllocationMethod Dynamic
$SubnetConfig = New-AzureRmVirtualNetworkSubnetConfig -Name $Subnet1Name -AddressPrefix $VNetSubnetAddressPrefix
$VNet = New-AzureRmVirtualNetwork -Name $VNetName -ResourceGroupName $resourceGroupName -Location $location -AddressPrefix $VNetAddressPrefix -Subnet $SubnetConfig
$Interface = New-AzureRmNetworkInterface -Name $InterfaceName -ResourceGroupName $resourceGroupName -Location $location -SubnetId $VNet.Subnets[0].Id -PublicIpAddressId $PIp.Id
    
# Compute
    
## Setup local VM object
$Credential = Get-Credential
$VirtualMachine = New-AzureRmVMConfig -VMName $vmName -VMSize $vmSize
$VirtualMachine = Set-AzureRmVMOperatingSystem -VM $VirtualMachine -Windows -ComputerName $ComputerName -Credential $Credential -ProvisionVMAgent -EnableAutoUpdate
$VirtualMachine = Set-AzureRmVMSourceImage -VM $VirtualMachine -PublisherName MicrosoftWindowsServer -Offer WindowsServer -Skus 2012-R2-Datacenter -Version "latest"
$VirtualMachine = Add-AzureRmVMNetworkInterface -VM $VirtualMachine -Id $Interface.Id
$OSDiskUri = $StorageAccount.PrimaryEndpoints.Blob.ToString() + "vhds/" + $OSDiskName + ".vhd"
$VirtualMachine = Set-AzureRmVMOSDisk -VM $VirtualMachine -Name $OSDiskName -VhdUri $OSDiskUri -CreateOption FromImage
    
## Create the VM in Azure
New-AzureRmVM -ResourceGroupName $ResourceGroupName -Location $Location -VM $VirtualMachine