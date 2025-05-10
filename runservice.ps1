# Navigate to the project root
Set-Location "D:\FintechMiniProject"

# Define subfolders for each microservice
$services = @(
    "EurekaServer"
    "CustomerProject",
    "DepositProductService"
    "AccountService",
    "LedgerService"
    "BookTransaction"
    "CardProductService"
    "CardService"
)

# Start each service in a new terminal window
foreach ($service in $services) {
    Start-Process powershell -ArgumentList "cd `"$PWD\$service`"; mvn spring-boot:run" -NoNewWindow
    Start-Sleep -Seconds 2  # Optional: stagger the startups
}
