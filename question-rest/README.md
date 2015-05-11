JAXRS using Swagger, Spring Framework and REST Jersey 2.0 example
=================
This project is a POC for JAXRS using Swagger, Spring Framework and REST Jersey 2.0.

Notes
-------------

* Before importing the project in Eclipse. Initially issue the command mvn -f pom.xml eclipse:clean eclipse:eclipse
* Then import the project in Eclipse using import Existing maven projects. Then locate the folder where you have cloned the jaxrs-poc.


Commit Changes
--------------

* https://linuxprograms.wordpress.com/2012/01/30/git-how-to-recursively-add-the-files-in-a-directory/

git add *
git commit -m "init"
git push

* http://rogerdudler.github.io/git-guide/

```

git add <filename>
git add *
git commit -m "Commit message"
git push origin master

Change master to whatever branch you want to push your changes to.

If you have not cloned an existing repository and want to connect your repository to a remote server, you need to add it with
git remote add origin <server>

to update your local repository to the newest commit, execute 
git pull


update & merge

to update your local repository to the newest commit, execute 

git pull

in your working directory to fetch and merge remote changes.

to merge another branch into your active branch (e.g. master), use

git merge <branch>

in both cases git tries to auto-merge changes. Unfortunately, this is not always possible and results in conflicts. You are responsible to merge those conflicts manually by editing the files shown by git. After changing, you need to mark them as merged with

git add <filename>

before merging changes, you can also preview them by using

git diff <source_branch> <target_branch>

```

Notes
========

* https://portal-librequestion.rhcloud.com/question-factory-rest/apidocs/
* https://portal-librequestion.rhcloud.com/question-factory-rest/rest/api-docs/questions
* https://portal-librequestion.rhcloud.com/question-factory-rest/rest/application.wadl
* https://portal-librequestion.rhcloud.com/question-factory-rest/questions/getFirstQuestion
* 

