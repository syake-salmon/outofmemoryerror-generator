# OOME Generator
*A generator for simulating java.lang.OutOfMemoryError because of the lack of java heap space.*

## How To Build
Using Apache-Maven, execute a simple command below.

<pre><code>mvn clean package</code></pre>

the WAR will be created in directory <code>target</code>.

## How To Use
1. Deploy WAR to JavaEE server(ex, JBoss EAP).
2. Access the application.(ex, http&#58;//localhost:8080/OOMEGenerator)
3. Click hire.
<img width="300" alt="screenshot" src="https://user-images.githubusercontent.com/34976416/72512044-e2372e00-388e-11ea-9cfc-4b2eafe08adf.png">

## Tips
### Generate OOME on boot
1. Edit WEB-INF/web.xml, then set <code>GENERATE_OOME_ON_BOOT</code> as <code>TRUE</code>.
```xml
<context-param>
  <param-name>GENERATE_OOME_ON_BOOT</param-name>
  <param-value>TRUE</param-value>
</context-param>
```
2. Repackage the WAR.

### Change interval of generating OOME
1. Edit WEB-INF/web.xml, then change value of <code>GENERATE_OOME_INTERVAL_MILLISEC</code>.
```xml
<context-param>
  <param-name>GENERATE_OOME_INTERVAL_MILLISEC</param-name>
  <param-value>5000</param-value>
</context-param>
```
2. Repackage the WAR.
