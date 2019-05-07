

$context = Get-AzContext

if($context) {
    $needLogin = ([string]::IsNullOrEmpty($context.Account))

    if($needLogin)
    {
        Connect-AzAccount
    }
    else {
        "Already logged in"
    } 
}
else {
    "No context"
} 


