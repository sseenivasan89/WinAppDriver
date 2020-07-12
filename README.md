# WinAppDriver-Windows Automation
  - Windows Application Driver (WinAppDriver) is a service to support Selenium-like UI Test Automation on Windows Applications

# Install & Run WinAppDriver
  - Download Windows Application Driver installer from https://github.com/Microsoft/WinAppDriver/releases
  - Run the installer on a Windows 10 machine
  - Enable Developer Mode in Windows settings
  - Run WinAppDriver.exe from the installation directory (E.g. C:\Program Files (x86)\Windows Application Driver)
  ```
  WinAppDriver.exe 4727
  WinAppDriver.exe 10.0.0.10 4725
  WinAppDriver.exe 10.0.0.10 4723/wd/hub
```
  Note: You must run WinAppDriver.exe as administrator to listen to a different IP address and port.
  
## Framework Details
> - Tested on JDK 1.8+
> - TestNG
> - maven

## Tests
> - Calculator Automation
> - Notepad Automation
> - Used both App ID and exe path execution

## Run Tests

**Clone the project Directory**
```
mvn clean verify
```
