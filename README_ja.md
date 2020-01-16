# OOMEジェネレータ
*Javaヒープ領域不足によるjava.lang.OutOfMemoryErrorの発生をシミュレートするためのジェネレータアプリです。*

for [English](README.md)

## ビルド方法
Apache-Mavenで下記コマンドを実行してください。

<pre><code>mvn clean package</code></pre>

<code>target</code>フォルダの下にWARが作成されます。

mavenをインストールしていない場合は、[こちら](https://github.com/syake-salmon/oome-generator/releases)からWARをダウンロードできます。

## 使用方法
1. JavaEEサーバにWARをデプロイする。(例, JBoss EAP)
2. アプリにアクセスする。(例, http&#58;//localhost:8080/OOMEGenerator)
3. リンクをクリックする。
<img width="300" alt="screenshot" src="https://user-images.githubusercontent.com/34976416/72512044-e2372e00-388e-11ea-9cfc-4b2eafe08adf.png">

## Tips
### 起動時にOOMEを発生させる
1. <code>WEB-INF/web.xml</code>を編集し、<code>GENERATE_OOME_ON_BOOT</code>に<code>TRUE</code>を設定する。
```xml
<context-param>
  <param-name>GENERATE_OOME_ON_BOOT</param-name>
  <param-value>TRUE</param-value>
</context-param>
```
2. WARを再作成する。

### OOME発生のウェイト時間を変更する
1. <code>WEB-INF/web.xml</code>を編集し、<code>GENERATE_OOME_INTERVAL_MILLISEC</code>の値を変更する。
```xml
<context-param>
  <param-name>GENERATE_OOME_INTERVAL_MILLISEC</param-name>
  <param-value>5000</param-value>
</context-param>
```
2. WARを再作成する。
