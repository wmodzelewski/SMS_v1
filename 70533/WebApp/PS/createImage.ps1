$rgName = "testrg1psvmus2"
$location = "East US"
$VMName = "vmOne1"
$imageName = "vmOne1Image"

Set-AzureRmVm -ResourceGroupName $rgName -Name $vmName -Generalized
$vm = Get-AzureRmVM -Name $vmName -ResourceGroupName $rgName
$image = New-AzureRmImageConfig -Location $location -SourceVirtualMachineId $vm.ID
New-AzureRmImage -Image $image -ImageName $imageName -ResourceGroupName $rgName