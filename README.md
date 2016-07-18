# question-application
question-application

=========
# Swagger

* https://portal-librequestion.rhcloud.com/question-rest/apidocs/
* http://ec2-54-175-28-226.compute-1.amazonaws.com/question-rest/apidocs/

# Demo

* https://qa1-mindelements.rhcloud.com/question-web/
* http://ec2-54-175-28-226.compute-1.amazonaws.com/question-web/

=========
# Docs

* https://portal-librequestion.rhcloud.com/question-web/rest/questions/getFirstQuestion/1
```
{"question":"1Halloween?","selection":{"d":"Dec 25","b":"Feb 4","c":"Jan 1","a":"Nov 1"},"questionNumber":7,"sessionId":"e461e9c0-a93a-4265-a825-28fb3a788e5a","answer":null,"explanation":null,"questionBucketDetails":{"questionNumber":7,"totalQuestionRunningValue":0,"totalQuestion":10,"numberOfSetsDone":1,"questionSetRunningValue":0,"questionSetTotalValue":8},"status":"QUESTION_AVAILABLE"}
```

* https://portal-librequestion.rhcloud.com/question-web/rest/questions/getNextQuestion/1/e461e9c0-a93a-4265-a825-28fb3a788e5a
```
{"question":"","selection":{},"questionNumber":9,"sessionId":"e461e9c0-a93a-4265-a825-28fb3a788e5a","answer":null,"explanation":null,"questionBucketDetails":{"questionNumber":9,"totalQuestionRunningValue":0,"totalQuestion":10,"numberOfSetsDone":1,"questionSetRunningValue":7,"questionSetTotalValue":8},"status":"QUESTION_SET_TOTAL_REACHED"}
```

* https://portal-librequestion.rhcloud.com/question-web/rest/questions/checkAnswer/a/1/e461e9c0-a93a-4265-a825-28fb3a788e5a/1

```
{"answer":"Answer is not correct"}
```


* https://portal-librequestion.rhcloud.com/question-web/rest/questions/getWrongAnswer/1/e461e9c0-a93a-4265-a825-28fb3a788e5a

```
{"question":"","selection":{},"questionNumber":1,"sessionId":"e461e9c0-a93a-4265-a825-28fb3a788e5a","answer":"NA","explanation":"NA","questionBucketDetails":{"questionNumber":1,"totalQuestionRunningValue":0,"totalQuestion":10,"numberOfSetsDone":1,"questionSetRunningValue":0,"questionSetTotalValue":8},"status":"STATUS_NULL_QUESTIONS_NOT_ANSWERED"}
```

* https://portal-librequestion.rhcloud.com/question-rest/apidocs/
 
