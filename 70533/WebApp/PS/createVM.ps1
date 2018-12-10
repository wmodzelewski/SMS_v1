$resourceGroupName = "testrg1psvmus4"
$location = "East US"
$VMName = "vmOne"
$VNetName = "vmOnetVNet"
$SubNetName = "vmOneSubnet"
$publicAddres = "vmOnePublicAddress"
$SecurityGroupName = "vmOneNGS"
$Size = "Standard_B1ms"
$avalSetName = "vmOnevailabilitySet"


New-AzureRmResourceGroup -Name $resourceGroupName -Location $location 

New-AzureRmAvailabilitySet `
   -Location $location  `
   -Name $avalSetName `
   -ResourceGroupName $resourceGroupName `
   -Sku aligned `
   -PlatformFaultDomainCount 2 `
   -PlatformUpdateDomainCount 2 `
   -Verbose


$cred = Get-Credential

for ($i=1; $i -le 2; $i++)
{

New-AzureRmVM -ResourceGroupName $resourceGroupName -Name $VMName$i -Location $location -VirtualNetworkName $VNetName -SubnetName $SubNetName -SecurityGroupName $SecurityGroupName `
-PublicIpAddressName $publicAddres$i -OpenPorts 80,3389 -Size $Size -AvailabilitySetName $avalSetName -Credential $cred -Verbose 

#Set-AzureRmVMExtension -ResourceGroupName $resourceGroupName `
#    -ExtensionName "IIS" `
#    -VMName $VMName$i `
#    -Location $location  `
#    -Publisher Microsoft.Compute `
#    -ExtensionType CustomScriptExtension `
#    -TypeHandlerVersion 1.8 `
#    -SettingString '{"commandToExecute":"powershell Add-WindowsFeature Web-Server; powershell Add-Content -Path \"C:\\inetpub\\wwwroot\\Default.htm\" -Value $($env:computername)"}' `
#    -Verbose

} 

for ($i=1; $i -le 2; $i++)
{



Set-AzureRmVMExtension -ResourceGroupName $resourceGroupName `
    -ExtensionName "IIS" `
    -VMName $VMName$i `
    -Location $location  `
    -Publisher Microsoft.Compute `
    -ExtensionType CustomScriptExtension `
    -TypeHandlerVersion 1.8 `
    -SettingString '{"commandToExecute":"powershell Add-WindowsFeature Web-Server; powershell Add-Content -Path \"C:\\inetpub\\wwwroot\\Default.htm\" -Value $($env:computername)"}' `
    -Verbose

}    
 
$publicIP = New-AzureRmPublicIpAddress `
  -ResourceGroupName $resourceGroupName `
  -Location $location  `
  -AllocationMethod "Static" `
  -Name "myPublicIP"  

$frontendIP = New-AzureRmLoadBalancerFrontendIpConfig `
  -Name "myFrontEndPool" `
  -PublicIpAddress $publicIP

$backendPool = New-AzureRmLoadBalancerBackendAddressPoolConfig -Name "myBackEndPool"

$lb = New-AzureRmLoadBalancer `
  -ResourceGroupName $resourceGroupName `
  -Name "myLoadBalancer" `
  -Location $location  `
  -FrontendIpConfiguration $frontendIP `
  -BackendAddressPool $backendPool

Add-AzureRmLoadBalancerProbeConfig `
  -Name "myHealthProbe" `
  -LoadBalancer $lb `
  -Protocol tcp `
  -Port 80 `
  -IntervalInSeconds 15 `
  -ProbeCount 2

Set-AzureRmLoadBalancer -LoadBalancer $lb

$probe = Get-AzureRmLoadBalancerProbeConfig -LoadBalancer $lb -Name "myHealthProbe"

Add-AzureRmLoadBalancerRuleConfig `
  -Name "myLoadBalancerRule" `
  -LoadBalancer $lb `
  -FrontendIpConfiguration $lb.FrontendIpConfigurations[0] `
  -BackendAddressPool $lb.BackendAddressPools[0] `
  -Protocol Tcp `
  -FrontendPort 80 `
  -BackendPort 80 `
  -Probe $probe

Set-AzureRmLoadBalancer -LoadBalancer $lb


for ($i=1; $i -le 2; $i++)
{


$nic = Get-AzureRmNetworkInterface –name $VMName$i -ResourceGroupName $resourceGroupName 
$nic.IpConfigurations[0].LoadBalancerBackendAddressPools=$backendPool
Set-AzureRmNetworkInterface -NetworkInterface $nic

}    

#Get-AzureRmVMExtension -ResourceGroupName $resourceGroupName -VMName $VMName -Name "IIS"

#Get-AzureRmVMSize -Location $location