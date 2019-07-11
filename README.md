# Cash Register
Mimics a cash register in a normal store. The goal here is to convert the Cash Register application you've created that prompts a user for some input, and turn it into a web application that exposes functionality that is capable of handling fetching data as well as manipulating and mutating data.

## Pull down this repository with Git
You may or may not have `Git` installed on your machine. Run `make tools` to see if you do.
And if you do, then
```
git clone https://github.com/dSakho/CashRegister.git
```

### Drop Wizard
This applications main dependency in the `pom.xml` file is the `dropwizard-core` dependency. 
[DropWizard](https://www.dropwizard.io/1.3.12/docs/index.html) is a set of Java libraries that make it easy to build and develop RESTful web services. RESTful web services allow functionality of programs to be exposed over the web through HTTP.
Please check these vides for more on RESTful web services:
* [What is an API?](https://www.youtube.com/watch?v=s7wmiS2mSXY](https://www.youtube.com/watch?v=s7wmiS2mSXY)
* [REST API & Restful Web Services Explained](https://www.youtube.com/watch?v=LooL6_chvN4)
* [REST API Concepts & Examples](https://www.youtube.com/watch?v=7YcW25PHnAA)

Also some articles:
* 
* 
### Make File 
Makefiles are a great place to start when you enter a brand new repo. All the words that precede a `:` are known as _targets_.  Make targets are commands that you can run in a shell that help ease complex commands required for local development. [Here]([https://blog.jayway.com/2017/03/12/short-introduction-makefiles/](https://blog.jayway.com/2017/03/12/short-introduction-makefiles/)) is a quick intro to make files. Please View the Makefile in this repository to view what things you will need to run this app through the terminal.
  

### File System Layout
Noticed how much your project has grown since you gave it to me. It now uses Apache Maven as a build tool to not only manage dependencies but to also assist in running your project with plugins and automation. Every Maven project has a top-level `pom.xml` file which lists the dependencies that are needed to run the application and bundles everything for you. 
* [Maven in 5 minutes](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)
* [Maven Project Layout](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html)
* [Tips & Tricks](https://dzone.com/articles/10-effective-tips-on-using-maven)

 ### Local Development
 Requirements for building this project on a terminal:
* Java (Minimum Version Java 1.8)
* Maven (Minimum Version Apache Maven 3.X.X)
* Run `make tools` to see if you are missing any tools required for running the applications

Requirements for running this project are:
* MySQL running on it's default port 3306


## But What About Eclipse...
Our goal is to get this application deployed to a service in the cloud that will have it run it for us. For that to happen, you need to get comfortable with running applications from a terminal / CLI tool. However, you will still be editing your Java source files and configurations in an IDE. I already saw that you're utilizing Eclipse which is very popular and widely used today in the Java ecosystem. Here are some Eclipse IDE [Hot Keys](https://dzone.com/articles/top-30-eclipse-keyboard-shortcuts-for-java-program-1**) that we both should know :)

### BTW, this whole README is written in MARKDOWN. For a nice overview of Markdown. Please Checkout the Markdown Cheat Sheet [Here](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet)