# OOMEジェネレータ

![Build](https://github.com/syake-salmon/oome-generator/workflows/Build/badge.svg) | [![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=syake-salmon_oome-generator&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=syake-salmon_oome-generator) | [![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=syake-salmon_oome-generator&metric=security_rating)](https://sonarcloud.io/dashboard?id=syake-salmon_oome-generator) | [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=syake-salmon_oome-generator&metric=coverage)](https://sonarcloud.io/dashboard?id=syake-salmon_oome-generator)

*Javaヒープ領域不足によるjava.lang.OutOfMemoryErrorの発生をシミュレートするためのジェネレータアプリです。*

for [English](README.md)

## ビルド方法
Apache-Mavenで下記コマンドを実行してください。

<pre><code>mvn clean package</code></pre>

<code>target</code>フォルダの下にWARが作成されます。

mavenをインストールしていない場合は、[こちら](https://github.com/syake-salmon/oome-generator/releases)からWARをダウンロードできます。

## 使用方法
1. JavaEEサーバにWARをデプロイする。(例, JBoss EAP)
2. アプリにアクセスする。(例, http&#58;//localhost:8080/oome-generator)
3. ボタンをクリックする。  
<img width="300" alt="screenshot" src="https://user-images.githubusercontent.com/34976416/76815133-1f448000-6840-11ea-8a36-6c357437da36.PNG">

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
