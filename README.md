# OOME Generator

![Build](https://github.com/syake-salmon/oome-generator/workflows/Build/badge.svg) | [![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=syake-salmon_oome-generator&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=syake-salmon_oome-generator) | [![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=syake-salmon_oome-generator&metric=security_rating)](https://sonarcloud.io/dashboard?id=syake-salmon_oome-generator) | [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=syake-salmon_oome-generator&metric=coverage)](https://sonarcloud.io/dashboard?id=syake-salmon_oome-generator)

*A generator for simulating java.lang.OutOfMemoryError because of the lack of java heap space.*

日本語は[こちら](README_ja.md)

## How To Build
Using Apache-Maven, execute a simple command below.

<pre><code>mvn clean package</code></pre>

the WAR will be created in directory <code>target</code>.

If you have not installed maven, can download the WAR from [here](https://github.com/syake-salmon/oome-generator/releases).

## How To Use
1. Deploy WAR to JavaEE server.(ex, JBoss EAP)
2. Access the application.(ex, http&#58;//localhost:8080/oome-generator)
3. Click the button.  
<img width="300" alt="screenshot" src="https://user-images.githubusercontent.com/34976416/76815133-1f448000-6840-11ea-8a36-6c357437da36.PNG">

## Tips
### Generate OOME on boot
1. Edit <code>WEB-INF/web.xml</code>, then set <code>GENERATE_OOME_ON_BOOT</code> as <code>TRUE</code>.
```xml
<context-param>
  <param-name>GENERATE_OOME_ON_BOOT</param-name>
  <param-value>TRUE</param-value>
</context-param>
```
2. Repackage the WAR.

### Change interval of generating OOME
1. Edit <code>WEB-INF/web.xml</code>, then change value of <code>GENERATE_OOME_INTERVAL_MILLISEC</code>.
```xml
<context-param>
  <param-name>GENERATE_OOME_INTERVAL_MILLISEC</param-name>
  <param-value>5000</param-value>
</context-param>
```
2. Repackage the WAR.
